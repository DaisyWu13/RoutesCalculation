package graph.model;

import graph.model.Edge;
import java.util.TreeSet;
import java.util.Vector;

/**
 *
 * @author Daisy Wu
 */
public class Graph<V> {

    private Vector<Edge<V>> edgeList;//store all the edges
    private Vector<String> vList;//store the key value of vertex
    private double[][] edges;//store the distance between vertex
    private int vNum;//the number of vertexes
    private int edgeNum;//the number of edges

    public int getEdgeNum() {
        return edgeNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }

    public Vector<Edge<V>> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Vector<Edge<V>> edgeList) {
        this.edgeList = edgeList;
    }

    public double[][] getEdges() {
        return edges;
    }

    public void setEdges(double[][] edges) {
        this.edges = edges;
    }

    public int getvNum() {
        return vNum;
    }

    public void setvNum(int vNum) {
        this.vNum = vNum;
    }

    public Vector<String> getvList() {
        return vList;
    }

    public void setvList(Vector<String> vList) {
        this.vList = vList;
    }

    public Graph(int size) {
        this.edges = new double[size][size];
        this.vNum = size;
    }

    public Graph() {
        // TODO Auto-generated constructor stub
    }

    /**
     * to get the vertex list from edge list, and the vertexes are in ascending
     * sequence sorted by the value of vertex
     *
     * @param list
     * @return
     */
    public static Vector<? extends Object> getVertexTreeset(Vector<Edge<? extends Object>> list) {
        TreeSet<Vertex<Object>> set = new TreeSet<Vertex<Object>>();
        for (Edge<? extends Object> e : list) {
            Vertex<? extends Object> vertStart = e.getVertStart();
            set.add((Vertex<Object>) vertStart);
            Vertex<? extends Object> vertEnd = e.getVertEnd();
            set.add((Vertex<Object>) vertEnd);
        }
        //set the index in sequence for vertex
        Vector<Object> vList = new Vector<Object>();
        int i = 0;
        for (Vertex<? extends Object> v : set) {
            v.setIndex(i);
            vList.add(v.getValue());
            i++;
        }

        return vList;
    }

    /**
     * set the edges[][] according to the edge list and vertex list
     */
    public static void initEdges(Graph graph) {
        Vector<Edge<Object>> edgeList = graph.getEdgeList();
        Vector<Object> vList = graph.getvList();
        double[][] edges = graph.getEdges();
        for (Edge e : edgeList) {
            Vertex vertStart = e.getVertStart();
            Vertex vertEnd = e.getVertEnd();
            int len = vList.size();
            int start = -1, end = -1;
            start = vList.indexOf(vertStart.getValue());
            end = vList.indexOf(vertEnd.getValue());
            if (start != -1 && end != -1) {
                edges[start][end] = e.getDistance();
            }

        }
    }
}
