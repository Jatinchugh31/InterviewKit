The JVM divides memory into several regions, each serving a specific purpose

a. Heap Memory
Purpose: Stores all the objects and arrays created by the application.
Managed by the Garbage Collector (GC).

Divided into generations for efficient garbage collection:
---Young Generation: Where new objects are allocated. It includes:
--Eden Space: The area where new objects are created.
--Survivor Spaces (S0 and S1): Objects that survive garbage collection in Eden are moved here.
--Old (Tenured) Generation: Contains objects that have existed for a longer period and survived multiple GC cycles.
Metaspace (Java 8+): Stores class metadata. (Before Java 8, this was known as the Permanent Generation or PermGen.)

b. Stack Memory
Purpose: Stores information about method calls, local variables, and references to objects in the heap.

Characteristics:
Each thread has its own stack.
Memory is allocated and deallocated in a last-in, first-out (LIFO) order.
When a method is invoked, a new stack frame is created; once the method returns, its stack frame is removed.
Limited in size; a deep or infinite recursion may lead to a StackOverflowError.

Types of Garbage Collectors
The JVM supports several garbage collection algorithms, each optimized for different scenarios:

Serial GC: Uses a single thread for garbage collection; best for small applications with a single processor.
--Parallel GC (Throughput Collector): Uses multiple threads for garbage collection, which improves throughput in
multi-core systems.
--CMS (Concurrent Mark-Sweep) GC: Minimizes pause times by doing most of the work concurrently with the application
threads.
--G1 (Garbage First) GC: Divides the heap into regions and performs garbage collection on regions that contain the most
garbage first; suitable for applications with large heaps.
--ZGC (Z Garbage Collector) and Shenandoah (in OpenJDK): Designed for low latency and very large heaps, with almost no
pause times.
