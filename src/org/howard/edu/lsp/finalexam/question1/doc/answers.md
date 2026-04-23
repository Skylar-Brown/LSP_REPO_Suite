# Question 1

## Part 1:

**Shared Resource #1:**
- nextId (shared counter used to generate unique IDs)

**Shared Resource #2:**
- requests list (shared ArrayList storing all requests)

**Concurrency Problem:**
- Race condition

**Why addRequest() is unsafe:**
- Multiple threads can call getNextId() at the same time, causing duplicate IDs.
- The increment operation (nextId++) is not atomic.
- The requests list is not thread-safe, so concurrent modifications can corrupt data.

---

## Part 2:

**Fix A: Explanation**
- Correct.
- Synchronizing getNextId() ensures only one thread can access and increment nextId at a time.
- This guarantees unique IDs and prevents race conditions on the counter.

**Fix B: Explanation**
- Correct.
- Synchronizing addRequest() ensures the entire sequence (getNextId + add to list) is atomic.
- Prevents both duplicate IDs and concurrent modification of the requests list.

**Fix C: Explanation**
- Incorrect.
- Synchronizing getRequests() only protects reading the list.
- It does NOT prevent race conditions during addRequest().
- The main issue (ID generation and list modification) still exists.

---

## Part 3:

**Answer + Explanation**
- No, getNextId() should not be public.
- According to Riel’s heuristics, implementation details should be hidden.
- getNextId() is an internal helper method and exposing it breaks encapsulation.
- It allows external classes to manipulate ID generation, which can lead to incorrect behavior.

---

## Part 4:

**Description:**
- Use atomic variables (e.g., AtomicInteger) to ensure thread-safe ID generation without using synchronized.
- Use a thread-safe collection such as CopyOnWriteArrayList to safely store requests.
- This avoids explicit locking while still ensuring correctness.

**Code Snippet:**
```java
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

private AtomicInteger nextId = new AtomicInteger(1);
private List<String> requests = new CopyOnWriteArrayList<>();

public void addRequest(String studentName) {
    int id = nextId.getAndIncrement();
    String request = "Request-" + id + " from " + studentName;
    requests.add(request);
}