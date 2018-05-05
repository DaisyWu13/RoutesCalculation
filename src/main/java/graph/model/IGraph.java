package graph.model;

/**
 *
 * @author Daisy Wu
 */
public interface IGraph {

    /**
     * set the edges[][] according to the edge list and vertex list
     */
    void initEdges();

    /**
     * compute the shortest distance between two vertexes, according to Dijkstra
     *
     * @param srcIndex
     * @param destIndex
     * @return
     */
    double dijkstra(int srcIndex, int destIndex);

    /**
     * compute the distance between the given route
     *
     * @param indexs
     * @return
     */
    double computeDistance(int[] indexs);

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
    int routesNum(int srcIndex, int destIndex, int level, boolean total);

    /**
     *
     * @param srcIndex
     * @param destIndex
     * @param distance
     * @return
     */
    int routesNumLimitedByDistance(int srcIndex, int destIndex, double distance);
}
