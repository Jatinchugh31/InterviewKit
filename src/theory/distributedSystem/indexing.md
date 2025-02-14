Indexing in Distributed Systems & RDBMS (Relational Databases)
Indexing is a key performance optimization technique used to speed up data retrieval in both distributed systems and
RDBMS (Relational Databases). However, indexing strategies vary depending on whether the system is centralized (RDBMS)
or distributed.

1. Indexing in RDBMS
   What is an Index in RDBMS?
   An index in RDBMS is a data structure (like B-Trees or Hash Tables) that improves the speed of data retrieval
   operations.
   Indexes reduce the number of rows scanned when executing queries, enhancing read performance.



Type	                      | Description	                                                    | Best Use Case
Primary Index	              | Automatically created on the Primary Key (Unique, Clustered)	| Fast lookup on primary key
Clustered Index	              | Stores data in sorted order based on key	                    |Fast range queries
Non-Clustered Index	          |A separate structure with a pointer to actual data	            |Searching on non-primary attributes
Unique Index	              |Ensures no duplicate values exist	                            |Prevents duplicate values in a column
Full-Text Index	              | optimized for text searches	Search engines                      | log analytics
Composite Index	              | An index on multiple columns	                                |Queries involving multiple conditions
Covering Index	              |Stores required columns inside index	Avoids table lookup         | (query optimization)


   Example: Creating an Index in RDBMS

   Creating an index on the "name" column for faster lookup
   CREATE INDEX idx_employee_name ON Employee(name);
   
   How Index Works in RDBMS?
   Without Index: A full table scan is performed → Slow query execution.
   With Index: The index quickly finds rows using the indexed column.
   Trade-offs of Indexing in RDBMS
   ✅ Faster Reads
   ✅ Efficient Query Execution
   ❌ Slower Writes (Indexes must be updated on INSERT/UPDATE/DELETE)
   ❌ Consumes Additional Storage
   


====================================================

Indexing in Distributed Systems

In distributed systems (e.g., NoSQL, Big Data, Elasticsearch, Cassandra), indexing must handle large-scale data across
multiple nodes efficiently.

Challenges of Indexing in Distributed Systems
Data Partitioning: Index must work across multiple nodes.
Scalability: Should support billions of records.
Consistency vs. Availability: CAP theorem trade-offs impact index updates.
Distributed Queries: Index lookup needs to minimize network latency.



Index    Type                   |description	                                        |Used In
Global Index	                |Single index maintained across all nodes (Centralized)	|Spanner, Google Bigtable
Local Index	                    |Index exists per partition (Decentralized)	            |Apache Cassandra, DynamoDB
Secondary Index	                |Allows lookups on non-primary key attributes	         |MongoDB, Elasticsearch
LSM (Log-Structured Merge) Index |	Uses an in-memory tree + persistent storage	          |Apache Cassandra, HBase
Inverted Index	                  |Optimized for full-text search (Word-to-Document mapping)	 |Elasticsearch, Solr



Indexing Strategies in Distributed Databases
1. Partitioned Indexing (Local Index)
   Each partition (node) maintains its own local index.
   Queries must check multiple partitions for full results.
   Example: Apache Cassandra


CREATE INDEX idx_employee_age ON employees (age);
🔹 Pros: Fast lookup within partition.
🔹 Cons: Slower queries across multiple partitions.

