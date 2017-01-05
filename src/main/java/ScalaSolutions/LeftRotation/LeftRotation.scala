package ScalaSolutions.LeftRotation


/**
 * Created by Rahul on 11/28/16.
 */
class LeftRotation {
  def reverseInPlace(arr: Array[Int],sourceIndex : Int,destIndex : Int) : Unit = {
    val mid = sourceIndex + (destIndex-sourceIndex)/2
    var last = destIndex
    for (i <- sourceIndex to mid) {
      val temp = arr(i)
      arr(i) = arr(last)
      arr(last) = temp
      last = last - 1
    }
  }
  def rotate(arr : Array[Int],d : Int) : Unit = {
    reverseInPlace(arr,0,d)
    reverseInPlace(arr,d+1,arr.length-1)
    reverseInPlace(arr,0,arr.length-1)
  }
}

object Solution {
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    val n = sc.nextInt();
    val k = sc.nextInt();
    val a = new Array[Int](n);
    for(a_i <- 0 to n-1) {
      a(a_i) = sc.nextInt();
    }

    val leftRotation = new LeftRotation
    leftRotation.rotate(a,k-1)
    println(a.deep.mkString(" "))
  }
}
