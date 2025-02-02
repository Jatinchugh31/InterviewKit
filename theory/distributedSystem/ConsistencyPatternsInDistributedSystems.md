Consistency patterns in distributed systems are strategies used to maintain data consistency across multiple nodes in a
distributed environment. Given the trade-offs between consistency, availability, and partition tolerance (as described
in the CAP theorem), different systems adopt different consistency models based on their requirements. Below are some
key consistency patterns:

1. Strong Consistency
   Ensures that all nodes always have the most recent write before serving a read.
   Guarantees that any read operation always returns the latest written value.
   Typically implemented using protocols like Paxos or Raft.
   Used in systems where correctness is critical (e.g., banking transactions).
   Example: Google Spanner, Zookeeper.


2. Eventual Consistency
   Guarantees that if no new updates are made, all replicas will eventually converge to the same state.
   Commonly used in large-scale distributed systems where availability is prioritized over immediate consistency.
   Implemented in systems like DynamoDB, Cassandra, and Riak.
   Variants:
   Causal Consistency: Ensures operations that are causally related appear in order.
   Read-Your-Write Consistency: Guarantees that a user always sees their own updates.
   Monotonic Reads: Ensures that once a user reads a value, they never see an older version.
   Session Consistency: Maintains consistency within a user’s session.

3. Linearizability
   A stricter form of consistency that ensures operations appear instantaneously across all nodes.
   Provides the illusion of a single global order of operations.
   Used in distributed databases that require strict correctness guarantees.
   Achieved using distributed consensus protocols like Paxos and Raft.
   Example: Google’s Spanner, CockroachDB.


4. Quorum-Based Consistency
   Uses quorum reads and writes (e.g., requiring acknowledgment from a majority of nodes) to balance consistency and
   availability.
   The system ensures a certain number of nodes acknowledge a write before confirming it.
   Commonly seen in Dynamo-style databases.
   Example: Apache Cassandra’s tunable consistency model.
5. CRDTs (Conflict-free Replicated Data Types)
   Data structures designed for eventual consistency while avoiding conflicts.
   Useful for collaborative applications (e.g., real-time document editing).
   Example: Google Docs, Riak, and CRDT-based databases.

6. Multi-Version Concurrency Control (MVCC)
   Allows multiple versions of a value to exist simultaneously and resolves conflicts based on timestamps or vector
   clocks.
   Frequently used in databases requiring snapshot isolation.
   Example: PostgreSQL, Spanner, BigTable.

7. Consistent Hashing
   Ensures balanced data distribution while handling node failures efficiently.
   Often used in distributed caching and databases like Amazon DynamoDB and Apache Cassandra.
   Trade-Off Considerations
   Consistency vs. Availability: Choosing between strong consistency (CP systems) and high availability (AP systems).
   Latency vs. Accuracy: Systems like Spanner use TrueTime for global consistency but with slight delays.
   Network Partition Handling: Eventual consistency systems handle network partitions better than strong consistency
   systems.