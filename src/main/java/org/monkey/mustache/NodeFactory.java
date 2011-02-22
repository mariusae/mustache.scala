package org.monkey.mustache;

interface NodeFactory {
  Node newIterationNode(String name);
  Node newInterpolationNode(String name);
  Node newDataNode(String data);
}