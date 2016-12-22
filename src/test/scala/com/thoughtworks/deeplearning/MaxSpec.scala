package com.thoughtworks.deeplearning

import com.thoughtworks.deeplearning.Bp2DArray._
import com.thoughtworks.deeplearning.Lift._
import com.thoughtworks.deeplearning.BpHList._
import com.thoughtworks.deeplearning.BpDouble._
import com.thoughtworks.deeplearning.BpAny._
import org.scalatest.{FreeSpec, Matchers}
import ToLayer._
import com.thoughtworks.deeplearning.Poly.MathOps
import com.thoughtworks.deeplearning.Poly.MathFunctions._

/**
  * @author 杨博 (Yang Bo) &lt;pop.atry@gmail.com&gt;
  */
final class MaxSpec extends FreeSpec with Matchers {

  "complex input" in {
    def buildNeuralNetwork(implicit input: DoubleBackProgationType :**: Bp2DArray :**: BpHNil) = {
      val m0 = max(1.0, 2.0.toLayer)
      val m1: input.To[DoubleBackProgationType] = max(m0, 1.6)
      val m2 = max(m0.toLayer, m1.toLayer)
    }

    buildNeuralNetwork
  }

  "DoubleBackProgationType input" in {
    def buildNeuralNetwork(implicit input: DoubleBackProgationType) = {
      val m0 = max(1.0, 2.0)
      val m1: input.To[DoubleBackProgationType] = max(input, 1.6)
      val m2 = max(m0, m1)
      val m3 = max(0.0, max(m0, max(max(input, m1), m2)))
      val m4: input.To[DoubleBackProgationType] = max(1.6, input)
    }

    buildNeuralNetwork
  }

  "Bp2DArray input" in {
    def buildNeuralNetwork(implicit input: Bp2DArray) = {
      val m0: input.To[DoubleBackProgationType] = max(1.0, 2.0)
      val m1: input.To[Bp2DArray] = max(input, 1.6)
      val m2 = max(input, m0)
      val m3: input.To[DoubleBackProgationType] = max(m0, 2.0)
      val m4 = max(max(max(max(m1, 3.0), m3), m0), 7.2)

      "max(0.1, input)" shouldNot compile
    }

    buildNeuralNetwork
  }

}
