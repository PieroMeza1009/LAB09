package graph;

public class Vertex<E> {  // Clase genérica Vertex que representa un nodo del grafo, donde E es el tipo de dato almacenado

    private E data; // Contiene la información del vértice (puede ser un número, string, objeto, etc.)
    protected ListLinked<Edge<E>> listAdj;// Lista de adyacencia que contiene las aristas (conexiones) desde este vértice

    //este es el constructor de la clase vertex
    public Vertex(E data) {
        this.data = data; //// aca se asigna el dato recibido al atributo data
        listAdj = new ListLinked<Edge<E>>();
    }

    public E getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<E> v = (Vertex<E>) o;
            return this.data.equals(v.data);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.data + " -> " + this.listAdj.toString() + "\n";
    }

}
