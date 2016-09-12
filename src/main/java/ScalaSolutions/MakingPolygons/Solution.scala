package ScalaSolutions.MakingPolygons

/**
 * Created by rasrivastava on 8/9/16.
 */
object Solution {



  def minCuts(arr : Array[Double]) : Int = {
    def minCutsInternal(arr : Array[Double]) : Int = {
      arr.sortWith(_ < _)

      val last = arr(arr.length-1)
      val minElements = arr.sum - arr(arr.length-1)
      if (minElements > last) {
        0
      }else {
        1
      }
    }

    if (arr.length == 1) {
      2
    }else {
      if (arr.length == 2) {
        if (arr(0) == arr(1)) {
          2
        }else {
          minCutsInternal(arr)

        }
      }else {
        minCutsInternal(arr)

      }

    }


  }
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var a = new Array[Double](n);
    for(a_i <- 0 to n-1) {
      a(a_i) = sc.nextInt();
    }

    println(minCuts(a))
  }

}
