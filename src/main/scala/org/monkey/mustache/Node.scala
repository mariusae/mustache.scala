package org.monkey.mustache

/**
 * Concrete implementations of AST nodes, used by the ANTLR grammar to
 * construct the actual AST (via @TheNodeFactory@).
 */

import collection.JavaConversions._
import collection.mutable.ArrayBuffer

abstract class AbstractNode extends Node {
  private[this] val children = new ArrayBuffer[Node]

  def addChild(child: Node) {
    children += child
  }

  def iterator = children.iterator

  override def toString = "[%s]".format(
    this map { _.toString } mkString ", ")
}

case class RootNode() extends AbstractNode {
  override def toString = "Root(%s)".format(super.toString)
}

case class SectionNode(name: String) extends AbstractNode {
  override def toString = "SectionNode(%s, %s)".format(name, super.toString)
}

case class InvertedSectionNode(name: String) extends AbstractNode {
  override def toString =
    "InvertedSectionNode(%s, %s)".format(name, super.toString)
}

case class InterpolationNode(name: String, escape: Boolean)
  extends AbstractNode
{
  override def toString =
    "InterpolationNode(%s, escape=%s, %s)".format(name, escape, super.toString)
}

case class DataNode(data: String) extends AbstractNode {
  override def toString =
    "DataNode(len=%d, %s)".format(data.size, super.toString)
}

object TheNodeFactory extends NodeFactory {
  def newSectionNode(name: String) =
    SectionNode(name)
  def newInvertedSectionNode(name: String) =
    InvertedSectionNode(name)
  def newInterpolationNode(name: String) =
    InterpolationNode(name, true)
  def newUnescapedInterpolationNode(name: String) =
    InterpolationNode(name, false)
  def newDataNode(data: String) =
    DataNode(data)
}

