package com.example.tools;

import com.corpize.core.*;
import com.corpize.core.common_bit_file;
import com.corpize.core.common_xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.example.tools.*;

public class App 
{
  public static void main( String[] args )
  {
    while(true){
      System.out.println( "Please input num to call funtion:" );
      System.out.println("1. create sharemem modules xml config file template!");
      System.out.println("2. call sharemem monitor!");
      System.out.println("3. check sharemem xml file text!");
      System.out.println("4. Stress Test (WARNING : LONG TIME TEST)");

      System.out.println(" ");
      int idx = common_java.StrToInt_safe(common_java.scanf(), -1);
      switch(idx){
        case 1 : System.out.println(String.format(" 1,create sharemem modules file: %s", createSMconfig()));break;
        case 2 : shmMonitor shmMoni = new shmMonitor();
                 String [] arg = {common_global_variant.GLOB_STRING_VDC_MEMSHARE_DEFAULT_LINUX_CONFIGFILE};
                 shmMoni.call(arg);
                 break;
        case 3 : checkShmConf();
        default : System.out.println(" default ");

      }
    }
  }

  public static String createSMconfig(){
    try{
      System.out.println(String.format("config file created in current path, try to copy it to linux os path, like: %s", common_global_variant.GLOB_STRING_VDC_MEMSHARE_DEFAULT_LINUX_CONFIGFILE));
      String fileName = common_java.combineFilePathEx( System.getProperty("user.dir"), "xml.conf") ; 
      Document doc = common_xml.createDocument();
      Element root = common_xml.rootElement(doc, "myRoot");
      Element elem = common_xml.addElement(doc, root, common_global_variant.GLOB_STRING_MEMSHARE_ELEMENT);
      common_xml.addAttr2Element(doc, elem, common_global_variant.GLOB_STRING_MEMSHARE_ATTRIBUTE_BLOCKCOUNT, "1");
      common_xml.addAttr2Element(doc, elem, common_global_variant.GLOB_STRING_MEMSHARE_FILE_CAPCITY, "20971520" );
      common_xml.addAttr2Element(doc, elem, common_global_variant.GLOB_STRING_MEMSHARE_FILE_PREFIX_ATTRIBUTE, common_global_variant.GLOB_STRING_VDC_MEMSHARE_DEFAULT_LINUX_DATAFILE);
      common_xml.saveXml(fileName, root, "utf-8");
      return fileName;
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }

  }

  public static void checkShmConf(){


  }
}
    
