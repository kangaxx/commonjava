import java.util.*;
public class common_java_logger{
  private static common_java_logger _instance = new common_java_logger();
  private static String _fileName;
  private common_java_logger(){
    try{
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  public static common_java_logger getInstance(){
    return _instance;

  }

  public static void setFileName(String name){
    _fileName = name;
  }

}
