package org.monkey.mustache;

class RootNode extends Node {
  void fill(Context ctx, StringBuffer content) {
    // Nothing
  }

  protected String nodeString() {
    System.out.println("oh hey");
    System.out.println("not bad");
    return "root";
  }
}