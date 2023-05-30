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
}
