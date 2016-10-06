package ScalaSolutions.GraphProblems

import scala.io.Source
/**
 * Created by Rahul on 10/3/16.
 */
class DirectedGraph (vertexCount : Int) extends GraphType {
  val vertexArr = new Array[Node](vertexCount)
  for (i <-0 to vertexArr.length-1) {
    vertexArr(i) = new Node(i)
  }

  def getVertxList() : Array[Node] = {
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
  }
}


object DAGTest extends App {
  val fileName = "/Users/rasrivastava/CODE_OPEN/SAMPLE_FILES/simpleDAG.txt"
  val sourceStream = Source.fromFile(fileName).getLines()
  val line1 = sourceStream.next()
  val vertexCount = line1.toInt
  val graph = new DirectedGraph(vertexCount)
  val lineCount = sourceStream.next().toInt
  while (sourceStream.hasNext) {
    val link = sourceStream.next()
    val arr = link.split(" ")
    val s1 = arr(0).toInt
    val s2 = arr(1).toInt
    graph.addEdge(s1,s2)
  }

  println(graph.toString())
//  val dfs = new DFS(graph)
//  dfs.init()
  val topologicalSort = new TopologicalSort(graph)
  println(topologicalSort.sortUsingDifferentAlgo())

  println(topologicalSort.longestPathsInDAG())

  println(topologicalSort.lcaDAG(0,3))

}