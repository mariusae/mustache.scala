package org.monkey.mustache

import org.specs.Specification

object DictionarySpec extends Specification {
  "Dictionary[values]" should {
    val d = Dictionary()

    "yield no values when empty" in {
      d("hey") must be_==(NoValue)
      d.push("hey") must beEmpty
    }

    "recall values" in {
      d.data("hey", "there")("hey") must be_==(Data("there"))
    }

    "recall dictionaries" in {
      val inner = Dictionary().data("there", "yep")
      val dd = d.dictionary("hey", inner)
      dd("hey") must be_==(Dictionaries(Seq(inner)))
    }

    "look up the stack" in {
      val dd = d.data("hey", "lower").dictionary(
        "dict", Dictionary().data("hey", "upper"))

      dd("hey") must be_==(Data("lower"))
      dd.push("dict") must haveSize(1)
      dd.push("dict").head("hey") must be_==(Data("upper"))
    }
  }

  "Dictionary[partials]" should {
    val m = new Mustache("1")
    val m2 = new Mustache("2")
    val d = Dictionary()

    "yield no values when empty" in {
      d.getPartial("foo") must beNone
    }

    "recall values" in {
      d.partial("m", m).getPartial("m") must beSome(m)
    }

    "look up the stack" in {
      val dd = d.partial("p", m)
        .dictionary("dict", Dictionary().partial("p", m2))
        .dictionary("dict2", Dictionary())

      dd.getPartial("p") must beSome(m)
      dd.push("dict") must haveSize(1)
      dd.push("dict").head.getPartial("p") must beSome(m2)

      dd.push("dict2") must haveSize(1)
      dd.push("dict2").head.getPartial("p") must beSome(m)
    }
  }
}
