package org.monkey.mustache

/**
 * Interpret a Mustache AST.
 */

import scala.collection.JavaConversions._
import scala.collection.immutable

object Eval {
  /**
   * Evaluate the AST given by @node@ relative to the dictionary in
   * @dictionary@.
   */
  def apply(node: Node, dictionary: Dictionary): String = {
    node match {
      case RootNode() =>
        node map { Eval(_, dictionary) } mkString

      case IterationNode(name) =>
        dictionary.push(name) flatMap { dictionary =>
          node map (Eval(_, dictionary))
        } mkString

      case InterpolationNode(name) =>
        val data = dictionary(name) flatMap {
          case Data(data) => Some(data)
          case Dictionaries(_) => None
        }

        data getOrElse ""

      case DataNode(data) => data
    }
  }
}
