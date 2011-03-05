package org.monkey.mustache

/**
 * Interpret a Mustache AST.
 */

import scala.collection.JavaConversions._
import scala.collection.immutable

import org.apache.commons.lang.StringEscapeUtils

object Eval {
  private[this] def escape(s: String): String =
    StringEscapeUtils.escapeHtml(s)

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

      case InterpolationNode(name, doEscape) =>
        val data = dictionary(name) match {
          case Data(data) => data
          case Dictionaries(_) | NoValue => ""
        }

        if (doEscape) escape(data) else data

      case DataNode(data) => data
    }
  }
}
