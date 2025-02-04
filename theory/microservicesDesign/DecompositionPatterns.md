1. Decomposition Patterns
   These patterns are used to break down the monolithic system into smaller, more manageable services.

API Gateway Pattern:

Description: An API Gateway serves as a single entry point for all client requests to microservices. It routes the
requests to the appropriate microservices, aggregates the results, and returns them to the client. It also handles
common tasks like authentication, rate limiting, and logging.
Use Case: Ideal for managing cross-cutting concerns such as authentication, logging, monitoring, or transformation of
responses.

Backend for Frontend (BFF) Pattern:
Description: A variation of the API Gateway pattern. In BFF, you create a separate backend service for each type of
client (e.g., one backend for mobile devices, one for web browsers). This helps to optimize the interaction for specific
clients and handle client-specific logic or data formatting.
Use Case: Useful for scenarios with multiple types of clients (web, mobile, etc.) needing different views of the same
data.

Decompose by Business Capability Pattern:
Description: This pattern recommends breaking the system into services based on business capabilities rather than
technical components. For example, a "Customer" service and an "Order" service would each encapsulate a business
capability and handle all related functionality.
Use Case: Best used when aligning microservices with business domains and teams.

Decompose by Subdomain Pattern:
Description: This pattern uses Domain-Driven Design (DDD) to divide the system into services based on subdomains. Each
microservice is aligned with a specific subdomain within the business.
Use Case: This is a good approach if you're implementing a DDD approach, ensuring each microservice corresponds to a
specific domain and boundary.