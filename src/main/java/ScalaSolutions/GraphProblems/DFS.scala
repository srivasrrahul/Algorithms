package ScalaSolutions.GraphProblems

import scala.collection.mutable

/**
 * Created by rasrivastava on 10/3/16.
 */
class TimeVisited(val preTime : Int,val postTime: Int) {

}

class DFS(val graph: GraphType) {
  val visited = new mutable.HashMap[Int,Boolean]()
  val timeVisited : mutable.HashMap[Int,TimeVisited] = new mutable.HashMap[Int,TimeVisited]()
  var timeCounter = 0
  var connectedComponents = 0
  graph.getVertxList().foreach(node => {
    visited.+=((node.id,false))
    timeVisited.+=((node.id,new TimeVisited(0,0)))
  })

  def init() : Unit = {
    for (i <- 0 to graph.getVertxList().length - 1) {
      if (visited(i) == false) {
        println("Visited false " + i)
        connectedComponents = connectedComponents + 1
        preVisitNode(i)
        explore(i)
        postVisitNode(i)
      }
    }
  }

  def preVisitNode(node : Int) : Unit = {
    val nodeTime = timeVisited.get(node)

    if (nodeTime.get.preTime == 0) {
      timeCounter = timeCounter+1
      timeVisited.+=((node,new TimeVisited(timeCounter,nodeTime.get.postTime)))
    }
  }

  def postVisitNode(node : Int) : Unit = {
    val nodeTime = timeVisited.get(node)
    if (nodeTime.get.postTime == 0) {
      timeCounter = timeCounter+1
      timeVisited.+=((node,new TimeVisited(nodeTime.get.preTime,timeCounter)))
    }
  }

  def explore(v : Int) : Unit = {

    //visited(v) = true
    //println("exploring " + v)

    graph.getVertxList()(v).getNeigbours().foreach(neighbour => {
      if (visited(neighbour) == false) {
        visited(neighbour) = true
        preVisitNode(neighbour)
        explore(neighbour)
        postVisitNode(neighbour)
      }
    })

  }

  def getTimeVisited():mutable.HashMap[Int,TimeVisited] = {
    timeVisited
  }

  def getConnectedComponents(): Int = {
    connectedComponents
  }







}
