package org.monkey.mustache

import io.Source

import org.specs.Specification

object MustacheSpec extends Specification {
  def eval(mustache: String, dictionary: Dictionary): String =
    (new Mustache(Source.fromString(mustache)))(dictionary)

  "data" should {
    "not interpret single '{'s" in {
      (new Mustache("{"))(Dictionary()) must be_==("{")
    }
  }

  "basic interpolation" should {
    val mustache = "hello {{world}}!"
    "substitute as expected" in {
      (eval(mustache, Dictionary().data("world", "WORLD"))
       must be_==("hello WORLD!"))
    }

    "ignore missing values" in {
      eval(mustache, Dictionary()) must be_==("hello !")
    }

    "escape HTML entities" in {
      (eval(mustache, Dictionary().data("world", "<WORLD>"))
       must be_==("hello &lt;WORLD&gt;!"))
    }
  }

  "unescaped interpolation" should {
    "not escape" in {
      val mustaches = Seq(
        "hello {{{world}}}!",
        "hello {{&world}}!",
        "hello {{& world}}!"
      )

      mustaches foreach { mustache =>
        (eval(mustache, Dictionary().data("world", "<world>"))
         must be_==("hello <world>!"))
      }
    }
  }

  "sections" should {
    "shouldn't evaluate on false values" in {
      val dictionaries = Seq(
        Dictionary(),
        Dictionary().data("true_value", "true"),
        Dictionary().bool("false_value", false)
      )

      dictionaries foreach { d =>
        (eval("{{#false_value}}something here{{/false_value}}outside", d)
         must be_==("outside"))
      }
    }

    "should evaluate on true values" in {
      val dictionaries = Seq(
        Dictionary().data("true_value", "something"),
        Dictionary().bool("true_value", true)
      )

      dictionaries foreach { d =>
        (eval("{{#true_value}}something here{{/true_value}}outside", d)
         must be_==("something hereoutside"))
      }
    }

    "evaluate lists" in {
      val d = Dictionary()
        .dictionary("hello", Dictionary().data("hello", "1"))
        .dictionary("hello", Dictionary().data("hello", "2"))
        .dictionary("hello", Dictionary().data("hello", "3"))

      (eval("{{#hello}}iter:{{hello}}{{/hello}}", d)
       must be_==("iter:1iter:2iter:3"))

    }

    "lookup the stack" in {
      val d = Dictionary()
        .data("variable", "0")
        .dictionary("d", Dictionary().data("variable", "1"))
        .dictionary("d", Dictionary())

      (eval("{{variable}}{{#d}}{{variable}}{{/d}}{{variable}}", d)
       must be_==("0100"))
    }

    "support nested sections" in {
      val d = Dictionary()
        .data("x", "X")
        .dictionary("dict", Dictionary()
          .data("y", "Y")
          .dictionary("dict", Dictionary()
            .data("y", "WTF")))

      (eval("{{#dict}}{{x}}{{y}}{{#dict}}{{y}}{{x}}{{/dict}}{{y}}{{/dict}}", d)
       must be_==("XYWTFXY"))
    }
  }

  "inverted sections" should {
    val m = "{{^v}}oh hey{{/v}}there"

    "evaluate when the value is false" in {
      eval(m, Dictionary())                  must be_==("oh heythere")
      eval(m, Dictionary().bool("v", false)) must be_==("oh heythere")
    }

    "not evaluate when the value is defined" in {
      eval(m, Dictionary().data("v", ""))                 must be_==("there")
      eval(m, Dictionary().bool("v", true))               must be_==("there")
      eval(m, Dictionary().dictionary("v", Dictionary())) must be_==("there")
    }
  }

  "comments" should {
    "be ignored" in {
      eval("hello{{! hey hey there}}", Dictionary()) must be_==("hello")
    }
  }

  "partials" should {
    "inline" in {
      val d = Dictionary()
        .partial("foopartial", new Mustache("hello {{world}}"))
        .data("world", "THERE!")

      eval("one {{world}} and {{> foopartial}}", d) must be_==(
        "one THERE! and hello THERE!"
      )
    }
  }
}
