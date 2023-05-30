class VertexWeight<Vertex> implements Comparable<VertexWeight> {
    private Vertex vertex;
    private double distance;

    public VertexWeight(Vertex vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public double getDistance() {
        return distance;
    }
}