package graph;

public class VertexObj<V, E> {
    protected V info;
    protected int position;

    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }
    public V getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VertexObj<?, ?>) {
            VertexObj<?, ?> v = (VertexObj<?, ?>) o;
            return info.equals(v.info);
        }
        return false;
    }

}
