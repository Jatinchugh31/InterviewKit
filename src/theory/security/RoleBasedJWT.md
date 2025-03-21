Here is an example of implementing **role-based authentication** using **JWT** in a **Spring Boot** application for
specific controllers. This implementation includes the creation of JWT tokens, securing endpoints based on roles, and
integrating with Spring Security.

---

### 1. **Add Dependencies**

In your `pom.xml`, add the following dependencies for **Spring Boot**, **Spring Security**, and **JWT** (we will use
`jjwt` library for JWT creation and parsing):

```xml

<dependencies>
    <!-- Spring Boot Web and Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- JWT dependency -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.11.5</version>
    </dependency>

    <!-- Spring Boot DevTools (optional) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

---

### 2. **JWT Utility Class**

Create a utility class to generate and validate JWTs. This will include methods to create JWTs with roles and validate
tokens.

```java
package com.example.jwtsecurity.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import java.util.Date;

public class JwtUtil {

    private final String secretKey = "mySecretKey";  // Use a strong secret in real applications

    // Generate JWT token with user roles
    public String generateToken(String username, String roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiry time 10 hours
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract roles from JWT token
    public String extractRoles(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("roles");
    }

    // Extract username from JWT token
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
```

---

### 3. **JWT Filter**

Create a filter that intercepts incoming requests to check for JWT tokens in the `Authorization` header. It will extract
the roles from the token and set the authentication in the context.

```java
package com.example.jwtsecurity.filter;

import com.example.jwtsecurity.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Override
    protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Remove the "Bearer " prefix
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                String roles = jwtUtil.extractRoles(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, getAuthoritiesFromRoles(roles));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

    // Convert roles to Spring authorities
    private Collection<? extends GrantedAuthority> getAuthoritiesFromRoles(String roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String[] rolesArray = roles.split(",");
        for (String role : rolesArray) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }
}
```

---

### 4. **Spring Security Configuration**

Configure Spring Security to use the `JwtRequestFilter` to authenticate requests with JWT. We’ll also configure
role-based access control for different endpoints.

```java
package com.example.jwtsecurity.config;

import com.example.jwtsecurity.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/public/**").permitAll() // No authentication needed
                .antMatchers("/admin/**").hasRole("ADMIN")  // Only accessible to ADMIN role
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")  // Accessible to USER or ADMIN
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        // Example in-memory users
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("{noop}password").roles("ADMIN").build());
        manager.createUser(User.withUsername("user").password("{noop}password").roles("USER").build());
        return manager;
    }
}
```

---

### 5. **Controller Example with Role-Based Authorization**

Now, define your controllers and secure them with role-based access.

```java
package com.example.jwtsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Public Endpoint!";
    }

    @GetMapping("/user/hello")
    public String userEndpoint() {
        return "Hello User!";
    }

    @GetMapping("/admin/hello")
    public String adminEndpoint() {
        return "Hello Admin!";
    }
}
```

---

### 6. **Application Properties**

Add any necessary configurations to your `application.properties` (e.g., to disable CSRF or set any other Spring
properties).

```properties
server.port=8080
```

---

### 7. **Testing the JWT Role-Based Authentication**

1. **Login (JWT Generation)**:
    - Send a **POST request** to your `/login` endpoint (you will need to create this endpoint) with username and
      password. If the user credentials are valid, return a JWT containing the user's roles.

2. **Accessing Protected Endpoints**:
    - For accessing the `/user/hello` or `/admin/hello` endpoints, send a **GET request** with the JWT token in the *
      *Authorization** header:
      ```bash
      Authorization: Bearer <Your-JWT-Token>
      ```

3. **Access Control**:
    - If a user with a **USER role** accesses `/admin/hello`, they will get a **403 Forbidden** response.
    - If a **USER** or **ADMIN** accesses `/user/hello`, they will be allowed access.

---

### Conclusion

In this example:

- **JWT Authentication** is implemented with roles (`USER` and `ADMIN`) encoded in the token.
- **Role-based Access Control** (RBAC) is enforced on specific endpoints (`/user/**` and `/admin/**`) using Spring
  Security.
- **JWT filter** is used to intercept requests and authenticate users based on the token's validity and roles.

This setup allows you to secure your Spring Boot application with JWT and role-based authentication.