package src.dataStructure.graph;

import java.util.*;

public class BFSGraph {
  public static void main(String[] args) {
      Map<Integer, List<Integer>> graph = new HashMap<>();
      graph.put(1, Arrays.asList(2, 3));
      graph.put(2, Arrays.asList(4, 5));
      graph.put(3, Arrays.asList(6, 7));
      graph.put(4, Arrays.asList());
      graph.put(5, Arrays.asList());
      graph.put(6, Arrays.asList());
      graph.put(7, Arrays.asList());

      System.out.println("BFS Traversal:");
      bfsTraversal(graph, 1); // Output: 1 2 3 4 5 6 7
  }

    private static void bfsTraversal(Map<Integer, List<Integer>> graph, int start) {

      Queue<Integer> queue = new LinkedList<>();
      Set<Integer> visited = new HashSet<>();
      queue.add(start);
      visited.add(start);
      while (!queue.isEmpty()) {
          int current = queue.poll();
          System.out.print(current + " ");

          for(int neighbour : graph.getOrDefault(current, Collections.emptyList())) {
              if (!visited.contains(neighbour)) {
                  queue.add(neighbour);
                  visited.add(neighbour);
              }
          }
      }

      }
}
