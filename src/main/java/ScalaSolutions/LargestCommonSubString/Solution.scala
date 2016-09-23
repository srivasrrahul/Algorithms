package ScalaSolutions.LargestCommonSubString
import scala.util.control.Breaks._

/**
 * Created by Rahul on 9/21/16.
 */
object Solution extends App {
  def getLenth(t : Tuple3[Int,Int,Int]) : Int = {
    t._2-t._1+1
  }

  def isValid(t : Tuple3[Int,Int,Int]) : Boolean = {
    t._1 != -1 && t._2 != -1
  }

  def getMax(t1 : Tuple3[Int,Int,Int],t2 : Tuple3[Int,Int,Int]) : Tuple3[Int,Int,Int] = {
    val b1 = isValid(t1)
    val b2 = isValid(t2)

    if (b1 && b2) {
      if (getLenth(t1) > getLenth(t2)) {
        t1
      }else {
        t2
      }
    }else {
      if (b1) {
        t1
      }else {
        if (b2) {
          t2
        }else {
          //Which one
          t1
        }
      }
    }
  }
  def largestCommonSubStringLen(s1 : String,s2 : String) : Tuple3[Int,Int,Int] = {

//    def largestRecursive(m : Int,n : Int) : Tuple2[Int,Int]  = {
//      if (m < 0 || n < 0) {
//        (-1,-1)
//      }else {
//
//        var l1  = largestRecursive(m - 1, n - 1)
//        val l2 = largestRecursive(m, n - 1)
//        val l3 = largestRecursive(m-1,n-1)
//
//        if (s1(m) == s2(n)) {
//          if (isValid(l1) && l1._2 == m-1) {
//            l1 = (l1._1,m)
//          }else {
//            //println("Here")
//            if (isValid(l1) == false) {
//              l1 = (m,m)
//            }else {
//
//            }
//          }
//        }
//
//
//
//        var l4 = (-1,-1)
//        if (s1(m) == s2(n)) {
//          breakable {
//            l4 = (m,m)
//            for (j <- m to 0 by -1; k <- n to 0 by -1
//                if k >= 0 && j >= 0) {
//              if (s1(j) != s2(k)) {
//                break()
//              } else {
//                l4 = (j,m)
//              }
//            }
//          }
//        }
//
//
//        var large1 = getMax(l1,l2)
//        large1 = getMax(large1,l3)
//        large1 = getMax(large1,l4)
//        large1
//
//      }
//    }
//
//    largestRecursive(s1.length-1,s2.length-1)
    val arr = Array.ofDim[Tuple3[Int,Int,Int]](s1.length,s2.length)
    for (j <- 0 to s2.length-1) {
      if (s1(0) == s2(j)) {
        arr(0)(j) = (0,0,1)
      }else {
        arr(0)(j) = (-1,-1,-1)
      }
    }

    for (i <- 0 to s1.length-1) {
      if (s1(i) == s2(0)) {
        arr(i)(0) = (i,i,1)
      }else {
        arr(i)(0) = (-1,-1,-1)
      }
    }

//    for (x <- 0 to s1.length-1) {
//      for (y <-0 to s2.length-1) {
//        print(arr(x)(y) + " ")
//      }
//
//      println()
//    }
//
//    println()
//    println()

    for (i<-1 to s1.length-1) {
      for (j<-1 to s2.length-1) {
        val l1  = arr(i - 1)(j)
        val l2 = arr(i)(j-1)
        val l3 = arr(i-1)(j-1)




        var l4 = (-1,-1,-1)
        if (s1(i) == s2(j)) {
          if (isValid(arr(i-1)(j-1))) {
            l4 = (arr(i-1)(j-1)._1,i,arr(i-1)(j-1)._3+1)
          }else {
            l4 = (i,i,1)
          }
          //l4 = (i,i,arr(i-1)(j-1)._3 + 1)
//          var y = j-1
//          breakable {
//            for (x <- i-1 to 0 by -1 if y >= 0) {
//              if (s1(x) == s2(y)) {
//                l4 = (x,i)
//                y = y -1
//              }else {
//                break
//              }
//            }
//          }

        }

        var large1 = getMax(l1,l2)
        large1 = getMax(large1,l3)
        large1 = getMax(large1,l4)

        if (i == 4 && j == 4) {
          println("Here    " + l1 + " " + l2 + " " + l3 + l4)
          println()
        }

        arr(i)(j) = large1
      }
    }

//    for (x <- 0 to s1.length-1) {
//      for (y <-0 to s2.length-1) {
//        print(arr(x)(y) + " ")
//      }
//
//      println()
//    }

    arr(s1.length-1)(s2.length-1)

  }



  println("Solution is " + largestCommonSubStringLen("tantan","btan"))

}
