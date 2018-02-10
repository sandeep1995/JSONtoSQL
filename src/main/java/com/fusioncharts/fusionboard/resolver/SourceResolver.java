package com.fusioncharts.fusionboard.resolver;

import com.fusioncharts.fusionboard.utils.KConst;
import com.fusioncharts.fusionboard.utils.SConst;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sandeepacharya on 09/02/18.
 */
public class SourceResolver implements PartResolver {
  private Map parts;
  private ArrayList <Map> $SOURCES;
  private ST st;

  public SourceResolver(Map parts) {
    this.parts = parts;
    this.st = new ST(SConst.SOURCES_TEMPLATE);
    this.populateTemplate();
  }

  private void extractKeys() {
    this.$SOURCES = (ArrayList) this.parts.getOrDefault(KConst.$SOURCES, null);
  }

  private void dispatch() {
    for (Map source : this.$SOURCES) {
      if (source.containsKey(KConst.$TABLE)) {
        TableResolver tableResolver = new TableResolver(source);
        this.st.add(SConst.source, tableResolver.getString());
      }
      // TODO: source can be another QUERY_SELECT
      if (source.containsKey(KConst.$QUERY_SELECT)) {
        QuerySelectResolver selectResolver = new QuerySelectResolver(source);
        this.st.add(SConst.source, selectResolver.getString());
      }
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