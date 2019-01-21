package org.apache.aichina.common.java;


import java.util.*;
import org.apache.aichina.common.java.*;
public class common_java_logger{
  private static common_java_logger _instance = new common_java_logger();
  private static String _fileName;
  private static common_bit_file _bf;
  private common_java_logger(){
    try{
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  
  public static common_java_logger getInstance(String fileName){
    try{
      _instance.setFileName(fileName);
      _instance.initial();
      return _instance;
    } catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  public static void setFileName(String name){
    _fileName = name;
    initial();
  }

  private static void initial(){
    try {
      _bf = new common_bit_file(_fileName, "rw");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void append(String word){
    //我要仔细想想，手动管理offset是有问题的
    _bf.append(word);
  }
 
  public static void info(String word){
    append(word);
  }

  private static void close(){
    try {
      _bf.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void finalize(){
    close();
  }
}
