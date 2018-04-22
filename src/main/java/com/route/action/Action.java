/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.route.action;

import com.route.model.*;
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
            for (String sub_s : list) {
                sub_s = sub_s.trim();
                if (sub_s != null) {
                    Edge<String> edge = new Edge<String>();
                    int len = sub_s.length();
                    Vertex<String> v_s = new Vertex<String>(sub_s.substring(len - 3, len - 2));
                    Vertex<String> v_e = new Vertex<String>(sub_s.substring(len - 2, len - 1));
                    edge.setV_s(v_s);
                    edge.setV_e(v_e);
                    edge.setDistance(Integer.valueOf(sub_s.substring(len - 1)));
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
            Vertex<String> v_s = e.getV_s();
            set.add((Vertex<String>) v_s);
            Vertex<String> v_e = e.getV_e();
            set.add((Vertex<String>) v_e);
        }
        //set the index in sequence for vertex
        Vector<String> v_list = new Vector<String>();
        int i = 0;
        for (Vertex<String> v : set) {
            v.setIndex(i);
            v_list.add(v.getValue());
            i++;
        }

        return v_list;
    }

    /**
     * initiate the graph, including its edge list, vertex list and
     * two-dimension array edges mapping the vertexes and edges
     *
     * @param txtPath
     */
    public void initGraph(List<String> list) {
        graph = new Graph<String>();
        graph.setEdgeList(readEdge(list));
        graph.setV_list(getVertexTreeset(graph.getEdgeList()));
        int v_num = graph.getV_list().size();
        graph.setV_num(v_num);
        graph.setEdge_num(graph.getEdgeList().size());
        float[][] edges = new float[v_num][v_num];
        for (int i = 0; i < v_num; i++) {
            for (int j = 0; j < v_num; j++) {
                edges[i][j] = max;
            }
        }
        graph.setEdges(edges);
    }

    /**
     * set the edges[][] according to the edge list and vertex list
     */
    public void initEdges() {
        Vector<Edge<String>> edge_list = graph.getEdgeList();
        Vector<String> v_list = graph.getV_list();
        float[][] edges = graph.getEdges();
        for (Edge e : edge_list) {
            Vertex v_s = e.getV_s();
            Vertex v_e = e.getV_e();
            int len = v_list.size();
            int start = -1, end = -1;
            start = v_list.indexOf(v_s.getValue());
            end = v_list.indexOf(v_e.getValue());
            if (start != -1 && end != -1) {
                edges[start][end] = e.getDistance();
            }

        }
    }

    /**
     * compute the distance between the given route
     *
     * @param indexs :the index of vertexes
     * @return
     */
    public float computeDistance(int[] indexs) {
        float[][] edges = graph.getEdges();
        float distance = 0;
        boolean not_exist = false;
        for (int i = 0; i < indexs.length - 1; i++) {
            if (indexs[i] != -1 && indexs[i + 1] != -1) {
                if (edges[indexs[i]][indexs[i + 1]] != max) {
                    distance += edges[indexs[i]][indexs[i + 1]];
                } else {
                    not_exist = true;
                }
            }

        }
        if (not_exist) {
            return -1;
        } else {
            return distance;
        }
    }

    /**
     * compute the shortest distance between two vertexes, according to Dijkstra
     *
     * @param vertex1
     * @param vertex2
     */
    public float shortestRoute(String vertex1, String vertex2) {
        Vector<String> v_list = graph.getV_list();
        int index1 = v_list.indexOf(vertex1);
        int index2 = v_list.indexOf(vertex2);
        if (index1 != -1 && index2 != -1) {
            int N = graph.getV_num();
            float[][] edges = graph.getEdges();

            //init
            int[] set = new int[N];// a set for storing processed vertex, the processed is 1, else is 0
            float[] d = new float[N];//the distance from start vertex
            int[] parent = new int[N];// the previous vertex index in the shortest route
            for (int i = 0; i < N; i++) {
                set[i] = 0;
                d[i] = edges[index1][i];
                parent[i] = 0;
            }
//				set[index1]=1;// if the start vertex is processed, it won't be calculated after
            parent[index1] = -1;

            //compute the shortest route, starting at original vertex to any vertex k, then add k to set
            for (int i = 1; i <= N; i++) {
                float min = max;
                int min_index = 0;//the minimum vertex
                for (int k = 0; k < N; k++) {
                    if (set[k] == 0 && d[k] < min) {
                        min = d[k];
                        min_index = k;
                    }
                }

                set[min_index] = 1;//added into set
                //if the current vertex is the destination, then break, else modify the distance to other unprocessed vertex
                if (min_index == index2) {
                    break;
                } else {
                    for (int k = 0; k < N; k++) {
                        if (set[k] == 0 && (d[min_index] + edges[min_index][k] < d[k])) {
                            d[k] = d[min_index] + edges[min_index][k];
                            parent[k] = min_index;
                        }
                    }
                }

            }

            return d[index2];
        } else {
            return -1;
        }

    }

    /**
     * compute the routes from srcVertex to destVertex regard the routes from
     * source vertex as a tree, then the number of nodes in each level h is the
     * number of routes as length of h if there is 3 stops, and 2 stops needs to
     * be calculate, due to the dest vertex is given the program only saves the
     * current level nodes and the last level nodes in 2 queues
     *
     * @param srcVertex
     * @param destVertex
     * @param level : stops number
     * @param total :if all 1-level of the routes length are concerned is true,
     * else only to compute the number of routes, length as level
     */
    public int routeNum(String srcVertex, String destVertex, int level, boolean total) {
        int num = 0;
        int N = graph.getV_num();
        Vector<String> v_list = graph.getV_list();
        float[][] edges = graph.getEdges();
        int src_index = v_list.indexOf(srcVertex);
        int dest_index = v_list.indexOf(destVertex);
        LinkedList<Integer> queue = new LinkedList<Integer>();
        if (src_index != -1 && dest_index != -1) {
            queue.add(src_index);
            for (int i = 1; i <= level; i++)//start at 1 stops
            {
                if (total) {
                    for (int row : queue) {
                        if (edges[row][dest_index] != max) {
                            num++;
                        }
                    }
                }
                if (!total && i == level) {
                    for (int row : queue) {
                        if (edges[row][dest_index] != max) {
                            num++;
                        }
                    }
                }
                if (i != level) {
                    LinkedList<Integer> queue_level = new LinkedList<Integer>();
                    for (int row : queue) {
                        for (int column = 0; column < N; column++) {
                            if (edges[row][column] != max) {
                                queue_level.addFirst(column);
                            }
                        }
                    }

                    queue.clear();
                    queue = queue_level;
                    queue_level = null;

                }

            }

            if (num > 0) {
                return num;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     *
     * @param srcVertex
     * @param destVertex
     * @param distance
     * @return
     */
    public int routeNumLimitedByDistance(String srcVertex, String destVertex, float distance) {
        int num = 0;
        int N = graph.getV_num();
        Vector<String> v_list = graph.getV_list();
        float[][] edges = graph.getEdges();
        int src_index = v_list.indexOf(srcVertex);
        int dest_index = v_list.indexOf(destVertex);
        LinkedList<Node> queue = null;
        if (distance > max) {
            return -1;
        }
        if (src_index != -1 && dest_index != -1) {
            queue = new LinkedList<Node>();
            queue.addFirst(new Node(src_index, 0));
            for (int i = 1;; i++) {
                for (Node node : queue) {
                    if ((node.getDistance() + edges[node.getIndex()][dest_index]) < distance) {
                        num++;
//							System.out.println(node.getIndex()+":"+node.getDistance());
                    }
                }
                LinkedList<Node> queue_level = new LinkedList<Node>();
                for (Node node : queue) {
                    for (int column = 0; column < N; column++) {
                        if ((node.getDistance() + edges[node.getIndex()][column]) < distance) {
                            queue_level.addFirst(new Node(column, node.getDistance() + edges[node.getIndex()][column]));
                        }
                    }
                }

                queue.clear();
                queue = queue_level;
                queue_level = null;
                if (queue.size() <= 0) {
                    break;
                }
            }

            if (num > 0) {
                return num;
            } else {
                return -1;
            }

        } else {
            return -1;
        }
    }

    class Node {

        int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public float getDistance() {
            return distance;
        }

        public void setDistance(float distance) {
            this.distance = distance;
        }

        float distance;

        public Node(int i, float d) {
            index = i;
            distance = d;
        }
    }
}
