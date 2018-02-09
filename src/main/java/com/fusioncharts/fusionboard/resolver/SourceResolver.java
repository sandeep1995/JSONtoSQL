package com.fusioncharts.fusionboard.resolver;

import java.util.Map;

/**
 * Created by sandeepacharya on 09/02/18.
 */
public class SourceResolver implements PartResolver {
  private Map parts;
  public SourceResolver(Map parts) {
    this.parts = parts;
  }
  @Override
  public String getString() {
    return null;
  }
}
