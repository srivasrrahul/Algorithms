package ScalaSolutions.CompareTriplets

/**
 * Created by Rahul on 8/4/16.
 */
object Solution {

  def comp(x : Int,y: Int,r : Int, s : Int) : (Int,Int) = {
    if (x > y) {
      (r+1,s)
    }else {
      if (x < y) {
        (r,s+1)
      }else {
        (r,s)
      }
    }

  }
  def compareTripLets(a0 : Int,a1 : Int,a2 : Int ,b0: Int,b1 : Int,b2 : Int): (Int,Int) = {

    var tuple = (0,0)
    tuple = comp(a0,b0,tuple._1,tuple._2)
    tuple = comp(a1,b1,tuple._1,tuple._2)
    tuple = comp(a2,b2,tuple._1,tuple._2)
    tuple

  }
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    var a0 = sc.nextInt();
    var a1 = sc.nextInt();
    var a2 = sc.nextInt();
    var b0 = sc.nextInt();
    var b1 = sc.nextInt();
    var b2 = sc.nextInt();
    val res = compareTripLets(a0,a1,a2,b0,b1,b2)
    print(res._1 + " " + res._2)
  }
}

