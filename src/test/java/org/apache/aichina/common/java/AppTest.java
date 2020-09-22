package org.apache.aichina.common.java;

import static org.junit.Assert.assertTrue;

import java.util.List;
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

    @Test
    public void ByteAndInt()
    {
      try{
        for (int i = 0; i < 10000; i++){
          assertTrue(i == common_java.byte2int(common_java.int2byte(i)));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Test
    public void ByteAndLong()
    {
      try{
        for (long i = 1, j = 0; i < Long.MAX_VALUE && i > 0 ; i = i * 100, j++  ){
          //if (i != common_java.byte2long(common_java.long2byte(i)))
          //  System.out.println(String.format("convert fail, orinal : [%d], converted : [%d]", i, common_java.byte2long(common_java.long2byte(i))));
          assertTrue(i == common_java.byte2long(common_java.long2byte(i)));
          assertTrue(j == common_java.byte2long(common_java.long2byte(j)));          
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Test
    public void ByteAndLong_unsafe()
    {
      try{
        for (long i = 1, j = 0; i < Long.MAX_VALUE && i > 0 ; i = i * 100, j++  ){
          //if (i != common_java.byte2long(common_java.long2byte(i)))
          //  System.out.println(String.format("convert fail, orinal : [%d], converted : [%d]", i, common_java.byte2long(common_java.long2byte(i))));
          assertTrue(i == common_java.byte2long_unsafe(common_java.long2byte_unsafe(i)));
          assertTrue(j == common_java.byte2long_unsafe(common_java.long2byte_unsafe(j)));          
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    @Test
    public void testGetPid()
    {
      try{
        String pid = common_java.getPId();
        System.out.println("pid is : " + pid);
        System.out.println("for comfirm pid ,user command : ps aux|grep java, this sentence will last 20 sec");
        Thread.sleep(1000 * 20);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Test
    public void testCalendar()
    {
      try{
        java.util.Calendar cal = java.util.Calendar.getInstance();
        System.out.println("today is : " + cal.getTime() + " and dateStr : " + common_java.getSysteDate(cal) + " and dateHourStr : " + common_java.getSystemDateHour(cal));
        cal.set(java.util.Calendar.MONTH, 0);
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        System.out.println("date [" + cal.getTime() + "] Str : " + common_java.getSysteDate(cal) +  " and dateHourStr : " + common_java.getSystemDateHour(cal));
        cal.set(java.util.Calendar.MONTH, 9);
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        cal.set(java.util.Calendar.HOUR, 5);
        System.out.println("date [" + cal.getTime() + "] Str : " + common_java.getSysteDate(cal) +  " and dateHourStr : " + common_java.getSystemDateHour(cal));
        cal.set(java.util.Calendar.MONTH, 5);
        cal.set(java.util.Calendar.DAY_OF_MONTH, 31);
        System.out.println("date [" + cal.getTime() + "] Str : " + common_java.getSysteDate(cal) +  " and dateHourStr : " + common_java.getSystemDateHour(cal));
        System.out.println("system date str is : " + common_java.getSysteDate() + " and dateHourStr : " + common_java.getSystemDateHour() ); 
      }catch(Exception e){
        e.printStackTrace();

      }

    }
    
    @Test
    public void testFileList()
    {
      try{
        for (String name : common_java.getFiles("/var/log", ".log"))
        {
          System.out.println("get log file in /var/log : " + name);
        }
      } catch (Exception e){
        e.printStackTrace();
      }
    }
    
}
