/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.graph.algorithm;

import java.util.LinkedList;
import java.util.Vector;
import route.action.Action;
import route.graph.model.Graph;

/**
 *
 * @author Administrator
 */
public class RouteAlgorithm {
    private static final int MAX = 65535;
    
     /**
     * compute the shortest distance between two vertexes, according to Dijkstra
     *
     * @param vertex1
     * @param vertex2
     */
    public static float dijkstra(Graph graph,String vertex1, String vertex2) {
        Vector<String> vList = graph.getvList();
        int index1 = vList.indexOf(vertex1);
        int index2 = vList.indexOf(vertex2);
        if (index1 != -1 && index2 != -1) {
            final int N = graph.getvNum();
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
                float min = MAX;
                int minIndex = 0;//the minimum vertex
                for (int k = 0; k < N; k++) {
                    if (set[k] == 0 && d[k] < min) {
                        min = d[k];
                        minIndex = k;
                    }
                }

                set[minIndex] = 1;//added into set
                //if the current vertex is the destination, then break, else modify the distance to other unprocessed vertex
                if (minIndex == index2) {
                    break;
                } else {
                    for (int k = 0; k < N; k++) {
                        if (set[k] == 0 && (d[minIndex] + edges[minIndex][k] < d[k])) {
                            d[k] = d[minIndex] + edges[minIndex][k];
                            parent[k] = minIndex;
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
    public static int routesNum(Graph graph, String srcVertex, String destVertex, int level, boolean total) {
        int num = 0;
        int N = graph.getvNum();
        Vector<String> vList = graph.getvList();
        float[][] edges = graph.getEdges();
        int srcIndex = vList.indexOf(srcVertex);
        int destIndex = vList.indexOf(destVertex);
        LinkedList<Integer> queue = new LinkedList<Integer>();
        if (srcIndex != -1 && destIndex != -1) {
            queue.add(srcIndex);
            for (int i = 1; i <= level; i++)//start at 1 stops
            {
                if (total) {
                    for (int row : queue) {
                        if (edges[row][destIndex] != MAX) {
                            num++;
                        }
                    }
                }
                if (!total && i == level) {
                    for (int row : queue) {
                        if (edges[row][destIndex] != MAX) {
                            num++;
                        }
                    }
                }
                if (i != level) {
                    LinkedList<Integer> queueLevel = new LinkedList<Integer>();
                    for (int row : queue) {
                        for (int column = 0; column < N; column++) {
                            if (edges[row][column] != MAX) {
                                queueLevel.addFirst(column);
                            }
                        }
                    }

                    queue.clear();
                    queue = queueLevel;
                    queueLevel = null;

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
    public static int routesNumLimitedByDistance(Graph graph, String srcVertex, String destVertex, float distance) {
        int num = 0;
        int N = graph.getvNum();
        Vector<String> vList = graph.getvList();
        float[][] edges = graph.getEdges();
        int srcIndex = vList.indexOf(srcVertex);
        int destIndex = vList.indexOf(destVertex);
        LinkedList<Node> queue = null;
        if (distance > MAX) {
            return -1;
        }
        if (srcIndex != -1 && destIndex != -1) {
            queue = new LinkedList<Node>();
            queue.addFirst(new Node(srcIndex, 0));
            for (int i = 1;; i++) {
                for (Node node : queue) {
                    if ((node.getDistance() + edges[node.getIndex()][destIndex]) < distance) {
                        num++;
//							System.out.println(node.getIndex()+":"+node.getDistance());
                    }
                }
                LinkedList<Node> queueLevel = new LinkedList<Node>();
                for (Node node : queue) {
                    for (int column = 0; column < N; column++) {
                        if ((node.getDistance() + edges[node.getIndex()][column]) < distance) {
                            queueLevel.addFirst(new Node(column, node.getDistance() + edges[node.getIndex()][column]));
                        }
                    }
                }

                queue.clear();
                queue = queueLevel;
                queueLevel = null;
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
    
        /**
     * compute the distance between the given route
     *
     * @param indexs :the index of vertexes
     * @return
     */
    public static float computeDistance(Graph graph,int[] indexs) {
        float[][] edges = graph.getEdges();
        float distance = 0;
        boolean notExist = false;
        for (int i = 0; i < indexs.length - 1; i++) {
            if (indexs[i] != -1 && indexs[i + 1] != -1) {
                if (edges[indexs[i]][indexs[i + 1]] != MAX) {
                    distance += edges[indexs[i]][indexs[i + 1]];
                } else {
                    notExist = true;
                }
            }

        }
        if (notExist) {
            return -1;
        } else {
            return distance;
        }
    }
    
    static class Node {

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
