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

    @Test
    public void bitFileReadWriteTest()
    {
      common_bit_file f = new common_bit_file("/root/test.tmp", "rw");
      byte [] tmp = new byte[8];
      for (int i = 0; i < tmp.length; ++i){
        tmp[i] = (byte)i;

      }
      f.write(tmp, 0, tmp.length);
      f.close();
    }

    @Test
    public void timeWork()
    {
      common_java_timer jt = new common_java_timer();
      jt.setEnd();
      assertTrue( jt.duration() > 0);
    }
}
