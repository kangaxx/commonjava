package org.apache.aichina.common.java;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class common_xml
{
  //通过xml文件读取配置
  public static String getAttributeByElem(String fileName, String elemName, String attrName){  
    try {
      //创建解析工厂
      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
      //指定DocumentBuilder
      DocumentBuilder builder = dbfactory.newDocumentBuilder();
      Document doc = builder.parse(new File(fileName));
      //得到Document的根
      Element root = doc.getDocumentElement();
      //获得一级子元素
      Element r = resFindElementByName(root, elemName);
      if (null != r) 
        return r.getAttribute(attrName);
      else
        return "";
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }

  }  
 
  public static List<String> getAttributeByElem(String fileName, List<String> elemNames, List<String> attrNames){
    try {
      if (elemNames.size() != attrNames.size())
        return null; //要求传来的元素和属性数量一致，避免一次无谓的错误
      List<String> result = new ArrayList<String>();
      //创建解析工厂
      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
      //指定DocumentBuilder
      DocumentBuilder builder = dbfactory.newDocumentBuilder();
      Document doc = builder.parse(new File(fileName));
      //得到Document的根
      Element root = doc.getDocumentElement();
      //获得一级子元素
      //遍历输入的数据来获取结果
      int size = elemNames.size();
      for (int i = 0; i < size; ++i){
        result.add(/*find元素后获取属性，如果属性不存在，可能会有异常 */ resFindElementByName(root, elemNames.get(i)).getAttribute(attrNames.get(i)) );
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;

    }

  }

  private static Element resFindElementByName(Element elem, String name)
  {
    NodeList l = null; 
    Element result = null;
    try {
      l = elem.getChildNodes();
      if (elem.getTagName().equals(name))
        return elem;
      for (int i = 0; i < l.getLength(); i++){
        if (l.item(i).getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element)l.item(i);
          result = resFindElementByName(element, name);
          if (null != result){
            return result;
          }
        }
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }



}
