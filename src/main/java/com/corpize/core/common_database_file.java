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

import com.corpize.core.common_global_variant;
import com.corpize.core.common_java;
import com.corpize.core.common_bit_file ;

public class common_database_file{
  private String infoFileName = null;
  private String indexFileName = null;
  private String dataFileName = null;
  public common_database_file(String dbName, String tableName){
   
  
  }

  public String getTableInfoFileName(String tableName){
    return tableName + common_global_variant.GLOB_STRING_DATABASE_INFOFILE_SUFFIX; 

  }

  public String getTableIndexFileName(String tableName){
    return tableName + common_global_variant.GLOB_STRING_DATABASE_INDEXFILE_SUFFIX;
  }

  public String getTableDataFileName(String tableName){
    return tableName + common_global_variant.GLOB_STRING_DATABASE_DATAFILE_SUFFIX;
  }


}
