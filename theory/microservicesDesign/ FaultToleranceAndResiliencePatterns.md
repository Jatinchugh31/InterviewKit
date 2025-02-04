These patterns ensure the reliability of the system even when some parts fail.

Circuit Breaker Pattern:

Description: This pattern is used to detect failures and prevent the system from repeatedly trying to execute operations
that are likely to fail. If a service is failing, the circuit breaker "opens" and stops calls to the service, allowing
it time to recover.
Use Case: Essential for improving system resilience and preventing cascading failures in microservices-based systems.

Retry Pattern:
Description: This pattern involves automatically retrying failed operations a predefined number of times before giving
up. This is often combined with the circuit breaker pattern.
Use Case: Useful for operations that can fail intermittently, such as network requests or external service calls.

Timeout Pattern:
Description: This pattern helps define a maximum time for requests to complete. If the request exceeds the defined
timeout, it is aborted, and a fallback is triggered.
Use Case: Helps ensure that long-running requests don't cause delays or bottlenecks in the system.

Bulkhead Pattern:
Description: The bulkhead pattern divides the system into isolated compartments (like bulkheads in a ship). If one
compartment fails, others continue to work. It is often used to limit the impact of failure on a single service.
Use Case: Helps isolate failures to specific parts of the system, preventing them from affecting other components.