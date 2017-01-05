package Kattis

import scala.collection.mutable
import java.util

import scala.util.Random

/**
 * Created by Rahul on 1/1/17.
 */
object Constant {
  val low = -50000
  val high = 50000
}

class APlusBProblemEnhanced(val lst : Array[Int],val data : mutable.HashMap[Int,Int])   {

  def getData() : Int = {
    //println(data)
    scala.util.Sorting.quickSort(lst)
    val minSum = lst(0) + lst(1)
    val maxSum = lst(lst.size-1) + lst(lst.size-2)

    var count = 0
    for (x <- minSum to maxSum) {
      //println("Sum to be searched for  " + x)
      if (data.contains(x)) {
        val sizeOfSum = data.get(x).size
        data.foreach(p => {
          val a = p._1
          val b = x - a
          if (a != b) {
            if (data.contains(a) && data.contains(b)) {

              val countCaclulated = sizeOfSum * data.get(a).get * data.get(b).get
              //println("Not equal For x " + x + " a = " + a + " b = " + b + " " + countCaclulated)
              count = count + countCaclulated
            }
          }else {
            if (data.contains(a) && data.contains(b)) {
              val countCaclulated = sizeOfSum * (data.get(a).get-1) * data.get(b).get
              //println("Equal For x " + x + " a = " + a + " b = " + b + " " + countCaclulated)
              count = count + countCaclulated
            }
          }
        })
      }
    }

    count

  }
}
//class APlusBProblem(val lst : Array[Int],val data : mutable.HashMap[Int,mutable.HashSet[Int]]) {
//  def get() :Int = {
//    var count = 0
//    for (i <- 0 to lst.length-1) {
//      for (j <- 0 to lst.length-1) {
//        if (i != j) {
//          val sum = lst(i) + lst(j)
//          data.get(sum) match {
//
//            case Some(placeHolder) => {
//
//              val sumSet = placeHolder.filter(sumIndex => sumIndex != i && sumIndex != j)
//              //println("Sum exists " + (i,j) + " " + sum + " " + lst(i) + " " + lst(j)  + " sumset " + sumSet)
//              count = count + sumSet.size
//            }
//            case None => {
//
//            }
//          }
//        }
//      }
//    }
//
//    count
//  }
//
//
////  def getRange() : Int = {
////
////
////    //println(data)
////
////    var count = 0
////    val pendingCompleted = new mutable.HashSet[Tuple2[Int,Int]]()
////    //lst.sortWith(_ < _)
////    scala.util.Sorting.quickSort(lst)
////    val minSum = lst(0) + lst(1)
////    val maxSum = lst(lst.size-1) + lst(lst.size-2)
////    //println("After sorting " + lst.deep.mkString(","))
////    //println(minSum + " " + maxSum)
////    //for (possibleSum <- 2*Constant.low to 2*Constant.high) {
////    for (possibleSum <- minSum to maxSum) {
////      //println("For sum " + possibleSum)
////      if (data.contains(possibleSum)) {
////        //println("Sum exists for " + possibleSum)
////        val dataSize = data.get(possibleSum).get.size
////        val c1 = count
////        data foreach { case (b, value) => {
////
////          val a = possibleSum - b
////          //println("A = " + a + " B = " + b)
////          if (a != b) {
////            //println("A is not equal to B")
////            data.get(a) match {
////              case Some(setContainingA) => {
////
////                //println("Incrementing count by " + setContainingA.size + " to  " +  (value.size * setContainingA.size))
////                count = count + (value.size * setContainingA.size * dataSize)
////              }
////              case None => {
////                //println("No addition")
////              }
////            }
////          } else {
////            data.get(b) match {
////              case Some(setContainAorB) => {
////                val sizeOfSetContainAorB = setContainAorB.size
////                val twoCombinations = sizeOfSetContainAorB * (sizeOfSetContainAorB - 1) * dataSize
////                count = count + twoCombinations
////              }
////              case None => {
////
////              }
////            }
////          }
////
////
////        }
////
////        }
////
////        //println("Added For sum " + possibleSum + " incremented " + (count-c1))
////      }
////    }
////
////    //println(pendingCompleted)
////    count
////  }
//
//
//
//  def getFinal() : Int = {
//    if (lst.size > 10) {
//      new APlusBProblemEnhanced()()
//    }else {
//      get()
//    }
//  }
//
//
//
//
//}

//class Test {
//  val rand = new Random(System.currentTimeMillis())
//
//  def randomInRange(low : Int,high : Int) : Int = {
//    low + rand.nextInt(high - low + 1)
//  }
//  def test() : Unit = {
//
//    Thread.sleep(10)
//    val arr = new Array[Int](10)
//    val map = new mutable.HashMap[Int,mutable.HashSet[Int]]()
//    for (i <- 0 to arr.size-1) {
//      val x =  randomInRange(1,100)
//      if (map.contains(x) == false) {
//        map.put(x,new mutable.HashSet[Int]())
//      }
//
//      map.get(x).get.+=(i)
//      arr(i) = x
//    }
//
//    println(arr.deep.mkString(","))
//
//    val problem = new APlusBProblem(arr,map)
//
//    println(problem.get() == problem.getRange())
//  }
//
//  def testSpecific() : Unit = {
//    val arr = Array(85,34,22,38,28,6,24,56,100,34)
//    val map = new mutable.HashMap[Int,mutable.HashSet[Int]]()
//    for (i <- 0 to arr.size-1) {
//      val x = arr(i)
//      if (map.contains(x) == false) {
//        map.put(x,new mutable.HashSet[Int]())
//      }
//
//      map.get(x).get.+=(i)
//      arr(i) = x
//    }
//
//    println(arr.deep.mkString(","))
//
//    val problem = new APlusBProblem(arr,map)
//
//    println(problem.get() + " " + problem.getRange())
//  }
//}
object APlusBProblemMain {
  def main(args: Array[String]) {
////    val test = new Test
////    test.testSpecific()
//    for (i <- 0 to 10000) {
//      val test = new Test
//      test.test()
//    }
    val in = new java.util.Scanner(System.in)


    val count = in.nextInt()
    //println(count)
    val arr = new Array[Int](count)
    //val data = new mutable.HashMap[Int,mutable.HashSet[Int]]()
    val data = new mutable.HashMap[Int,Int]()


    //val line = scala.io.StdIn.readLine().split(" ")


    //val ranges = new mutable.HashMap[Int,mutable.HashSet[Int]]()
//    for (i <- Constant.low to Constant.high) {
//      ranges.put(i,new mutable.HashSet[Int]())
//    }
    for (i <- 0 to count-1) {

      val value = in.nextInt()
      //println("Value " + value)
      arr(i) = value
      if (data.contains(value)) {
        data.put(value,data.get(value).get+1)
      }else {
        //val set = new mutable.HashSet[Int]()
        //set.+=(i)
        data.put(value,1)
      }

      //ranges.get(value).get.+=(i)
    }

    println(new APlusBProblemEnhanced(arr,data).getData())
  }
}
