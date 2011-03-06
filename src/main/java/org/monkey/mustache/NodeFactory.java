package org.monkey.mustache;

interface NodeFactory {
  Node newSectionNode(String name);
  Node newInvertedSectionNode(String name);
  Node newInterpolationNode(String name);
  Node newUnescapedInterpolationNode(String name);
  Node newDataNode(String data);
}