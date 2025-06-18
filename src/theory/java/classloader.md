### 📦 What is a **ClassLoader** in Java?

In Java, the **ClassLoader** is part of the **Java Runtime Environment (JRE)** responsible for **loading `.class` files into memory**. It dynamically loads classes **at runtime**, not during compilation.

---

## 🔄 Why ClassLoader Matters

Java doesn't load all classes upfront. It uses ClassLoaders to load classes **only when needed**, which:

* Improves **startup time**
* Enables **dynamic loading** of classes (e.g., plugins)
* Supports **modular and secure** application design

---

## 🔍 Basic Responsibilities of ClassLoader

1. **Loading:** Locate and load class files (`.class`) into memory.
2. **Linking:** Verify bytecode and prepare for use.
3. **Delegation:** Use parent-first strategy to avoid duplicate class definitions.

---

## 🧱 Types of ClassLoaders in Java

| ClassLoader                        | Description                                                                                                        |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| **Bootstrap ClassLoader**          | Loads core Java classes from `rt.jar` (e.g., `java.lang.*`). It's part of the JVM (native code), not a Java class. |
| **Extension ClassLoader**          | Loads classes from `JAVA_HOME/lib/ext` or `java.ext.dirs`.                                                         |
| **System/Application ClassLoader** | Loads classes from the application's classpath (`-cp`, `CLASSPATH`).                                               |
| **Custom ClassLoader**             | User-defined for custom loading behavior (plugins, encrypted classes, etc.).                                       |

---

## 🔄 ClassLoader Delegation Model

Java uses a **parent delegation model**:

```
ClassLoader.loadClass(String name):
    1. Ask parent class loader to load it.
    2. If parent fails, try to load it itself.
```

This ensures:

* **Security** (core classes are not overridden)
* **Consistency** (same class loaded by one loader)

---

## 🧪 Example: Accessing ClassLoader

```java
public class Demo {
    public static void main(String[] args) {
        ClassLoader loader = Demo.class.getClassLoader();
        System.out.println("ClassLoader: " + loader);
        System.out.println("Parent: " + loader.getParent());
    }
}
```

---

## 🛠️ Custom ClassLoader Example (Advanced)

```java
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadFromSomewhere(name);
        return defineClass(name, bytes, 0, bytes.length);
    }
}
```

Used in:

* Web servers (Tomcat)
* IDEs
* OSGi / modular frameworks
* Dynamic reloading (plugins)

---

## ❗ Common Issues

| Issue                    | Cause                                                                            |
| ------------------------ | -------------------------------------------------------------------------------- |
| `ClassNotFoundException` | ClassLoader couldn't find the class file                                         |
| `NoClassDefFoundError`   | Class was available at compile time, but missing at runtime                      |
| ClassCastException       | Same class loaded by **two different class loaders** is treated as **different** |

---

## ✅ Summary

> The **ClassLoader** is a foundational part of the JVM that **loads Java classes into memory**, using a **delegation model** to ensure security, consistency, and modularity.

Let me know if you want a deep dive into **how ClassLoaders work in containers like Tomcat or Spring Boot**—those are great real-world examples!
