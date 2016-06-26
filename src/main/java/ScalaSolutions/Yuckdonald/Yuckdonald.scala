package ScalaSolutions.Yuckdonald

import scala.util.control.Breaks

/**
 * Created by Rahul on 6/25/16.
 */
class Yuckdonald {
  def valid(currentIndex : Int,lastIndex : Int,distanceArr : Array[Int],k : Int) : Boolean = {
    //println(currentIndex + " " + lastIndex)
    if (lastIndex == -1) {
      true
    }else {
      Math.abs(distanceArr(lastIndex) - distanceArr(currentIndex)) >= k
    }
  }

  def maxProfit(currentIndex : Int,lastIndex : Int,distanceArr : Array[Int], k : Int,profitArr : Array[Int])  : Int = {
    //println(currentIndex + " " + lastIndex)
    val validDistance = valid(currentIndex,lastIndex,distanceArr,k)
     if (currentIndex == 0) {
       if (validDistance) {
         profitArr(currentIndex)
       }else {
         0
       }

     }else {
       if (currentIndex == distanceArr.length - 1) {
         //Assume current is picked up
         val p1 = profitArr(currentIndex) + maxProfit(currentIndex - 1, currentIndex, distanceArr, k, profitArr)
         //Assume we don't pick up current
         val p2 = maxProfit(currentIndex - 1, -1, distanceArr, k, profitArr)
         if (p1 > p2) {
           p1
         }else {
           p2
         }
       } else {
         var p1 = 0
         val p2 = maxProfit(currentIndex - 1, lastIndex, distanceArr, k, profitArr)
         if (validDistance) {
           p1 = profitArr(currentIndex) + maxProfit(currentIndex - 1, currentIndex, distanceArr, k, profitArr)

         }

         if (p1 > p2) {
           p1
         } else {
           p2
         }
       }
     }
  }

  def maxProfitInterface(distanceArr : Array[Int], k : Int,profitArr : Array[Int]) : Unit = {
    def maxProfitRecursive(currentIndex: Int, distanceArr: Array[Int], k: Int, profitArr: Array[Int]): Int = {
      //println(currentIndex)
      if (currentIndex != 0) {
        var maxProfitIfIStopHere = Int.MinValue
        for (j <- currentIndex - 1 to 0 by -1) {
          //println(" " + j)
          if (valid(j,currentIndex, distanceArr, k)) {
            val maxProfit = maxProfitRecursive(j, distanceArr, k, profitArr) + profitArr(currentIndex)
            if (maxProfitIfIStopHere < maxProfit) {
              maxProfitIfIStopHere = maxProfit
            }

          }
        }

        maxProfitIfIStopHere


      }
      else {
        profitArr(0)
      }
    }

    val profitVector = Array.fill[Int](distanceArr.length)(Int.MinValue)
    for (j <- distanceArr.length-1 to 0 by -1) {
      profitVector(j) = maxProfitRecursive(j,distanceArr,k,profitArr)
    }

    println(profitVector.deep.mkString(","))

  }

  def maxProfitDP(profitArr : Array[Int],distanceArr : Array[Int],minDistance : Int) : Unit = {

    val size = profitArr.length+1
    val matrix = Array.ofDim[Int](size,size)
    for (j <- 1 to size-1) {
      matrix(0)(j) = profitArr(j-1)
    }

    for (j<-0 to size-1) {
      //No profit
      matrix(j)(j) = 0

    }

    val profitIndex = Array.fill[Int](profitArr.length)(0)
    profitIndex(0) = profitArr(0)
    for (k<-0 to size-1) {
      for (j<-1 to size-1) {
        val rowIndex = j-1
        val colIndex = k-1

        if (k>j) {
          //println(k + " " + j)

          if (valid(rowIndex,colIndex,distanceArr,minDistance)) {

            //println("Valid")
            val tempMaxProfit = profitIndex(rowIndex) + profitArr(colIndex)
            //println("TempMaxProfit " + tempMaxProfit + " " + colIndex + " " + rowIndex)
            matrix(j)(k) = tempMaxProfit

          }else {
            //println("invalid " + colIndex + " " + rowIndex)
          }

        }else {
          //println("not considered " + colIndex + " " + rowIndex)
        }

      }

      var maxProfitForThisCol = matrix(k)(0)
      for (i <- 0 to size) {
        if (i < k) {
          if (matrix(i)(k) > maxProfitForThisCol) {
            maxProfitForThisCol = matrix(i)(k)
          }
        }
      }

      if (k > 0) {
        //println("Max Profit for k " + k + " " + maxProfitForThisCol)
        profitIndex(k-1) = maxProfitForThisCol
      }





    }

    println(profitIndex.deep.mkString(","))


  }



}


object Main extends App {
  val profitArr = Array(10,20,30,40,50)
  val distanceArr = Array(0,10,20,30,40)
  val p = new Yuckdonald
  println(p.maxProfitInterface(distanceArr,20,profitArr))
  println(p.maxProfitDP(profitArr,distanceArr,20))
}