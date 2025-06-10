package graph;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    public void insertVertex(E data) {
        Vertex<E> newVertex = new Vertex<>(data);
        if (listVertex.search(newVertex) == null) {
            listVertex.insertLast(newVertex);
        }
    }

    public void insertEdge(E verOri, E verDes) {

}
