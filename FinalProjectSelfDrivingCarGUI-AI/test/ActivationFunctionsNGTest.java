/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author YOUSSEF
 */
public class ActivationFunctionsNGTest {
    
    public ActivationFunctionsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of sigmoid method, of class ActivationFunctions.
     */
    @Test
    public void testSigmoid() {
        System.out.println("sigmoid");
        double x = 0.0;
        double expResult = 0.5;
        double result = ActivationFunctions.sigmoid(x);
        
        assertEquals(result, expResult,"sigmoid test passed");
        
    }

    /**
     * Test of relu method, of class ActivationFunctions.
     */
    @Test
    public void testRelu() {
        System.out.println("relu");
        double x = 6.8;
        double expResult = 6.8;
        
        double result = ActivationFunctions.relu(x);
        assertEquals(result, expResult,"relu test passed");
       
    }

    /**
     * Test of tanh method, of class ActivationFunctions.
     */
    @Test
    public void testTanh() {
        
        System.out.println("tanh");
        double x = 19.7;
        double expResult = 1.0;
        
        double result = ActivationFunctions.tanh(x);
       
        assertEquals(result, expResult,"tanh test passed");
        
    }
    
}
