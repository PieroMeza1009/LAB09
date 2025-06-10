package graph;


public class ListLinked<T> {
    private Node<T> head;

    public ListLinked() {
        head = null;
    }

    public void insertLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> current = head;
        while (current.next != null)
            current = current.next;
        current.next = newNode;
    }

    public void remove(T data) {
        if (head == null) return;
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node<T> prev = head;
        Node<T> curr = head.next;
        while (curr != null) {
            if (curr.data.equals(data)) {
                prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public T search(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data))
                return current.data;
            current = current.next;
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data.toString());
            current = current.next;
        }
        return sb.toString();
    }

    // Clase interna
    public static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}