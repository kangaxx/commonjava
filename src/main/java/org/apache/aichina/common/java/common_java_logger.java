package org.apache.aichina.common.java;


import java.util.*;
import org.apache.aichina.common.java.*;
public class common_java_logger{
  private static common_java_logger _instance = new common_java_logger();
  private static String _fileName;
  private common_java_logger(){
    try{
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  private common_java_logger(String fileName){
    try{
      _fileName = fileName;
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  public static common_java_logger getInstance(){
    return _instance;
  }

  public static void setFileName(String name){
    _fileName = name;
  }

  public static void initial(){
    try {
      common_bit_file bf = new common_bit_file(_fileName, "rw");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void insert(String word){
    //我要仔细想想，手动管理offset是有问题的

  }
}
