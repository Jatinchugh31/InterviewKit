1. Add Roles (Authorities) to the JWT
   1.1 Modify JwtUtil to Accept and Include Roles
   Suppose you have a list of roles/authorities (e.g., List<String> roles). Update your generateToken(...) method to add those roles as a claim in the JWT payload.

java
Copy
Edit
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.List;

public class JwtUtil {

    // Replace with your secure, properly stored secret key.
    private static final String SECRET_KEY = "your-secret-key-of-sufficient-length"; 
    private static final long EXPIRATION_TIME = 3600_000; // 1 hour

    public String generateToken(String username, List<String> roles) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)  // Include the roles claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateTokenAndGetUsername(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid token: " + e.getMessage());
        }
    }

    public List<String> getRolesFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            // In this example, we stored roles as a List<String>
            return (List<String>) claims.get("roles");
        } catch (JwtException e) {
            throw new RuntimeException("Invalid token: " + e.getMessage());
        }
    }
}
2. Create a Custom Filter for JWT Extraction and Role Setup
   2.1 Extract Roles and Build Authorities
   When we parse the JWT, we need to create a UsernamePasswordAuthenticationToken that includes the user’s authorities/roles in Spring Security’s format.

java
Copy
Edit
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws IOException, javax.servlet.ServletException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                // Validate token and extract username
                String username = jwtUtil.validateTokenAndGetUsername(token);
                // Get roles from token
                List<String> roles = jwtUtil.getRolesFromToken(token);

                // Convert roles (String) into a list of GrantedAuthority
                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                // Create authentication token
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                
                // Set authentication in security context
                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
3. Spring Security Configuration for Role-Based Access
   With Spring Security (Spring Security 6+), create a SecurityFilterChain bean to define your authorization rules. For example, only users with the role ADMIN can access /admin/**, while any authenticated user can access /user/**.

java
Copy
Edit
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                // Public endpoints
                auth.requestMatchers("/api/auth/**").permitAll();

                // Role-based access:
                auth.requestMatchers("/admin/**").hasAuthority("ADMIN"); 
                auth.requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN");

                // Everything else requires authentication
                auth.anyRequest().authenticated();
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Add JWT filter before UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtAuthenticationFilter, 
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
hasAuthority("ADMIN") checks if the user’s GrantedAuthority list contains ADMIN.
hasAnyAuthority("USER", "ADMIN") checks if the list contains either USER or ADMIN.
4. Authentication Endpoint & Role Handling
   4.1 Example: Login Controller
   The controller below simulates a login endpoint that checks credentials (hardcoded for demonstration) and then includes the user’s roles in the generated JWT.

java
Copy
Edit
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Validate user credentials against DB or user service (mocked here)
        if ("admin".equals(request.getUsername()) && "adminpass".equals(request.getPassword())) {
            // Roles for admin
            List<String> roles = Arrays.asList("ADMIN", "USER");
            String token = jwtUtil.generateToken(request.getUsername(), roles);
            return ResponseEntity.ok(new AuthResponse(token));
        } else if ("user".equals(request.getUsername()) && "userpass".equals(request.getPassword())) {
            // Roles for regular user
            List<String> roles = Arrays.asList("USER");
            String token = jwtUtil.generateToken(request.getUsername(), roles);
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    // Simple check endpoint that doesn't require any specific role
    @GetMapping("/check")
    public String check() {
        return "Public endpoint: No auth needed.";
    }

    // DTOs for demonstration
    static class LoginRequest {
        private String username;
        private String password;
        // getters and setters
    }
    
    static class AuthResponse {
        private String token;
        public AuthResponse(String token) {
            this.token = token;
        }
        public String getToken() {
            return token;
        }
    }
}
4.2 Create Role-Protected Controllers
For example:

java
Copy
Edit
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin/secret")
    public String adminOnlyEndpoint() {
        return "This is only accessible to ADMIN role";
    }
}

@RestController
class UserController {

    @GetMapping("/user/profile")
    public String userOrAdminEndpoint() {
        return "This is accessible to USER or ADMIN roles";
    }
}
/admin/secret requires ADMIN authority.
/user/profile can be accessed by USER or ADMIN.
5. Testing the Role-Based Flow
   Login as Admin

Send a POST request to /api/auth/login with body:
json
Copy
Edit
{
"username": "admin",
"password": "adminpass"
}
Response will contain a JWT with roles: ["ADMIN","USER"].
Access Admin Endpoint

Use the returned token in the Authorization header:
makefile
Copy
Edit
Authorization: Bearer <JWT_STRING>
Send a GET request to /admin/secret.
If the token is valid and has ADMIN in roles, you get a successful response.
Login as User

Similar process with "username": "user", "password": "userpass".
Token will have roles: ["USER"].
You can access /user/profile but not /admin/secret.
6. Best Practices Recap
   Use HTTPS/TLS: Always serve JWT over HTTPS to prevent token interception.
   Store Secrets Securely: Keep your SECRET_KEY out of source code. Use environment variables, vaults, or encrypted configs.
   Token Expiration and Refresh: Short-lived tokens for security, plus a refresh token mechanism to re-issue access tokens.
   Roles vs. Scopes: Roles are broader privileges; scopes or authorities can be more granular. Use them consistently.
   Token Revocation: Keep tokens short-lived or implement a token blacklist if you need immediate revocation.
   Final Notes
   This role-based approach leverages Spring Security filters to parse roles from the JWT, then uses built-in hasAuthority, hasAnyAuthority, or method-level security annotations (@PreAuthorize("hasRole('ADMIN')")) to enforce access rules.

In an interview context, emphasize how this design:

Minimizes server state (stateless).
Allows easy scaling.
Keeps clear boundaries for different roles and their permissions.







