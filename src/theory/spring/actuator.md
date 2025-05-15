### **What is Spring Boot Actuator?**

Spring Boot Actuator is a set of production-ready features that you can add to your Spring Boot application to monitor
and manage it. It provides built-in endpoints that allow you to get insights into the health, metrics, application
environment, and other vital aspects of your Spring Boot application.

These endpoints can be used to:

- **Monitor the health of your application**.
- **Collect metrics** about your application.
- **Track application configurations**.
- **Debug the application by exposing relevant information**.

Some of the common use cases for Spring Boot Actuator include:

- Health monitoring (e.g., database connectivity, disk space).
- Exposing application metrics (e.g., memory usage, request counts).
- Managing application settings and configurations.

### **Key Features of Spring Boot Actuator:**

1. **Health Endpoints**: To monitor the application's health (e.g., whether the app is running correctly, if the
   database is reachable, etc.).
2. **Metrics**: Provides useful metrics about the app (e.g., memory usage, database query counts).
3. **Logging**: Allows you to manage and view logging levels at runtime.
4. **Environment**: Exposes environment properties, system properties, and configuration details.
5. **Thread Dumps**: Provides a snapshot of the thread state to help identify performance bottlenecks.

### **Commonly Available Actuator Endpoints**:

- **`/actuator/health`**: Provides basic health check information (e.g., whether the app is healthy or not).
- **`/actuator/metrics`**: Provides various metrics like JVM memory usage, number of active threads, etc.
- **`/actuator/env`**: Exposes properties of the application context, including system properties, environment
  variables, and configuration properties.
- **`/actuator/info`**: Provides application-related information (e.g., build version, custom info).
- **`/actuator/loggers`**: Allows you to view and change logging levels in the application.
- **`/actuator/auditevents`**: Provides audit event information (e.g., login events).
- **`/actuator/threaddump`**: Dumps the thread state, useful for diagnosing performance issues.

### **How to Add Spring Boot Actuator to Your Project:**

#### **1. Add Spring Boot Actuator Dependency:**

To add Spring Boot Actuator to your project, include the following dependency in your `pom.xml` (for Maven users) or
`build.gradle` (for Gradle users):


**For Gradle**:

```gradle
implementation 'org.springframework.boot:spring-boot-starter-actuator'
```

#### **2. Enable Endpoints (Optional)**:

By default, only a few endpoints (like `/actuator/health`) are enabled. To enable more endpoints, you can configure them
in your `application.properties` or `application.yml`.

**Example (`application.properties`)**:

```properties
management.endpoints.web.exposure.include=health,info,metrics,env
```

This will expose the `/actuator/health`, `/actuator/info`, `/actuator/metrics`, and `/actuator/env` endpoints.

You can also use `management.endpoints.web.exposure.exclude` to exclude specific endpoints if you don't want them
exposed.

#### **3. Security Configuration (Optional)**:

Since Actuator exposes potentially sensitive information (e.g., database status, environment properties), it's important
to secure these endpoints.

To enable basic security for your actuator endpoints, you can configure HTTP security to restrict access. For example,
in your `application.properties`:

```properties
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always
spring.security.user.name=admin
spring.security.user.password=admin123
```

This configuration exposes only `health` and `info` endpoints and requires authentication for access.

#### **4. Run Your Application**:

Once you've added the dependencies and configuration, you can start your Spring Boot application. The actuator endpoints
will be available.

For example, to check the health of the application:

```
http://localhost:8080/actuator/health
```

To view application metrics:

```
http://localhost:8080/actuator/metrics
```

### **Customizing Actuator Endpoints:**

- **Custom Health Indicators**:
  You can create custom health indicators to monitor specific resources like databases, queues, etc. Here's how you can
  implement a custom health check.

```java
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // Perform your custom health check logic here
        if (/* some condition */) {
            return Health.up().withDetail("Custom", "Service is up").build();
        } else {
            return Health.down().withDetail("Custom", "Service is down").build();
        }
    }
}
```

- **Custom Metrics**:
  You can expose your custom metrics using `MeterRegistry`:

```java
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    private final MeterRegistry meterRegistry;

    @Autowired
    public CustomMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void recordCustomMetric() {
        meterRegistry.gauge("custom.metric", 100); // Create a custom metric
    }
}
```

### **Important Configuration Options**:

1. **Expose Specific Endpoints**:
   By default, only a few actuator endpoints are enabled. You can configure which endpoints should be exposed:
   ```properties
   management.endpoints.web.exposure.include=health,metrics,info
   ```

2. **Customize Health Check Details**:
   You can expose detailed health information (like database or disk space) using:
   ```properties
   management.endpoint.health.show-details=always
   ```

3. **Secure Actuator Endpoints**:
   You can secure the actuator endpoints using Spring Security:
   ```properties
   management.endpoints.web.exposure.include=health,info
   spring.security.user.name=admin
   spring.security.user.password=adminpassword
   ```

### **Example of Common Actuator Endpoints**:

- **Health Check**:
    - **GET** `/actuator/health`: Returns the health status of the application.
    - You can customize the health check logic by adding custom health indicators.

- **Metrics**:
    - **GET** `/actuator/metrics`: Returns various metrics like JVM memory, garbage collection, HTTP request counts,
      etc.
    - Example response:
      ```json
      {
        "names": ["jvm.memory.used", "jvm.threads.live"]
      }
      ```

- **Info**:
    - **GET** `/actuator/info`: Provides application-related information (can be configured to show build info, version,
      etc.).

- **Thread Dump**:
    - **GET** `/actuator/threaddump`: Provides information about the current state of threads in the application.

### **Conclusion**:

- **Spring Boot Actuator** is a powerful tool for monitoring and managing your Spring Boot applications.
- By default, it provides a variety of endpoints such as health, metrics, and environment, and allows you to add custom
  functionality, such as health checks and metrics.
- You can enable or disable specific actuator endpoints, and it can be secured using Spring Security to ensure that
  sensitive data is not exposed publicly.
