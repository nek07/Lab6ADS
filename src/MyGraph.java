import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

}
