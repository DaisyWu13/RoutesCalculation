/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.graph.model;

/**
 *
 * @author Administrator
 */
public class Edge<V> {

    private Vertex<V> vertStart;//start point
    private Vertex<V> vertEnd;//end point
    private float distance;

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

    

   

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
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
