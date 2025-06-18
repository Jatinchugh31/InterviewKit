Excellent and advanced Spring question! You're asking:

> 💬 “I want the **bean scope to be prototype** (fresh object each time),
> but still get **singleton behavior** (one main object)
> and ensure that **sub-objects are fresh each time**.”

You're thinking in the right direction — **immutability helps**, but here are the complete patterns you can use in Spring to achieve this.

---

## ✅ GOAL:

* **Singleton main bean** (`@Service`, `@Component`)
* **Prototype-scoped dependencies** injected **fresh each time** you need them

---

## 🧠 Problem with `@Autowired Prototype`:

If you simply do this:

```java
@Component
@Scope("singleton") // default
public class SingletonService {

    @Autowired
    private PrototypeBean prototypeBean; // ❌ Only injected once
}
```

Then `prototypeBean` is **created only once** during context init and reused — so it's **not fresh each time**.

---

## ✅ Solution 1: **Use `ObjectFactory` or `Provider`**

```java
@Component
public class SingletonService {

    @Autowired
    private ObjectFactory<PrototypeBean> prototypeFactory;

    public void doSomething() {
        PrototypeBean bean = prototypeFactory.getObject(); // 🔁 Fresh every time
        bean.work();
    }
}
```

You can also use `javax.inject.Provider<PrototypeBean>` in the same way.

---

## ✅ Solution 2: **Use `ApplicationContext.getBean()`**

```java
@Component
public class SingletonService {

    @Autowired
    private ApplicationContext context;

    public void useBean() {
        PrototypeBean bean = context.getBean(PrototypeBean.class);
        bean.process();
    }
}
```

This gives you a **new instance each time**, respecting prototype scope.

---

## ✅ Solution 3: **Use method injection (`@Lookup`)**

```java
@Component
public abstract class SingletonService {

    public void serve() {
        PrototypeBean bean = getPrototypeBean(); // 🔁 fresh every time
        bean.doWork();
    }

    @Lookup
    protected abstract PrototypeBean getPrototypeBean();
}
```

Spring will override this method behind the scenes to call `getBean()` on demand.

---

## ✅ Bonus: Use **Immutable Sub-Beans**

As you said — using **immutable beans** (with final fields, constructor injection) ensures you're safe even when sub-objects are cached/stored.
But it **doesn’t fix the scope problem** — it complements the solution by making objects stateless.

---

## 🧠 Summary

| Approach                       | What It Does                      | Use When                         |
| ------------------------------ | --------------------------------- | -------------------------------- |
| `ObjectFactory` / `Provider`   | Lazily creates new prototype bean | Best balance of clean + flexible |
| `ApplicationContext.getBean()` | Manual bean lookup                | Good for quick testing/debugging |
| `@Lookup`                      | Spring auto-overrides the method  | Best for hiding lookup logic     |
| Immutable class                | Ensures thread safety & isolation | Combine with other solutions     |

---

Let me know if you want a working code example using one of these patterns!
