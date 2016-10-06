package ScalaSolutions.GraphProblems

import scala.StringBuilder
import scala.collection.mutable
import scala.io.Source

/**
 * Created by rasrivastava on 9/27/16.
 */
//class Node(val id : Int) {
//  def addVertex(v : Int) : Unit = {
//    neighbours.+=(v)
//  }
//
//  def getNeigbours() : List[Int] = {
//    neighbours.toList
//  }
//
//  var neighbours = new mutable.MutableList[Int]
//}

//trait GraphType {
//  def addEdge(u : Int,v : Int)
//}
class Graph(vertexCount : Int) extends GraphType{

  val vertexArr = new Array[Node](vertexCount)
  for (i <-0 to vertexArr.length-1) {
    vertexArr(i) = new Node(i)
  }

//  def addEdge(u : Int,v : Int) : Unit = {
//    vertexArr(u).addVertex(v)
//    vertexArr(v).addVertex(u)
//
//  }

  override def getVertxList() : Array[Node] = {
    vertexArr
  }

  override def toString() : String = {
    val strBuilder = new StringBuilder()
    for (i <- 0 to vertexArr.length-1) {
      strBuilder.append("Source " + i + " Connected : " + vertexArr(i).getNeigbours())
      strBuilder.append("\n")

    }

    strBuilder.toString()
  }

  override def addEdge(u: Int, v: Int): Unit = {
    vertexArr(u).addVertex(v)
    vertexArr(v).addVertex(u)
  }
}

//class TimeVisited(val preTime : Int,val postTime: Int) {
//
//}
//
//class DFS(val graph: Graph) {
//  val visited = new mutable.HashMap[Int,Boolean]()
//  val timeVisited : mutable.HashMap[Int,TimeVisited] = new mutable.HashMap[Int,TimeVisited]()
//  var timeCounter = 0
//  var connectedComponents = 0
//  graph.getVertxList().foreach(node => {
//    visited.+=((node.id,false))
//    timeVisited.+=((node.id,new TimeVisited(0,0)))
//  })
//
//  def init() : Unit = {
//    for (i <- 0 to graph.vertexArr.length - 1) {
//      if (visited(i) == false) {
//        println("Visited false " + i)
//        connectedComponents = connectedComponents + 1
//        preVisitNode(i)
//        explore(i)
//        postVisitNode(i)
//      }
//    }
//  }
//
//  def preVisitNode(node : Int) : Unit = {
//    val nodeTime = timeVisited.get(node)
//
//    if (nodeTime.get.preTime == 0) {
//      timeCounter = timeCounter+1
//       timeVisited.+=((node,new TimeVisited(timeCounter,nodeTime.get.postTime)))
//    }
//  }
//
//  def postVisitNode(node : Int) : Unit = {
//    val nodeTime = timeVisited.get(node)
//    if (nodeTime.get.postTime == 0) {
//      timeCounter = timeCounter+1
//      timeVisited.+=((node,new TimeVisited(nodeTime.get.preTime,timeCounter)))
//    }
//  }
//
//  def explore(v : Int) : Unit = {
//
//    //visited(v) = true
//    println("exploring " + v)
//
//    graph.vertexArr(v).getNeigbours().foreach(neighbour => {
//      if (visited(neighbour) == false) {
//        visited(neighbour) = true
//        preVisitNode(neighbour)
//        explore(neighbour)
//        postVisitNode(neighbour)
//      }
//    })
//
//  }
//
//  def getTimeVisited():mutable.HashMap[Int,TimeVisited] = {
//    timeVisited
//  }
//
//  def getConnectedComponents(): Int = {
//    connectedComponents
//  }
//
//
//
//
//
//
//
//}

object GraphTest extends App {
//  val graph = new Graph(4)
//  graph.addEdge(0,1)
//  graph.addEdge(1,2)
//  graph.addEdge(1,3)
//  val dfs = new DFS(graph,0,null)
//  val timeVisisted = dfs.getTimeVisited()
//  graph.getVertxList().foreach(node => {
//    val timeOfNode = timeVisisted.get(node.id).get
//    println(node.id + " " + timeOfNode.preTime + " " + timeOfNode.postTime)
//  })
  val fileName = "/Users/rasrivastava/CODE_OPEN/SAMPLE_FILES/mediumG.txt"
  //val fileName = "/Users/rasrivastava/CODE_OPEN/SAMPLE_FILES/tinyG.txt"
  val lines = Source.fromFile(fileName).getLines()
  val line1 = lines.next()
  val vertexCount = line1.toInt
  val graph = new Graph(vertexCount)
  val countOfEdges = lines.next()

  while (lines.hasNext) {
    val l = lines.next()
    val arr = l.split(" ")
    val n1 = arr(0).toInt
    val n2 = arr(1).toInt
    graph.addEdge(n1,n2)
  }

  //println(graph.toString)

  val dfs = new DFS(graph)
  dfs.init()
  println(dfs.connectedComponents)


//  val vertexCount = lines.get

}
