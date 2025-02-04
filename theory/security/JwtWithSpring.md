1. What is JWT?
   JSON Web Token (JWT) is an open standard (RFC 7519) for securely transmitting information between parties as a JSON
   object.
   It is self-contained, meaning it includes all the necessary information about the user (claims) and does not require
   server-side session storage.
   It typically has three parts, separated by dots (.):
   Header (algorithm & token type)
   Payload (claims)
   Signature (for verification)
   The structure looks like this:

css
Copy
Edit
header.payload.signature
Where:

Header usually looks like {"alg": "HS256","typ": "JWT"}
Payload might include claims like {"sub": "user123", "name": "John Doe", "iat": 1516239022}
Signature is created by encoding the header and payload, then signing them with a secret key (symmetric) or private
key (asymmetric).

2. Why use JWT for security?
   Stateless Authentication: The server does not need to store session information in a database or in-memory store. All
   required auth data is inside the token.
   Scalability: Stateless nature makes it easy to scale across multiple servers or microservices without worrying about
   centralized session stores.
   Decoupling: JWTs can be verified by any service that has the appropriate key, allowing microservices to authenticate
   requests without depending on a shared session state.
3. Typical Flow of JWT-based Authentication
   Login / Authenticate

The user sends credentials (username/password) to a designated login endpoint.
The server verifies credentials (e.g., checks the user in the database).
If valid, the server generates a JWT, signs it, and returns it to the client (often in the Authorization response header
or the response body).
Access Protected Resources

The client (browser or mobile app) stores the token (commonly in localStorage, sessionStorage, or a secure cookie).
With each subsequent request to protected endpoints, the client includes the token in the Authorization: Bearer <token>
header.
The server receives the token and validates it by:
Checking the signature.
Checking if the token is expired.
Optionally verifying additional claims (e.g., roles, issuer).
Token Renewal / Refresh (Optional but recommended)

A short-lived token (access token) is often paired with a longer-lived refresh token.
When the access token is about to expire, the client can request a new one using the refresh token without forcing the
user to log in again.

4. Securing a Spring Boot App with JWT
   Below is a simplified example demonstrating how JWT security can be integrated into a Spring Boot application using
   Spring Security. (This example uses Spring Security’s filter approach.)

Step 1: Add Dependencies
In your pom.xml (Maven) or build.gradle (Gradle), include:

xml
Copy
Edit
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
</dependency>
<!-- JWT library -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
Step 2: Create a JWT Utility Class
This class helps generate and validate JWT tokens.

java
Copy
Edit
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // For production, store key securely (e.g., environment variable, vault).
    private static final String SECRET_KEY = "your-secret-key-minimum-256-bit-length"; 
    private static final long EXPIRATION_TIME = 3600_000; // 1 hour

    // Generate token
    public String generateToken(String username) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate and parse token
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
            // token is invalid
            throw new RuntimeException("Invalid token");
        }
    }

}
Step 3: Create an Authentication Filter
You’ll create a custom filter that:

Reads the JWT from the Authorization header.
Validates and parses it.
Sets the authentication in the SecurityContext if valid.
java
Copy
Edit
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7);
                String username = jwtUtil.validateTokenAndGetUsername(token);
                
                // Create Authentication object and set it
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, /*roles or authorities*/ null);

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception e) {
            // In case of failure, clear the context
            SecurityContextHolder.clearContext();
        }

        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
Step 4: Configure Spring Security
In Spring Boot (Spring Security 6+), you can configure security by creating a class annotated with @Configuration that
extends WebSecurityConfigurerAdapter (for older versions) or uses the newer SecurityFilterChain bean configuration.

Example using SecurityFilterChain (Spring Security 6):

java
Copy
Edit
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
                auth.requestMatchers("/api/auth/**").permitAll() // Public endpoints (login, register, etc.)
                    .anyRequest().authenticated();
            })
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Add the JWT filter before the standard UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtAuthenticationFilter, 
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
Step 5: Expose an Authentication Endpoint
Create a REST controller to handle user login and generate a JWT.

java
Copy
Edit
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Validate user credentials (e.g., check against database).
        //    If valid, generate JWT:
        String token = jwtUtil.generateToken(request.getUsername());
        
        // Return the token to client
        return ResponseEntity.ok(new AuthResponse(token));
    }

    // A sample secured endpoint
    @GetMapping("/test")
    public String testSecuredEndpoint() {
        return "You are authenticated!";
    }

    // Inner classes for demonstration
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

5. Best Practices & Common Interview Points
   Secure Key Management

Store secret keys (for HMAC) or private keys (for RSA/ECDSA) in a secure location (vault, environment variable, etc.),
not in code.
Use HTTPS

Always send JWTs over TLS/HTTPS to prevent eavesdropping or MITM attacks.
Token Expiration

Keep access tokens short-lived (e.g., minutes to hours). For longer sessions, use refresh tokens.
Refresh Tokens

Implement a dedicated endpoint for refreshing tokens when the access token expires.
Revoke / Invalidate Tokens

Since JWTs are stateless, revoking them can be tricky. Common strategies involve:
Token blacklists (requires additional storage).
Short-lived tokens combined with frequent refreshes.
Tracking “token version” in the user entity and verifying it against the token claim.
Avoid Storing JWT in Local Storage

Storing in localStorage is vulnerable to XSS attacks. If possible, use HTTP-only cookies with proper settings (SameSite,
secure).
Claims Validation

Validate standard claims like iss (issuer), aud (audience), and exp (expiration).
Use custom claims to store additional user information (e.g., roles).
Bearer Token Format

In practice, the Authorization header is:
makefile
Copy
Edit
Authorization: Bearer <token>
Sample Interview Answers
What is JWT, and why use it?
“JWT (JSON Web Token) is a compact, URL-safe means of representing claims between two parties. We use it because it’s
self-contained (no session storage), stateless, and easy to distribute across microservices.”

How do you secure a Spring Boot application with JWT?
“I create an authentication endpoint that generates a token upon valid credentials, store the token client-side, and
then a custom filter verifies and sets authentication on subsequent requests. We configure Spring Security to be
stateless and parse the Authorization header for the JWT.”

Where do you store the secret key, and why?
“We never store it directly in code or in a public repo. We keep it in environment variables, a vault, or an encrypted
configuration. This prevents compromise if code is exposed.”

How do you invalidate a JWT?
“Because JWT is stateless, we cannot simply ‘kill’ a token on the server side. We either keep a blacklist, use
short-lived tokens, or track a token version in the database. When a user logs out or an admin revokes access, we update
the token version to prevent future tokens from being valid.”

Summary
Securing a Spring Boot application with JWT involves configuring Spring Security to rely on a stateless session policy,
implementing an auth endpoint to generate tokens, and creating a filter that validates tokens on every request. Beyond
code, always emphasize secure key handling, HTTPS enforcement, and best practices for expiration and storage.











