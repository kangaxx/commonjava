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
      if (common_java.combineFilePathEx("/abc/", "def").equals("/abc/def"))
        assertTrue( true );
      else
        assertTrue(false);
      if (common_java.combineFilePathEx("/abc", "/def").equals("/abc/def"))
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
      String s = "wa ka wa ka";
      s += "\n";
      f.append(s);
      f.append(s);
      f.close();
    }

    @Test 
    public void TypeConvertTest()
    {
      String normal_str = "123";
      assertTrue( common_java.StrToInt_safe(normal_str) == 123);
      assertTrue( common_java.StrToInt(normal_str) == 123);
      int normal_int = 123;
      assertTrue( common_java.IntToStr(normal_int).equals("123"));
      assertTrue( normal_str.equals(common_java.CharToStr(common_java.StrToChar(normal_str))));
      assertTrue( normal_str.equals(common_java.ByteToStr(common_java.StrToByte(normal_str))));
      long normal_long = 123L;
      assertTrue( common_java.LongToStr(normal_long).equals("123"));
      assertTrue( normal_str.equals(common_java.LongToStr(common_java.StrToLong(normal_str))));
    }

    @Test
    public void loggerTest()
    {
      common_java_logger log = common_java_logger.getInstance("/root/common_log_test.tmp"); 
      log.info(common_java_timer.now() + ": run maven install test! \n");
    }

    @Test
    public void getProgramArgsTest()
    {
      assertTrue(common_java.getProgramArgs("-r", "-r55").equals("55"));
      assertTrue(common_java.getProgramArgs("-r", "-t5").equals(""));
    }

    @Test
    public void timeWork()
    {
      common_java_timer jt = new common_java_timer();
      jt.setEnd();
      System.out.println("duration " + jt.duration());
      System.out.println("duration_1 " + jt.duration(1));
      System.out.println("duration_2 " + jt.duration(2));
      System.out.println("duration_3 " + jt.duration(3));
      System.out.println("begin " + jt.getBegin());
      System.out.println("end " + jt.getEnd());
      System.out.println("begin date : " + jt.getBeginDate());
      System.out.println("end date : " + jt.getEndDate());
      assertTrue( jt.duration() >= 0);
      assertTrue(1000000 % 1000 == 0);
      assertTrue(0 % 1000 == 0);
    }
}
