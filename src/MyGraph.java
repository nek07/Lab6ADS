import java.util.*;

public class MyGraph<Vertex> {
private Map<Vertex, List<Edge<Vertex>>> map;
    public MyGraph() {
        map = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        map.put(vertex, new LinkedList<>());

    }
    private void validateVertex(Vertex index) {
        if (!map.containsKey(index)) {
            throw new IllegalArgumentException("Vertex " + index + " is out of the range");
        }
    }
    public void addEdge(Vertex source, Vertex destination, double weight) {
        validateVertex(source);
        validateVertex(destination);
        addDirectedEdge(source, destination, weight);
        addDirectedEdge(destination, source, weight);
    }

    private void addDirectedEdge(Vertex source, Vertex destination, double weight) {
        List<Edge<Vertex>> edges = map.getOrDefault(source, new LinkedList<>());
        edges.add(new Edge(source,destination, weight));
        map.put(source, edges);
    }

    public void printGraph() {
        for (Vertex vertex : map.keySet()) {
            List<Edge<Vertex>> edges = map.get(vertex);
            System.out.print("Vertex " + vertex + ": ");
            for (Edge edge : edges) {
                System.out.print("(" + edge.getDest() + ", " + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }
    public void removeEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        removeDirectedEdge(source,destination);
        removeDirectedEdge(destination,source);
    }
    public void removeDirectedEdge(Vertex source, Vertex destination) {
        if (map.containsKey(source)) {
            List<Edge<Vertex>> edges = map.get(source);
            edges.removeIf(edge -> edge.getDest() == destination);
        }
    }
    public Map<Vertex, Double> dijkstra(Vertex source) {
        //created new empty HashMap
        Map<Vertex, Double> distances = new HashMap<>();

        //put all the vertices with weight which equals to infinity
        for (Vertex vertex : map.keySet()) {
            distances.put(vertex,(Double) Double.MAX_VALUE);
        }

        //put the start point(distance to start, and it's distance equals 0)
        distances.put(source, (double) 0);
        return distances;
    }

}
