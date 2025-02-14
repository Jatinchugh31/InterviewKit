These patterns help manage data and ensure consistency across different microservices.

Database per Service Pattern:

Description: Each microservice manages its own database to maintain loose coupling between them. This ensures that
changes in one service's database do not affect others. Services communicate through APIs instead of directly accessing
each other's data.
Use Case: Ideal when each service has its own data requirements and ensures independence of services.

Shared Database Pattern:
Description: All microservices share a common database. This approach can lead to tight coupling between services, as
changes to one service's schema could potentially impact other services.
Use Case: Use this pattern when multiple services need to share data and you need strong consistency between them. It’s
often not recommended for large-scale systems due to the potential for bottlenecks.

Event Sourcing Pattern:
Description: Instead of storing the current state of an entity, this pattern stores a series of events that represent
state changes. The state can be re-built by replaying the events.
Use Case: Useful in systems where auditability, traceability, or rebuildable state is necessary, like financial systems
or systems that require history tracking.

CQRS (Command Query Responsibility Segregation) Pattern:
Description: This pattern separates the handling of data modification (commands) from data retrieval (queries). It
allows the system to scale better by optimizing read and write operations separately.
Use Case: Useful in scenarios where the read and write workloads have very different performance characteristics, and
when event-driven systems are in place.
