package com.fusioncharts.fusionboard.resolver;

import com.fusioncharts.fusionboard.utils.KConst;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sandeepacharya on 09/02/18.
 */
public class FieldsResolver implements PartResolver {
  private Map parts;
  private ArrayList <Map> $FIELDS;

  public FieldsResolver(Map parts) {
    this.parts = parts;
    this.populateTemplate();
  }

  private void extractKeys() {
    this.$FIELDS = (ArrayList) this.parts.getOrDefault(KConst.$FIELDS, null);
  }

  private void dispatch() {
    for (Map field : this.$FIELDS) {
      FieldResolver fieldResolver = new FieldResolver(field);
      System.out.println(fieldResolver.getString());
    }
  }

  private void populateTemplate() {
    this.extractKeys();
    this.dispatch();
  }
  @Override
  public String getString() {
    return null;
  }
}
