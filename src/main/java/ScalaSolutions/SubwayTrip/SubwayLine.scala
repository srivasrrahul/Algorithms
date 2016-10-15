package ScalaSolutions.SubwayTrip

import scala.StringBuilder
import scala.collection.mutable

/**
 * Created by Rahul on 10/14/16.
 */

class SubwayLine(val source : Int,val dest: Int,val company : Int) {
  override def toString: String = {
    source + " => " + dest  + " " + company
  }
}

class SubwayStation(val id : Int) {
  def addStation(subwayLine: SubwayLine) : Unit ={
    lines.+=(subwayLine)
  }

  val lines = new mutable.HashSet[SubwayLine]()

  override def toString: String = {
    val strBuilder = new StringBuilder(" ")
    strBuilder.append(id + " ")
    lines.foreach(line => strBuilder.append(line + ","))
    strBuilder.toString()
  }
}
class Subways(val stationCount : Int) {
  val stations = new Array[SubwayStation](stationCount)
  for (i<- 0 to stations.length-1) {
    stations(i) = new SubwayStation(i)
  }


  def addLine(s1 : Int, s2 : Int, company : Int): Unit = {
    //println(s1 + " " + s2)
    val line = new SubwayLine(s1,s2,company)
    val line1 = new SubwayLine(s2,s1,company)
    stations(s1).addStation(line)
    stations(s2).addStation(line1)
  }
}

class VertexBasedShortestPath(val vertex : Int,val cost : Int) extends Ordered[VertexBasedShortestPath]{
  override def compare(that: VertexBasedShortestPath): Int = {
    this.cost.compare(that.cost)
  }
}
class ShortestPath(source : Int,val subways: Subways) {
  val distTo = new Array[Int](subways.stationCount)
  val pathTo = new Array[Int](subways.stationCount)
  val companyUsed = new Array[Int](subways.stationCount)
  val pq = new mutable.PriorityQueue[VertexBasedShortestPath]()

  pq.+=(new VertexBasedShortestPath(source,0))
  for (i <- 0 to subways.stationCount-1) {
    distTo(i) = Int.MaxValue
  }

  companyUsed(source) = 0
  distTo(source) = 0



  while (pq.isEmpty == false) {

    val vertex = pq.dequeue()
    //println("Extracted vertex " + subways.stations(vertex.vertex))
    relaxVertex(subways.stations(vertex.vertex))
  }

  def relaxEdge(subwayLine: SubwayLine) : Unit = {
    val sourceCompany  = companyUsed(subwayLine.source)
    val localSource = subwayLine.source
    val localDest = subwayLine.dest
    if (sourceCompany != 0) {
      val destCompany = subwayLine.company
      var cost = distTo(localSource)
      if (sourceCompany != destCompany) {
        cost = cost + 1
      }

      val currentCost = distTo(localDest)
      if (cost < currentCost) {
        distTo(localDest) = cost
        pathTo(localDest) = localSource
        companyUsed(localDest) = destCompany
        val vertexBasedShortestPath = new VertexBasedShortestPath(localDest,cost)
        pq.+=(vertexBasedShortestPath)
      }
    }else {
      //Initial one
      val currentCost = distTo(localDest)
      val cost = 1
      if (cost < currentCost) {
        distTo(localDest) = cost
        pathTo(localDest) = localSource

        companyUsed(localDest) = subwayLine.company
        val vertexBasedShortestPath = new VertexBasedShortestPath(localDest,cost)
        pq.+=(vertexBasedShortestPath)
      }
    }
  }

  def relaxVertex(vertex : SubwayStation) = {
    for (edge <- vertex.lines) {
      relaxEdge(edge)
    }
  }


}

object Main extends App {
  val x = readLine()
  val line1 = x.split(" ")
  //println("Line1(0) " + line1(0).toInt)
  val subways = new Subways(line1(0).toInt)
  val lineCount = line1(1).toInt
  for (i <- 0 to lineCount-1) {
    val edgeLine = readLine()
    val edgeStr = edgeLine.split(" ")
    //val subwayLine = new SubwayLine(edgeStr(0).toInt,edgeStr(1).toInt,edgeStr(2).toInt)
    subways.addLine(edgeStr(0).toInt-1,edgeStr(1).toInt-1,edgeStr(2).toInt)
  }

  val shortestPath = new ShortestPath(0,subways)
  println(shortestPath.distTo(subways.stations.length-1))

}