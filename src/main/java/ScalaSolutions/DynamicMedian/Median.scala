package ScalaSolutions.DynamicMedian

import java.util



/**
 * Created by rasrivastava on 10/14/16.
 */

class MultiIntSet {
  val map = new util.TreeMap[Long,Int]()
  var size = 0
  def add(x : Long) : Unit = {
    if (false == map.containsKey(x)) {
      map.put(x,0)
    }
    val count = map.get(x)
    map.put(x,count+1)
    size = size + 1
  }

  def remove(x : Long) : Unit = {
    val count = map.get(x)
    if (count == 1) {
      map.remove(x)
    }else {
      map.put(x,count-1)
    }

    size = size-1
  }

  def contains(x : Long) : Boolean = {
    map.containsKey(x)
  }

  def first() : Long = {
    map.firstKey()
  }

  def last() : Long = {
    map.lastKey()
  }

  def print() :Unit = {
    println(map)
  }
}
class RunningMedian {

  def addValue(value : Int) : Unit = {
//    if (minSet.size == maxSet.size) {
//      if (minSet.size > 0) {
//        if (value <= minSet.first()) {
//          maxSet.add(value)
//        }else {
//          val minSetFirst = minSet.first()
//          minSet.remove(minSetFirst)
//          maxSet.add(minSetFirst)
//          minSet.add(value)
//        }
//      }else {
//        maxSet.add(value)
//      }
//    }else {
//      //Size is unequal
//      if (value > maxSet.last()) {
//        minSet.add(value)
//      }else {
//        val maxSetLast = maxSet.last()
//        maxSet.remove(maxSetLast)
//        minSet.add(maxSetLast)
//        maxSet.add(value)
//      }
//    }
    if (minSet.size == 0 && maxSet.size == 0) {
      maxSet.add(value)
    }else {
      if (minSet.size == 0) {
        if (maxSet.last() >= value) {
          maxSet.add(value)
        }else {
          minSet.add(value)
        }
      }else {
        if (minSet.first() >= value) {
          maxSet.add(value)
        }else {
          minSet.add(value)
        }
      }
    }

    adjust()

  }

  def adjust() : Unit = {
    if (maxSet.size > minSet.size+1) {
      val lastElement = maxSet.last()
      maxSet.remove(lastElement)
      minSet.add(lastElement)
    }else {
      if (maxSet.size < minSet.size) {
        val firstElement = minSet.first()
        minSet.remove(firstElement)
        maxSet.add(firstElement)
      }
    }
  }
  def getMedian() : Double = {
    if (maxSet.size == minSet.size) {
      (maxSet.last() + minSet.first())/2.0
    }else {
      maxSet.last()
    }
  }

  def printMedian() : Unit = {
    if (maxSet.size == minSet.size) {
      val sum = maxSet.last() + minSet.first()
      if (sum % 2 == 0) {
        println(sum/2)
      }else {
        printf("%.1f",sum/2.0)
        println()
      }
    }else {
      println(maxSet.last())
    }
  }


  def remove(x : Int) : Unit = {

    var removed = false
    if (maxSet.contains(x)) {
      maxSet.remove(x)
      removed = true
    }

    if (removed == false  && minSet.contains(x)) {
      minSet.remove(x)
    }

  }

  def exists(x : Long) : Boolean = {
    maxSet.contains(x) || minSet.contains(x)
  }

  def print() : Unit = {
    println("Content Begin")
    println(maxSet.print())
    println(minSet.print())
    println("Content End")
  }

  //val maxSet = new util.TreeSet[Int]()
  //val minSet = new util.TreeSet[Int]()
  val maxSet = new MultiIntSet
  val minSet = new MultiIntSet



}

object Solution extends  App {

  val runningMedian = new RunningMedian()
  val sc = new java.util.Scanner (System.in)
  var n = sc.nextLine().split(" ")(0).toInt
  //println("N is " + n)
  //var a = new Array[Int](n);
  for(a_i <- 0 to n-1) {

    val l = sc.nextLine()
    val lStr = l.split(" ")
    //println("Command is " + l)
    val command = lStr(0)
    val value = lStr(1)
    val valueInt = value.toInt
    command match {

      case "r" => {
        if (runningMedian.exists(valueInt) == false) {
          println("Wrong!")
        }else {

          runningMedian.remove(valueInt)
          runningMedian.adjust()
          if (runningMedian.maxSet.size == 0) {
            println("Wrong!")
          }else {
            //printf("%.1f",runningMedian.getMedian())
            //println()
            runningMedian.printMedian()
          }



        }

        //println(runningMedian.print())
      }
      case "a" => {
        val valueInt = value.toInt
        runningMedian.addValue(valueInt)
        //printf("%.1f",runningMedian.getMedian())
        runningMedian.printMedian()
        //println()
        //println(runningMedian.print())
      }
    }

  }


}