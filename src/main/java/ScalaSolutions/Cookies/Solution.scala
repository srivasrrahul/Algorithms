package ScalaSolutions.Cookies

import scala.annotation.tailrec

/**
 * Created by Rahul on 8/8/16.
 */
object Solution {
  //m mod n

  @tailrec def getmod(m: Int,n: Int,orig : Int) : Int = {
    //println(m + " " + n )
    if (m < n) {
      m
    }else {
      if (m == n) {
        0
      }else {

        val t = n << 1
        if (t > m) {
          getmod(m-n,orig,orig)
        }else {
          if (t == m) {
            0
          }else {
            getmod(m,t,orig)
          }
        }
       }

    }
  }

  def isPowerOfTwo(x : Int) : Boolean = {
    (x & (x-1)) == 0
  }

  def getAdditional(n : Int,m : Int) : Int = {
    if (n > m) {
      n-m
    }else {
      if (n==m) {
        0
      }else {
//        val r = m % n
//        n-r
        if (isPowerOfTwo(n)) {
         val r = m & (n-1)
          n-r
        }else {
          val r = getmod(m, n, n)
          n - r
        }
      }
    }
  }
  def main(args: Array[String]) {
    //println(getmod(10000000,3,3))
//    for (j <- 6 to 100000) {
//      val x = getmod(j,6,6)
//      val y = j % 6
//      if (x != y) {
//        println("error")
//      }
//    }
    val sc = new java.util.Scanner (System.in);
    val n = sc.nextInt();
    val m = sc.nextInt();
    println(getAdditional(n,m))
  }
}
