package ScalaSolutions.GraphProblems

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
 * Created by Rahul on 10/7/16.
 */
class AcyclicGraphShortestPath(val graph: EdgeWeightedDirectedGraph,val source : Int) {
  val distTo = new Array[Double](graph.verticesCount)
  val edgeTo = new Array[DirectedEdge](graph.verticesCount)
  for (i <-0 to graph.verticesCount-1) {
    distTo(i) = Int.MaxValue
  }
  distTo(source) = 0.0
  edgeTo(source) = null

  val topologicalSort = new TopologicalSortEdgeWeightedDirectedGraph(graph)
  val sortedLst = topologicalSort.getSortedOrder()
  //println("Sorted Lst " + sortedLst)

  for (elem <- sortedLst) {
    relaxVertex(elem)
  }

//  for (i <- 0 to edgeTo.length-1) {
//    println(edgeTo(i))
//  }

  //============Functions================
  def relaxVertex(vertex : Int) : Unit = {
    //println("Relaxing " + vertex)
    graph.vertexArr(vertex).getEdges().foreach(edge => {
      relax(edge)
    })
  }

  def relax(edge : DirectedEdge): Unit = {
    val source = edge.source
    val dest = edge.dest
    //println(edge + " " + distTo(dest))
    if (distTo(dest) > distTo(source) + edge.weight) {
      distTo(dest) = distTo(source) + edge.weight
      edgeTo(dest) = edge
    }
  }

  def pathTo(vertex : Int) : List[Int] = {
    val stack = new mutable.Stack[Int]()
    stack.push(vertex)
    var current = vertex
    while (current != source && edgeTo(current) != null) {
      //println(current)
      val path = edgeTo(current)

      stack.push(path.source)
      current = path.source
    }

    stack.toList
  }

}

object AcyclicGraphShortestPathTest extends App {
  val fileName = "/Users/rasrivastava/CODE_OPEN/SAMPLE_FILES/tinyEWDAG.txt"
  val sourceStream = Source.fromFile(fileName).getLines()
  val vertexCount = sourceStream.next()
  val edgeCount = sourceStream.next()
  val graph = new EdgeWeightedDirectedGraph(vertexCount.toInt)
  for (line <- sourceStream) {
    val values = line.split(" ")
    graph.addEdge(new DirectedEdge(values(0).toInt,values(1).toInt,values(2).toDouble))
  }

  println(graph.toString)
//  val shortestPath = new AcyclicGraphShortestPath(graph,0)
//  println(shortestPath.pathTo(1))
  val longestPath = new TopologicalSortEdgeWeightedDirectedGraph(graph).getLongestPaths()
  longestPath.foreach(p => {
    println(p._1 + " " + p._2)
  })


}