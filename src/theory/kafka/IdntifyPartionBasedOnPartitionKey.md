In Apache Kafka, when you send a message with a key, Kafka uses the key to determine the partition to which the message
is sent. This ensures that messages with the same key are consistently routed to the same partition. Here’s a
step-by-step breakdown of how Kafka handles the partitioning process when you send a message with a key:

### Step-by-Step Process:

1. **Message Key**: When you send a Kafka message, you can optionally specify a key. This key can be any string or other
   data type. For example, this could be a user ID or a session ID.

2. **Partitioner Logic**:

    * Kafka uses a **partitioner** to determine which partition a message should go to. The default partitioner uses the
      **hash of the message key**.
    * The key (e.g., "userID123") is hashed using a hashing function (like Murmur2), which generates a numeric value.

3. **Partition Calculation**:

    * Kafka has a set number of partitions for each topic. Let's say your topic has **N partitions**.
    * The partition for the message is determined by taking the hash of the key and applying the formula:

      $$
      \text{Partition} = (\text{hash of key}) \% N
      $$
    * So, Kafka calculates the hash of the key, takes the remainder when divided by the number of partitions, and that
      determines which partition the message goes to.

4. **First Message with Key**:

    * The first time you send a message with a key, Kafka will calculate the hash of that key and map it to one of the
      topic’s partitions based on the hash value and the number of partitions in the topic.
    * Kafka ensures that any subsequent message with the **same key** will go to the **same partition** because the hash
      of the key doesn’t change unless the number of partitions changes.

5. **Subsequent Messages**:

    * The next time you send a message with the **same key**, Kafka will use the same partitioning logic.
    * The key’s hash remains the same, so it will consistently map to the same partition (as long as the number of
      partitions for the topic has not changed).

### Example:

Let’s say your Kafka topic has **3 partitions**, and you’re sending messages with keys.

* **Message 1**: Key = "userID123"

    * Kafka hashes the key "userID123" and calculates the partition as:

      $$
      \text{Partition} = (\text{hash of "userID123"}) \% 3
      $$
    * Let's assume the hash of "userID123" results in a value that maps to **partition 1**.
    * The message with key "userID123" will go to partition 1.

* **Message 2**: Key = "userID123"

    * When you send another message with the same key "userID123", Kafka will again hash the key and calculate the
      partition using the same formula.
    * This will result in **partition 1** again because the hash of "userID123" remains unchanged.

* **Message 3**: Key = "userID456"

    * Now, Kafka calculates the hash for a different key "userID456".
    * This key may map to a different partition, say **partition 0**.

### Important Points:

1. **Consistency**: Kafka guarantees that all messages with the same key will be sent to the same partition, ensuring
   message ordering within a partition.
2. **Partition Count**: The number of partitions can affect which partition a key maps to. If you change the number of
   partitions (e.g., by adding partitions to the topic), the hash calculation will change, and the key might map to a
   different partition than it did previously.
3. **Repartitioning**: Changing the number of partitions in the topic may affect the partition assignment of the keys
   because it changes the number of available partitions for the hash to be applied to.

### Summary:

* When you send a message with a key, Kafka calculates a hash of that key and maps it to a partition based on the number
  of partitions in the topic.
* Kafka ensures that messages with the same key always go to the same partition, as long as the number of partitions
  remains the same.
