Excellent question! When **multiple containers are deployed in a single pod** (which happens in Kubernetes), they share
certain resources. Here’s how Kubernetes Pods manage **network**, **disk**, and **resource isolation**:

---

## 🧩 How Pods Manage Shared Resources (When Multiple Containers in One Pod)

### 1. 🕸 **Network (Shared)**

* All containers in a pod **share the same network namespace**:

    * **Same IP address**
    * **Same port space**
    * They can communicate with each other via `localhost` (like `localhost:8080`)
* From outside the pod, Kubernetes routes traffic to the **pod IP**, not individual containers.

✅ **Use Case:** A sidecar container can log traffic from the main app container without external exposure.

---

### 2. 💾 **Disk (Volumes - Shared Optionally)**

* Containers in the same pod can **share volumes** (mounted directories).
* Kubernetes allows defining one or more **Volumes** at the Pod level.
* Each container can **mount these volumes** at different mount points.

```yaml
volumes:
  - name: shared-logs
    emptyDir: { }

containers:
  - name: app
    volumeMounts:
      - mountPath: /app/logs
        name: shared-logs
  - name: logger
    volumeMounts:
      - mountPath: /logs
        name: shared-logs
```

✅ **Use Case:** One container writes logs, another container reads and ships them.

---

### 3. ⚙️ **CPU/Memory (Resources - Independently Controlled)**

* You can set **resource requests and limits per container**, even in the same pod.
* But **the pod as a whole shares the node's CPU/memory** allocated to it.

```yaml
containers:
  - name: app
    resources:
      requests:
        memory: "256Mi"
        cpu: "250m"
  - name: sidecar
    resources:
      requests:
        memory: "128Mi"
        cpu: "100m"
```

✅ **Use Case:** Prevent sidecar from starving the main app of resources.

---

## 🧠 Why Group Containers in One Pod?

Use a multi-container pod **only when containers are tightly coupled**:

* Need shared memory/disk
* Need to talk over `localhost`
* Need to start/stop together
* Sidecars (logging, metrics, proxies like Envoy)

❌ **Don’t use it just to save space** — they are deployed and scheduled **together**.

---

## 📝 Summary

| Resource       | Shared in Pod?       | Details                                                                |
|----------------|----------------------|------------------------------------------------------------------------|
| **Network**    | ✅ Yes                | Same IP, same localhost, same ports                                    |
| **Disk**       | ⚠️ Optional          | Shared via Volumes                                                     |
| **CPU/Memory** | ❌ No (per container) | Each container gets its own request/limit, but shares the node's quota |
| **Lifecycle**  | ✅ Yes                | Containers in a pod start/stop/restart together                        |

---

Would you like a YAML example of a pod with multiple containers sharing network and volumes?
