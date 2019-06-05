package org.apache.aichina.common.java;

import java.lang.Thread;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.FileLock;
import sun.nio.ch.FileChannelImpl;

import org.apache.aichina.common.java.*;

public class ShareMemHelper{
  private common_sharemem csm;
  private MappedByteBuffer[] buffers;
  private enum BlockStatus{
    sleep(0), writing(1), reading(2), after_write(3), write_continue(4), wait(5);
    private final int idx;
    private BlockStatus(int index){
      this.idx = index;
    }
    
    public int getValue(){
      return this.idx;
    }
    
    public static BlockStatus getStatusByValue(int value){
      for (BlockStatus status : BlockStatus.values()){
        if (status.getValue() == value) return status;
      }
      return null;
    }
  }

  public ShareMemHelper(String config){
    csm = common_sharemem.getInstance(config);
    if (csm == null){
      System.out.println("Share memory failed !");
    }
    else {
      while( (buffers = csm.createWriteSharemem()) == null){System.out.print("Try to initial share memory ");}
    }
  }

  //供写入函数调用，直接返回空闲可写入的block序号供写入程序调用。
  private int getEmptyIdx(){
    int result = -1;
    for (int i = 0; i < csm.blockCount(); ++i){
           
      switch(BlockStatus.getStatusByValue((int)(buffers[i].get(0)))){
      case sleep :
      case wait :
        result = i;
        return result;
      default:
        //do nothing
      }
    }  
    return result;
  } 


  //供完整写入函数调用，直接返回空闲未使用的block序号，供完整写入程序调用
  //一次写完整个block
  public Boolean writeBlockComplete(String words) throws common_exception{
    int idx = getEmptyIdx();
    if (words.length()+ 1 < csm.fileSize()){
      buffers[idx].put(0,(byte)0);
      buffers[idx].put(words.getBytes());
      return true;
    } else {
      throw new common_exception("Send too many data to share memory!");
    }
  }

}
