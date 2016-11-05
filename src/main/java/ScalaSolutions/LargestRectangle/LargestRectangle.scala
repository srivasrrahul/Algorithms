package ScalaSolutions.LargestRectangle

import java.io.File
import java.util.Scanner

import scala.StringBuilder
import scala.collection.mutable
import scala.util.control.Breaks._

/**
 * Created by Rahul on 10/27/16.
 */


class LargestRectangle(val arr : Array[Int]) {

  def getLargestAreaUsingStack() : Long = {
    val indexStack = new mutable.Stack[Long]()


    var maxArea : Long = 0
    var counter  = 0
    while (counter < arr.length) {
      //println("Counter " + counter)
      if (indexStack.isEmpty || arr(indexStack.top.toInt) < arr(counter)) {
        //println("Appending to stack ")
        indexStack.push(counter)
        counter +=1
        //println(indexStack)
      }else {
        //println("Exatracting ")
        //println("Stack before " + indexStack)
        val topHeight : Long = arr(indexStack.pop().toInt)

        var currentArea : Long = 0
        if (indexStack.isEmpty) {
          currentArea = topHeight * counter
        }else {
          currentArea = topHeight * (counter - indexStack.top.toInt-1)
        }

        if (currentArea > maxArea) {
          maxArea = currentArea
        }

        //println("Area after " + currentArea)
      }
    }

    while (indexStack.isEmpty == false) {
      val topHeight : Long = arr(indexStack.pop().toInt)
      //indexStack.pop()

      var currentArea : Long = 0
      if (indexStack.isEmpty) {
        currentArea = topHeight * counter
      }else {
        currentArea = topHeight * (counter - indexStack.top-1)
      }

      if (currentArea > maxArea) {
        maxArea = currentArea
      }
    }

    maxArea
  }
}


object Solution extends App {
//  val arr = Array[Int](3,4,10)
//  val largestRectangle = new LargestRectangle(arr)
//  println(largestRectangle.getLargestArea())
  val sc = new java.util.Scanner (System.in)
  //val fileName = "/Users/rasrivastava/CODE_OPEN/Algorithms/src/main/java/ScalaSolutions/LargestRectangle/test.txt"
  //val file = new File(fileName)
  //val sc = new Scanner(file)
  val l = sc.nextLine()
  val size = l.toInt
  var arr = new Array[Int](size)
  var maxHeight = 0
  for (i <- 0 to size-1) {
    arr(i) = sc.nextInt()
    if (arr(i) > maxHeight) {
      maxHeight = arr(i)
    }
  }


  val largestRectangle = new LargestRectangle(arr)
  println(largestRectangle.getLargestAreaUsingStack())

}