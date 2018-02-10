package com.fusioncharts.fusionboard;

import com.fusioncharts.fusionboard.resolver.QuerySelectResolver;
import com.fusioncharts.fusionboard.utils.JsonMapper;

import java.util.Map;

public class SqlConverter {

  private String json;
  private String sqlStatement = "";
  private Map jsonMap;

  QuerySelectResolver selectResolver;

  public SqlConverter(String json) {
    this.json = json;
  }

  public void setJson(String json) {
    this.json = json;
  }


  private void createJsonMap() {
    JsonMapper mapper = new JsonMapper();
    mapper.setJsonString(this.json);
    this.jsonMap = mapper.getJSONObjectMap();
  }

  public String convert() {
    this.createJsonMap();
    this.selectResolver = new QuerySelectResolver(this.jsonMap);
    this.sqlStatement = this.selectResolver.getString();
    return this.sqlStatement;
  }
}
