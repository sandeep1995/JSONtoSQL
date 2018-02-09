package com.fusioncharts.fusionboard.resolver;

import com.fusioncharts.fusionboard.utils.KConst;
import com.fusioncharts.fusionboard.utils.SConst;
import org.stringtemplate.v4.ST;

import java.util.Map;

/**
 * Created by sandeepacharya on 09/02/18.
 */
public class FieldResolver implements PartResolver{

  private Map parts, $FIELD;
  private String name, alias;
  private ST st;

  public FieldResolver(Map parts) {
    this.parts = parts;
    this.st = new ST(SConst.FIELD_TEMPLATE);
    this.populateTemplate();
  }

  private void extractKeys() {
    this.$FIELD = (Map) this.parts.getOrDefault(KConst.$FIELD, null);
    this.name = (String) this.$FIELD.getOrDefault(KConst.name, null);
    this.alias = (String) this.$FIELD.getOrDefault(KConst.alias, null);
  }

  private void dispatch() {
    this.st.add(KConst.name, this.name);
    if (this.alias != null) {
      this.st.add(SConst.isAlias, true);
      this.st.add(KConst.alias, this.alias);
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
