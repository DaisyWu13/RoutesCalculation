package route.test;

import demo.Demo;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * unit test
 *
 * @author Daisy Wu
 */
public class TestRoute {

    private String input;
    private Demo demo;

    @Before
    public void setUp() {
        input = "input.txt";
        demo = new Demo(input);
    }

    @After
    public void tearDown() {
        demo = null;
    }

    @Test
    public void testNotExistFile() {
        Demo demo = new Demo("input1.txt");
        assertEquals(null, demo.getRoute());

    }

    @Test
    public void testOutput1() {
        double d = demo.output1();
        assertEquals(9, d, 0);
    }

    @Test
    public void testOutput2() {
        double d = demo.output2();
        assertEquals(5, d, 0);
    }

    @Test
    public void testOutput3() {
        double d = demo.output3();
        assertEquals(13, d, 0);
    }

    @Test
    public void testOutput4() {
        double d = demo.output4();
        assertEquals(22, d, 0);
    }

    @Test
    public void testOutput5() {
        double d = demo.output5();
        assertEquals(-1, d, 0);
    }

    @Test
    public void testOutput6() {
        int d = demo.output6();
        assertEquals(2, d, 0);
    }

    @Test
    public void testOutput7() {
        int d = demo.output7();
        assertEquals(3, d, 0);
    }

    @Test
    public void testOutput8() {
        double d = demo.output8();
        assertEquals(9, d, 0);
    }

    @Test
    public void testOutput9() {
        double d = demo.output9();
        assertEquals(9, d, 0);
    }

    @Test
    public void testOutput10() {
        int d = demo.output10();
        assertEquals(7, d, 0);
    }

}
