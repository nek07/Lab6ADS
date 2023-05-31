import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        MyWeightedGraph mg = new MyWeightedGraph();
        mg.addVertex(1);
        mg.addVertex(2);
        mg.addVertex(3);
        mg.addVertex(5);
        mg.addVertex(4);
        mg.addVertex(6);
        mg.addEdge(1,2,70);
        mg.addEdge(2,3,40);
        mg.addEdge(4,5,50);
        mg.addEdge(3,5,20);
        mg.addEdge(4,6,100);
        mg.addEdge(5,6,50);
        System.out.println("Print graph");
        mg.printGraph();
        System.out.println("\nBreath-First-Search");
        mg.BFS(1);
        System.out.println("\nDepth-First-Search");
        mg.DFS(1);
        System.out.println("\nDijkstra");
        System.out.println(mg.dijkstra(1));
        mg.removeEdge(5,6);
        System.out.println(mg.dijkstra(1) + " - after removing 5-6 edge");





    }
}