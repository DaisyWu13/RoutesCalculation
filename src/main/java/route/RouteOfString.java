package route;

import graph.model.Graph;
import graph.model.Vertex;
import graph.model.Edge;
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
 * @author Daisy Wu
 */
public class RouteOfString {

    private Graph<String> graph;

    public Graph<String> getGraph() {
        return graph;
    }

    public void setGraph(Graph<String> graph) {
        this.graph = graph;
    }

    private static final int MAX = Integer.MAX_VALUE;

    public RouteOfString(List<String> list) {
        this.graph = CreateGraph(list);
        Graph.initEdges(this.graph);
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
    public Graph CreateGraph(List<String> list) {
        Graph graph = new Graph<String>();
        graph.setEdgeList(readEdge(list));
        graph.setvList(getVertexTreeset(graph.getEdgeList()));
        int vNum = graph.getvList().size();
        graph.setvNum(vNum);
        graph.setEdgeNum(graph.getEdgeList().size());
        double[][] edges = new double[vNum][vNum];
        for (int i = 0; i < vNum; i++) {
            for (int j = 0; j < vNum; j++) {
                edges[i][j] = MAX;
            }
        }
        graph.setEdges(edges);
        return graph;
    }


}
