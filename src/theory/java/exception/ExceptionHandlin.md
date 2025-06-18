Great topic! Let's clearly understand how **exceptions work in Java inheritance**, especially when you're **overriding methods** in a class hierarchy.

---

## ✅ Basic Concepts

### 🔸 **Checked exceptions**:

Must be either caught or declared with `throws`.  compile time
Examples: `IOException`, `SQLException`

### 🔸 **Unchecked exceptions**:

Do **not** need to be declared.
Examples: `NullPointerException`, `ArithmeticException`

---

## 🔧 Exception Rules in Inheritance

### 1️⃣ **Overriding methods and exceptions**

> When you **override a method**, you **cannot throw broader (more general) checked exceptions** than the method in the parent class.

---

### ✅ Allowed:

```java
class Parent {
    void doSomething() throws IOException { }
}

class Child extends Parent {
    @Override
    void doSomething() throws FileNotFoundException { } // ✅ Allowed — narrower exception
}
```

✅ `FileNotFoundException` is a **subclass** of `IOException`, so it's fine.

---

### ❌ Not Allowed:

```java
class Parent {
    void doSomething() throws IOException { }
}

class Child extends Parent {
    @Override
    void doSomething() throws Exception { } // ❌ Not allowed — broader exception
}
```

❌ You cannot throw a **broader or unrelated checked exception** in the child.

---

### 2️⃣ **If superclass method does NOT throw any checked exception...**

Then the overriding method **must not** throw any **new checked exceptions**.

```java
class Parent {
    void doSomething() { }
}

class Child extends Parent {
    @Override
    void doSomething() throws IOException { } // ❌ Not allowed
}
```

---

### ✅ Unchecked exceptions are always allowed:

```java
class Parent {
    void doSomething() { }
}

class Child extends Parent {
    @Override
    void doSomething() throws NullPointerException { } // ✅ Allowed
}
```

---

## 📦 Why This Rule Exists

To maintain the **Liskov Substitution Principle**:

> You should be able to use a `Child` wherever a `Parent` is expected — without breaking exception contracts.

---

## 🧠 Quick Summary

| Rule                           | Checked Exceptions                            | Unchecked Exceptions |
| ------------------------------ | --------------------------------------------- | -------------------- |
| Parent throws exception        | Child can throw **same or subclass**          | ✅ Always allowed     |
| Parent doesn't throw exception | Child **cannot throw new checked** exceptions | ✅ Allowed            |
| Broader checked exceptions     | ❌ Not allowed                                 | ✅ Allowed            |

---

Let me know if you want a **real code example with try-catch**, or how this applies to **interfaces and abstract classes**!
