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

  public void setEnd(long now = 0){
    if (now <= 0){
      _end = System.currentTimeMillis();
    }
  }

  public long duration(int result_type = GLOB_INT_TIMER_TYPE_MILLION){
    long result;

    result = _end - _begin;
    switch(result_type){
      case GLOB_INT_TIMER_TYPE_MILLION:
        return result;break;
      case GLOB_INT_TIMER_TYPE_SECOND:
        return result/1000.0; break;
      case GLOB_INT_TIMER_TYPE_MINS:
        return result/60000.0; break;
    }
  }


}
