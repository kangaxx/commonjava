package com.corpize.core;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.corpize.core.common_java;

public class common_bit_file
{
  private RandomAccessFile _file = null;
  public common_bit_file(String fileName, String mode){
    try{
      _file = new RandomAccessFile(fileName, filterMode(mode));
    }
    catch(Exception e){
    }
  }

  //判断读写文件mode选项合法性，不合法则一律返回read模式
  private String filterMode(String mode){
    if (mode == "rw" || mode == "r" || mode == "rws" || mode == "rwd")
      return mode;
    else
      return "r";
  }

  public void read(byte[] words, int offset, int size){
    try{
      if (null == _file)
        throw new Exception("ERR: read bit file error ,file not initial!");
 
      _file.read(words, offset, size);
    } catch (Exception e){
    }
  }
  
  public void write(byte[] words, int offset, int size){
    try{ 
      if (null == _file)
        throw new Exception("ERR: write bit file error ,file not initial!");

      _file.write(words, offset, size);
    } catch(Exception e){
    }
  }

  public void close(){
    try{
      if (null == _file)
        throw new Exception("ERR: close bit file error, file not initial!");
      _file.close();
    }catch (Exception e){
    }
  }

  public  void append(byte[] words, int size){
    try{
      if (null == _file)
        throw new Exception("ERR: append bit file error, file not initial!");
      
      _file.write(words, (int)length(), size);
    } catch (Exception e){
      e.printStackTrace();
    }
  }

  public void append(String words){
    try {
      if (null == _file)
        throw new Exception("ERR: append bit file error, file not initial!");
      _file.seek(length());
      _file.writeBytes(words);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public long length(){
    try{
      if (null == _file)
        return 0;
      else
        return _file.length();
    } catch(Exception e) {
      e.printStackTrace();
    } 
    return 0;
  }
}
