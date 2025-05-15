#Day2
🚀 Understanding Idempotency: A Key Concept in API Design 🚀

What is Idempotency?
Idempotency ensures that an operation can be safely repeated without changing the result beyond the initial application.

🔑 Why use Idempotency?
It helps prevent unintended side effects and ensures reliability in distributed systems, especially when requests are retried, multiple calls forthe same request

⚙️ When to use it?
Use idempotency in scenarios like payment processing, retries, and requests that could be accidentally triggered multiple times.

🔧 How to use it?
Implement unique IDs for requests (e.g., a payment ID) and ensure the server handles repeated requests with the same ID by returning the same result.

POST: Typically non-idempotent, but you can make it idempotent by using unique identifiers for each request (e.g., a payment ID).

PUT: Idempotent by design. Repeating a PUT request with the same data will always yield the same result. returning the same result.
