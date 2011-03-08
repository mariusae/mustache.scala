package org.monkey.mustache

/**
 * Parsing and evaluation for Mustaches.
 */

import io.Source

import org.antlr.runtime._

/**
 * The @Mustache@ class parses the mustache in @source@ on
 * instantiation, throwing a parsing exception if it fails. The
 * mustache can later be evaluated by using apply().
 */

class Mustache(source: Source) {
  private[this] val root = {
    // TODO: implement an ANTLR CharStream directly from Source.
    val stream = new ANTLRStringStream(source.mkString)
    val lexer  = new MustacheLexer(stream)
    val tokens = new CommonTokenStream(lexer)
    val parser = new MustacheParser(tokens)

    parser.root        = new RootNode
    parser.nodeFactory = TheNodeFactory
    parser.toplevel()

    parser.root
  }

  def this(stringSource: String) = this(Source.fromString(stringSource))

  /**
   * Evaluates this mustache relative to the passed-in dictionary.
   */
  def apply(dictionary: Dictionary): String = Eval(root, dictionary)
}
