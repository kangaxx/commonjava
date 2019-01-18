package org.apache.aichina.common.java;

import java.util.*;
import org.apache.aichina.common.java.common_global_variant;

public class common_java_timer{
  private long _begin; //开始时间
  private long _init;  //初始化时间
  private long _end;   //结束时间
  public void common_java_timer(){
    _init = System.currentTimeMillis();
    _begin = _init;
  }

  public void setBegin(long now){
    if (now <= 0L){
      _begin = System.currentTimeMillis();
    }
  }

  public void setBegin(){
    setBegin(0L);
  }

  public void setEnd(long now){
    if (now <= 0L){
      _end = System.currentTimeMillis();
    }
  }

  public void setEnd(){
    setEnd(0L);
  }

  public long duration(int result_type){
    long result;

    result = _end - _begin;
    switch(result_type){
      case common_global_variant.GLOB_INT_TIMER_TYPE_MILLION:
        return result;
      case common_global_variant.GLOB_INT_TIMER_TYPE_SECOND:
        return result/1000L;
      case common_global_variant.GLOB_INT_TIMER_TYPE_MINS:
        return result/60000L;
      default:
        return 0L;
    }
  }

  public long duration(){
    return duration(common_global_variant.GLOB_INT_TIMER_TYPE_MILLION);
  }

}
