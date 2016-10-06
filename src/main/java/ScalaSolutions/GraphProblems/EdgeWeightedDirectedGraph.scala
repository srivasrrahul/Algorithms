package ScalaSolutions.GraphProblems

import scala.collection.mutable
import scala.io.Source


/**
 * Created by rasrivastava on 10/6/16.
 */
class EdgeWeightedNode(val id : Int) {
  def addEdge(directedEdge: DirectedEdge): Unit = {
    edges.add(directedEdge)
  }

  def getEdges() : Set[DirectedEdge] = {
    edges.toSet
  }

  val edges = new mutable.HashSet[DirectedEdge]()
}
class EdgeWeightedDirectedGraph(val verticesCount : Int) {
  val vertexArr = new Array[EdgeWeightedNode](verticesCount)
  for (i<-0 to vertexArr.length-1) {
    vertexArr(i) = new EdgeWeightedNode(i)
  }
  def addEdge(directedEdge : DirectedEdge) : Unit = {
    vertexArr(directedEdge.source).addEdge(directedEdge)
  }

  def getAllEdges() : Set[DirectedEdge] = {
    val edges = new mutable.HashSet[DirectedEdge]()
    vertexArr.foreach(node => {
      node.getEdges().forall(edge => {
        edges.add(edge)
      })
    })

    edges.toSet
  }

}

object EdgeWeightedDirectedGraphTest extends App {
  val fileName = "/Users/rasrivastava/CODE_OPEN/SAMPLE_FILES/tinyEWD.txt"
  //Lazy iterator
  val sourceStream = Source.fromFile(fileName).getLines()
  val firstLine = sourceStream.next()
  println(firstLine.toInt)
  val graph = new EdgeWeightedDirectedGraph(firstLine.toInt)
  val countOfEdges = sourceStream.next()
  while (sourceStream.hasNext) {
    val str = sourceStream.next()
    val edge = str.split(" ")
    val directedEdge = new DirectedEdge(edge(0).toInt,edge(1).toInt,edge(2).toDouble)
    println(directedEdge)
    graph.addEdge(directedEdge)
  }





}
