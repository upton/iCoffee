package upton;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloTest {
    private Hello hh;
    @Before
    public void setUp() throws Exception {
        hh = new Hello();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHi() {
        String result = hh.hi("upton");
        
        Assert.assertEquals("not eq", "hello upton", result);
    }
    
    @Test
    public void testHi2() {
        System.out.println("hi2");
    }

}
