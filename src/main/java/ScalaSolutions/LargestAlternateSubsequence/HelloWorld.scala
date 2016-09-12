package ScalaSolutions.LargestAlternateSubsequence

/**
 * Created by Rahul on 8/16/16.
 */
object HelloWorld {

  def largestAlternateSubsequence(arr : Array[Int]) : Int = {
    def valid(x : Int,y: Int) : Boolean = {
      (Math.abs(arr(x)) < Math.abs(arr(y)) &&
        (
          (arr(x) > 0 && arr(y) < 0)
          ||
          (arr(x) < 0 && arr(y) > 0)))
    }
    val cache = Array.ofDim[Int](arr.length)
    cache(0) = 1

    var globalMax = cache(0)
    for (i <- 1 to arr.length-1) {
      var maxSubSequence = 1
      for (j<-i-1 to 0 by -1) {
        if (valid(j,i)) {
          val tempMax = cache(j) + 1
          if (tempMax > maxSubSequence) {
            maxSubSequence = tempMax
          }
        }
      }

      cache(i) = maxSubSequence
      if (maxSubSequence > globalMax) {
        globalMax = maxSubSequence
      }
    }

    //println(cache.deep.mkString(","))
    globalMax
  }

  def main(arr : Array[String]) : Unit = {
    //println(largestAlternateSubsequence(Array(1, 2, -2, -3, 5, -7, -8, 10)))
    val sc = new java.util.Scanner (System.in);
    val n = sc.nextInt();
    val a = new Array[Int](n);
    for(a_i <- 0 to n-1) {
      a(a_i) = sc.nextInt();
    }

    println(largestAlternateSubsequence(a))
  }

}
