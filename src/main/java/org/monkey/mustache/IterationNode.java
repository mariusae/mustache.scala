package org.monkey.mustache;

class IterationNode extends Node {
  private String name;

  IterationNode(String name) {
    this.name = name;
    System.out.println("iter.a");
  }

  void fill(Context ctx, StringBuffer content) {
    // Nothing.
  }

  protected String nodeString() {
    return "iteration: " + this.name;
  }
}
