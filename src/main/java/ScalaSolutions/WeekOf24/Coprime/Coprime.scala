package ScalaSolutions.WeekOf24.Coprime

import java.util.Scanner

import scala.collection.mutable

/**
 * Created by rasrivastava on 10/10/16.
 */
class GCD {
  val map = new mutable.HashMap[Tuple2[Int,Int],Int]()
  def perform( m : Int, n : Int) : Int = {
    def calculate(x: Int, y: Int) : Int = {
      if (x > y) {
        calculate(y, x)
      } else {
        if (x == 1) {
          1
        } else {
          val rem = y % x
          if (rem == 0) {
            map.+=((Tuple2(x, y), x))
            x
          } else {
            val tuple = new Tuple2[Int, Int](rem, x)
            if (map.contains(tuple)) {
              //println("Cache " + x + " " + y)
              map.get(tuple).get
            } else {
              calculate(rem, x)
            }
          }
        }
      }
    }

    val tuple = new Tuple2[Int, Int](m, n)
    if (map.contains(tuple)) {
      //println("Cache saved")
      map.get(tuple).get
    }else {
      val res = calculate(m,n)
      map.+=((tuple,res))
      res
    }



  }


}
class Coprime {
  //val map = new mutable.HashMap[Tuple2[Int,Int],Int]()
  val gcd = new GCD()
//  def gcd(x : Int,y : Int) : Int = {
//    if (x > y) {
//      gcd(y,x)
//    }else {
//      if (x == 1) {
//        1
//      }else {
//        val rem = y % x
//        if (rem == 0) {
//          map.+=((Tuple2(x,y),x))
//          x
//        } else {
//          val tuple = new Tuple2[Int,Int](rem,x)
//          if (map.contains(tuple)) {
//            println("Cache " + x + " " + y)
//            map.get(tuple).get
//          }else {
//            gcd(rem, x)
//          }
//        }
//      }
//    }
//  }

  def coprimeCount(k : Int) : Int = {
    var count = 0
    val limit  = Math.sqrt(k).toInt
    for (p <- 2 to limit) {

      val qRemainder = k % p
      if (qRemainder == 0) {
        val qCalculated = k / p
        if (qRemainder == 0 && qCalculated >= p) {

          if (gcd.perform(p, qCalculated) == 1) {
            count = count + 1
          }

        }
      }
    }

    //println("For k " + k + " " + count )
    count
  }

  def coprimeOverallCount(n : Int) : Int = {
    var count = 0
    for (k <- 1 to n) {
      count = count + coprimeCount(k)
    }

    count
  }



}

object Solution{
  def main(args : Array[String]) : Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt()
    val coprime = new Coprime
    println(coprime.coprimeOverallCount(n))
  }
}
