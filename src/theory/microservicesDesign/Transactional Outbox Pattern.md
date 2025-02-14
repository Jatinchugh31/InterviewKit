Description: The Transactional Outbox pattern is used to reliably send events or messages from a microservice to another
service or system in a distributed environment. The key idea is to store the events/messages in a dedicated "outbox"
table within the same transactional scope as the local business transaction.
Use Case: This pattern is often used when you need to ensure that an event is sent and stored reliably after a
successful transaction without losing data, even if the application crashes or restarts.
How it works:

A microservice writes both the result of a business transaction and an event (message) to an "outbox" table in the same
transaction.
A separate process (e.g., a message sender or event publisher) reads messages from the outbox and sends them to the
appropriate systems (like a message broker).
This ensures that no message is sent if the transaction is not committed, maintaining data consistency.
Use Case: Common in event-driven systems where reliability and guaranteed delivery of events is necessary.