package Hackercup.DynamicProgramming

package Hackercup

import scala.collection.mutable

import scala.math.BigDecimal.RoundingMode


/**
 * Created by Rahul on 1/8/17.
 */

class Rational (val nr : BigInt,val dr : BigInt) extends Ordered[Rational]{
  if (dr == 0) {
    //println("throw error")
    throw new IllegalArgumentException
  }
//  def gcd(a : Long,b : Long) : Long = {
//    if (b == 0) {
//      a
//    }else {
//
//      gcd(b,a % b)
//    }
//  }

  def add(other : Rational) : Rational = {
    //println(other + " " + this)

    val top = nr * other.dr + dr * other.nr
    val bottom = other.dr * dr
    //val g = gcd(top,bottom)
    val g = top.gcd(bottom)
    //println(this + " " + other)
    new Rational(top/g,bottom/g)
  }

  def multiply(other : Rational) : Rational = {
    val top = nr * other.nr
    val bottom = dr * other.dr
    val g = top.gcd(bottom)
    new Rational(top/g,bottom/g)
  }

  def subs(other : Rational) : Rational = {
    val top = nr * other.dr - dr * other.nr
    val bottom = other.dr * dr
    val g = top.gcd(bottom)
    new Rational(top/g,bottom/g)
    //new Rational((nr * other.dr - dr * other.nr),(other.dr * dr))
  }


//  def simplify() : Rational = {
//
//
//    val g = BigInt(nr).gcd(BigInt(dr))
//    new Rational(nr/g.toLong,dr/g.toLong)
//  }

  def convertValidString(precision : Int) : String = {
    val d = BigDecimal(nr.toDouble/dr.toDouble).setScale(precision,RoundingMode.CEILING)
    d.toString()

  }

  override def toString = s"Rational($nr, $dr)"

  override def compare(that: Rational): Int = {
    val product = dr * that.dr
    val leftMultiple = product/dr
    val leftValue = nr * leftMultiple

    val rightMultiple = product/that.dr
    val rightValue = that.nr * rightMultiple

    leftValue.compare(rightValue)

  }
}


object ZeroRational extends Rational(0,1)
object OneRational extends Rational(1,1)

class DieDP(val n : Int,val maxIteration : Int) {
  val rows = maxIteration+1
  val cols = (n * maxIteration)+ 1
  val arr = Array.ofDim[Rational](rows,cols)
  init()
  def init() : Unit = {
    for (i <- 0 to rows-1) {
      for (j <- 0 to cols-1) {
        arr(i)(j) = ZeroRational
      }
    }
    for (i <- 1 to cols-1) {
      val current = i
      if (current <= n) {
        arr(1)(i) = new Rational(1,n)

      }
    }

    val constPr = new Rational(1,n)

    for (i <- 2 to rows-1) {
      //println("For i " + i)
      val min = i
      val max = i*n
      //println("min = " + min + " " + max)
      for (j <- min to max) {
        //println(" j = " + j)
        var sum : Rational = ZeroRational
        for (k <- j to 1 by -1) {

          if (k <= n) {
            val diff = j - k

            val pr = arr(i - 1)(diff).multiply(constPr)
            //println(" k = " + k + " diff " + diff + " " + pr)
            sum = sum.add(pr)
          }
        }

        arr(i)(j) = sum
      }
    }
  }

  def count(pendingCount : Int,pendingCast : Int) : Rational = {
    arr(pendingCast)(pendingCount)
  }

  def minCount(pendingCount : Int,pendingCast : Int) : Rational = {
    var sum : Rational= ZeroRational
    for (x <- pendingCount to cols-1) {
      sum = sum.add(count(x,pendingCast))
    }

    sum
  }
}

object DieManager {
  val MAX_ITERATION = 20
  val map = new mutable.HashMap[Int,DieDP]()
  def getDie(faces : Int) : DieDP = {
    if (map.contains(faces) == false) {
      val die = new DieDP(faces,MAX_ITERATION)
      map.+=((faces,die))
    }

    map.get(faces).get

  }
}


class EventProbability(val die: DieDP) {
  def getMinEvent(event : Int,minValue : Int,maxValue : Int) : (List[Int],Boolean) = {
    val left = event-minValue
    val right = maxValue-event
    if (right <= left) {
      val r = event to maxValue
      (r.toList,true)
    }else {
      val r = minValue to event-1
      (r.toList,false)
    }
  }
  def eval(event : Int,castNumber : Int) : Rational = {
    val minValue = 1 * castNumber
    val maxValue = castNumber * die.n
    if (event <= maxValue) {
      val retValue = getMinEvent(event,minValue,maxValue)
      val lst = retValue._1
      //println(lst + " " + retValue._2)
      var pr = new Rational(0,1)

      lst.foreach(event => {
        pr = pr.add(die.count(event,castNumber))
      })

      if (retValue._2 == false) {
        OneRational.subs(pr)
      }else {
        pr
      }
    }else {
      ZeroRational
    }
  }
}

class Spell(val die: DieDP,val adder : Int) {
  def cast(valueRequired : Int,castNumber : Int) : Rational = {
    //println("Inside cast")
    //val minValue = adder - 1* castNumber
    val maxValue = valueRequired - adder
    val eventPr = new EventProbability(die)
    //println(maxValue)
    eventPr.eval(maxValue,castNumber)
  }
}
class SpellExecuter() {


  def execute(expr: String,minSpell : Int) : Rational = {
    val parseStep1 = expr.split("d")
    val castNumber = parseStep1(0).toInt
    val parseStep2 = parseStep1(1).split("\\+|\\-")
    var adder = 0
    if (parseStep2.size > 1) {
      if (parseStep1(1).contains("+") == true) {
        adder = parseStep2(1).toInt
      }else {
        adder = parseStep2(1).toInt * -1
      }
    }

    val dieType = parseStep2(0).toInt

    val die = DieManager.getDie(dieType)
    val spell = new Spell(die,adder)

    //println("DieType = " + dieType + " castCount = " + castNumber  + " " + " minValueNeeded " + minSpell + " adder " + adder)
    spell.cast(minSpell,castNumber)


  }
}

object ZombieFighterMain extends App {
//  val dieDP = new DieDP(4,20)
//  dieDP.init()
////  for (i <- 0 to dieDP.rows-1) {
////    for (j <- 0 to dieDP.cols-1) {
////      print(dieDP.arr(i)(j) + " ")
////    }
////    println()
////  }
//
//  println(dieDP.minCount(6,2))

  //println(dieDP.count(2,4))
  //val die = new Die(4)
  //  //println(die.count(12,3).simplify())
  //  //val eventPr = new EventProbability(die)
  //  //println(eventPr.eval(4,2))
  //val spell = new Spell(die,4)
  //println(spell.cast(8,1))
    val testCases = scala.io.StdIn.readLine().toInt
    val spellExecuter = new SpellExecuter

    for (i <- 0 to testCases-1) {
      val metaInfo =  scala.io.StdIn.readLine().split(" ")
      val minScoreNeeded = metaInfo(0).toInt
      val possibleScores = metaInfo(1).toInt
      val possibleScoresStr = scala.io.StdIn.readLine().split(" ")
      var maxValue :Rational = ZeroRational

      for (j <- 0 to possibleScoresStr.size-1) {
        val pr = spellExecuter.execute(possibleScoresStr(j),minScoreNeeded)
        //println(" "  + pr.convertDouble(6))
        if (pr.compare(maxValue) == 1) {
          maxValue = pr
        }
      }

      println("Case #" + (i+1) + ": " + maxValue.convertValidString(6))


    }
}

