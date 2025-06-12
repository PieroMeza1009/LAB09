package graph;
import java.util.*;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }


    public void insertVertex(V data) {
        if (!searchVertex(data)) {
            secVertex.add(new VertexObj<>(data, secVertex.size()));
        }
    }

    public void insertEdge(V v, V z) {
        VertexObj<V, E> v1 = getVertex(v);
        VertexObj<V, E> v2 = getVertex(z);
        if (v1 != null && v2 != null && !searchEdge(v, z)) {
            EdgeObj<V, E> newEdge = new EdgeObj<>(v1, v2, null, secEdge.size());
            secEdge.add(newEdge);
        }
    }
}
