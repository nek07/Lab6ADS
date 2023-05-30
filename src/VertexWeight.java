class VertexWeight<Vertex> implements Comparable<VertexWeight> {
    private Vertex vertex;
    private double distance;

    public VertexWeight(Vertex vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(VertexWeight other) {
        return Integer.compare((int)distance, (int)other.distance);
    }

    public Vertex getVertex() {
        return vertex;
    }

    public double getDistance() {
        return distance;
    }
}