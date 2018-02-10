package com.fusioncharts.fusionboard.utils;

/**
 * Created by sandeepacharya on 09/02/18.
 */
public class SConst {

  public final static String isAlias = "isAlias";
  public final static String source = "source";
  public final static String field = "field";

  public final static String QUERY_SELECT_TEMPLATE = "<if(isAlias)>" +
      "( SELECT <fields> FROM <sources> ) AS <alias>" +
      "<else>" +
      "SELECT <fields> FROM <sources>" +
      "<endif>";

  public final static String FIELD_TEMPLATE = "<name><if(isAlias)> AS <alias><endif>";
  public final static String TABLE_TEMPLATE = "<name><if(isAlias)> AS <alias><endif>";

  public final static String FILEDS_TEMPLATE = "<field; separator=\", \">";
  public final static String SOURCES_TEMPLATE = "<source; separator=\", \">";

}
