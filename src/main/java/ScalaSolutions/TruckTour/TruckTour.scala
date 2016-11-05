package ScalaSolutions.TruckTour

import scala.collection.mutable
import scala.util.control.Breaks._

/**
 * Created by rasrivastava on 11/4/16.
 */
class Edge(val source : Long,val dest : Long,val weight : Long) {

}

class Node(val id : Long,val petrol: Long) {
  val neighbours = new mutable.HashSet[Edge]()
  def addEdge(dest : Long,weight : Long) : Unit = {
    neighbours.+=(new Edge(id,dest,weight))
  }

  def getEdges() : Set[Edge] = {
    neighbours.toSet
  }
}



class Graph(val nodes : Long) {
  val graph = new Array[Node](nodes.toInt)
  var totalWeight : Long = 0
  var totalPetrol : Long = 0


  def addEdge(source : Long,dest : Long,weight : Long) : Unit = {
    graph(source.toInt).addEdge(dest,weight)
    totalWeight += weight
  }

  def addNode(nodeId : Int,petrol : Int) : Unit = {
    graph(nodeId) = new Node(nodeId,petrol)
    totalPetrol += petrol
  }

  def getPetrol(index : Int) : Long = {
    graph(index).petrol
  }

  def getDistanceToNext(index : Int) : Long = {
    graph(index).getEdges().head.weight
  }

  def getNext(index : Int) : Long = {
    graph(index).getEdges().head.dest
  }
}


class TruckTour(val graph: Graph) {
  def travel() : Int = {
    val stationsTravelled = new mutable.Queue[Long]()
    var currentIndex = 0
    var distanceCovered : Long = 0
    //var petrolUsed = 0
    var petrolInTank : Long = 0
    while (distanceCovered < graph.totalWeight) {

      val hop = graph.getDistanceToNext(currentIndex)
      val nextIndex = graph.getNext(currentIndex)
      val petrolTakenOutFromCurrentIndex = graph.getPetrol(currentIndex)
      if (petrolTakenOutFromCurrentIndex + petrolInTank < hop) {
        breakable {
          while (petrolTakenOutFromCurrentIndex + petrolInTank < hop) {
            if (stationsTravelled.isEmpty) {
              //Not possible if current acts as starting point
              currentIndex = nextIndex.toInt
              distanceCovered = 0
              petrolInTank = 0
              break()
            }else {
              val lastStation = stationsTravelled.dequeue()
              petrolInTank = petrolInTank - graph.getPetrol(lastStation.toInt)
              petrolInTank = petrolInTank + graph.getDistanceToNext(lastStation.toInt)
              distanceCovered = distanceCovered - graph.getDistanceToNext(lastStation.toInt)
            }
          }
        }
      }else {
        stationsTravelled.enqueue(currentIndex)
        //Take petrol from current
        petrolInTank = petrolInTank + graph.getPetrol(currentIndex)
        //Hop
        petrolInTank = petrolInTank - hop
        currentIndex = nextIndex.toInt
        distanceCovered = distanceCovered + hop

      }

    }

    stationsTravelled.head.toInt
  }


}


object Solution extends App {
  val sc = new java.util.Scanner (System.in)
  val count = sc.nextLine().toInt
  val graph = new Graph(count)
  for (i<- 0 to count-1) {
    val arr = sc.nextLine().split(" ")
    graph.addNode(i,arr(0).toInt)
    if (i == count-1) {
      graph.addEdge(i,0,arr(1).toInt)
    }else {
      graph.addEdge(i,i+1,arr(1).toInt)
    }
  }
//  val graph = new Graph(3)
//  graph.addNode(0,1)
//  graph.addEdge(0,1,5)
//
//  graph.addNode(1,10)
//  graph.addEdge(1,2,3)
//
//  graph.addNode(2,3)
//  graph.addEdge(2,0,4)

  val travel = new TruckTour(graph)
  println(travel.travel())
}
