package com.fusioncharts.fusionboard.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by sandeepacharya on 08/02/18.
 */
public class Utils {
  public static String getResourcePath(String resourceName){
    return ClassLoader.getSystemClassLoader().getResource(resourceName).getPath();
  }

  public static String getFileContentAsString(String path) throws Exception {
    try {
      return new String(Files.readAllBytes(Paths.get(path)));
    }catch (Exception e){
      throw new Exception(e);
    }
  }

  public static String readJson(String resourceName) throws Exception {
    String jsonContents = "";
    try {
      jsonContents = getFileContentAsString(getResourcePath(resourceName));
    }
    catch(Exception e){
      throw new Exception("File not found");
    }
    return jsonContents;
  }


}
