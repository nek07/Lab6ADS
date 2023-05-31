# MyWeightedGraph
## 
### ➕ MyWeightedGraph
**Description**: Constructor of the class.
```java
public MyWeightedGraph() {
        map = new HashMap<>();
        }
```
### ➕ addVertex
**Description**: Adds a vertex to the graph.

**Parameters**:
- `vertex`: The vertex to be added to the graph.
```java
public void addVertex(Vertex vertex) {
        map.put(vertex, new LinkedList<>());

        }
```
### ➕ addEdge
**Description**: Adds a weighted edge between two vertices in the graph.

**Parameters**:
- `source`: The source vertex of the edge.
- `destination`: The destination vertex of the edge.
- `weight`: The weight of the edge.
printGraph()
```java
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
```
### ➕ removeEdge
**Description**: Removes an edge between two vertices in the graph.

**Parameters**:
- `source`: The source vertex of the edge.
- `destination`: The destination vertex of the edge.
```java
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
```
### ➕ validateVertex
**Description**: Validates is the vertex within graph.

**Parameters**:
- `index`: index of the vertex.
```java
private void validateVertex(Vertex index) {
        if (!map.containsKey(index)) {
        throw new IllegalArgumentException("Vertex " + index + " is out of the range");
        }
        }
```
### ➕ printGraph
**Description**: Prints the adjacency list representation of the graph.

**Parameters**:
- `void`
```java
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
```
### ➕ dijkstra
**Description**: Performs Dijkstra's algorithm to find the shortest paths from a given source vertex to all other vertices in the graph.

**Parameters**:
- `source`: The vertex which is the startVertex or point A.
```java
public Map<Vertex, Double> dijkstra(Vertex source) {
        //created new empty HashMap
        Map<Vertex, Double> distances = new HashMap<>();

        //put all the vertices with weight which equals to infinity
        for (Vertex vertex : map.keySet()) {
            distances.put(vertex,(Double) Double.MAX_VALUE);
        }

        //put the start point(distance to start, and it's distance equals 0)
        distances.put(source, (double) 0);
        PriorityQueue<VertexWeight> queue = new PriorityQueue<>();
        queue.add(new VertexWeight<>(source, 0));

        while (!queue.isEmpty()) {
            VertexWeight pair = queue.poll();
            int vertex = (int) pair.getVertex();
            int distance = (int) pair.getDistance();

            if (distance > distances.get(vertex)) {
                continue;
            }

            List<Edge<Vertex>> edges = map.get(vertex);
            for (Edge edge : edges) {
                int newDistance = (int) (distance + edge.getWeight());
                if (newDistance < distances.get(edge.getDest())) {
                    distances.put((Vertex) edge.getDest(), (double) newDistance);
                    queue.add(new VertexWeight<>(edge.getDest(), newDistance));
                }
            }
        }
        return distances;
    }
```
### ➕ BFS
**Description**: Performs a Breadth-First Search traversal starting from a given vertex.

**Parameters**:
- `startVertex`: The vertex from which to start the BFS traversal.
```java
public void BFS(Vertex startVertex) {
        validateVertex(startVertex);
        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> vertices = new LinkedList<>();

        vertices.add(startVertex);
        visited.add(startVertex);

        while(!vertices.isEmpty()){
            Vertex v = vertices.poll();
            System.out.print(v+" ");

            List<Edge<Vertex>> edges = map.get(v);

            for(Edge<Vertex> edge : edges){
                if(!visited.contains(edge.getDest())){

                visited.add(edge.getDest());
                vertices.add(edge.getDest());
        }
        }
        }

        }
```
### ➕ DFS
**Description**: Performs a Depth-First Search traversal starting from a given vertex.

**Parameters**:
- `start`: The vertex from which to start the DFS traversal.
```java
public void DFS(Vertex start) {
        validateVertex(start);
        Map<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex vertex:map.keySet()) {
        visited.put(vertex,false);
        }
        DFSHelper(start, visited);

        }
private void DFSHelper(Vertex vertex, Map<Vertex, Boolean> visited) {
        visited.put(vertex, true);
        System.out.print(vertex + " ");
        List<Edge<Vertex>> edges = map.get(vertex);
        for (Edge edge : edges) {
        if (!visited.get(edge.getDest())){
        DFSHelper((Vertex) edge.getDest(), visited);
        }
        }
        }
```

