package AtCoder.AdditionAndSubstractionHad

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * Created by Rahul on 1/5/17.
 */
class AdditionAndSubstraction {

//  def maxSumDP(arr : Array[Long]) : Long = {
//    val array = Array.ofDim[Tuple2[Long,Long]](arr.size,arr.size)
//    for (i <- 0 to arr.size-1) {
//      array(i)(i) = (arr(i),arr(i))
//    }
//
//    val n = arr.length
//    for (s <- 0 to n-2) {
//      for (i <- 0 to n-s-1) {
//        val j = i + s
//        var maxSumObserved = Long.MinValue
//        var minSumObserved = Long.MaxValue
//        for (k <- i to j-1) {
//          val comb = new Array[Long](4)
//          val lst1 = array(i)(k)
//          val lst2 = array(k+1)(j)
//          if (arr(k) < 0) {
//            comb(0) = lst1._1 - lst2._1
//            comb(1) = lst1._1 - lst2._2
//            comb(2) = lst1._2 - lst2._1
//            comb(3) = lst1._2 - lst2._2
//            arr(k)
//          }else {
//            comb(0) = lst1._1 - lst2._1
//            comb(1) = lst1._1 - lst2._2
//            comb(2) = lst1._2 - lst2._1
//            comb(3) = lst1._2 - lst2._2
//          }
//          val localMax = comb.max
//          val localMin = comb.min
//          if (localMax > maxSumObserved) {
//            //println(i  + " " + maxSumObserved)
//            maxSumObserved = localMax
//          }
//
//          if (localMin < minSumObserved) {
//            minSumObserved = localMin
//          }
//        }
//      }
//    }
//
//  }
  val cache = new mutable.HashMap[(Int,Int),Tuple2[Long,Long]]()
  def maxSum(arr : Array[Long],start : Int,end : Int,depth : Int) : Tuple2[Long,Long] = {
//    for (i <- 0 to depth-1) {
//      print(" ")
//    }
//    println("start = " + start + "  end " + end)
    if (cache.contains((start,end))) {
      println("Cache hit " + (start,end))
      cache.get((start,end)).get
    }else {
      if (start == end) {
        (arr(start),arr(end))
      } else {
        val lstBuffer = new mutable.HashSet[Long]
        var maxSumObserved : Long = Int.MinValue
        var minSumObserved : Long = Int.MaxValue
        for (i <- start + 1 to end) {
          val current = arr(i)
          if (current < 0) {
            //Negate the value
            arr(i) = -current
            val lst1 : Tuple2[Long,Long] = maxSum(arr, start, i - 1, depth + 1)
            val lst2 : Tuple2[Long,Long] = maxSum(arr, i, end, depth + 1)
            val comb = new Array[Long](4)
            comb(0) = lst1._1 - lst2._1
            comb(1) = lst1._1 - lst2._2
            comb(2) = lst1._2 - lst2._1
            comb(3) = lst1._2 - lst2._2
            val localMax = comb.max
            val localMin = comb.min
            if (localMax > maxSumObserved) {
              //println(i  + " " + maxSumObserved)
              maxSumObserved = localMax
            }

            if (localMin < minSumObserved) {
              minSumObserved = localMin
            }
            //minSumObserved = comb.min

//            lst1.foreach(s1 => {
//              lst2.foreach(s2 => {
//                lstBuffer.+=(s1 - s2)
//              })
//            })
            //reset back
            arr(i) = current

          } else {
            val lst1 = maxSum(arr, start, i - 1, depth + 1)
            val lst2 = maxSum(arr, i, end, depth + 1)
//            lst1.foreach(s1 => {
//              lst2.foreach(s2 => {
//                lstBuffer.+=(s1 + s2)
//              })
//            })
            val comb = new Array[Long](4)
            comb(0) = lst1._1 + lst2._1
            comb(1) = lst1._1 + lst2._2
            comb(2) = lst1._2 + lst2._1
            comb(3) = lst1._2 + lst2._2
            val localMax = comb.max
            val localMin = comb.min
            if (localMax > maxSumObserved) {
              //println(i  + " " + maxSumObserved)
              maxSumObserved = localMax
            }

            if (localMin < minSumObserved) {
              minSumObserved = localMin
            }
          }
        }

        var currentSum : Long = 0
        for (i <- start to end) {
          currentSum += arr(i)
        }

        //lstBuffer.+=(currentSum)
        if (currentSum > maxSumObserved) {
          //println("Here ")
          maxSumObserved = currentSum
        }

        if (currentSum < minSumObserved) {
          //println("Here")
          minSumObserved = currentSum
        }


        //val s = lstBuffer.toSet
        cache.put((start,end),(maxSumObserved,minSumObserved))
//        for (i <- 0 to depth-1) {
//          print(" ")
//        }
        //println("Start = " + start + " End = " + end + " " + (maxSumObserved,minSumObserved))
        (maxSumObserved,minSumObserved)


      }
    }
  }

}


object Main extends App {
  val addSub = new AdditionAndSubstraction
  val count = scala.io.StdIn.readLine().toInt
  val exprArr = scala.io.StdIn.readLine().split(" ")
  var state = 1
  val exprIntArr = new Array[Long](count)
  exprIntArr(0) = exprArr(0).toLong
  var j = 1
  var totalAdd = 1
  var totalSub = 0
  for (i <- 1 to count-1) {

    exprArr(j) match {
      case "+" => {
        totalAdd = totalAdd + 1
        state = 1
        exprIntArr(i) = exprArr(j+1).toLong * state
        j = j + 2
      }
      case "-" => {
        totalSub = totalSub + 1
        state = -1
        exprIntArr(i) = exprArr(j+1).toLong * state
        j = j + 2
      }
      case num => {
        println("Error")
      }
    }



  }

  //println(exprIntArr.deep.mkString(" "))

  if (totalSub == 0) {
    println(exprIntArr.sum)
  }else {
    println(addSub.maxSum(exprIntArr,0,exprIntArr.length-1,0)._1)
  }

}