package org.monkey.mustache;

class DataNode extends Node {
  private String data;

  DataNode(String data) {
    this.data = data;
  }

  void fill(Context ctx, StringBuffer content) {
    /*
     * nothing here yet.
     */
    // Nothing.
  }

  protected String nodeString() {
    return "data: " + this.data;
  }
}
