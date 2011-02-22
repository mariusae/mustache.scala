package org.monkey.mustache;

interface Node extends Iterable<Node> {
  void addChild(Node child);
}
