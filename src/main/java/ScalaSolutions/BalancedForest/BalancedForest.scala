package ScalaSolutions.BalancedForest

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * Created by rasrivastava on 11/2/16.
 */
class BalancedForest {

  //forest is a reverse Index with c against a set of indexes
  def isBalanced(perGroupSum  : Int,forest : Map[Int,Set[Int]]) : Boolean = {

    def extractGroup(level : Int,perGroupSum : Int,currentlyExtracted : Int,pendingForest : Map[Int,Set[Int]]) : Boolean = {
      //println(pendingForest)
      if (perGroupSum == currentlyExtracted) {
        if (pendingForest.isEmpty) {
          //println(level)
          level == 2
        }else {
          //println("Here")
          extractGroup(level+1,perGroupSum,0,pendingForest)
        }
      }else {

        var retValue = false
        pendingForest.foreach(node => {
          val currentValue = node._1
          val currentVertexSet = node._2
          if (currentlyExtracted + currentValue <= perGroupSum) {

            if (currentVertexSet.size == 1) {
              retValue = retValue | extractGroup(level,perGroupSum,currentlyExtracted + currentValue,pendingForest.-(currentValue))
            }else {
              currentVertexSet.foreach(vertex => {
                val updatedSet = currentVertexSet.-(vertex)
                retValue = retValue | extractGroup(level,perGroupSum,currentlyExtracted + currentValue,pendingForest + (currentValue -> updatedSet))
              })
            }
          }
        })

        retValue
      }


    }

    extractGroup(0,perGroupSum,0,forest)

  }

}


object Main extends App {
  val map = new mutable.HashMap[Int,Set[Int]]()
  map.+=((1,Set[Int](0)))
  map.+=((3,Set[Int](1,3)))
  map.+=((5,Set[Int](2)))


  val balancedForest = new BalancedForest
  println(balancedForest.isBalanced(3,map.toMap))
}