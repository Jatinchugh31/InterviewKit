package datastructure.src.StackQueue;

import java.util.*;

public class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.set(1, 1);
        cache.set(2, 2);
        System.out.println(cache.get(1));
        cache.set(3, 3);
        cache.set(4, 4);
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));


    }
}


class LRUCache {
    private Map<Integer, Integer> map;
    private Deque<Integer> queue;
    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            queue.remove(key);
            queue.addFirst(key);
            return map.get(key);
        } else
            return -1;
    }

    public void set(int key, int value) {
        if (map.size() == capacity) {
            Integer val = queue.removeLast();
            System.out.println("Removed " + val + " from cache");
            map.remove(val);
        }
        map.put(key, value);
        queue.addFirst(key);
    }

}
