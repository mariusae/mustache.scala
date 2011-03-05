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

    "escape HTML entities" in {
      (mustache(Dictionary().data("world", "<WORLD>"))
       must be_==("hello &lt;WORLD&gt;!"))
    }
  }

  "unescaped interpolation" should {
    "not escape" in {
      val mustaches = Seq(
        "hello {{{world}}}!",
        "hello {{&world}}!",
        "hello {{& world}}!"
      ) map { s => new Mustache(Source.fromString(s)) }

      mustaches foreach { mustache =>
        (mustache(Dictionary().data("world", "<world>"))
         must be_==("hello <world>!"))
      }
    }
  }
}
