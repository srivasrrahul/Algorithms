package ScalaSolutions.Barricade

import scala.collection.mutable.ListBuffer

/**
 * Created by rasrivastava on 10/17/16.
 */
class Barricade(val classStartBegin : Int, val classStartEnd : Int) {
  def getAvoidClasses(classes : List[Int]) : Int = {
    var noClasses = 0
    val range = Range(classStartBegin,classStartEnd)
    //println(range)
    classes.foreach(classPeriod => {
      if (range.contains(classPeriod)) {
        noClasses += 1
      }
    })

    noClasses
  }
}

object Main extends App {
  val line = readLine()
  val lineArr = line.split(" ")
  val n = lineArr(0).toInt
  val a = lineArr(1).toInt
  val b = lineArr(2).toInt
  val barricade = new Barricade(a,b)
  val lstBuffer = new ListBuffer[Int]
  for (i <- 0 to n-1) {
    lstBuffer.+=(readLine().toInt)
  }

  val lst = lstBuffer.toList
  println(lst.size - barricade.getAvoidClasses(lst))
}
