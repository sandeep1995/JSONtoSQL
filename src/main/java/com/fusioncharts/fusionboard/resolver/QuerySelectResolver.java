package com.fusioncharts.fusionboard.resolver;

import com.fusioncharts.fusionboard.utils.SConst;
import org.stringtemplate.v4.*;

import com.fusioncharts.fusionboard.utils.KConst;
import java.util.Map;

/**
 * Created by sandeepacharya on 09/02/18.
 */
public class QuerySelectResolver implements PartResolver {

  private Map parts;
  private Map $QUERY_SELECT;
  private String alias;
  private ST st;

  public QuerySelectResolver(Map parts){
    this.parts = parts;
    this.st = new ST(SConst.QUERY_SELECT_TEMPLATE);
    this.populateTemplate();
  }

  private void extractKeys() {
    this.$QUERY_SELECT = (Map) this.parts.getOrDefault(KConst.$QUERY_SELECT, null);
    this.alias = (String) this.parts.getOrDefault(KConst.alias, null);
  }

  private void dispatch() {

    if (this.$QUERY_SELECT.containsKey(KConst.fields)) {
      Map fields = (Map)this.$QUERY_SELECT.get(KConst.fields);
      FieldsResolver fieldsResolver = new FieldsResolver(fields);
      this.st.add(KConst.fields, fieldsResolver.getString());
    }

    if (this.$QUERY_SELECT.containsKey(KConst.sources)) {
      Map source = (Map) this.$QUERY_SELECT.get(KConst.sources);
      SourceResolver sourceResolver = new SourceResolver(source);
      this.st.add(KConst.sources, sourceResolver.getString());
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
