package graph.model;

/**
 *
 * @author Daisy Wu
 */
public class Vertex<V> implements Comparable<Object> {

    private int index;//the number to search
    private V value; //the content of vertex

    public Vertex() {

    }

    public Vertex(V v) {
        this.value = v;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        Vertex v = (Vertex) o;
        String s1 = this.value.toString();
        String s2 = v.getValue().toString();
        int ret = s1.compareTo(s2);
        return ret;
    }
}
