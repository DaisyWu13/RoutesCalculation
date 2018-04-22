/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.route.model;

import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class Graph<V> {

    private Vector<Edge<V>> edgeList;//store all the edges
    private Vector<String> v_list;//store the key value of vertex
    private float[][] edges;//store the distance between vertex
    private int v_num;//the number of vertexes
    private int edge_num;//the number of edges

    public int getEdge_num() {
        return edge_num;
    }

    public void setEdge_num(int edge_num) {
        this.edge_num = edge_num;
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

    public int getV_num() {
        return v_num;
    }

    public void setV_num(int v_num) {
        this.v_num = v_num;
    }

    public Vector<String> getV_list() {
        return v_list;
    }

    public void setV_list(Vector<String> v_list) {
        this.v_list = v_list;
    }

    public Graph(int size) {
        this.edges = new float[size][size];
        this.v_num = size;
    }

    public Graph() {
        // TODO Auto-generated constructor stub
    }
}
