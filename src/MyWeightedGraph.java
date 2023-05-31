import java.util.*;

public class MyWeightedGraph<Vertex> {
    private Map<Vertex, List<Edge<Vertex>>> map;

    /**
     * Constructs an empty weighted graph.
     */
    public MyWeightedGraph() {
        map = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     * @param vertex The vertex to be added.
     */
    public void addVertex(Vertex vertex) {
        map.put(vertex, new LinkedList<>());
    }

    /**
     * Validates if a given vertex is in the graph.
     * @param index The vertex to be validated.
     * @throws IllegalArgumentException if the vertex is not in the graph.
     */
    private void validateVertex(Vertex index) {
        if (!map.containsKey(index)) {
            throw new IllegalArgumentException("Vertex " + index + " is out of the range");
        }
    }

    /**
     * Adds a weighted edge between two vertices in the graph.
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param weight      The weight of the edge.
     */
    public void addEdge(Vertex source, Vertex destination, double weight) {
        validateVertex(source);
        validateVertex(destination);
        addDirectedEdge(source, destination, weight);
        addDirectedEdge(destination, source, weight);
    }

    /**
     * Adds a directed weighted edge from source to destination in the graph.
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param weight      The weight of the edge.
     */
    private void addDirectedEdge(Vertex source, Vertex destination, double weight) {
        List<Edge<Vertex>> edges = map.getOrDefault(source, new LinkedList<>());
        edges.add(new Edge<>(source, destination, weight));
        map.put(source, edges);
    }

    /**
     * Prints the adjacency list representation of the graph.
     */
    public void printGraph() {
        for (Vertex vertex : map.keySet()) {
            List<Edge<Vertex>> edges = map.get(vertex);
            System.out.print("Vertex " + vertex + ": ");
            for (Edge<Vertex> edge : edges) {
                System.out.print("(" + edge.getDest() + ", " + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Removes an edge between two vertices in the graph.
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     */
    public void removeEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        removeDirectedEdge(source, destination);
        removeDirectedEdge(destination, source);
    }

    /**
     * Removes a directed edge from the graph.
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     */
    private void removeDirectedEdge(Vertex source, Vertex destination) {
        if (map.containsKey(source)) {
            List<Edge<Vertex>> edges = map.get(source);
            edges.removeIf(edge -> edge.getDest() == destination);
        }
    }

    /**
     * Performs Dijkstra's algorithm to find the shortest paths from a given source vertex to all other vertices in the graph.
     * @param source The source vertex from which to calculate the shortest paths.
     * @return A map that maps each vertex to its shortest distance from the source vertex.
     */
    public Map<Vertex, Double> dijkstra(Vertex source) {
        //result container
        Map<Vertex, Double> distances = new HashMap<>();

        // Put all the vertices with weight equal to infinity
        for (Vertex vertex : map.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }

        // Set the start point (distance to start, and its distance equals 0)
        distances.put(source, 0.0);
        PriorityQueue<VertexWeight> queue = new PriorityQueue<>();
        //add first vertex
        queue.add(new VertexWeight<>(source, 0));
        //empty - when all the vertices will be visited
        while (!queue.isEmpty()) {
            //prioritized(min) vertex poll from queue.
            VertexWeight pair = queue.poll();
            //set polled value to var
            Vertex vertex = (Vertex) pair.getVertex();
            double distance = pair.getDistance();
            //if this distance more than previous, next iteration
            if (distance > distances.get(vertex)) {
                continue;
            }
            //pick from edges the neighbors of current vertex
            List<Edge<Vertex>> edges = map.get(vertex);
            for (Edge<Vertex> edge : edges) {
                //checks all the possible ways and choose lesser edge
                double newDistance = distance + edge.getWeight();
                if (newDistance < distances.get(edge.getDest())) {
                    distances.put(edge.getDest(), newDistance);
                    queue.add(new VertexWeight<>(edge.getDest(), newDistance));
                }
            }
        }
        return distances;
    }

    /**
     * Performs a Depth-First Search traversal starting from a given vertex.
     * @param start The vertex from which to start the DFS traversal.
     */
    public void DFS(Vertex start) {
        validateVertex(start);
        Map<Vertex, Boolean> visited = new HashMap<>();
        //put to every vertex false for start
        for (Vertex vertex : map.keySet()) {
            visited.put(vertex, false);
        }
        DFSHelper(start, visited);
    }

    /**
     * Helper method for DFS traversal.
     * @param vertex  The current vertex.
     * @param visited A map to track visited vertices.
     */
    private void DFSHelper(Vertex vertex, Map<Vertex, Boolean> visited) {
        //check vertex = set visited true
        visited.put(vertex, true);
        System.out.print(vertex + " ");
        List<Edge<Vertex>> edges = map.get(vertex);
        for (Edge<Vertex> edge : edges) {
            //one by one check all the destinated vertices
            //until there is no destinated vertex
            //going through one way,then return to curr and change way
            if (!visited.get(edge.getDest())) {
                DFSHelper(edge.getDest(), visited);
            }
        }
    }

    /**
     * Performs a Breadth-First Search traversal starting from a given vertex.
     * @param startVertex The vertex from which to start the BFS traversal.
     */
    public void BFS(Vertex startVertex) {
        validateVertex(startVertex);
        Set<Vertex> visited = new HashSet<>();
        //we use queue to give prioritize to unchecked near neighbors of vertex
        Queue<Vertex> vertices = new LinkedList<>();

        vertices.add(startVertex);
        visited.add(startVertex);

        while (!vertices.isEmpty()) {
            //poll first vertex in queue(by prioritizing fifo)
            Vertex v = vertices.poll();
            System.out.print(v + " ");
            //receive neighbors of current vertex
            List<Edge<Vertex>> edges = map.get(v);

            for (Edge<Vertex> edge : edges) {
                //add all the neighbors of vertex
                if (!visited.contains(edge.getDest())) {
                    visited.add(edge.getDest());
                    vertices.add(edge.getDest());
                }
            }
        }
    }
}
