package org.monkey.deerstache

import org.specs.Specification
//import org.specs.mock.{ClassMocker, JMocker}

class SampleSpec extends Specification /* with JMocker with ClassMocker */ {
  "Sample" should {
    "run a test" in {
      23 mustEqual 23
    }
  }
}
