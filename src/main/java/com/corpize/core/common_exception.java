package com.corpize.core;

import java.io.File;

import java.lang.*;

public class common_exception extends Exception{
  public common_exception(){
    super();
  }

  public common_exception(String msg){
    super(msg);
    System.out.println(msg);  //暂时用简单的办法
  }
  
  //msg 表示异常信息， common_log表示是否执行本地化操作（写文档？println？取决于这里代码怎么写）
  public common_exception(String msg, Boolean common_log) {
    super(msg);
    if (common_log){
      System.out.println(msg);
    }

  }
}


