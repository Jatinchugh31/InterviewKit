package src.companies.altimatrick;

import java.util.*;

public class TraverseGraph {
    public static void main(String[] args) {

        Map<String, List<String>> graph = new HashMap<>();
        graph.put("source 1", Arrays.asList("filter1"));
        graph.put("source 3", Arrays.asList("filter2"));
        graph.put("filter1", Arrays.asList("Join 1"));
        graph.put("filter2", Arrays.asList("Join 1"));
        graph.put("Join 1", Arrays.asList("filter3"));
        graph.put("filter3", Arrays.asList("split1"));
        graph.put("split1", Arrays.asList("target 1", "target 2"));
        String starting = "source 1";
        String target = "target 1";
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        if (dfs(starting, target, graph, visited, path)) {
            System.out.println("path" + path);
        }
    }

    private static boolean dfs(String starting, String target, Map<String, List<String>> graph, Set<String> visited, List<String> path) {
        if (visited.contains(starting)) {
            return false;
        }
        path.add(starting);
        visited.add(starting);
        if (starting.equals(target)) {
            return true;
        }
        if (graph.containsKey(starting)) {
            for (String next : graph.get(starting)) {
                if (dfs(next, target, graph, visited, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }
}
