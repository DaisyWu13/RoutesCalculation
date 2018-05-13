package graph.model;

/**
 *
 * @author Daisy Wu
 */
public abstract class AbstractGraph {

    private double[][] edges;//store the distance between vertex
    private int vNum;//the number of vertexes
    private int edgeNum;//the number of edges

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

    public int getEdgeNum() {
        return edgeNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }

    /**
     * set the edges[][] according to the edge list and vertex list
     */
    public abstract void initEdges();

    /**
     * compute the shortest distance between two vertexes, according to Dijkstra
     *
     * @param srcIndex
     * @param destIndex
     * @return
     */
    public abstract double dijkstra(int srcIndex, int destIndex);

    /**
     * compute the distance between the given route
     *
     * @param indexs
     * @return
     */
    public abstract double computeDistance(int[] indexs);

    /**
     * compute the routes from srcVertex to destVertex regard the routes from
     * source vertex as a tree, then the number of nodes in each level h is the
     * number of routes as length of h if there is 3 stops, and 2 stops needs to
     * be calculate, due to the dest vertex is given the program only saves the
     * current level nodes and the last level nodes in 2 queues
     *
     * @param srcIndex
     * @param destIndex
     * @param level
     * @param total
     * @return
     */
    public abstract int routesNum(int srcIndex, int destIndex, int level, boolean total);

    /**
     *
     * @param srcIndex
     * @param destIndex
     * @param distance
     * @return
     */
    public abstract int routesNumLimitedByDistance(int srcIndex, int destIndex, double distance);
}
