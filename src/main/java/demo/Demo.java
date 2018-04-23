package demo;

import route.RouteOfString;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import graph.algorithm.GraphAlgorithm;

/**
 *
 * @author Daisy Wu
 */
public class Demo {

    private Log logger = LogFactory.getLog(Demo.class);
    private RouteOfString route;

    public RouteOfString getRoute() {
        return route;
    }

    public void setRoute(RouteOfString route) {
        this.route = route;
    }

    public Demo(String txtPath) {
        try {
            List<String> list = readEdgeFromTxt(txtPath);
            if (CollectionUtils.isNotEmpty(list)) {
                route = new RouteOfString(list);
            }
        } catch (Exception e) {
            logger.error("construct error", e);
        }
    }

    private List<String> readEdgeFromTxt(String txtPath) throws Exception {
        List<String> edgeList = null;
        if (txtPath != null) {
            File file = new File(txtPath);
            if (file.exists()) {
                edgeList = new ArrayList<String>();
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                    String line = null;

                    while ((line = br.readLine()) != null) {
                        String[] s = line.split(",");
                        for (String str : s) {
                            str = str.trim();
                            if (str != null) {
                                edgeList.add(str);
                            }
                        }
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    throw e;
                }
            } else {
                logger.info("file does not exist: " + txtPath);
            }
        }
        return edgeList;
    }

    //The distance of the route A-B-C.
    public double output1() {
        double distance = 0;
        if (this.route != null && this.route.getGraph() != null) {
            Vector<String> vList = route.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexB = vList.indexOf("B");
            int indexC = vList.indexOf("C");
            int[] indexs = new int[]{indexA, indexB, indexC};
            distance = GraphAlgorithm.computeDistance(this.route.getGraph(), indexs);

        }
        return distance;
    }

    //The distance of the route A-D.
    public double output2() {
        double distance = 0;
        if (this.route != null && this.route.getGraph() != null) {
            Vector<String> vList = route.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexD = vList.indexOf("D");
            int[] indexs = new int[]{indexA, indexD};
            distance = GraphAlgorithm.computeDistance(this.route.getGraph(), indexs);

        }
        return distance;

    }

    //The distance of the route A-D-C.
    public double output3() {
        double distance = 0;
        if (this.route != null && this.route.getGraph() != null) {
            Vector<String> vList = route.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexD = vList.indexOf("D");
            int indexC = vList.indexOf("C");
            int[] indexs = new int[]{indexA, indexD, indexC};
            distance = GraphAlgorithm.computeDistance(this.route.getGraph(), indexs);

        }
        return distance;
    }

    //The distance of the route  A-E-B-C-D.
    public double output4() {
        double distance = 0;
        if (this.route != null && this.route.getGraph() != null) {
            Vector<String> vList = route.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexE = vList.indexOf("E");
            int indexB = vList.indexOf("B");
            int indexC = vList.indexOf("C");
            int indexD = vList.indexOf("D");
            int[] indexs = new int[]{indexA, indexE, indexB, indexC, indexD};
            distance = GraphAlgorithm.computeDistance(this.route.getGraph(), indexs);

        }
        return distance;
    }

    //The distance of the route A-E-D.
    public double output5() {
        double distance = 0;
        if (this.route != null && this.route.getGraph() != null) {
            Vector<String> vList = route.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexE = vList.indexOf("E");
            int indexD = vList.indexOf("D");
            int[] indexs = new int[]{indexA, indexE, indexD};
            distance = GraphAlgorithm.computeDistance(this.route.getGraph(), indexs);

        }
        return distance;
    }

    //The number of trips starting at C and ending at C with a maximum of 3 stops.
    public int output6() {
        int num = 0;
        if (this.route != null && this.route.getGraph() != null) {
            String v1 = "C";
            String v2 = "C";
            Vector<String> vList = this.route.getGraph().getvList();
            int srcIndex = vList.indexOf(v1);
            int destIndex = vList.indexOf(v2);
            if (srcIndex == -1 || destIndex == -1) {
                return -1;
            }
            num = GraphAlgorithm.routesNum(route.getGraph(), srcIndex, destIndex, 3, true);

        }
        return num;
    }

    //The number of trips starting at A and ending at C with exactly 4 stops.
    public int output7() {
        int num = 0;
        if (this.route != null && this.route.getGraph() != null) {
            String v1 = "A";
            String v2 = "C";
            Vector<String> vList = this.route.getGraph().getvList();
            int srcIndex = vList.indexOf(v1);
            int destIndex = vList.indexOf(v2);
            if (srcIndex == -1 || destIndex == -1) {
                return -1;
            }
            num = GraphAlgorithm.routesNum(route.getGraph(), srcIndex, destIndex, 4, false);

        }
        return num;
    }

    //The length of the shortest route (in terms of distance to travel) from A to C.
    public double output8() {
        double distance = 0;
        if (this.route != null) {
            String v1 = "A";
            String v2 = "C";
            Vector<String> vList = this.route.getGraph().getvList();
            int srcIndex = vList.indexOf(v1);
            int destIndex = vList.indexOf(v2);
            if (srcIndex == -1 || destIndex == -1) {
                return -1;
            }
            distance = GraphAlgorithm.dijkstra(this.route.getGraph(), srcIndex, destIndex);

        }
        return distance;
    }

    //The length of the shortest route (in terms of distance to travel) from B to B.
    public double output9() {
        double distance = 0;
        if (this.route != null) {
            String v1 = "B";
            String v2 = "B";
            Vector<String> vList = this.route.getGraph().getvList();
            int srcIndex = vList.indexOf(v1);
            int destIndex = vList.indexOf(v2);
            if (srcIndex == -1 || destIndex == -1) {
                return -1;
            }
            distance = GraphAlgorithm.dijkstra(this.route.getGraph(), srcIndex, destIndex);

        }
        return distance;
    }

    //The number of different routes from C to C with a distance of less than 30.
    public int output10() {
        int num = 0;
        if (this.route != null && this.route.getGraph() != null) {
            String v1 = "C";
            String v2 = "C";
            Vector<String> vList = this.route.getGraph().getvList();
            int srcIndex = vList.indexOf(v1);
            int destIndex = vList.indexOf(v2);
            if (srcIndex == -1 || destIndex == -1) {
                return -1;
            }
            num = GraphAlgorithm.routesNumLimitedByDistance(route.getGraph(), srcIndex, destIndex, 30);

        }
        return num;
    }

    public void print(int num, int distance) {
        if (distance != -1) {
            System.out.println("Output #" + String.valueOf(num) + ": " + String.valueOf(distance));
        } else {
            System.out.println("Output #" + String.valueOf(num) + ": NO SUCH ROUTE");
        }
    }

}
