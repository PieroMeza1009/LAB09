package pruebalibreria;
import lib.*;
import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.DijkstraManyToManyShortestPaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class PruebaJGraphT {
    public static void main(String[] args) {
        Graph<String, DefaultWeightedEdge> mapaRutas = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        mapaRutas.addVertex("Lima");
        mapaRutas.addVertex("Arequipa");
        mapaRutas.addVertex("Ancash");
        mapaRutas.addVertex("Trujillo");

        mapaRutas.setEdgeWeight(mapaRutas.addEdge("Lima", "Ancash"), 1100);
        mapaRutas.setEdgeWeight(mapaRutas.addEdge("Lima", "Arequipa"), 1000);
        mapaRutas.setEdgeWeight(mapaRutas.addEdge("Arequipa", "Ancash"), 500);
        mapaRutas.setEdgeWeight(mapaRutas.addEdge("Trujillo", "Lima"), 560);

        System.out.println("Ciudades: " + mapaRutas.vertexSet());
        System.out.println("Rutas: " + mapaRutas.edgeSet());

        DijkstraManyToManyShortestPaths<String, DefaultWeightedEdge> dijkstra = new DijkstraManyToManyShortestPaths<>(mapaRutas);
        var rutaCorta = dijkstra.getPath("Lima", "Ancash");

        if (rutaCorta != null) {
            System.out.println("Ruta mas corta de Lima a Ancash: " + rutaCorta.getVertexList());
            System.out.println("Distancia total: " + rutaCorta.getWeight() + "km");
        } else {
            System.out.println("No hay ruta disponible.");
        }
    }
}
