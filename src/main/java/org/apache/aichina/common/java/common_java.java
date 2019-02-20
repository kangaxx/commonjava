package org.apache.aichina.common.java;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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

import org.apache.aichina.common.java.*;

public class common_java
{
  public static String HDFS_PROCESS_PROPERTIES = "hdfs-start.properties";

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

  public static void Printf(String words){
    System.out.println(words);
  }

  public static String Scanf(){
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
  
}
