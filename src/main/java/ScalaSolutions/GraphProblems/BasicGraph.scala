package ScalaSolutions.GraphProblems

import scala.collection.mutable
import scala.io.Source

/**
 * Created by rasrivastava on 9/27/16.
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

class Graph(vertexCount : Int) {

  val vertexArr = new Array[Node](vertexCount)
  for (i <-0 to vertexArr.length-1) {
    vertexArr(i) = new Node(i)
  }

  def addEdge(u : Int,v : Int) : Unit = {
    vertexArr(u).addVertex(v)
  }

  def getVertxList() : Array[Node] = {
    vertexArr
  }
}

trait DFSVisit {
  def preVisitNode(node : Node) : Unit
  def postVisitNode(node : Node) : Unit
}

class TimeVisited(val preTime : Int,val postTime: Int) {

}

class DFS(val graph: Graph,val startNode : Int,visit: DFSVisit) {
  val visited = new mutable.HashMap[Int,Boolean]()
  val timeVisited : mutable.HashMap[Int,TimeVisited] = new mutable.HashMap[Int,TimeVisited]()
  var timeCounter = 0
  graph.getVertxList().foreach(node => {
    visited.+=((node.id,false))
    timeVisited.+=((node.id,new TimeVisited(0,0)))
  })

  preVisitNode(startNode)
  explore(startNode)
  postVisitNode(startNode)

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

    visited(v) = true

    graph.getVertxList().foreach(neighbour => {
      if (visited(neighbour.id) == false) {
        preVisitNode(neighbour.id)
        explore(neighbour.id)
        postVisitNode(neighbour.id)
      }
    })

  }

  def getTimeVisited():mutable.HashMap[Int,TimeVisited] = {
    timeVisited
  }







}

object GraphTest extends App {
  val graph = new Graph(4)
  graph.addEdge(0,1)
  graph.addEdge(1,2)
  graph.addEdge(1,3)
  val dfs = new DFS(graph,0,null)
  val timeVisisted = dfs.getTimeVisited()
  graph.getVertxList().foreach(node => {
    val timeOfNode = timeVisisted.get(node.id).get
    println(node.id + " " + timeOfNode.preTime + " " + timeOfNode.postTime)
  })
//  val fileName = "/Users/rasrivastava/CODE_OPEN/SAMPLE_FILES/largeG.txt"
//  val lines = Source.fromFile(fileName).getLines()
//  val vertexCount = lines.get

}
