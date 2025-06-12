package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

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
    Vertex<E> vOri = listVertex.search(new Vertex<>(verOri));
    Vertex<E> vDes = listVertex.search(new Vertex<>(verDes));
    if (vOri != null && vDes != null) {
        if (vOri.listAdj.search(new Edge<>(vDes)) == null)
            vOri.listAdj.insertLast(new Edge<>(vDes));
        if (vDes.listAdj.search(new Edge<>(vOri)) == null)
            vDes.listAdj.insertLast(new Edge<>(vOri)); // porque es grafo no dirigido
        }
    }

    public boolean searchVertex(E data) {
        return listVertex.search(new Vertex<E>(data)) != null;
    }

    public boolean searchEdge(E verOri, E verDes) {
        Vertex<E> vOri = listVertex.search(new Vertex<E>(verOri));
        Vertex<E> vDes = listVertex.search(new Vertex<E>(verDes));
        if (vOri != null && vDes != null) {
            return vOri.listAdj.search(new Edge<E>(vDes)) != null;
        }
        return false;
    }

    public void removeVertex(E data) {
        Vertex<E> vRemove = listVertex.search(new Vertex<>(data));
        if (vRemove == null) return;

        // Elimina todas las aristas hacia vRemove
        ListLinked.Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            current.data.listAdj.remove(new Edge<>(vRemove));
            current = current.next;
        }

        // Elimina el vértice
        listVertex.remove(vRemove);
    }

    public void removeEdge(E verOri, E verDes) {
        Vertex<E> vOri = listVertex.search(new Vertex<>(verOri));
        Vertex<E> vDes = listVertex.search(new Vertex<>(verDes));
        if (vOri != null && vDes != null) {
            vOri.listAdj.remove(new Edge<>(vDes));
            vDes.listAdj.remove(new Edge<>(vOri)); // por ser no dirigido
        }
    }

    public void dfs(E data) {
        HashSet<E> visited = new HashSet<>();
        Vertex<E> start = listVertex.search(new Vertex<>(data));
        if (start != null)
            dfsRecursive(start, visited);
    }

    private void dfsRecursive(Vertex<E> vertex, HashSet<E> visited) {
        if (visited.contains(vertex.getData()))
            return;

        System.out.println(vertex.getData());
        visited.add(vertex.getData());

        ListLinked.Node<Edge<E>> current = vertex.listAdj.getHead();
        while (current != null) {
            Vertex<E> neighbor = current.data.getRefDest();
            if (!visited.contains(neighbor.getData())) {
                dfsRecursive(neighbor, visited);
            }
            current = current.next;
        }
    }

    /////////////EJERCICIO 1/////////////////////////
  // a) bfs(v): recorrido en anchura
    public void bfs(E data) {
        Vertex<E> start = listVertex.search(new Vertex<>(data));
        if (start == null) return;

        Queue<Vertex<E>> queue = new LinkedList<>();
        HashSet<E> visited = new HashSet<>();

        queue.add(start);
        visited.add(start.getData());

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.println(current.getData());

            ListLinked.Node<Edge<E>> adjNode = current.listAdj.getHead();
            while (adjNode != null) {
                Vertex<E> neighbor = adjNode.data.getRefDest();
                if (!visited.contains(neighbor.getData())) {
                    visited.add(neighbor.getData());
                    queue.add(neighbor);
                }
                adjNode = adjNode.next;
            }
        }
    }

    // b) bfsPath(v, z): camino entre v y z si existe
    public ArrayList<E> bfsPath(E origin, E destination) {
        ArrayList<E> path = new ArrayList<>();
        Vertex<E> start = listVertex.search(new Vertex<>(origin));
        Vertex<E> end = listVertex.search(new Vertex<>(destination));

        if (start == null || end == null) return path;
        Queue<Vertex<E>> queue = new LinkedList<>();
        HashMap<E, E> predecessor = new HashMap<>();
        HashSet<E> visited = new HashSet<>();

        queue.add(start);
        visited.add(start.getData());
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            Vertex<E> current = queue.poll();
            ListLinked.Node<Edge<E>> adjNode = current.listAdj.getHead();
            while (adjNode != null) {
                Vertex<E> neighbor = adjNode.data.getRefDest();
                E neighborData = neighbor.getData();

                if (!visited.contains(neighborData)) {
                    visited.add(neighborData);
                    predecessor.put(neighborData, current.getData());
                    queue.add(neighbor);
                    if (neighborData.equals(destination)) {
                        found = true;
                        break;
                    }
                }
                adjNode = adjNode.next;
            }
        }
            if (!found) return path;

        E step = destination;
        while (step != null) {
            path.add(0, step);
            step = predecessor.get(step);
        }

        return path;
    }



    @Override
    public String toString() {
        return this.listVertex.toString();
    }

     /////////////EJERCICIO 2/////////////////////////


     //A)
    public void insertEdgeWeight(E verOri, E verDes, int weight) {
        Vertex<E> vOri = listVertex.search(new Vertex<>(verOri));
        Vertex<E> vDes = listVertex.search(new Vertex<>(verDes));
        if (vOri != null && vDes != null) {
            if (vOri.listAdj.search(new Edge<>(vDes)) == null)
                vOri.listAdj.insertLast(new Edge<>(vDes, weight));
            if (vDes.listAdj.search(new Edge<>(vOri)) == null)
                vDes.listAdj.insertLast(new Edge<>(vOri, weight));
        }
    }
     //b)
    public ArrayList<E> shortPath(E origin, E destination) {
        return bfsPath(origin, destination);
    }
     //C)

    public boolean isConexo() {
        if (listVertex.isEmpty()) return true;

        HashSet<E> visited = new HashSet<>();
        Vertex<E> start = listVertex.getHead().data;
        dfsRecursive(start, visited);
        int totalVertices = 0;
        ListLinked.Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            totalVertices++;
            current = current.next;
        }

        return visited.size() == totalVertices;
    }
     //d)
    public Stack<E> Dijkstra(E origin, E destination) {
        Stack<E> path = new Stack<>();
        HashMap<E, Integer> distances = new HashMap<>();
        HashMap<E, E> predecessors = new HashMap<>();
        HashSet<E> visited = new HashSet<>();
        PriorityQueue<VertexDistance<E>> pq = new PriorityQueue<>();
        ListLinked.Node<Vertex<E>> node = listVertex.getHead();
        while (node != null) {
            E vertexData = node.data.getData();
            distances.put(vertexData, vertexData.equals(origin) ? 0 : Integer.MAX_VALUE);
            pq.add(new VertexDistance<>(node.data, distances.get(vertexData)));
            node = node.next;
        }
        while (!pq.isEmpty()) {
            VertexDistance<E> current = pq.poll();
            E currentData = current.vertex.getData();

            if (visited.contains(currentData)) continue;
            visited.add(currentData);

            ListLinked.Node<Edge<E>> adj = current.vertex.listAdj.getHead();
            while (adj != null) {
                Vertex<E> neighbor = adj.data.getRefDest();
                int weight = adj.data.getWeight();
                E neighborData = neighbor.getData();


                if (!visited.contains(neighborData)) {
                    int newDist = distances.get(currentData) + weight;
                    if (newDist < distances.get(neighborData)) {
                        distances.put(neighborData, newDist);
                        predecessors.put(neighborData, currentData);
                        pq.add(new VertexDistance<>(neighbor, newDist));
                    }
                }

                adj = adj.next;
            }
        }
        if (!predecessors.containsKey(destination)) return path;

        E step = destination;
        while (step != null) {
            path.push(step);
            step = predecessors.get(step);
        }

        return path;
    }

    private static class VertexDistance<E> implements Comparable<VertexDistance<E>> {
        Vertex<E> vertex;
        int distance;

        public VertexDistance(Vertex<E> vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        @Override
        public int compareTo(VertexDistance<E> other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
////////////////EJERCICIO 5/////////////////////////

    public int gradoNodo(E data) {
        Vertex<E> v = listVertex.search(new Vertex<>(data));
        if (v == null) return -1;
        int grado = 0;
        ListLinked.Node<Edge<E>> node = v.listAdj.getHead();
        while (node != null) {
            grado++;
            node = node.next;
        }
        return grado;
    }
    public boolean esCamino() {
        int grado1 = 0, grado2 = 0;
        ListLinked.Node<Vertex<E>> current = listVertex.getHead();

        while (current != null) {
            int grado = gradoNodo(current.data.getData());
            if (grado == 1) grado1++;
            else if (grado == 2) grado2++;
            else return false;
            current = current.next;
        }

        return grado1 == 2;
    }

    public boolean esCiclo() {
        if (!isConexo()) return false;

        ListLinked.Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            if (gradoNodo(current.data.getData()) != 2)
                return false;
            current = current.next;
        }

        return true;
    }

    public boolean esRueda() {
        int n = numVertices();
        int centro = 0, periferia = 0;

        ListLinked.Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            int g = gradoNodo(current.data.getData());
            if (g == n - 1) centro++;
            else if (g == 3) periferia++;
            else return false;
            current = current.next;
        }

        return centro == 1 && periferia == (n - 1);
    }

    public boolean esCompleto() {
        int n = numVertices();

        ListLinked.Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            if (gradoNodo(current.data.getData()) != n - 1)
                return false;
            current = current.next;
        }

        return true;
    }

    public int numVertices() {
        int count = 0;
        ListLinked.Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

}
