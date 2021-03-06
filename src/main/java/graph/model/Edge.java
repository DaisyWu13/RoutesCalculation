package graph.model;

/**
 *
 * @author Daisy Wu
 */
public class Edge<V> {

    private Vertex<V> vertStart;//start point
    private Vertex<V> vertEnd;//end point
    private double distance;

    public Vertex<V> getVertStart() {
        return vertStart;
    }

    public void setVertStart(Vertex<V> vertStart) {
        this.vertStart = vertStart;
    }

    public Vertex<V> getVertEnd() {
        return vertEnd;
    }

    public void setVertEnd(Vertex<V> vertEnd) {
        this.vertEnd = vertEnd;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int compare(Edge<V> e) {
        int flag;
        if (this.vertStart.getValue().equals(e.getVertStart().getValue()) && this.vertEnd.getValue().equals(e.getVertEnd().getValue())) {
            flag = 0;
        } else {
            flag = 1;
        }
        return flag;
    }
}
