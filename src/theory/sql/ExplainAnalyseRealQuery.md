Awesome! Let’s walk through a **real query**, generate its **EXPLAIN ANALYZE** output, and break it down in detail. I’ll use a simplified `users` table as an example.

---

## ✅ Table Schema: `users`

```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    age INT,
    created_at TIMESTAMP
);
```

Let’s assume this table has **500,000 rows**, and there is **no index** on the `email` column.

---

## 🎯 Query:

```sql
EXPLAIN ANALYZE SELECT * FROM users WHERE email = 'abc@example.com';
```

---

## 🔎 Output (No Index Present):

```
Seq Scan on users  (cost=0.00..10458.00 rows=1 width=100)
  Filter: (email = 'abc@example.com')
  Rows Removed by Filter: 499999
Planning Time: 0.112 ms
Execution Time: 89.574 ms
```

---

## ✅ Breakdown

### 📌 Line 1: `Seq Scan on users`

* PostgreSQL is doing a **sequential scan** of all 500,000 rows.
* This happens because **no index** is available for `email`.

### 📌 Line 2: `(cost=0.00..10458.00 rows=1 width=100)`

* These are **planner estimates**:

    * `0.00` = start-up cost
    * `10458.00` = total estimated cost to scan the table
    * `rows=1` = expected to find 1 match
    * `width=100` = average row size in bytes

### 📌 Line 3: `Filter: (email = 'abc@example.com')`

* Condition applied to each row during scan.

### 📌 Line 4: `Rows Removed by Filter: 499999`

* PostgreSQL read 500,000 rows.
* **Only 1 row matched**, and the rest were filtered out — very inefficient!

### 📌 Line 5: `Planning Time: 0.112 ms`

* Time PostgreSQL took to plan the query.

### 📌 Line 6: `Execution Time: 89.574 ms`

* Total time to **scan, filter, and return** the result.

---

## 🚀 Optimization: Add Index

```sql
CREATE INDEX idx_users_email ON users(email);
```

Now re-run:

```sql
EXPLAIN ANALYZE SELECT * FROM users WHERE email = 'abc@example.com';
```

---

## ✅ Optimized Output:

```
Index Scan using idx_users_email on users  (cost=0.42..8.44 rows=1 width=100)
  Index Cond: (email = 'abc@example.com')
  Planning Time: 0.137 ms
  Execution Time: 0.095 ms
```

---

### 🧠 Why This is Better:

| Before                | After                  |
| --------------------- | ---------------------- |
| Seq Scan (500k rows)  | Index Scan (1 match)   |
| 89 ms                 | 0.09 ms                |
| 499,999 filtered rows | 0 filtered             |
| No index used         | Index used efficiently |

---

Would you like:

* A follow-up query using **JOINs** and **EXPLAIN plan for that**?
* Or an example using **aggregations or sorting** with plan interpretation?
