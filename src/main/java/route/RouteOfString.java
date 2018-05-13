package route;

import graph.model.*;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import org.apache.commons.collections.CollectionUtils;

/**
 *
 * @author Daisy Wu
 */
public class RouteOfString {

    public static final int MAX_DISTANCE = Integer.MAX_VALUE;
    private Graph<String> graph;

    public Graph<String> getGraph() {
        return graph;
    }

    public void setGraph(Graph<String> graph) {
        this.graph = graph;
    }

    public RouteOfString(List<String> list) {
        this.graph = CreateGraph(list);
        this.graph.initEdges();
    }

    /**
     * to get the list of edges from text file
     *
     * @param txtPath
     * @return
     */
    private Vector<Edge<String>> readEdge(List<String> list) {
        Vector<Edge<String>> edgeList = new Vector<>();
        if (CollectionUtils.isEmpty(list)) {
            return edgeList;
        }
        for (String edgeStr : list) {
            Edge<String> edge = new Edge<String>();
            int len = edgeStr.length();
            int index = 0;

            Vertex<String> vertStart = new Vertex<>(edgeStr.substring(index, ++index));
            Vertex<String> vertEnd = new Vertex<>(edgeStr.substring(index, ++index));
            edge.setVertStart(vertStart);
            edge.setVertEnd(vertEnd);
            edge.setDistance(Integer.valueOf(edgeStr.substring(index, len)));
            edgeList.addElement(edge);
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
        TreeSet<Vertex<String>> set = new TreeSet<>();

        for (Edge<String> e : list) {
            Vertex<String> vertStart = e.getVertStart();
            set.add((Vertex<String>) vertStart);
            Vertex<String> vertEnd = e.getVertEnd();
            set.add((Vertex<String>) vertEnd);
        }
        //set the index in sequence for vertex
        Vector<String> vList = new Vector<>();
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
    public Graph<String> CreateGraph(List<String> list) {
        Graph<String> graph = new Graph<>();
        graph.setEdgeList(readEdge(list));
        graph.setvList(getVertexTreeset(graph.getEdgeList()));
        int vNum = graph.getvList().size();
        graph.setvNum(vNum);
        graph.setEdgeNum(graph.getEdgeList().size());
        double[][] edges = new double[vNum][vNum];
        for (int i = 0; i < vNum; i++) {
            for (int j = 0; j < vNum; j++) {
                edges[i][j] = MAX_DISTANCE;
            }
        }
        graph.setEdges(edges);
        return graph;
    }

}
