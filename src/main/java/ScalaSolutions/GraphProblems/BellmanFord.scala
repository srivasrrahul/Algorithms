package ScalaSolutions.GraphProblems

import scala.collection.mutable
import scala.io.Source
import scala.util.control.Breaks._

/**
 * Created by rasrivastava on 10/9/16.
 */
class BellmanFord(val graph : EdgeWeightedDirectedGraph,val source : Int) {
  val edgeTo = new Array[DirectedEdge](graph.verticesCount)
  val distTo = new Array[Double](graph.verticesCount)

  for (i <- 0 to distTo.length-1) {
    distTo(i) = Double.MaxValue
  }

  distTo(source) = 0.0
  val allEdges = graph.getAllEdges()
  for (i <- 0 to graph.verticesCount-1) {
    allEdges.foreach(edge => {
      relax(edge)
    })
  }

  //Functions
  def relax(edge : DirectedEdge): Boolean = {
    var relaxed = false
    val source = edge.source
    val dest = edge.dest
    if (distTo(dest) > distTo(source) + edge.weight) {
      distTo(dest) = distTo(source) + edge.weight
      edgeTo(dest) = edge
      relaxed = true
    }

    relaxed
  }

  def isNegativeCycle() : Boolean = {
    var negativeCycle = false
    val allEdges = graph.getAllEdges()
    for (i <- 0 to graph.verticesCount-1) {
      allEdges.foreach(edge => {
        if (relax(edge)) {
          negativeCycle = true
        }
      })
    }

    negativeCycle
  }
  def pathTo(dest : Int) : List[Int] = {
    //println("For destination "  + dest )

    val stack = new scala.collection.mutable.Stack[Int]()
    stack.push(dest)
    var current = dest
    while (current != source && edgeTo(current) != null) {
      println(current)
      stack.push(edgeTo(current).dest)
      current = edgeTo(current).source
    }

    stack.toList
  }

  def findNegativeCycle(dest : Int) : List[Int] = {
    //println("For destination "  + dest )
    val visited = new mutable.HashSet[Int]()
    val stack = new scala.collection.mutable.Stack[Int]()
    stack.push(dest)
    visited.add(dest)
    var current = dest
    breakable {
      while (current != source && edgeTo(current) != null) {
        //println(current)
        stack.push(edgeTo(current).source)
        if (visited.contains(edgeTo(current).source)) {
          println("Cycle exists")
          break
        }else {

          current = edgeTo(current).source
          visited.add(current)
        }


      }
    }

    stack.toList
  }
}

object BellmanFordTest extends App {
  val sourceFileName = "/Users/rasrivastava/CODE_OPEN/SAMPLE_FILES/tinyEWDnc.txt"
  val sourceStream = Source.fromFile(sourceFileName).getLines()
  val vertexCount = sourceStream.next()
  val edgeCount = sourceStream.next()
  val graph = new EdgeWeightedDirectedGraph(vertexCount.toInt)
  for (line <- sourceStream) {
    val str = line.split(" ")
    //println(str.deep.mkString(","))
    graph.addEdge(new DirectedEdge(str(0).toInt,str(1).toInt,str(2).toDouble))
  }

  val bellmanFord = new BellmanFord(graph,0)
  println(bellmanFord.isNegativeCycle())

  for (i <- 0 to vertexCount.toInt-1) {
    println("For i = " + i + " " + bellmanFord.findNegativeCycle(i))
  }

}