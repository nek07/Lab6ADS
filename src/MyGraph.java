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
    public void removeEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        List<Edge<Vertex>> neighbors = map.get(source);
        if (neighbors!=null) {
            neighbors.remove(destination);
        }
        map.get(destination).remove(source);
    }
}
