package ScalaSolutions.SumOfArrays

/**
 * Created by Rahul on 8/4/16.
 */
object Solution {

  def sum(arr : Array[Int]) : Long = {
    arr.sum
  }
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var arr = new Array[Int](n);
    for(arr_i <- 0 to n-1) {
      arr(arr_i) = sc.nextInt();
    }

    print(sum(arr))
  }
}
