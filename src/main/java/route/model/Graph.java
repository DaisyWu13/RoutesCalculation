/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.model;

import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class Graph<V> {

    private Vector<Edge<V>> edgeList;//store all the edges
    private Vector<String> vList;//store the key value of vertex
    private float[][] edges;//store the distance between vertex
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

    public float[][] getEdges() {
        return edges;
    }

    public void setEdges(float[][] edges) {
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
        this.edges = new float[size][size];
        this.vNum = size;
    }

    public Graph() {
        // TODO Auto-generated constructor stub
    }
}
