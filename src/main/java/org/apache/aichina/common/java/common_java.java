package org.apache.aichina.common.java;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.management.*;
import java.net.*;
import org.apache.aichina.common.java.*;

public class common_java
{
  public static String HDFS_PROCESS_PROPERTIES = "hdfs-start.properties";
  private static ByteBuffer buffer = ByteBuffer.allocate(8);
  /////////////////////////////////////////////////////////////////////////////////////////////////////
  //通过xml文件读取配置，代码已经升级迁移到common_xml类内
  public static String getAttributeByElem(String fileName, String elemName, String attrName){  
    return common_xml.getAttributeByElem(fileName, elemName, attrName);
  }  
 
  public static List<String> getAttributeByElem(String fileName, List<String> elemNames, List<String> attrNames){
    return common_xml.getAttributeByElem(fileName, elemNames, attrNames);
  }

  private static Element resFindElementByName(Element elem, String name)
  {
    return common_xml.resFindElementByName(elem, name);
  }
  ///////////////////////////////////////////////////////////////////////////////////////////////////////

  public static int StrToInt_safe(String input){
    return StrToInt_safe(input, -1);
  }

  public static int StrToInt_safe(String input, int execReturn){
    try{
      return Integer.parseInt(input);
    }catch(Exception e){
      return execReturn;
    }
  }

  public static int StrToInt(String input){
    return Integer.parseInt(input);    
  }

  public static String IntToStr(int input){
    return String.valueOf(input);
  }

  public static long StrToLong_safe(String in){
    return StrToLong_safe(in, -1L);
  }

  public static long StrToLong_safe(String in, long execReturn){
    try{
      return Long.parseLong(in);
    } catch(Exception e) {
      return execReturn;
    }
  }

  public static long StrToLong(String in){
    return Long.parseLong(in);
  }

  public static String LongToStr(long in){
    return String.valueOf(in);
  }

  public static char[] StrToChar(String input){
    return input.toCharArray();
  }

  public static String CharToStr(char[] input){
    return new String(input);
  }

  public static byte[] StrToByte(String input){
    return StrToByte(input , "UTF-8");
  }

