package org.apache.aichina.common.java;

import java.util.*;

public class common_java_timer{
  private long _begin; //开始时间
  private long _init;  //初始化时间
  public void common_java_timer(){
    _init = System.currentTimeMillis();
    _begin = _init;
  }

  public void setBegin(long now = -1){
    if (now <= 0){
      _begin = System.currentTimeMillis();
    }
  }

  public void setEnd(long now = -1){
    if (now <= 0){
      _end = System.currentTimeMillis();
    }
  }

  public long getTime(long now = -1, int result_type){
    
  }


}
