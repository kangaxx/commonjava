package org.apache.aichina.common.java;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void combinePathTest()
    {
      if (common_java.combineFilePathEx("/abc", "def").equals("/abc/def"))
        assertTrue( true );
      else
        assertTrue(false);
    }
}