  public static byte[] StrToByte(String input, String codec){
    try{
      return input.getBytes(codec);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String ByteToStr(byte[] input){
    return ByteToStr(input, "UTF-8");   
  }

  public static String ByteToStr(byte[] input, String codec){
    try{
      return new String(input, codec);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static byte[] int2byte(int input){
    byte[] result = new byte[4];
    result[0] = (byte)(input >> 24);
    result[1] = (byte)((input >> 16) & 0xff);
    result[2] = (byte)((input >> 8) & 0xff);
    result[3] = (byte)(input & 0xff);
    return result;
  }

  public static int byte2int(byte[] input) throws Exception{
    if (input.length != 4)
      throw new Exception("Error, function byte2int error , input length must be 4!");
    int result = 0;
    for (int i = 24, j = 0; i >= 0; i = i - 8, j++){
      result = result|((input[j] & 0xff) << i);
    }
    return result;
  }

  public static byte[] long2byte(long input){
    byte[] result = new byte[8];
    for (int i = 0; i < result.length; i++){
      result[i] = new Long(input & 0xff).byteValue();
      input = input >> 8;
    }    
    return result;
  }

  public static long byte2long(byte[] input) throws Exception{
    if (input.length != 8)
      throw new Exception("Error, function byte2long error , input length must be 8!");

    long result = 0L;
    long tmp = 0L;
    for (int i = 0; i < input.length; i++){
      tmp = input[i] & 0xff;
      if (i > 0)
        tmp <<= i*8;
      result |= tmp;
    }
    return result;
  }

  //该函数容易出现数组越界等问题，在spark模式下，一些java反射函数方法不能直接运行
  public static long byte2long_unsafe(byte[] input){
    try{
      buffer.clear();
      buffer.put(input, 0, 8);
      buffer.flip();
      long result = buffer.getLong();
      return result;
    } catch (Exception e) {
      return 0L;
    }
  }

  public static byte[] long2byte_unsafe(long input){
    buffer.clear();
    buffer.putLong(0, input);
    return buffer.array();
  }
  public static void printf(String words){
    System.out.println(words);
  }

  //获取日期值
  public static String getSysteDate(Calendar cal){
    return IntToStr(cal.get(Calendar.YEAR) * 10000 + (cal.get(Calendar.MONTH) + 1) * 100 + cal.get(Calendar.DAY_OF_MONTH));
  }

  public static String getSysteDate(){
    java.util.Calendar cal = Calendar.getInstance();
    return getSysteDate(cal);
  }

  public static String getSystemDateHour(Calendar cal){
    return IntToStr(cal.get(Calendar.YEAR) * 1000000 + (cal.get(Calendar.MONTH) + 1) * 10000 + cal.get(Calendar.DAY_OF_MONTH) * 100 + cal.get(Calendar.HOUR));
  }

  public static String getSystemDateHour(){
    java.util.Calendar cal = Calendar.getInstance();
    return getSystemDateHour(cal);
  }

  public static String scanf(){
    try{
      Scanner sc = new Scanner(System.in);
      return sc.nextLine();
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  //为debug println专用的参数类
  public class debugProperties{
    private int printNum = 0;
    private int jumpNum = 1; //算法上，用printNum 对jumpNum取余，这样可以实现每jumpNum次打印一次等loop功能
    private int _int_cache[] = {0, 0, 0};//记录一些cache 值，方便打印与记录

    public debugProperties(){
      printNum = 0;
      jumpNum = 1;
    }

    //记录cache，方便调试时的打印统计功能
    public void setCache(int num){
      setIntCache(num, 0);
    }

    public void setIntCache(int num, int idx){
      _int_cache[idx] = num;
    }

    public void incCache(int num){
      incIntCache(num, 0);
    }

    public void incIntCache(int num, int idx){
      setIntCache(num + _int_cache[idx], idx);
    }

    public int getIntCache(int idx){
      return _int_cache[idx];
    }

    public void printNumInc(){
      printNum ++;
    }
    
    //获取真实的打印次数,如果jumpNum > 1,真实打印次数会小于调用debugPrint的次数
    public int getPrintNum_truth(){
      return printNum / jumpNum;
    }

    public void setPrintNum(int num){
      printNum = num;
    }

    public void setJumpNum(int num){
      if (num > 0)
        jumpNum = num;
      else{
        System.out.println("System debug warning ,jump num must > 0 ,otherwise , it will be set to 1");
        jumpNum = 1; //maybe warning
      }
    }
    
    //针对printnum取余，为0时则表示打印，jumpNum设为1时默认每次打印
    public boolean isPrint(){
      if (printNum % jumpNum == 0)
        return true;
      else
        return false;
    }
    
    public void doPrint(String msg){
      if (isPrint()){
        System.out.println(msg);
        printNum ++;
      }
    }

    public void doPrintWithCount(String msg){
      if (isPrint()){
        System.out.println(msg + " ;Count cache[0]:" + getIntCache(0)); 
      }
    }
  }

  //调试代码用的println语句，为了大数据编程，特地增加跳跃显示（每n次只打印一次）功能参数
  public static void debugPrintln(String msg, debugProperties printProp){
    printProp.doPrint(msg);
  }

  //简单统计数据功能
  public static void debugPrintln(String msg, debugProperties printProp, int count){
    printProp.incCache(count);
    printProp.doPrintWithCount(msg);    
  }

  //文件路径及文件名合并,仿照c++ std ,有路径分隔符判定
  public static String combineFilePathEx(String path, String fileName){
    String separator;
    if (path.substring(path.length() - 1).equals(java.io.File.separator) || fileName.substring(0, 1).equals(java.io.File.separator))
      separator = "";
    else
      separator = java.io.File.separator;
    return path + separator + fileName;
  }
  
  //解析系统运行参数实际值
  //例如 getProgramArgs（"^-r" , "-r55"),会返回55
  public static String getProgramArgs(String pattern, String matcher){
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(matcher);
    String result = "";
    if (m.find())
      result = m.replaceAll("");
    return result;
  }

  public static class WorkerBTT extends Thread {
    public ServerSocket server;
    public  WorkerBTT(ServerSocket s) {
      server = s;
    }
    public void run() {
      while (true) {
        try {
          Socket socket = server.accept();
          try {
          } finally {
            socket.close();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  public static byte[] byteMerge(byte[] bt1, byte[] bt2){
    byte[] result = new byte[bt1.length + bt2.length];  
    System.arraycopy(bt1, 0, result, 0, bt1.length);  
    System.arraycopy(bt2, 0, result, bt1.length, bt2.length);  
    return result;
  }

  //获取当前进程id号
  public static String getPId(){
    //get name representing the running Java virtual machine.  
    String name = ManagementFactory.getRuntimeMXBean().getName();  
    System.out.println(name);  
    // // get pid  
    String pid = name.split("@")[0];  
    return pid;
  }

  //列出路径内所有文件
  public static List<String> getFiles(String directory){
    List<String> result = new ArrayList<String>();
    File f = new File(directory); 
    for (File tmp : f.listFiles()){
      if (tmp.isFile())
        result.add(tmp.getAbsolutePath());
    }
    return result;
  }

  //列出路径内所有特定类型的文件
  public static List<String> getFiles(String directory, String ends){
    List<String> result = new ArrayList<String>();
    File f = new File(directory);
    for (File tmp: f.listFiles()){
      if (tmp.isFile() && tmp.getName().endsWith(ends))
        result.add(tmp.getAbsolutePath());
    }
    return result;
  }


}
