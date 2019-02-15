package org.apache.aichina.common.java;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.transform.TransformerException;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class common_xml
{
  private static void writeXml(OutputStream os, Node node, String encoding) throws TransformerException {
    TransformerFactory transFactory = TransformerFactory.newInstance();
    Transformer transformer = transFactory.newTransformer();
    transformer.setOutputProperty("indent", "yes");
    transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
                       
    DOMSource source = new DOMSource();
    source.setNode(node);
    StreamResult result = new StreamResult();
    result.setOutputStream(os);
    transformer.transform(source, result);
  }


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

  public static Element resFindElementByName(Element elem, String name)
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

  public static Document createDocument() throw ParserConfigurationException {
    // 定义工厂 API，使应用程序能够从 XML 文档获取生成 DOM 对象树的解析器
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    // 定义 API， 使其从 XML 文档获取 DOM 文档实例。使用此类，应用程序员可以从 XML 获取一个 Document
    DocumentBuilder builder = factory.newDocumentBuilder();
    // Document 接口表示整个 HTML 或 XML 文档。从概念上讲，它是文档树的根，并提供对文档数据的基本访问
    return builder.newDocument();
  }

  public static Element rootElement(Document document, String rootName) {
    Element element = document.createElement(rootName);
    document.appendChild(element);
    return element;
  }
                 
  public static Element docCreateElement(Document document, String elementName) {
    return document.createElement(elementName);
  }
                           
  public static void parentAddChild(Document document, Element parentElement,
                                    Element childName) {
    parentElement.appendChild(childName);
  }

  public static void addAttrtoElement(Document document, Element elem, String attrName, String attrValue) 
  {
    Element name = document.createElement(attrName);
    name.appendChild(document.createTextNode(attrValue));
    elem.appendChild(name);
  }

  public static void saveXml(final String fileName, final Node node, String encoding) throws java.io.FileNotFoundException, TransformerException {
    writeXml(new FileOutputStream(fileName), node, encoding);
  }

}
