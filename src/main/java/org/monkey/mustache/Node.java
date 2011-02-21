package org.monkey.mustache;

import java.util.List;
import java.util.ArrayList;

abstract class Node {
  private List<Node> children = new ArrayList<Node>();

  abstract void fill(Context ctx, StringBuffer content);

  List<Node> children() {
    return children;
  }

  void addChild(Node child) {
    children.add(child);
  }

  abstract protected String nodeString();

  @Override
  public String toString() {
    StringBuffer output = new StringBuffer();
    boolean first = true;

    output.append("(<" + nodeString() + ">");

    for (Node child : children) {
      if (first)
        output.append(",");
      else
        first = false;

      output.append(" ");
      output.append(child.toString());
    }

    output.append(")");

    return output.toString();
  }
}
