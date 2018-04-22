/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.caculation;

import route.action.Action;
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

/**
 *
 * @author Administrator
 */
public class Output {

    private Log logger = LogFactory.getLog(Output.class);
    private Action action;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Output(String txtPath) {
        List<String> list = readEdgeFromTxt(txtPath);
        if (CollectionUtils.isNotEmpty(list)) {
            action = new Action(list);
        }
    }

    private List<String> readEdgeFromTxt(String txtPath) {
        List<String> edgeList = null;
        if (txtPath != null) {            
            File file = new File(txtPath);
            if (file.exists()) {
                edgeList = new ArrayList<String>();
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                    String line = null;
                    try {
                        while ((line = br.readLine()) != null) {
                            String[] s = line.split(",");
                            for (String str : s) {
                                str = str.trim();
                                if (str != null) {
                                    edgeList.add(str);
                                }
                            }
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        logger.error("IOException", e);
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    logger.error("FileNotFoundException", e);
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    logger.error("UnsupportedEncodingException", e1);
                }
            } else {
                logger.info("file does not exist: " + txtPath);
            }
        }
        return edgeList;
    }

    //The distance of the route A-B-C.
    public float output1() {
        float distance = 0;
        if(this.action != null){
            Vector<String> vList = action.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexB = vList.indexOf("B");
            int indexC = vList.indexOf("C");
            int[] indexs = new int[]{indexA, indexB, indexC};
            distance = action.computeDistance(indexs);
            
        }
        return distance;
    }

    //The distance of the route A-D.
    public float output2() {
         float distance = 0;
        if(this.action != null){
            Vector<String> vList = action.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexD = vList.indexOf("D");           
            int[] indexs = new int[]{indexA, indexD};
            distance = action.computeDistance(indexs);
           
        }
        return distance;

    }

    //The distance of the route A-D-C.
    public float output3() {
         float distance = 0;
        if(this.action != null){
            Vector<String> vList = action.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexD = vList.indexOf("D");
            int indexC = vList.indexOf("C");           
            int[] indexs = new int[]{indexA, indexD, indexC};
            distance = action.computeDistance(indexs);
           
        }
        return distance;
    }

    //The distance of the route  A-E-B-C-D.
    public float output4() {
        float distance = 0;
        if(this.action != null){
            Vector<String> vList = action.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexE = vList.indexOf("E");
            int indexB = vList.indexOf("B");
            int indexC = vList.indexOf("C");
            int indexD = vList.indexOf("D");            
            int[] indexs = new int[]{indexA, indexE, indexB, indexC, indexD};
            distance = action.computeDistance(indexs);
           
        }
        return distance;
    }

    //The distance of the route A-E-D.
    public float output5() {
        float distance = 0;
        if(this.action != null){
            Vector<String> vList = action.getGraph().getvList();
            int indexA = vList.indexOf("A");
            int indexE = vList.indexOf("E");
            int indexD = vList.indexOf("D");            
            int[] indexs = new int[]{indexA, indexE, indexD};
            distance = action.computeDistance(indexs);
            
        }
        return distance;
    }

    //The number of trips starting at C and ending at C with a maximum of 3 stops.
    public int output6() {
        int num = 0;
        if(this.action != null){            
            num = action.routeNum("C", "C", 3, true);
            
        }
        return num;
    }

    //The number of trips starting at A and ending at C with exactly 4 stops.
    public int output7() {
        int num = 0;
        if(this.action != null){            
            num = action.routeNum("A", "C", 4, false);
            
        }
        return num;
    }

    //The length of the shortest route (in terms of distance to travel) from A to C.
    public float output8() {
        float distance = 0;
        if(this.action != null){            
            String v1 = "A";
            String v2 = "C";
            distance = action.shortestRoute(v1, v2);
            
        }
        return distance;
    }

    //The length of the shortest route (in terms of distance to travel) from B to B.
    public float output9() {
        float distance = 0;
        if(this.action != null){            
            String v1 = "B";
            String v2 = "B";
            distance = action.shortestRoute(v1, v2);
            
        }
         return distance;
    }

    //The number of different routes from C to C with a distance of less than 30.
    public int output10() {
        int num = 0;
        if(this.action != null){            
            num = action.routeNumLimitedByDistance("C", "C", 30);
            
        }
        return num;
    }
    
    private void print(int num, int distance){
        if (distance != -1) {
                System.out.println("Output #"+String.valueOf(num)+": " + String.valueOf(distance));
            } else {
                System.out.println("Output #"+String.valueOf(num)+": NO SUCH ROUTE");
            }
    }

    public static void main(String[] args) {
        String txtPath = null;
        System.out.println("Please input the path of the input text file!");
        //read the txt path from console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            txtPath = br.readLine();
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        File file = new File(txtPath);
        if (!file.exists()) {
            System.out.println(txtPath + " :this file doesn't exist!");
            return;
        }
        Output out = new Output(txtPath);
        float d = out.output1();
        out.print(1,(int)d);
        d = out.output2();
        out.print(2,(int)d);
        d = out.output3();
        out.print(3,(int)d);
        d = out.output4();
        out.print(4,(int)d);
        d = out.output5();
        out.print(5,(int)d);
        int num  = out.output6();
        out.print(6,num);
        num  = out.output7();
        out.print(7,num);
        d = out.output8();
        out.print(8,(int)d);
        d = out.output9();
        out.print(9,(int)d);
        num = out.output10();
        out.print(10,num);

    }

}
