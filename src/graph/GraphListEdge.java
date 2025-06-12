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

    public boolean searchVertex(V data) {
        return getVertex(data) != null;
    }

    private VertexObj<V, E> getVertex(V data) {
        for (VertexObj<V, E> v : secVertex) {
            if (v.getInfo().equals(data)) return v;
        }
        return null;
    }
    public boolean searchEdge(V v, V z) {
        VertexObj<V, E> v1 = getVertex(v);
        VertexObj<V, E> v2 = getVertex(z);

        if (v1 == null || v2 == null) return false;

        for (EdgeObj<V, E> e : secEdge) {
            if ((e.getEndVertex1().equals(v1) && e.getEndVertex2().equals(v2)) ||
                (e.getEndVertex1().equals(v2) && e.getEndVertex2().equals(v1))) {
                return true;
            }
        }
        return false;
    }
    public void bfs(V startData) {
        VertexObj<V, E> start = getVertex(startData);
        if (start == null) return;

        Queue<VertexObj<V, E>> queue = new LinkedList<>();
        Set<VertexObj<V, E>> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();
            System.out.println(current.getInfo());

            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> neighbor = null;
                if (edge.getEndVertex1().equals(current)) {
                    neighbor = edge.getEndVertex2();
                } else if (edge.getEndVertex2().equals(current)) {
                    neighbor = edge.getEndVertex1();
                }
}
