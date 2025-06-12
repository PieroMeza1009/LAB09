package graph;

public class Mainn {
    public static void main(String[] args) {
        GraphListEdge<String, Integer> g = new GraphListEdge<>();
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");

        System.out.println("Grafo:");
        System.out.println(g);

        System.out.println("¿Existe vértice B?: " + g.searchVertex("B"));
        System.out.println("¿Existe arista A-D?: " + g.searchEdge("A", "D"));

        System.out.println("Recorrido BFS desde A:");
        g.bfs("A");
    }
}