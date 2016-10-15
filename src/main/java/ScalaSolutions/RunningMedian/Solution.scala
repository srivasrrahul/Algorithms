package ScalaSolutions.RunningMedian

import scala.collection.mutable

/**
 * Created by rasrivastava on 10/14/16.
 */
class MinOrder(val value : Int) extends Ordered[MinOrder] {
  override def compare(that: MinOrder): Int = {
    that.value compare this.value
  }
}

class MaxOrder(val value : Int) extends Ordered[MaxOrder] {
  override def compare(that: MaxOrder): Int = {
    this.value compare that.value
  }
}
class RunningMedian {
  val minHeap = new mutable.PriorityQueue[MinOrder]()
  val maxHeap = new mutable.PriorityQueue[MaxOrder]()

  def addElement(newElement : Int) : Double = {
    if (maxHeap.size == 0 || minHeap.size == 0) {
      if (maxHeap.size ==0 && minHeap.size == 0) {
        //Pick low and just add
        maxHeap.+=(new MaxOrder(newElement))
      }else {
        if (minHeap.size == 0) {
          if (newElement >= maxHeap.head.value) {
            minHeap.+=(new MinOrder(newElement))
          }else {
            val top = maxHeap.dequeue().value
            maxHeap.+=(new MaxOrder(newElement))
            minHeap.+=(new MinOrder(top))
          }

        }
      }
    }else {
      val s1 = maxHeap.head
      val s2 = minHeap.head

      if (minHeap.size == maxHeap.size) {
        if (newElement <= s1.value) {
          maxHeap.+=(new MaxOrder(newElement))

        } else {
          if (newElement > s1.value && newElement < s2.value) {
            maxHeap.+=(new MaxOrder(newElement))
          } else {
            if (newElement >= s2.value) {
              minHeap.+=(new MinOrder(newElement))
            }
          }
        }
      } else {
        if (maxHeap.size > minHeap.size) {
          if (newElement <= maxHeap.head.value) {
            val topCurrent = maxHeap.dequeue()
            maxHeap.+=(new MaxOrder(newElement))
            minHeap.+=(new MinOrder(topCurrent.value))
          } else {
            minHeap.+=(new MinOrder(newElement))
          }
        } else {
          //minHeap.size > maxHeap.size
          if (newElement <= minHeap.head.value) {
            maxHeap.+=(new MaxOrder(newElement))
          } else {
            val top = minHeap.dequeue()
            minHeap.+=(new MinOrder(newElement))
            maxHeap.+=(new MaxOrder(top.value))
          }
        }
      }
    }

    //Get median
    if (maxHeap.size > minHeap.size) {
      maxHeap.head.value
    }else {
      if (minHeap.size > maxHeap.size) {
        minHeap.head.value
      }else {
        //Equal
        (maxHeap.head.value + minHeap.head.value)/2.0
      }
    }
  }



}

object Solution extends  App {

  val runningMedian = new RunningMedian()
  val sc = new java.util.Scanner (System.in);
  var n = sc.nextInt();
  var a = new Array[Int](n);
  for(a_i <- 0 to n-1) {

    printf("%.1f",runningMedian.addElement(sc.nextInt()))
    println()
  }

//  println(runningMedian.addElement(12))
//  println(runningMedian.addElement(4))
//
//  //println(runningMedian.maxHeap.head.value + " " + runningMedian.minHeap.head.value)
//  println(runningMedian.addElement(5))
//  //println(runningMedian.maxHeap.head.value + " " + runningMedian.minHeap.head.value)
//  println(runningMedian.addElement(3))
//  println(runningMedian.addElement(8))
//  println(runningMedian.addElement(7))
}
