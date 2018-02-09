package com.fusioncharts.fusionboard.utils;

import com.google.gson.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonMapper {

  private JsonElement root;
  private String jsonString;

  public JsonMapper(){}

  public JsonMapper(String jsonString) {
    this.jsonString = jsonString;
    JsonParser parser = new JsonParser();
    this.root = parser.parse(this.jsonString).deepCopy();
  }

  public String getJsonString() {
    return jsonString;
  }

  public void setJsonString(String jsonString) {
    this.jsonString = jsonString;
  }

  public Map getJSONObjectMap(){
    Map jsonMap = (Map) this.convert();
    return jsonMap;
  }

  private Object convert() {
    this.root = (new JsonParser().parse(this.jsonString)).deepCopy();
    return json2Java(this.root);
  }

  private Object json2Java(JsonElement elem) {
    if(elem.isJsonArray()) {
      return jsonArrayAsList(elem.getAsJsonArray());
    } else if(elem.isJsonPrimitive()) {
      return json2JavaPrimitive(elem.getAsJsonPrimitive());
    } else {
      return jsonObject2Map(elem.getAsJsonObject());
    }
  }

  @SuppressWarnings({"unchecked" })
  private List jsonArrayAsList(JsonArray arr) {
    ArrayList list = new ArrayList(arr.size());
    for (int j = 0; j < arr.size(); j++) {
      list.add(json2Java(arr.get(j)));
    }
    return list;
  }

  @SuppressWarnings({"unchecked" })
  private LinkedHashMap jsonObject2Map(JsonObject obj) {
    LinkedHashMap map = new LinkedHashMap();
    for(Map.Entry<String, JsonElement> entry: obj.entrySet()) {
      map.put(entry.getKey(), json2Java(entry.getValue()));
    }
    return map;
  }

  private Object json2JavaPrimitive(JsonPrimitive prim) {
    if(prim.isBoolean()) {
      return prim.getAsBoolean();
    } else if (prim.isString()) {
      return prim.getAsString();
    } else if (prim.isNumber()) {
      return prim.getAsNumber();
    } else {
      throw new IllegalStateException();
    }
  }

}