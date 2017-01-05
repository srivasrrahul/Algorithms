package ScalaSolutions.RotateArray

/**
 * Created by rasrivastava on 11/18/16.
 */
class RotateArray() {
  def rotate(arr : Array[Int],d : Int) : Array[Int] = {
    for (i <- 0 to d-1) {
      arr(i) = arr(d+i)
    }


    for (i <- d to arr.length-d-1) {
      arr(i) =  arr(i+d)
    }

    arr
  }
}

object Solution {
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in)
    val n = sc.nextInt()
    val k = sc.nextInt()
    val length = n + k
    val a = new Array[Int](length)
    for(a_i <- k to length-1) {
      a(a_i) = sc.nextInt();
    }

    println(a.deep.mkString(","))
    val sol = new RotateArray
    println(sol.rotate(a,k).deep.mkString(","))
  }
}
