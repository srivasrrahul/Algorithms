package ScalaSolutions.LongestSum

/**
 * Created by Rahul on 6/21/16.
 */
class LongestSum {

  def findMax(arr : Array[Int]) : Int = {
    var max = Int.MinValue
    for (i <- arr.indices) {
      if (max < arr(i)) {
        max = arr(i)
      }
    }

    max
  }

  def largestSum(arr : Array[Int]) : Int = {

    val cache = Array.fill[Int](arr.length)(0)
    cache(0) = arr(0)
    for (i <-1 to arr.length-1) {
      val prev = cache(i-1)
      val current = arr(i)
      if (current + prev > current) {
        cache(i) = current+prev
      }else {
        cache(i) = current
      }
    }

    findMax(cache)

  }

}

object Main extends App {
  val longestSum = new LongestSum
  println(longestSum.largestSum(Array[Int](5,15,-30,10,-5,40,10)))
}
