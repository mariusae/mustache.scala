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

case class IterationNode(name: String) extends AbstractNode {
  override def toString = "IterationNode(%s, %s)".format(name, super.toString)
}

case class InterpolationNode(name: String) extends AbstractNode {
  override def toString =
    "InterpolationNode(%s, %s)".format(name, super.toString)
}

case class DataNode(data: String) extends AbstractNode {
  override def toString =
    "DataNode(len=%d, %s)".format(data.size, super.toString)
}

object TheNodeFactory extends NodeFactory {
  def newIterationNode(name: String)     = IterationNode(name)
  def newInterpolationNode(name: String) = InterpolationNode(name)
  def newDataNode(data: String)          = DataNode(data)
}

