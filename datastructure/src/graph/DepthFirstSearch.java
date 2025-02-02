package datastructure.src.graph;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {
    public static void main(String[] args) {
        int v = 5;
        ArrayList<Integer> node1 = new ArrayList<>();
        node1.add(2);
        node1.add(3);
        node1.add(1);
        ArrayList<Integer> node2 = new ArrayList<>();
        node2.add(0);
        ArrayList<Integer> node3 = new ArrayList<>();
        node3.add(0);
        node3.add(4);
        ArrayList<Integer> node4 = new ArrayList<>();
        node4.add(0);
        ArrayList<Integer> node5 = new ArrayList<>();
        node4.add(2);

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        graph.add(node1);
        graph.add(node2);
        graph.add(node3);
        graph.add(node4);
        graph.add(node5);
        List<Integer> rs = dfsOfGraph(v, graph);
        System.out.println(rs);


    }

    public static ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        ArrayList<Integer> ans = new ArrayList<>();
        dfs(0, visited, ans, adj);
        return ans;
    }

    private static void dfs(int i, boolean[] visited, ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> adj) {
        visited[i] = true;
        ans.add(i);
        for (int k = 0; k < adj.get(i).size(); k++) {
            if (!visited[adj.get(i).get(k)]) {
                dfs(adj.get(i).get(k), visited, ans, adj);
            }
        }
    }
}
