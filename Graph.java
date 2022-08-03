import java.util.*;

public class Graph {

    private List<Integer>[] adjList;

    public static void main(String[] args) {
        Graph graph = new Graph();
        int v = 5;
        graph.initGraph(v);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.printGraph();
    }

    public void initGraph(int v) {
        adjList = new ArrayList[v];
        for (int i = 0; i < v; ++i) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v1, int v2) {
        adjList[v1].add(v2);
    }

    public void printGraph() {
        int v = adjList.length;
        for (int v1 = 0; v1 < v; ++v1) {
            System.out.print(v1 + " -> ");
            for (int v2 : adjList[v1]) {
                System.out.print(v2 + " ");
            }
            System.out.println();
        }
    }
}
