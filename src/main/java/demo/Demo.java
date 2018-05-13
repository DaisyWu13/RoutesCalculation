package demo;

import graph.action.*;
import route.RouteOfString;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import graph.invoker.GraphInvoker;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Daisy Wu
 */
public class Demo {

    private static final String ROUTEPATTERN = "^[A-Z]{2}[0-9]{1,9}";
    
    private RouteOfString route;

    public RouteOfString getRoute() {
        return route;
    }

    public void setRoute(RouteOfString route) {
        this.route = route;
    }

    public Demo(String txtPath) throws IOException {
        try {
            List<String> list = readEdgeFromTxt(txtPath);
            if (CollectionUtils.isNotEmpty(list)) {
                route = new RouteOfString(list);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    private List<String> readEdgeFromTxt(String txtPath) throws IOException {
        List<String> edgeList = new ArrayList<>();
        File file = new File(txtPath);
        if (null == txtPath || !file.exists()) {
            return edgeList;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                edgeList.addAll(extractEdgeFromLine(line));
            }
        } catch (IOException e) {
            throw e;
        }
        return edgeList;
    }

    private List<String> extractEdgeFromLine(String line) {
        List<String> edgeList = new ArrayList<>();
        String[] strArray = line.split(",");
        for (String str : strArray) {
            String edgeStr = findEdgeString(str);
            boolean isLegal = isLegalEdgeStr(edgeStr);
            if (!isLegal) {
                break;
            }
            edgeList.add(edgeStr);
        }
        return edgeList;
    }

    private String findEdgeString(String edgeStr) {
        int index = 0;
        edgeStr = edgeStr.trim();
        if (StringUtils.isBlank(edgeStr)) {
            return null;
        }
        int len = edgeStr.length();
        //record blank string
        while (index < len) {
            char c = edgeStr.charAt(index);
            if (c >= 'A' && c <= 'Z') {
                break;
            }
            index++;
        }
        if (index >= len) {
            return null;
        }
        return edgeStr.substring(index);
    }

    private boolean isLegalEdgeStr(String edgeStr) {
        boolean isLegal = false;
        if (StringUtils.isBlank(edgeStr)) {
            return isLegal;
        }
        Pattern pattern = Pattern.compile(ROUTEPATTERN);
        Matcher match = pattern.matcher(edgeStr);
        if (match.matches()) {
            isLegal = true;
        }
        return isLegal;
    }

    /**
     *
     * @return The distance of the route A-B-C.
     */
    public double output1() {
        double distance = 0;
        if (this.route != null && this.route.getGraph() != null) {
            Vector<String> vList = route.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexB = vList.indexOf("B");
            int indexC = vList.indexOf("C");
            int[] indexs = new int[]{indexA, indexB, indexC};
            ComputeDistanceAction action = new ComputeDistanceAction(this.route.getGraph(), indexs);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            distance = action.getResult();
        }
        return distance;
    }

    /**
     *
     * @return The distance of the route A-D
     */
    public double output2() {
        double distance = 0;
        if (this.route != null && this.route.getGraph() != null) {
            Vector<String> vList = route.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexD = vList.indexOf("D");
            int[] indexs = new int[]{indexA, indexD};
            ComputeDistanceAction action = new ComputeDistanceAction(this.route.getGraph(), indexs);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            distance = action.getResult();
        }
        return distance;

    }

    /**
     *
     * @return The distance of the route A-D-C
     */
    public double output3() {
        double distance = 0;
        if (this.route != null && this.route.getGraph() != null) {
            Vector<String> vList = route.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexD = vList.indexOf("D");
            int indexC = vList.indexOf("C");
            int[] indexs = new int[]{indexA, indexD, indexC};
            ComputeDistanceAction action = new ComputeDistanceAction(this.route.getGraph(), indexs);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            distance = action.getResult();
        }
        return distance;
    }

    /**
     *
     * @return The distance of the route A-E-B-C-D
     */
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
            ComputeDistanceAction action = new ComputeDistanceAction(this.route.getGraph(), indexs);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            distance = action.getResult();
        }
        return distance;
    }

    /**
     *
     * @return The distance of the route A-E-D
     */
    public double output5() {
        double distance = 0;
        if (this.route != null && this.route.getGraph() != null) {
            Vector<String> vList = route.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexE = vList.indexOf("E");
            int indexD = vList.indexOf("D");
            int[] indexs = new int[]{indexA, indexE, indexD};
            ComputeDistanceAction action = new ComputeDistanceAction(this.route.getGraph(), indexs);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            distance = action.getResult();
        }
        return distance;
    }

    /**
     *
     * @return The number of trips starting at C and ending at C with a maximum
     * of 3 stops
     */
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
            RoutesNumAction action = new RoutesNumAction(this.route.getGraph(), srcIndex, destIndex, 3, true);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            num = action.getResult();
        }
        return num;
    }

    /**
     *
     * @return The number of trips starting at A and ending at C with exactly 4
     * stops
     */
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
            RoutesNumAction action = new RoutesNumAction(this.route.getGraph(), srcIndex, destIndex, 4, false);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            num = action.getResult();
        }
        return num;
    }

    /**
     *
     * @return The length of the shortest route (in terms of distance to travel)
     * from A to C
     */
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
            DijkstraAction action = new DijkstraAction(this.route.getGraph(), srcIndex, destIndex);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            distance = action.getResult();
        }
        return distance;
    }

    /**
     *
     * @return The length of the shortest route (in terms of distance to travel)
     * from B to B
     */
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
            DijkstraAction action = new DijkstraAction(this.route.getGraph(), srcIndex, destIndex);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            distance = action.getResult();
        }
        return distance;
    }

    /**
     *
     * @return The number of different routes from C to C with a distance of
     * less than 30
     */
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
            RoutesNumLimitedAction action = new RoutesNumLimitedAction(this.route.getGraph(), srcIndex, destIndex, 30);
            GraphInvoker invoker = new GraphInvoker(action);
            invoker.runAction();
            num = action.getResult();
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
