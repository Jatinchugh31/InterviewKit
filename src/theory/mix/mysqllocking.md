🔐 **Locking in Databases: Understanding Different Types & How to Implement Them** 🔐

In the world of databases, locking is crucial to ensure **data integrity** when multiple transactions are accessing the
same data at once. Let’s break down the **4 key types of locks** you need to know, and **how to implement them**:

### 1. **Optimistic Locking** 💡

* **What It Is**: Assume no conflicts will happen. You read the data and proceed with changes. Before committing, you *
  *check if another transaction modified the data**. If it did, you can reject your update and handle it accordingly.
* **How to Implement**: Typically done using a **version number** or **timestamp**. When you read the data, you also
  read the version. When updating, you check if the version matches what you originally read. If the version has
  changed, the update is rejected.

    * Example (using version number):

      ```sql
      SELECT balance, version FROM accounts WHERE account_id = 101;
      -- Transaction modifies the balance
      UPDATE accounts SET balance = balance - 100, version = version + 1
      WHERE account_id = 101 AND version = 1;
      ```
* **Use Case**: Great when **conflicts are rare**, like in **e-commerce** where inventory is frequently updated.

### 2. **Pessimistic Locking** 🔒

* **What It Is**: Assume **conflicts will happen**, so you lock the data immediately to prevent others from modifying it
  until you're done.
* **How to Implement**: Often done with `SELECT ... FOR UPDATE`, which locks the data when reading it, ensuring no one
  else can modify it until the transaction is committed or rolled back.

    * Example:

      ```sql
      SELECT * FROM accounts WHERE account_id = 101 FOR UPDATE;
      -- Transaction modifies the balance
      UPDATE accounts SET balance = balance - 100 WHERE account_id = 101;
      COMMIT;
      ```
* **Use Case**: Ideal for **highly concurrent** systems, such as **banking** or **inventory management**, where you need
  to ensure **data consistency**.

### 3. **Shared Lock** 🔄

* **What It Is**: Multiple transactions can **read** the data simultaneously, but **none can modify** it until the lock
  is released.
* **How to Implement**: Implemented with a **shared lock** that allows **read-only** access to the data by multiple
  transactions, but **no updates** are allowed until the lock is released.

    * Example:

      ```sql
      SELECT * FROM products WHERE product_id = 101 LOCK IN SHARE MODE;
      ```
* **Use Case**: Perfect for **read-heavy systems** where many users need to **view data** but don’t need to change it at
  the same time, such as **reporting** or **analytics**.

### 4. **Exclusive Lock** 🚫

* **What It Is**: **Only one transaction** can **read or write** the data. It locks the resource **exclusively** for
  updates or modifications, blocking all other transactions.
* **How to Implement**: Often implemented using the `FOR UPDATE` clause, ensuring that the row is locked for exclusive
  access.

    * Example:

      ```sql
      SELECT * FROM accounts WHERE account_id = 101 FOR UPDATE;
      -- Transaction modifies the balance
      UPDATE accounts SET balance = balance - 100 WHERE account_id = 101;
      COMMIT;
      ```
* **Use Case**: Critical for **updates or deletions**, like **fund transfers** in a **banking system**, ensuring **no
  conflicts** during sensitive operations.

### **Key Takeaway**:

* Use **Optimistic Locking** when you expect **few conflicts** (implement with versioning or timestamps).
* Use **Pessimistic Locking** when **data integrity** is a top priority (implement with `FOR UPDATE`).
* Use **Shared Locks** for concurrent **read-only** access (implement with `LOCK IN SHARE MODE`).
* Use **Exclusive Locks** when you need to **exclusively modify** data (implement with `FOR UPDATE`).

Understanding these locks and **how to implement** them can **optimize performance** and **maintain data consistency**
in your systems! 🚀

\#DatabaseDesign #TechTips #SoftwareEngineering #Concurrency #DataIntegrity #SystemDesign #OptimisticLocking
#PessimisticLocking #SharedLock #ExclusiveLock #Coding

---

This post now includes **implementation details** for each lock type, making it not only educational but also practical
for anyone looking to apply these concepts in their systems. The examples provide a clear idea of how each locking
strategy is implemented in SQL.
