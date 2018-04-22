/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.test;

import route.caculation.Output;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;



/**
 *unit test
 * @author Administrator
 */
public class TestOutput {
    private String input;
    private Output out;
    
     @Before
     public void setUp(){
         input = "input.txt";
         out = new Output(input);
     }
     
     @After
     public void tearDown(){
         out = null;
     }
    
    @Test
    public void testNotExistFile(){
        Output out = new Output("input1.txt");
        assertEquals(null,out.getAction());
        
    }
    
    @Test
    public void testOutput1(){
        float d = out.output1();
        assertEquals(9, d,0);        
    }
    
    @Test
    public void testOutput2(){
        float d = out.output2();
        assertEquals(5, d,0);        
    }
    
    @Test
    public void testOutput3(){
        float d = out.output3();
        assertEquals(13, d,0);        
    }
    
    @Test
    public void testOutput4(){
        float d = out.output4();
        assertEquals(22, d,0);        
    }
    
    @Test
    public void testOutput5(){
        float d = out.output5();
        assertEquals(-1, d,0);        
    }
    
    @Test
    public void testOutput6(){
        int d = out.output6();
        assertEquals(2, d,0);        
    }
    
    @Test
    public void testOutput7(){
        int d = out.output7();
        assertEquals(3, d,0);        
    }
    
    @Test
    public void testOutput8(){
        float d = out.output8();
        assertEquals(9, d,0);        
    }
    
    @Test
    public void testOutput9(){
        float d = out.output9();
        assertEquals(9, d,0);        
    }
    
    @Test
    public void testOutput10(){
        int d = out.output10();
        assertEquals(7, d,0);        
    }
    
}
