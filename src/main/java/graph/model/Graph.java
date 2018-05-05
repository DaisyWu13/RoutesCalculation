package graph.model;

import java.util.LinkedList;
import java.util.Vector;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author Daisy Wu
 */
public class Graph<V> implements IGraph {

    private static final int MAX = Integer.MAX_VALUE;

    private Vector<Edge<V>> edgeList;//store all the edges
    private Vector<V> vList;//store the key value of vertex
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

    public Vector<V> getvList() {
        return vList;
    }

    public void setvList(Vector<V> vList) {
        this.vList = vList;
    }

    public Graph(int size) {
        this.edges = new double[size][size];
        this.vNum = size;
    }

    public Graph() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initEdges() {
        Vector<Edge<V>> edgeList = this.edgeList;
        Vector<V> vList = this.vList;
        double[][] edges = this.edges;
        if (CollectionUtils.isEmpty(edgeList) || CollectionUtils.isEmpty(vList)) {
            return;
        }
        for (Edge e : edgeList) {
            Vertex vertStart = e.getVertStart();
            Vertex vertEnd = e.getVertEnd();
            int start = -1, end = -1;
            start = vList.indexOf(vertStart.getValue());
            end = vList.indexOf(vertEnd.getValue());
            if (start != -1 && end != -1) {
                edges[start][end] = e.getDistance();
            }

        }
    }

    @Override
    public double dijkstra(int srcIndex, int destIndex) {
        final int N = this.getvNum();
        double[][] edges = this.getEdges();
        //init
        // a set for storing processed vertex, the processed is 1, else is 0
        int[] set = new int[N];
        //the distance from start vertex
        double[] d = new double[N];
        // the previous vertex index in the shortest route
        int[] parent = new int[N];
        for (int i = 0; i < N; i++) {
            set[i] = 0;
            d[i] = edges[srcIndex][i];
            parent[i] = 0;
        }
        //set[srcIndex]=1;
        // if the start vertex is processed, it won't be calculated after
        parent[srcIndex] = -1;

        //compute the shortest route, starting at original vertex to any vertex k, then add k to set
        for (int i = 1; i <= N; i++) {
            //the minimum vertex
            int minIndex = findMinIndex(N, set, d);
            //added into set
            set[minIndex] = 1;
            //if the current vertex is the destination, then break, else modify the distance to other unprocessed vertex
            if (minIndex == destIndex) {
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

        return d[destIndex];
    }

    private static int findMinIndex(int n, int[] set, double[] d) {
        int minIndex = 0;
        double min = MAX;
        for (int k = 0; k < n; k++) {
            if (set[k] == 0 && d[k] < min) {
                min = d[k];
                minIndex = k;
            }
        }
        return minIndex;
    }

    @Override
    public double computeDistance(int[] indexs) {
        double[][] edges = this.getEdges();
        double distance = 0;
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
        }
        return distance;
    }

    @Override
    public int routesNum(int srcIndex, int destIndex, int level, boolean total) {
        int num = 0;
        int N = this.getvNum();
        double[][] edges = this.getEdges();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        if (srcIndex == -1 || destIndex == -1) {
            return -1;
        }

        queue.add(srcIndex);
        //start at 1 stops
        for (int i = 1; i <= level; i++) {
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
        }
        return -1;
    }

    @Override
    public int routesNumLimitedByDistance(int srcIndex, int destIndex, double distance) {
        int num = 0;
        int N = this.getvNum();
        double[][] edges = this.getEdges();
        LinkedList<Node> queue = null;
        if (distance > MAX || srcIndex == -1 || destIndex == -1) {
            return -1;
        }

        queue = new LinkedList<Node>();
        queue.addFirst(new Node(srcIndex, 0));
        for (int i = 1;; i++) {
            for (Node node : queue) {
                if ((node.getDistance() + edges[node.getIndex()][destIndex]) < distance) {
                    num++;
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
        }
        return -1;
    }

    class Node {

        int index;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        double distance;

        public Node(int i, double d) {
            index = i;
            distance = d;
        }
    }

}
