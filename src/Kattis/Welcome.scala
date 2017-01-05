package Kattis

import java.util

import scala.collection.mutable

/**
 * Created by Rahul on 12/27/16.
 */
class Welcome(val str : String) {
  val strMap = new mutable.HashMap[Char,util.TreeSet[Int]]()
  var index1 = 0
  str.foreach(ch => {
    if (false == strMap.contains(ch)) {
      strMap.+=((ch,new util.TreeSet[Int]))
    }

    strMap.get(ch).get.add(index1)
    index1 = index1 + 1
  })

  //println(strMap)
  val fixedStr = "welcome to code jam"
  def eval(indexForFixedStr : Int,lastIndexInOrder : Int) : BigInt = {
    //println(indexForFixedStr + " " + lastIndexInOrder)
    if (indexForFixedStr >= fixedStr.length) {
      1
    }else {
      val currentChar = fixedStr.charAt(indexForFixedStr)
      //println(currentChar)
      val pendingSet = strMap.get(currentChar)
      pendingSet match {
        case Some(pendingTreeSet) => {

          val greaterThanCurrentIndexSet = pendingTreeSet.tailSet(lastIndexInOrder,true)
          //println(">> " + greaterThanCurrentIndexSet)
          if (greaterThanCurrentIndexSet.size() == 0) {
            0
          }else {
            var total = BigInt.int2bigInt(0)
            val itr = greaterThanCurrentIndexSet.iterator()
            while (itr.hasNext) {
              val next = itr.next()
              total = total + eval(indexForFixedStr+1,next+1)

            }

            total
          }
        }
        case None =>
          BigInt.int2bigInt(0)
      }
    }
  }


}

object Welcome {
  def main(str : Array[String]) : Unit  = {
    val count = scala.io.StdIn.readLine().toInt
    for (i <- 1 to count) {
      val l = scala.io.StdIn.readLine()
      val welcome = new Welcome(l)
      val result = welcome.eval(0, -1).toString()
      if (result.length > 4) {
        println("Case #" + i + ": "  + result.substring(result.length - 4))
      } else {
        val pendingZeroes = "0" * (4 - result.length)

        println("Case #" + i + ": " + pendingZeroes + result)
      }
    }
  }
  //val welcome = new Welcome("wweellccoommee to code qps jam")

}
