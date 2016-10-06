package ScalaSolutions.GraphProblems

import java.util

import scala.collection.mutable


/**
 * Created by Rahul on 10/3/16.
 */

class TopologicalSort(val graph : DirectedGraph) {
  def sort(): util.LinkedList[Int] = {
    val dfs = new DFS(graph)
    dfs.init()
    val timeVisited = dfs.timeVisited
    val sortedByPostTime = new util.TreeMap[Int,Int]()
    timeVisited.foreach(n => {
      sortedByPostTime.put(n._2.postTime,n._1)
    })

    val sortedNode = new util.LinkedList[Int]()
    val itr = sortedByPostTime.descendingMap()
    val reverseItr = itr.keySet().iterator()
    while (reverseItr.hasNext) {
      sortedNode.addLast(sortedByPostTime.get(reverseItr.next()))
    }

    sortedNode
  }

  def calculateInDegree() : Array[Int] = {
    val indegrees = new Array[Int](graph.vertexArr.length)
    graph.vertexArr.foreach(node => {
      node.getNeigbours().foreach(neighbour => {
        indegrees(neighbour) = indegrees(neighbour) + 1
      })
    })

    indegrees
  }
  def sortUsingDifferentAlgo() : util.LinkedList[Int] = {
    val indegrees = calculateInDegree()
    val queue  = new mutable.Queue[Int]()
    for (i <-0 to indegrees.length-1) {
      if (indegrees(i) == 0) {
        queue.enqueue(i)
      }
    }

    val lst = new util.LinkedList[Int]()
    while (queue.size != 0) {
      val lowestIndex = queue.dequeue()
      lst.addLast(lowestIndex)
      val neighbours = graph.vertexArr(lowestIndex).getNeigbours()
      neighbours.foreach(n => {
        indegrees(n) = indegrees(n) - 1
        if (indegrees(n) == 0) {
          queue.enqueue(n)
        }
      })

    }

    lst
  }

  def longestPathsInDAG() : Map[Int,Int] = {
    val indegrees = calculateInDegree()
    val longestPaths = new mutable.HashMap[Int,Int]()
    graph.vertexArr.foreach(node => {
      longestPaths.+=((node.id,0))
    })

    val queue = new mutable.Queue[Int]()
    for (i <- 0 to indegrees.length-1) {
      if (indegrees(i) == 0) {
        queue.enqueue(i)
      }
    }

    while (queue.size > 0) {
      val smallestIndex = queue.dequeue()
      val neighbours = graph.vertexArr(smallestIndex).getNeigbours()
      neighbours.foreach(n => {
        indegrees(n) = indegrees(n)-1
        if (indegrees(n) == 0) {
          queue.enqueue(n)
        }

        val currentPathLength  = longestPaths.get(n).get
        val updatedPathLength = longestPaths.get(smallestIndex).get + 1
        if (currentPathLength < updatedPathLength)  {
          longestPaths.update(n,updatedPathLength)
        }

      })
    }

    longestPaths.toMap

  }

  def lcaDAG(u : Int,v : Int) : Int = {
    val longestPaths = longestPathsInDAG()
    val firstAncestor = new util.TreeMap[Int,Int]()
    val secondAncestor = new util.TreeMap[Int,Int]()
    val pathLengthFirst = longestPaths(u)
    val pathLengthSecond = longestPaths(v)
    var resultIndex = -1
    var resultPath = Int.MinValue
    longestPaths.foreach(entry => {
      if (entry._1 != u && entry._1 != v) {
        val pathLength = entry._2
        if (pathLength < pathLengthFirst && pathLength < pathLengthSecond) {
          if (pathLength > resultPath) {
            resultPath = pathLength
            resultIndex = entry._1
          }
        }

      }
    })

    resultIndex

  }
}
