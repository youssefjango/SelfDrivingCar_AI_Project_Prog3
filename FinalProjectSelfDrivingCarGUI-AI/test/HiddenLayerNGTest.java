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
public class HiddenLayerNGTest {

    /**
     *
     */
    public HiddenLayerNGTest() {
    }

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of clone method, of class HiddenLayer.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        HiddenLayer instance = new HiddenLayer(3, 2);
        HiddenLayer expResult = new HiddenLayer(3, 2);
        HiddenLayer result = instance.clone();
        for (int i = 0; i < result.getWeights().length; i++) {
            for (int j = 0; j < result.getWeights()[i].length; j++) {
               
                assertEquals(result.getWeights()[i][j], instance.getWeights()[i][j],"clone test passed");
            }
        }
        
        
    }

    
    

}
