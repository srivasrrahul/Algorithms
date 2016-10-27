package ScalaSolutions.CoinChange

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, HashSet}

/**
 * Created by rasrivastava on 10/16/16.
 */
class CoinChange(val denominication : Int ,val coins : HashSet[Int]) {

  def getCount() : Int = {
    def getCountItr(currentDenomination : Int) : List[List[Int]] = {
      //println("Current " + currentDenomination)
      val retValue = new ListBuffer[List[Int]]()
      if (currentDenomination == 0) {
        retValue.toList

      }else {

        if (cache.contains(currentDenomination)) {
          println("Cache hit")
          cache.get(currentDenomination).get
        } else {


          for (valueOfCoin <- coins) {

            if (currentDenomination > valueOfCoin) {

              val pendingLst = getCountItr(currentDenomination - valueOfCoin)
              pendingLst.foreach(lst => {

                //println(lst)
                retValue.+=(lst.::(valueOfCoin))
              })


            }else {
              if (currentDenomination == valueOfCoin) {
                retValue.+=(List[Int](valueOfCoin))
              }
            }

          }

          val lst = retValue.toList
          //println("Adding for " + currentDenomination + " " + lst)
          cache.+=((currentDenomination,lst))
          lst

        }

      }




    }



    val lst = getCountItr(denominication)
    //val arr = new Array[List[Int]](lst.size)
    val set = new mutable.HashSet[mutable.WrappedArray[Int]]()
    lst.foreach(combination => {

      val combinationArr = combination.toArray
      scala.util.Sorting.quickSort(combinationArr)
      //println("Combination is " + combinationArr.deep.mkString(","))
      if (set.contains(combinationArr)) {
        //println("Already exists")
      }
      set.add(combinationArr)
    })

    //println(" Set " + set)

    set.size


  }

  var cache = new mutable.HashMap[Int,List[List[Int]]]()

}

object Solution {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    val n = sc.nextInt();
    val m = sc.nextInt();
    val coins = new HashSet[Int]();
    for(coins_i <- 0 to m-1) {
      coins.+=(sc.nextInt());
    }

    //println(coins)
    //println("Ask " + n)
    val coinChange = new CoinChange(n,coins)
    println(coinChange.getCount())
  }
}
