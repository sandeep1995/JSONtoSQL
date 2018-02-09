package com.fusioncharts.fusionboard.resolver;

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
  private final static String SELECT_TEMPLATE = "SELECT <fields> FROM <source>";

  public QuerySelectResolver(Map parts){
    this.parts = parts;
    this.st = new ST(SELECT_TEMPLATE);
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

    if (this.$QUERY_SELECT.containsKey(KConst.source)) {
      Map source = (Map) this.$QUERY_SELECT.get(KConst.source);
      SourceResolver sourceResolver = new SourceResolver(source);
      this.st.add(KConst.source, sourceResolver.getString());
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
