package ScalaSolutions.MatchingSets

import scala.collection.mutable

/**
 * Created by Rahul on 8/10/16.
 */
object Solution {
  def operation(arr : Array[Int],index1 : Int,index2 : Int) : Array[Int] = {
    val arr1 = arr.clone()
    arr1(index1) = arr(index1)-1
    arr1(index2) = arr(index2)+1
    arr1
  }


  def dist(a1 : Array[Int],a2 : Array[Int]) : Int = {
    val arr1 = a1.clone()
    val arr2 = a2.clone()
    arr1.sortWith(_ < _)
    arr2.sortWith(_ < _)
    var diff = 0
    for (i <- 0 to arr1.length-1) {
      diff = diff + Math.abs(arr1(i)-arr2(i))
    }

    diff


  }
  def minChange(arr1 : Array[Int],arr2 : Array[Int],valObserved : mutable.HashMap[Set[Int],Int]) : Int = {

    //println(arr1.deep.mkString(",") + "  " + arr2.deep.mkString(","))
    //println()
    val s1 = arr1.toSet
    val s2 = arr2.toSet
    if (s1 == s2) {
      println("true")
      0
    }else {
      val diffVal = dist(arr1,arr2)
      val s = arr1.toSet
      if (valObserved.contains(s)) {
        println("Loop")
        valObserved.get(s).get
      }else {
        valObserved.+=(s -> Int.MaxValue)
        var minChangeVal = Int.MaxValue

        for (i <- 0 to arr1.length - 1) {
          for (j <- 0 to arr1.length - 1) {
            if (i != j) {
              val updatedArr = operation(arr1, i, j)
              val diffValCurrent = dist(updatedArr,arr2)
              if (false == valObserved.contains(updatedArr.toSet) && diffValCurrent <= diffVal) {
              //if (false == valObserved.contains(updatedArr.toSet)) {
                val minChangeTempVal = minChange(updatedArr, arr2, valObserved)
                if (minChangeTempVal != Int.MaxValue) {
                  if (minChangeTempVal + 1 < minChangeVal) {
                    minChangeVal = minChangeTempVal+1
                  }
                }
              }

            }


          }
        }

        valObserved.+=(s -> minChangeVal)
        minChangeVal
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner (System.in);
    var n = sc.nextInt();
    var a = new Array[Int](n);
    for(a_i <- 0 to n-1) {
      a(a_i) = sc.nextInt();
    }

    var b = new Array[Int](n);
    for(b_i <- 0 to n-1) {
      b(b_i) = sc.nextInt();
    }



    val map = scala.collection.mutable.HashMap.empty[Set[Int],Int]
    val sol = minChange(a,b,map)
    //println("Solution " + sol + " " + Int.MaxValue )
    if (sol == Int.MaxValue) {
      println(-1)
    }else {
      println(sol)
    }

  }

}
