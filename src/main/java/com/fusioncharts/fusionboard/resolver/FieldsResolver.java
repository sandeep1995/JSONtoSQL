package com.fusioncharts.fusionboard.resolver;

import com.fusioncharts.fusionboard.utils.KConst;
import com.fusioncharts.fusionboard.utils.SConst;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sandeepacharya on 09/02/18.
 */
public class FieldsResolver implements PartResolver {
  private Map parts;
  private ArrayList <Map> $FIELDS;
  private ST st;

  public FieldsResolver(Map parts) {
    this.parts = parts;
    this.st = new ST(SConst.FILEDS_TEMPLATE);
    this.populateTemplate();
  }

  private void extractKeys() {
    this.$FIELDS = (ArrayList) this.parts.getOrDefault(KConst.$FIELDS, null);
  }

  private void dispatch() {
    for (Map field : this.$FIELDS) {
      FieldResolver fieldResolver = new FieldResolver(field);
      this.st.add(SConst.field, fieldResolver.getString());
    }
  }

  private void populateTemplate() {
    this.extractKeys();
    this.dispatch();
  }
  @Override
  public String getString() {
    return this.st.render();
  }
}
