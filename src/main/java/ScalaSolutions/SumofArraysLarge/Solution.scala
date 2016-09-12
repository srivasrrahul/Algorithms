package ScalaSolutions.SumofArraysLarge

/**
 * Created by rasrivastava on 8/4/16.
 */
object Solution {

  def sumArr(arr : Array[Long]) : Long = {
    arr.sum
  }
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var arr = new Array[Long](n);
    for(arr_i <- 0 to n-1) {
      arr(arr_i) = sc.nextInt();
    }

    println(sumArr(arr))
  }
}
