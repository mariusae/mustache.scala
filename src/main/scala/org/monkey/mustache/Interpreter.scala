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

      case SectionNode(name) =>
        dictionary(name) match {
          case Dictionaries(_) =>
            dictionary.push(name) flatMap { dictionary =>
              node map { Eval(_, dictionary) }
            } mkString

          case Bool(true) | Data(_) =>
            node map { Eval(_, dictionary) } mkString

          case Bool(false) | NoValue=>
            ""
        }

      case InvertedSectionNode(name) =>
        dictionary(name) match {
          case NoValue | Bool(false) =>
            node map { Eval(_, dictionary) } mkString
          case _ =>
            ""
        }

      case InterpolationNode(name, doEscape) =>
        val data = dictionary(name) match {
          case Data(data) => data
          case _ => ""
        }

        if (doEscape) escape(data) else data

      case PartialNode(name) =>
        dictionary.getPartial(name) map { mustache =>
          mustache(dictionary)
        } getOrElse ""

      case DataNode(data) => data
    }
  }
}
