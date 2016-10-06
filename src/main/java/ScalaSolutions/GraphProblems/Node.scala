package ScalaSolutions.GraphProblems

import scala.collection.mutable

/**
 * Created by rasrivastava on 10/3/16.
 */
class Node(val id : Int) {
  def addVertex(v : Int) : Unit = {
    neighbours.+=(v)
  }

  def getNeigbours() : List[Int] = {
    neighbours.toList
  }

  var neighbours = new mutable.MutableList[Int]
}
