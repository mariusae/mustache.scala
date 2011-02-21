package org.monkey.mustache

import collection.JavaConversions._
import collection.mutable

import java.io.File

import org.antlr.runtime._

object Main {
  def main(args: Array[String]) {
    // TODO: can we match an empty string? (just EOF)

    val stream = new ANTLRStringStream(
      "something something foo bar {{ohhey}} or something else {{#foo}}\n{{#biz}}oh {{foo}} yeah{{/biz}}{{bar}}{{/foo}}")
    val lexer = new MustacheLexer(stream)

    var token: Token = lexer.nextToken()
    // while (token ne Token.EOF_TOKEN) {
    //   println("T", token)
    //   token = lexer.nextToken()
    // }

    val tokens = new CommonTokenStream(lexer)
    val parser = new MustacheParser(tokens)

    // parser.context.put("ohhey", "OKAY")
    // parser.context.put("bar", "YAY")
    // Build "context" data structure that does the right thing
    // wrt. stacks, etc. -- allows to look up by name.

    parser.toplevel()
    println(parser.root)
  }
}
