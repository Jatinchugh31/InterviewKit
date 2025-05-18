In Apache Kafka, the **offset** represents the position of a consumer in a topic partition, i.e., which message the
consumer has consumed. The offset is crucial for managing message consumption, ensuring messages are processed once and
only once, and for handling retries or reprocessing of messages if needed.

Let's break down the **offset lifecycle** and explain how it works in different scenarios.

### Kafka Offset Lifecycle

1. **Initial State**:
   When a consumer starts consuming from a Kafka topic partition, it begins from an **initial offset**. This can be
   either:

    * **Earliest**: Start from the oldest available message (if the consumer is consuming for the first time or has no
      committed offset).
    * **Latest**: Start from the most recent message (if the consumer was previously consuming the topic, and its offset
      has been committed).

2. **Consuming Messages**:

    * **Consumer Reads a Message**:
      Suppose the consumer is reading from partition `X`, and the offset is `10` (meaning the consumer is about to read
      the message at offset 10 in that partition).
    * The consumer fetches the message at offset `10` (which corresponds to message number 10).

3. **Commit the Offset**:

    * **Automatic Offset Commit (`enable.auto.commit=true`)**:

        * If the consumer is using automatic offset commit (which is the default behavior in Kafka), the offset is *
          *automatically committed** to Kafka after each successful message consumption, typically after each `poll()`
          call.
        * The offset is committed **after** the consumer successfully processes the message. This means that Kafka
          considers the message at offset `10` to be successfully consumed, and the next time the consumer fetches a
          message, it will start from offset `11`.
        * The timing of this commit depends on the `auto.commit.interval.ms` setting (default is 5000ms). So, if the
          consumer doesn't commit the offset explicitly, it will be committed automatically after the interval has
          passed since the last commit.
    * **Manual Offset Commit (`enable.auto.commit=false`)**:

        * In this case, the consumer **must commit the offset manually** after it has successfully processed a message.
        * If the consumer consumes a message at offset `10` and processes it successfully, it can choose when to commit
          the offset. The offset can be committed either **immediately after processing the message** or at some later
          point.
        * If the consumer does not commit the offset for `10`, it can re-read the message the next time it polls Kafka,
          which is useful for scenarios where the consumer has to retry processing (e.g., due to a failure in message
          processing).

4. **Offset Commit Process**:

    * Once the message at offset `10` is processed, and the consumer commits the offset (either manually or
      automatically), Kafka marks that the consumer has consumed and processed this message.

    * Kafka **stores the committed offset** in a special Kafka topic called `__consumer_offsets`. The committed offset
      is recorded for the consumer group, allowing the consumer to pick up where it left off in case it restarts or
      crashes.

    * **After committing** the offset for message `10`, the next time the consumer starts or polls, it will start from
      offset `11` and consume the subsequent messages.

### What Happens if the Consumer Crashes?

* If the consumer crashes before committing the offset for message `10`, Kafka will treat the last successfully
  committed offset as the last known good position.

    * For example, if the consumer crashes right after reading message `10` but before committing it, when the consumer
      restarts, it will pick up from the **last committed offset**, which might be `9`. This ensures that message `10`
      will be consumed again after the consumer recovers, preventing message loss but potentially causing duplicate
      consumption (this is often called "at least once" delivery).

### Example Scenario

* **Partition Offset**: 10

    * The consumer reads the message at offset `10`.
    * The consumer processes the message and then either:

        * Automatically commits the offset to `11` after processing (if `enable.auto.commit=true`).
        * Or the consumer manually commits the offset to `11` after processing (if `enable.auto.commit=false` and the
          consumer calls `commitSync()` or `commitAsync()`).

  After the offset commit, the next time the consumer starts, it will continue from **offset `11`**.

### Key Points on Offset Lifecycle:

1. **Reading Messages**: A consumer can read messages starting from a specific offset.
2. **Processing Messages**: After reading a message, the consumer processes it. The consumer doesn't need to move the
   offset at this point; it's only updated after the message is processed.
3. **Committing Offsets**:

    * **Automatic Commit**: The offset is committed automatically after the message is processed (based on the
      configured interval).
    * **Manual Commit**: The offset is committed explicitly by the consumer after the message is processed (either
      synchronously or asynchronously).
4. **Consumer Failure**: If the consumer fails before committing the offset, it will re-read messages starting from the
   last committed offset.
5. **Offset Storage**: Kafka stores committed offsets in the `__consumer_offsets` internal Kafka topic. Each consumer
   group has its own offset storage.
6. **Re-consumption and Retries**: If you want to re-consume a message, you can manually reset the offset or not commit
   it until you've successfully processed the message, allowing for retries.

### Summary

* When a consumer reads a message, it doesn't immediately move the offset to the next one. The offset is **committed**
  after the message is successfully processed.
* If the consumer has **auto commit enabled** (`enable.auto.commit=true`), the offset is committed after processing,
  which moves the offset forward.
* If **manual commit** is used, the consumer explicitly commits the offset after processing the message.
* If a consumer crashes before committing the offset, it will re-read messages from the last committed offset, ensuring
  no messages are lost, but it may cause some duplication of message processing.
