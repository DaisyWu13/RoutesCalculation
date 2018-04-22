/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.route.model;

/**
 *
 * @author Administrator
 */
public class Edge<V> {

    private Vertex<V> v_s;//起点
    private Vertex<V> v_e;//终点
    private float distance;//距离

    public Vertex<V> getV_s() {
        return v_s;
    }

    public void setV_s(Vertex<V> v_s) {
        this.v_s = v_s;
    }

    public Vertex<V> getV_e() {
        return v_e;
    }

    public void setV_e(Vertex<V> v_e) {
        this.v_e = v_e;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int compare(Edge<V> e) {
        int flag;
        if (this.v_s.getValue().equals(e.getV_s().getValue()) && this.v_e.getValue().equals(e.getV_e().getValue())) {
            flag = 0;
        } else {
            flag = 1;
        }
        return flag;
    }
}
