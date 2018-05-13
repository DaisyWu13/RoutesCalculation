package graph.model;

import java.util.LinkedList;
import java.util.Vector;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author Daisy Wu
 */
public class Graph<V> extends AbstractGraph {

    public static final int MAX_DISTANCE = Integer.MAX_VALUE;
    //store all the edges
    private Vector<Edge<V>> edgeList;
    //store the key value of vertex
    private Vector<V> vList;

    public Graph() {
        this.setvNum(0);
    }
    
    public Graph(int size) {
        this.setEdges(new double[size][size]);
        this.setvNum(size);
    }

    public Vector<Edge<V>> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Vector<Edge<V>> edgeList) {
        this.edgeList = edgeList;
    }

    public Vector<V> getvList() {
        return vList;
    }

    public void setvList(Vector<V> vList) {
        this.vList = vList;
    }


    @Override
    public void initEdges() {
        Vector<Edge<V>> edgeList = this.edgeList;
        Vector<V> vList = this.vList;
        double[][] edges = this.getEdges();
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
            }
            for (int k = 0; k < N; k++) {
                if (set[k] == 0 && (d[minIndex] + edges[minIndex][k] < d[k])) {
                    d[k] = d[minIndex] + edges[minIndex][k];
                    parent[k] = minIndex;
                }
            }
        }

        return d[destIndex];
    }

    private int findMinIndex(int n, int[] set, double[] d) {
        int minIndex = 0;
        double min = MAX_DISTANCE;
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
                if (edges[indexs[i]][indexs[i + 1]] != MAX_DISTANCE) {
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
        int routeNum = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        if (srcIndex == -1 || destIndex == -1) {
            return -1;
        }
        queue.add(srcIndex);
        //start at 1 stops
        for (int i = 1; i <= level; i++) {
            if (total) {
                routeNum += findRouteCount(queue, destIndex);
            }
            if (!total && i == level) {
                routeNum += findRouteCount(queue, destIndex);
            }
            if (i != level) {
                LinkedList<Integer> queueLevel = getCurrentLevelQueue(queue);
                queue.clear();
                queue = queueLevel;
            }

        }

        if (routeNum > 0) {
            return routeNum;
        }
        return -1;
    }

    private int findRouteCount(LinkedList<Integer> queue, int destIndex) {
        int routeNum = 0;
        double[][] edges = this.getEdges();
        for (int row : queue) {
            if (edges[row][destIndex] == MAX_DISTANCE) {
                continue;
            }
            routeNum++;
        }
        return routeNum;
    }

    private LinkedList<Integer> getCurrentLevelQueue(LinkedList<Integer> queue) {
        LinkedList<Integer> queueLevel = new LinkedList<>();
        double[][] edges = this.getEdges();
        int verNum = this.getvNum();
        for (int row : queue) {
            for (int column = 0; column < verNum; column++) {
                if (edges[row][column] == MAX_DISTANCE) {
                    continue;
                }
                queueLevel.addFirst(column);
            }
        }
        return queueLevel;
    }

    @Override
    public int routesNumLimitedByDistance(int srcIndex, int destIndex, double distance) {
        int routeNum = 0;
        LinkedList<Node> queue = null;
        if (distance > MAX_DISTANCE || srcIndex == -1 || destIndex == -1) {
            return -1;
        }

        queue = new LinkedList<>();
        queue.addFirst(new Node(srcIndex, 0));
        for (int i = 1;; i++) {
            routeNum += findLimitedRouteCount(queue, destIndex, distance);
            LinkedList<Node> queueLevel = getLimitedCurrentLevelQueue(queue, distance);
            queue.clear();
            queue = queueLevel;
            if (CollectionUtils.isEmpty(queue)) {
                break;
            }
        }

        if (routeNum > 0) {
            return routeNum;
        }
        return -1;
    }

    private int findLimitedRouteCount(LinkedList<Node> queue, int destIndex, double distance) {
        int routeNum = 0;
        double[][] edges = this.getEdges();
        for (Node node : queue) {
            if ((node.getDistance() + edges[node.getIndex()][destIndex]) >= distance) {
                continue;
            }
            routeNum++;
        }
        return routeNum;
    }

    private LinkedList<Node> getLimitedCurrentLevelQueue(LinkedList<Node> queue, double distance) {
        LinkedList<Node> queueLevel = new LinkedList<>();
        double[][] edges = this.getEdges();
        int verNum = this.getvNum();
        for (Node node : queue) {
            for (int column = 0; column < verNum; column++) {
                if ((node.getDistance() + edges[node.getIndex()][column]) >= distance) {
                    continue;
                }
                queueLevel.addFirst(new Node(column, node.getDistance() + edges[node.getIndex()][column]));
            }
        }
        return queueLevel;
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
