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
                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Vértices: " + secVertex + "\nAristas: " + secEdge;
    }
// EJERCICIO 7: GRAFOS DIRIGIDOS
// ==============================

// Grado de entrada: cuántas aristas llegan al vértice
public int gradoEntrada(V data) {
    int count = 0;
    for (EdgeObj<V, E> edge : secEdge) {
        if (edge.getEndVertex2().getInfo().equals(data)) {
            count++;
        }
    }
    return count;
}

// Grado de salida: cuántas aristas salen del vértice
public int gradoSalida(V data) {
    int count = 0;
    for (EdgeObj<V, E> edge : secEdge) {
        if (edge.getEndVertex1().getInfo().equals(data)) {
            count++;
        }
    }
    return count;
}

// Es camino dirigido: un nodo con (in=0, out=1), uno con (in=1, out=0), resto (in=1, out=1)
public boolean esCaminoDirigido() {
    int start = 0, end = 0;
    for (VertexObj<V, E> v : secVertex) {
        int in = gradoEntrada(v.getInfo());
        int out = gradoSalida(v.getInfo());

        if (in == 0 && out == 1) start++;
        else if (in == 1 && out == 0) end++;
        else if (!(in == 1 && out == 1)) return false;
    }
    return start == 1 && end == 1;
}

// Es ciclo dirigido: todos los vértices con in=1 y out=1
public boolean esCicloDirigido() {
    for (VertexObj<V, E> v : secVertex) {
        if (gradoEntrada(v.getInfo()) != 1 || gradoSalida(v.getInfo()) != 1)
            return false;
    }
    return true;
}

// Es completo dirigido: cada vértice tiene salida a todos los demás (n - 1)
public boolean esCompletoDirigido() {
    int n = secVertex.size();
    for (VertexObj<V, E> v : secVertex) {
        if (gradoSalida(v.getInfo()) != n - 1)
            return false;
    }
    return true;
}

   // EJERCICIO 8: REPRESENTACIONES DEL GRAFO
    // ==============================

    public void representacionFormal() {
        StringBuilder vStr = new StringBuilder("V = {");
        for (int i = 0; i < secVertex.size(); i++) {
            vStr.append(secVertex.get(i).getInfo());
            if (i < secVertex.size() - 1) vStr.append(", ");
        }
        vStr.append("}");

        StringBuilder eStr = new StringBuilder("E = {");
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> edge = secEdge.get(i);
            eStr.append("(")
                .append(edge.getEndVertex1().getInfo())
                .append(", ")
                .append(edge.getEndVertex2().getInfo())
                .append(")");
            if (i < secEdge.size() - 1) eStr.append(", ");
        }
        eStr.append("}");

        System.out.println(vStr);
        System.out.println(eStr);
    }

    public void listaAdyacencia() {
        for (VertexObj<V, E> v : secVertex) {
            System.out.print(v.getInfo() + " -> ");
            ArrayList<String> adyacentes = new ArrayList<>();

            for (EdgeObj<V, E> e : secEdge) {
                if (e.getEndVertex1().equals(v)) {
                    adyacentes.add(e.getEndVertex2().getInfo().toString());
                } else if (e.getEndVertex2().equals(v)) {
                    adyacentes.add(e.getEndVertex1().getInfo().toString());
                }
            }

            System.out.println(String.join(", ", adyacentes));
        }
    }

    public void matrizAdyacencia() {
        int n = secVertex.size();
        V[] labels = (V[]) new Object[n];

        for (int i = 0; i < n; i++) {
            labels[i] = secVertex.get(i).getInfo();
        }

        int[][] matriz = new int[n][n];

        for (EdgeObj<V, E> e : secEdge) {
            int i = secVertex.indexOf(e.getEndVertex1());
            int j = secVertex.indexOf(e.getEndVertex2());

            matriz[i][j] = 1;
            matriz[j][i] = 1;
        }

        System.out.print("   ");
        for (V v : labels) System.out.print(v + " ");
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(labels[i] + " ");
            for (int j = 0; j < n; j++) {
                System.out.print(" " + matriz[i][j]);
            }
            System.out.println();
        }
    }

}
