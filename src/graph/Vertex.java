package graph;

public class Vertex<E> {
    private E data;
    protected ListLinked<Edge<E>> listAdj;

    public Vertex(E data) {
        this.data = data;
        listAdj = new ListLinked<Edge<E>>();
    }

    public E getData() {
        return data;
    }

}
