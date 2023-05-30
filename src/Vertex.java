import java.util.Map;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;
    private void AdjacentVertex(Vertex<V> destination, double weight){
        adjacentVertices.put(destination,weight);
    }
}
