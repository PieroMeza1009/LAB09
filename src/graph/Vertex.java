package graph;

public class Vertex {
    public String data;
    public Vertex next;
    public Edge adjList;

    public Vertex(String data) {
        this.data = data;
        this.next = null;
        this.adjList = null;
    }

}
