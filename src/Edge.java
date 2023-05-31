public class Edge<Vertex> {

    private Vertex source;
    private Vertex dest;
    private Double weight;
    public Edge(Vertex source, Vertex dest, Double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDest() {
        return dest;
    }


}
