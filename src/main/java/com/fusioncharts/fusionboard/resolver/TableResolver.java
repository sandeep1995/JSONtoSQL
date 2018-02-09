package com.fusioncharts.fusionboard.resolver;

import com.fusioncharts.fusionboard.utils.KConst;
import com.fusioncharts.fusionboard.utils.SConst;
import org.stringtemplate.v4.ST;

import java.util.Map;

/**
 * Created by sandeepacharya on 09/02/18.
 */
public class TableResolver implements PartResolver{

  private Map parts, $TABLE;
  private String name, alias, fieldStr;
  private ST st;

  public TableResolver(Map parts) {
    this.parts = parts;
    this.st = new ST(SConst.TABLE_TEMPLATE);
    this.populateTemplate();
  }

  private void extractKeys() {
    this.$TABLE = (Map) this.parts.getOrDefault(KConst.$TABLE, null);
    this.name = (String) this.$TABLE.getOrDefault(KConst.name, null);
    this.alias = (String) this.$TABLE.getOrDefault(KConst.alias, null);
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
