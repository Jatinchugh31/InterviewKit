### **What is the Circuit Breaker Pattern?**

The **Circuit Breaker** pattern is a design pattern used to handle failures in distributed systems, such as
microservices. It helps prevent an application from performing an operation that is likely to fail and allows the system
to recover gracefully.

In a distributed system, when one service calls another, the called service may fail or experience issues like network
problems, slow responses, or even crashes. When multiple such failures happen in quick succession, the system might keep
making requests to the failing service, exacerbating the issue. The **Circuit Breaker** pattern is designed to handle
such failures by stopping the application from making further requests to the service until it is healthy again.

A circuit breaker works similarly to an electrical circuit breaker: when the failure threshold is reached, the breaker "
trips," and requests are prevented from going through until the system recovers.

### **States of a Circuit Breaker**:

1. **Closed**: In this state, the circuit breaker allows all requests to pass through. If requests continue to succeed,
   the circuit remains in the closed state.
2. **Open**: When the failure threshold is exceeded (e.g., a certain number of failed requests in a time window), the
   circuit breaker moves to the open state. No requests are allowed to pass through, and errors are immediately
   returned.
3. **Half-Open**: In this state, the circuit breaker allows a limited number of requests to pass through to check if the
   service has recovered. If those requests are successful, the circuit breaker returns to the closed state. If they
   fail, it moves back to the open state.

### **Benefits of the Circuit Breaker Pattern**:

- **Prevents cascading failures**: By stopping requests to a failing service, it prevents the entire system from going
  down.
- **Provides fallback logic**: When the service is unavailable, a fallback mechanism can be triggered, allowing the
  system to continue functioning with degraded functionality.
- **Helps in system recovery**: The circuit breaker allows a service to "cool off" and recover after experiencing
  multiple failures.

---

### **How to Implement Circuit Breaker in Spring Boot**

In **Spring Boot**, the **Resilience4j** or **Hystrix** library is commonly used to implement the Circuit Breaker
pattern. The Spring Cloud ecosystem provides **Spring Cloud Circuit Breaker** for integration with both libraries.

**We will focus on implementing the Circuit Breaker pattern using Resilience4j**, which is a lightweight, fault
tolerance library.

### **Steps to Implement Circuit Breaker in Spring Boot using Resilience4j**:

#### 1. Add Dependencies

Add the following dependencies to your `pom.xml` for Resilience4j:

```xml

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot2</artifactId>
    <version>1.7.0</version>  <!-- Make sure to check for the latest version -->
</dependency>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

#### 2. Configure the Circuit Breaker in `application.yml` or `application.properties`

You need to configure the properties of the circuit breaker, such as failure rate, wait time, and success threshold, in
your `application.yml` or `application.properties` file.

**Example (`application.yml`)**:

```yaml
resilience4j.circuitbreaker:
  instances:
    myServiceCircuitBreaker:
      registerHealthIndicator: true
      failureRateThreshold: 50  # Open the circuit if 50% of requests fail
      waitDurationInOpenState: 10000ms  # 10 seconds wait before half-open state
      permittedNumberOfCallsInHalfOpenState: 3  # Test the service with 3 requests
      slidingWindowSize: 10  # Sliding window of the last 10 requests
      minimumNumberOfCalls: 5  # Minimum 5 calls to evaluate the failure rate
      eventConsumerBufferSize: 10  # Buffer size to store events
      ignoreExceptions:
        - org.springframework.web.client.HttpServerErrorException$ServiceUnavailable
```

#### 3. Implement the Circuit Breaker in Code

You can annotate the method with `@CircuitBreaker` provided by Resilience4j or use the `@Retry` or `@RateLimiter`
annotations.

**Example Service (`MyService.java`)**:

```java
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {

    private final RestTemplate restTemplate;

    public MyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "myServiceCircuitBreaker", fallbackMethod = "fallbackMethod")
    public String callExternalService() {
        // Simulating an external service call
        String response = restTemplate.getForObject("http://external-service", String.class);
        return response;
    }

    public String fallbackMethod(Throwable throwable) {
        // Fallback method in case of failure
        return "Fallback response: Service unavailable";
    }
}
```

In this example:

- The `@CircuitBreaker` annotation is used to mark the method `callExternalService()` that interacts with an external
  service.
- **`name`** refers to the circuit breaker name defined in the `application.yml` (`myServiceCircuitBreaker`).
- **`fallbackMethod`** specifies the fallback method to invoke in case the circuit breaker is open or the request fails.

#### 4. Use the Service in a Controller

**Example Controller (`MyController.java`)**:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/test")
    public String testCircuitBreaker() {
        return myService.callExternalService();
    }
}
```

In this controller, the `/test` endpoint triggers the `callExternalService()` method.

#### 5. Run and Test

Start your application, and make multiple requests to the `/test` endpoint. If the external service fails or becomes
slow, the circuit breaker will trip, and the fallback method will be invoked. Once the service becomes healthy again,
the circuit breaker will allow calls to pass through.

### **Fallback Handling**:

If the circuit breaker is open (i.e., the failure rate threshold is exceeded), the fallback method will be called
automatically.

For example:

```java
public String fallbackMethod(Throwable throwable) {
    // Fallback logic (e.g., return cached data, default response, etc.)
    return "Service is currently unavailable. Please try again later.";
}
```

---

### **Circuit Breaker Properties and Configuration**:

Here are some important properties you can configure for the Circuit Breaker:

1. **failureRateThreshold**:
    - The percentage of failures that trigger the circuit breaker to open. For example, if this is set to 50, and more
      than 50% of requests fail, the circuit breaker opens.

2. **waitDurationInOpenState**:
    - The amount of time the circuit breaker remains open before it transitions to the half-open state.

3. **slidingWindowSize**:
    - The number of requests to track to calculate the failure rate. A sliding window is used to monitor recent requests
      and detect failures.

4. **minimumNumberOfCalls**:
    - The minimum number of calls required to evaluate the failure rate. If there are fewer requests, the circuit
      breaker does not evaluate the failure rate.

5. **permittedNumberOfCallsInHalfOpenState**:
    - The number of requests allowed in the half-open state. If these requests succeed, the circuit breaker will close;
      if they fail, it will remain open.

6. **eventConsumerBufferSize**:
    - The number of events to buffer for analysis of the circuit breaker state.

7. **ignoreExceptions**:
    - You can define specific exceptions that should be ignored by the circuit breaker and not considered as failures.

---

### **Conclusion**:

- **Circuit Breaker** is an important pattern to prevent cascading failures in distributed systems.
- Spring Boot provides easy integration with **Resilience4j** to implement circuit breakers and handle failures
  gracefully.
- By using annotations like `@CircuitBreaker` and proper configuration in `application.yml`, you can manage circuit
  breaker states, configure fallback methods, and improve the resilience of your application.