/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.action;

import route.graph.model.Graph;
import route.graph.model.Vertex;
import route.graph.model.Edge;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author Administrator
 */
public class Action {

    private Graph<String> graph;

    public Graph<String> getGraph() {
        return graph;
    }

    public void setGraph(Graph<String> graph) {
        this.graph = graph;
    }

    private int max = 65535;

    public Action(List<String> list) {
        initGraph(list);
        initEdges();
    }

    /**
     * to get the list of edges from text file
     *
     * @param txtPath
     * @return
     */
    private Vector<Edge<String>> readEdge(List<String> list) {
        Vector<Edge<String>> edgeList = null;
        if (CollectionUtils.isNotEmpty(list)) {
            edgeList = new Vector<Edge<String>>();         
            for (String subStr : list) {
                subStr = subStr.trim();
                if (subStr != null) {
                    Edge<String> edge = new Edge<String>();
                    int len = subStr.length();
                    Vertex<String> vertStart = new Vertex<String>(subStr.substring(len - 3, len - 2));
                    Vertex<String> vertEnd = new Vertex<String>(subStr.substring(len - 2, len - 1));
                    edge.setVertStart(vertStart);
                    edge.setVertEnd(vertEnd);
                    edge.setDistance(Integer.valueOf(subStr.substring(len - 1)));
                    edgeList.addElement(edge);
                }
            }

        }
        return edgeList;
    }

    /**
     * to get the vertex list from edge list, and the vertexes are in ascending
     * sequence sorted by the value of vertex
     *
     * @param list
     * @return
     */
    private Vector<String> getVertexTreeset(Vector<Edge<String>> list) {
        TreeSet<Vertex<String>> set = new TreeSet<Vertex<String>>();

        for (Edge<String> e : list) {
            Vertex<String> vertStart = e.getVertStart();
            set.add((Vertex<String>) vertStart);
            Vertex<String> vertEnd = e.getVertEnd();
            set.add((Vertex<String>) vertEnd);
        }
        //set the index in sequence for vertex
        Vector<String> vList = new Vector<String>();
        int i = 0;
        for (Vertex<String> v : set) {
            v.setIndex(i);
            vList.add(v.getValue());
            i++;
        }

        return vList;
    }

    /**
     * initiate the graph, including its edge list, vertex list and
     * two-dimension array edges mapping the vertexes and edges
     *
     * @param list
     */
    public void initGraph(List<String> list) {
        graph = new Graph<String>();
        graph.setEdgeList(readEdge(list));
        graph.setvList(getVertexTreeset(graph.getEdgeList()));
        int vNum = graph.getvList().size();
        graph.setvNum(vNum);
        graph.setEdgeNum(graph.getEdgeList().size());
        float[][] edges = new float[vNum][vNum];
        for (int i = 0; i < vNum; i++) {
            for (int j = 0; j < vNum; j++) {
                edges[i][j] = max;
            }
        }
        graph.setEdges(edges);
    }

    /**
     * set the edges[][] according to the edge list and vertex list
     */
    public void initEdges() {
        Vector<Edge<String>> edgeList = graph.getEdgeList();
        Vector<String> vList = graph.getvList();
        float[][] edges = graph.getEdges();
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
