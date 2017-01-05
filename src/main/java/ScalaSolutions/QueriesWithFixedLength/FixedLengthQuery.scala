package ScalaSolutions.QueriesWithFixedLength

import java.io.File
import java.util
import java.util.Scanner



/**
 * Created by rasrivastava on 11/5/16.
 */

class FixedLengthQuery(val arr : Array[Int]) {
  def query(d : Int) : Int = {
    def queryInternal(): Int = {
      val mm = new util.TreeMap[Int, Int]()
      var maxContent = Int.MinValue
      val minHeap = scala.collection.mutable.PriorityQueue.empty(Ordering[Int].reverse)
      for (i <- 0 to d - 1) {
        if (false == mm.containsKey(arr(i))) {
          mm.put(arr(i), 0)
        }

        mm.put(arr(i), mm.get(arr(i)) + 1)

        if (arr(i) > maxContent) {
          maxContent = arr(i)
        }
      }

      minHeap.enqueue(maxContent)
      //println("Before entering main loop " + mm)
      for (i <- d to arr.length - 1) {

        val elementToBeAddedIndex = i
        val elementToBeRemovedIndex = i - d
        //println(elementToBeAddedIndex + " " + elementToBeRemovedIndex)
        val elementToBeRemoved = arr(elementToBeRemovedIndex)
        if (mm.containsKey(elementToBeRemoved)) {
          if (mm.get(elementToBeRemoved) > 1) {
            mm.put(elementToBeRemoved, mm.get(elementToBeRemoved) - 1)
          } else {
            mm.remove(elementToBeRemoved)
          }
        }
        //      if (mm.lastKey() == arr(elementToBeRemovedIndex)) {
        //        println("Remove last index")
        //        if (mm.lastEntry().getValue == 1) {
        //          mm.remove(mm.lastKey())
        //        }else {
        //          val countOfLast = mm.lastEntry().getValue
        //          mm.put(mm.lastEntry().getKey,countOfLast-1)
        //        }
        //      }else {
        //
        //      }

        if (false == mm.containsKey(arr(elementToBeAddedIndex))) {
          mm.put(arr(elementToBeAddedIndex), 0)
        }

        mm.put(arr(elementToBeAddedIndex), mm.get(arr(elementToBeAddedIndex)) + 1)
        minHeap.enqueue(mm.lastKey())


      }


      //println(minHeap)
      minHeap.head
    }

    if (cache.containsKey(d)) {
      cache.get(d)
    }else {
      val retValue = queryInternal()
      cache.put(d, retValue)
      retValue
    }

  }

  val cache = new util.HashMap[Int,Int]()
}

object Solution extends App {
//  val arr = Array[Int](1,2,3,4,5)
//  val fixedQueryLength = new FixedLengthQuery(arr)
//  println(fixedQueryLength.query(5))
  val scanner = new Scanner(System.in)
  //val scanner = new Scanner(new File("/Users/rasrivastava/CODE_OPEN/Algorithms/src/main/java/ScalaSolutions/QueriesWithFixedLength/test.txt"))
  val lineStr = scanner.nextLine()
  val lineArr = lineStr.split(" ")
  val arr = new Array[Int](lineArr(0).toInt)
  val content = scanner.nextLine()
  val contentArr = content.split(" ")
  var i = 0
  for (contentStr <- contentArr) {
    arr(i) = contentStr.toInt
    i = i + 1
  }

  val fixedLengthQuery = new FixedLengthQuery(arr)
  val count = lineArr(1).toInt
  for (i <- 0 to count-1) {
    val d = scanner.nextLine().toInt
    println(fixedLengthQuery.query(d))
  }
}
