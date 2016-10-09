package ScalaSolutions.GraphProblems

import scala.collection.mutable
import scala.io.Source

/**
 * Created by Rahul on 10/6/16.
 */

class VertexBasedShortestPath(val id : Int, val weight : Double) extends Ordered[VertexBasedShortestPath]{
  override def compare(that: VertexBasedShortestPath): Int = {
    this.weight.compareTo(that.weight)
  }
}
class ShortestPathEdgeWeighted(val graph: EdgeWeightedDirectedGraph,val source : Int) {
  val edgeTo = new Array[DirectedEdge](graph.verticesCount)
  val distTo = new Array[Double](graph.verticesCount)
  val pq = new mutable.PriorityQueue[VertexBasedShortestPath]()

  for (i<- 0 to graph.verticesCount-1) {
    distTo(i) = Int.MaxValue
  }

  distTo(source) = 0

  def relax(directedEdge: DirectedEdge) : Unit = {
    val start = directedEdge.source
    val end = directedEdge.dest
    val weight = directedEdge.weight
    if (distTo(end) > distTo(start) + weight) {
      distTo(end) = distTo(start) + weight
      edgeTo(end) = directedEdge
      val updatedKeyOfEndVertex = new VertexBasedShortestPath(end,distTo(end))
      pq.+=(updatedKeyOfEndVertex)
    }
  }

  def relaxVertex(v : Int) : Unit = {
    val edges = graph.vertexArr(v).getEdges()
    edges.foreach(edge => {
      relax(edge)
    })
  }

  def dijkstra() : Unit = {
    pq.+=(new VertexBasedShortestPath(source,0.0))
    while (pq.size > 0) {
      val minVertex = pq.dequeue()
      relaxVertex(minVertex.id)
    }
  }

  def pathTo(v : Int) : List[DirectedEdge] = {
    val stack = new mutable.Stack[DirectedEdge]()
    var edge = edgeTo(v)
    while (edge != null) {
      stack.push(edge)
      edge = edgeTo(edge.source)
    }

    stack.toList
  }




}

class AllPairsShortestPathUsingDikstra(val graph: EdgeWeightedDirectedGraph) {
  val shortestPathFromAllVertices = new Array[ShortestPathEdgeWeighted](graph.verticesCount)
  for (i<-0 to shortestPathFromAllVertices.length-1) {
    shortestPathFromAllVertices(i) = new ShortestPathEdgeWeighted(graph,i)
  }
}

object DijkistraTest extends App {
  val fileName = "/Users/rasrivastava/CODE_OPEN/SAMPLE_FILES/tinyEWD.txt"
  val sourceStream = Source.fromFile(fileName).getLines()
  val firstLine = sourceStream.next()
  println(firstLine.toInt)
  val graph = new EdgeWeightedDirectedGraph(firstLine.toInt)
  val countOfEdges = sourceStream.next()
  while (sourceStream.hasNext) {
    val str = sourceStream.next()
    val edge = str.split(" ")
    val directedEdge = new DirectedEdge(edge(0).toInt,edge(1).toInt,edge(2).toDouble)
    //println(directedEdge)
    graph.addEdge(directedEdge)
  }

  val dikstra = new ShortestPathEdgeWeighted(graph,0)
  dikstra.dijkstra()
  for (i<-0 to graph.verticesCount-1) {
    println(dikstra.pathTo(i) + "  " + dikstra.distTo(i))

  }

  //for


}
