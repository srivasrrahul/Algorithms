package Kattis

import scala.collection.mutable.ListBuffer


/**
 * Created by Rahul on 12/27/16.
 */

class Sum(val lst : List[Int]) {
  def getExactValue(y : Int) : Tuple2[Int,Int] = {
    val rem = y % 10
    ((y - rem) /10,rem)
  }
  def value() : Int = {
    var sum = 0
    lst.foreach(x => {
      val retValue = getExactValue(x)
      sum = sum + Math.pow(retValue._1,retValue._2).toInt
    })

    sum
  }
}
object Pot {

  def main(args: Array[String]): Unit = {
    val line = scala.io.StdIn.readLine().toInt
    val lst = new ListBuffer[Int]
    for (i <- 0 to line-1) {
      lst.+=(scala.io.StdIn.readLine().toInt)
    }

    val s = new Sum(lst.toList)
    println(s.value())
  }


//  val lst = new ListBuffer[Int]
//  lst.+=(212)
//  lst.+=(1253)
//  val s = new Sum(lst.toList)
//  println(s.value())
//  val fileName = ""
//  val stream = Source.fromFile(fileName).getLines()
}
