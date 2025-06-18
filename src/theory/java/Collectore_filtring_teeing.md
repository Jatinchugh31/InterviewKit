Absolutely! Let's dive **deep into the `Collectors.filtering()` and `Collectors.teeing()`** features, including **how
they work, when to use them, and real-world examples**.

---

## 🔍 **1. `Collectors.filtering()` (Java 9+)**

### ✅ What It Does:

It **filters elements** before passing them to the **downstream collector** — all **inside the collector itself**.

### 🚫 Without `filtering()`:

You'd need to **filter the stream before collecting**, but that loses context (like grouping keys).

### ✅ With `filtering()`:

You can **filter while grouping**, or during other downstream operations.

---

### 🧪 Example 1: Group even and odd numbers, but only include those > 3

```java
Map<String, List<Integer>> result = List.of(1, 2, 3, 4, 5, 6).stream()
        .collect(Collectors.groupingBy(
                i -> i % 2 == 0 ? "even" : "odd",
                Collectors.filtering(i -> i > 3, Collectors.toList())
        ));
```

### 🔍 Explanation:

* Group numbers by "even"/"odd"
* But only **include values > 3** in each group

### ✅ Output:

```java
{even=[4,6],odd=[5]}
```

---

### 🧪 Example 2: Group people by gender, but only include adults

```java
record Person(String name, String gender, int age) {
}

List<Person> people = List.of(
        new Person("Alice", "F", 25),
        new Person("Bob", "M", 17),
        new Person("Clara", "F", 19),
        new Person("Dan", "M", 40)
);

Map<String, List<String>> grouped = people.stream()
        .collect(Collectors.groupingBy(
                Person::gender,
                Collectors.filtering(p -> p.age() >= 18,
                        Collectors.mapping(Person::name, Collectors.toList()))
        ));
```

### ✅ Output:

```java
{F=[Alice,Clara],M=[Dan]}
```

---

## 🔍 **2. `Collectors.teeing()` (Java 12+)**

### ✅ What It Does:

It runs **two collectors in parallel** on the same stream, and **combines** their results.

### 🔁 Structure:

```java
Collectors.teeing(
        Collector1,
        Collector2,
    (result1, result2) ->

combine(result1, result2)
)
```

---

### 🧪 Example 1: Find min and max in one pass

```java
List<Integer> nums = List.of(5, 3, 8, 1, 9);

Map.Entry<Integer, Integer> minMax = nums.stream()
        .collect(Collectors.teeing(
                Collectors.minBy(Integer::compareTo),
                Collectors.maxBy(Integer::compareTo),
                (min, max) -> Map.entry(min.orElse(null), max.orElse(null))
        ));

System.out.

println("Min: "+minMax.getKey() +", Max: "+minMax.

getValue());
```

### ✅ Output:

```java
Min:1,Max:9
```

### 🚀 Benefit:

You don’t need to traverse the stream twice (as you would with two `.collect()` calls).

---

### 🧪 Example 2: Get total price and average price of products

```java
record Product(String name, double price) {
}

List<Product> products = List.of(
        new Product("Pen", 10),
        new Product("Book", 50),
        new Product("Bag", 90)
);

record Summary(double total, double avg) {
}

Summary summary = products.stream()
        .collect(Collectors.teeing(
                Collectors.summingDouble(Product::price),
                Collectors.averagingDouble(Product::price),
                Summary::new
        ));

System.out.

println("Total: "+summary.total() +", Avg: "+summary.

avg());
```

### ✅ Output:

```java
Total:150.0,Avg:50.0
```

---

## 🧠 When to Use

| Feature       | Use When...                                                                                    |
|---------------|------------------------------------------------------------------------------------------------|
| `filtering()` | You want to **filter within a group**, or inside another collector                             |
| `teeing()`    | You need **multiple different results from the same stream**, like sum + average, or min + max |

---

## 🛠️ Performance Notes

* Both are **stream-optimized**: they avoid multiple passes through the data.
* `teeing()` is especially powerful when **collecting multiple aggregates at once**.

---

Would you like to see a **Spring Boot REST API** example using `filtering()` or `teeing()` in a service method?
