package org.apache.aichina.common.java;

import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.aichina.common.java.common_global_variant;

public class common_java_timer{
  private long _begin; //开始时间
  private long _init;  //初始化时间
  private long _end;   //结束时间
  private SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
  public common_java_timer(){
    this._init = System.currentTimeMillis();
    this._begin = System.currentTimeMillis();
    
  }

  public void setBegin(long now){
    if (0L == now)
      this._begin = System.currentTimeMillis();
    else
      this._begin = now;

  }

  public void setBegin(){
    setBegin(0L);
  }

  public void setEnd(long now){
    if (0L == now)
      this._end = System.currentTimeMillis();
    else
      this._end = now;
  }

  public void setEnd(){
    this._end = System.currentTimeMillis();
  }

  public long duration(int result_type){
    long result;
    result = this._end - this._begin;
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

  public long getBegin(){
    return this._begin;
  }

  public long getEnd(){
    return this._end;
  }
  
  public long duration(){
    return duration(common_global_variant.GLOB_INT_TIMER_TYPE_MILLION);
  }

  public String getBeginDate(){
    return formatter.format(new Date(_begin));
  }

  public String getEndDate(){
    return formatter.format(new Date(_end));
  }
}
