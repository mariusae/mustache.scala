package org.monkey.mustache

import io.Source

import org.specs.Specification

object MustacheSpec extends Specification {
  "basic interpolation" should {
    val mustache = new Mustache(Source.fromString("hello {{world}}!"))
    "substitute as expected" in {
      (mustache(Dictionary().data("world", "WORLD"))
       must be_==("hello WORLD!"))
    }

    "ignore missing values" in {
      mustache(Dictionary()) must be_==("hello !")
    }
  }
}
