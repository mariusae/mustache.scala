package org.monkey.mustache;

class InterpolationNode extends Node {
  private String name;
  
  InterpolationNode(String name) {
    this.name = name;
  }

  void fill(Context ctx, StringBuffer content) {
    // Nothing.
  }

  protected String nodeString() {
    return "interpolate: " + this.name;
  }
}