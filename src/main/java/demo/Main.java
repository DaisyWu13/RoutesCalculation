package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Daisy Wu
 */
public class Main {

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
        Demo out = new Demo(txtPath);
        double d = out.output1();
        out.print(1, (int) d);
        d = out.output2();
        out.print(2, (int) d);
        d = out.output3();
        out.print(3, (int) d);
        d = out.output4();
        out.print(4, (int) d);
        d = out.output5();
        out.print(5, (int) d);
        int num = out.output6();
        out.print(6, num);
        num = out.output7();
        out.print(7, num);
        d = out.output8();
        out.print(8, (int) d);
        d = out.output9();
        out.print(9, (int) d);
        num = out.output10();
        out.print(10, num);

    }

}
