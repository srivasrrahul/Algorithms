package ScalaSolutions.BinarySearch

import java.util

/**
 * Created by Rahul on 10/7/16.
 */
class BinarySearchVariations {
  def findValueRotated(arr : Array[Int],needle : Int,low : Int,high : Int) : Int = {
    println(low + " " + high)
    if (high < low) {
      -1
    }else {
      if (low == high) {
        if (needle == arr(low)) {
          low
        } else {
          -1
        }
      } else {
        val mid = low + (high - low) / 2
        if (arr(mid) == needle) {
          mid
        }else {
          val midValue = arr(mid)
          val leftValue = arr(low)
          val rightValue = arr(high)
          var retValue = -1
          if (midValue < rightValue && leftValue > rightValue) {
            if (needle > midValue && needle <= rightValue) {
              findValueRotated(arr,needle,mid+1,high)
            }else {
              findValueRotated(arr,needle,low,mid)
            }
          }else {
            if (leftValue > rightValue) {
              //Completely sorted
            }
            //Simple binary search
            println("Simple binarySearch " + low + " " + high + " " + needle)
            util.Arrays.binarySearch(arr,low,high+1,needle)
          }
        }

      }
    }
  }

}

object BinarySearchVariationsTest extends App {
  val arr = Array[Int](8,9,1,2,3,4,5,6)
  val binarySearch = new BinarySearchVariations
  println(binarySearch.findValueRotated(arr,1,0,arr.length-1))
}
