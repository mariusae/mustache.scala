package org.monkey.mustache

/**
 * Interpret a Mustache AST.
 */

import scala.collection.JavaConversions._

import java.io.{StringWriter, Writer}
import org.apache.commons.lang.StringEscapeUtils

class Eval {
  protected def escape(s: String): String =
    StringEscapeUtils.escapeHtml(s)

  /**
   * Evaluate the AST given by @node@ relative to the dictionary in
   * @dictionary@, returning the result as a string..
   */
  def apply(node: Node, dictionary: Dictionary): String = {
    val out = new StringWriter
    apply(node, dictionary, out)
    out.toString
  }

  /**
   * Evaluate the AST given by @node@ relative to the dictionary in
   * @dictionary@, writing the content to @out@.
   */
  def apply(node: Node, dictionary: Dictionary, out: Writer) {
    node match {
      case RootNode() =>
        node foreach { apply(_, dictionary, out) }

      case SectionNode(name) =>
        dictionary(name) match {
          case Dictionaries(_) =>
            dictionary.push(name) foreach { dictionary =>
              node foreach { apply(_, dictionary, out) }
            }

          case Bool(true) | Data(_) =>
            node foreach { this(_, dictionary, out) }

          case Bool(false) | NoValue=>
            // no output
        }

      case InvertedSectionNode(name) =>
        dictionary(name) match {
          case NoValue | Bool(false) =>
            node foreach { this(_, dictionary, out) }
          case _ =>
            // no output
        }

      case InterpolationNode(name, doEscape) =>
        val data = dictionary(name) match {
          case Data(data) => data
          case _ => ""
        }

        out.write(if (doEscape) escape(data) else data)

      case PartialNode(name) =>
        dictionary.getPartial(name) foreach { mustache =>
          mustache(dictionary, this, out)
        }

      case DataNode(data) =>
        out.write(data)
    }
  }
}
