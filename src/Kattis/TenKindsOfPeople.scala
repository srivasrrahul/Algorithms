package Kattis

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
 * Created by Rahul on 12/30/16.
 */

class Node(val index : Int,val content : Int) {
  val lst = new mutable.MutableList[Int]
}

class Graph(val rows : Int,val cols : Int ) {

  val nodes = new Array[Node](rows * cols)

  val connectedComponents = new mutable.MutableList[mutable.HashSet[Int]]

  //val tree = new mutable.HashMap[Int,Int]()
  //var treeIndex = 0
  //row and col are 1 indexed but need the data in 0 index
  def getIndex(row : Int,col : Int,cols : Int) : Int = {
    ((row-1) * cols) + (col-1)
  }

  def getPriorNeigbours(row : Int,col : Int,currentContent : Int) : List[Node] = {
    //println(" row " + row + " " + col)
    val lstBuffer = new ListBuffer[Node]

    if (row-1 > 0) {
      val topIndex = getIndex(row-1,col,cols)
      val node = nodes(topIndex)
      if (node.content == currentContent) {
        lstBuffer.+=(node)
      }
    }

    if (col-1 > 0) {
      val leftIndex = getIndex(row,col-1,cols)
      val node = nodes(leftIndex)
      if (node.content == currentContent) {
        lstBuffer.+=(node)
      }
    }

    lstBuffer.toList
  }
  //row is 1 indexed

  def getConnectedComponent(nodeIndex : Int) : mutable.HashSet[Int] = {

    //println("For searching " + nodeIndex + " " + connectedComponents)
    connectedComponents.find(component => {
      component.contains(nodeIndex)
    }).get
  }
  def fillRow(row : Int,str : String) : Unit  = {
    var currentCol = 1
    str.foreach(ch => {

      val currentIndex = getIndex(row,currentCol,cols)
      //println("For currentIndex " + currentIndex)
      val node = new Node(currentIndex,ch - '0')

      val priorNeigbours = getPriorNeigbours(row,currentCol,ch-'0')
      priorNeigbours.size match {
        case 0 => {
          //println("No neigbour")
          val loneConnectedComponent = new mutable.HashSet[Int]()
          loneConnectedComponent.+=(currentIndex)
          connectedComponents.+=(loneConnectedComponent)
          //println("After merge 0 neigbour " + connectedComponents)
        }
        case 1 => {
          //println("1 neigbour")
          val oldConnectedComponent = getConnectedComponent(priorNeigbours.head.index)
          oldConnectedComponent.+=(currentIndex)
          //println("After merge 1 neigbour " + connectedComponents)
        }
        case 2 => {
          //println("2 neigbour")
          val old1 = getConnectedComponent(priorNeigbours.head.index)
          val old2 = getConnectedComponent(priorNeigbours.tail.head.index)
          if (old1 != old2) {
            old1 ++= old2
            old1.+=(currentIndex)
            //println("After merge " + old1)

            old2.clear()
            //println("Complete " + connectedComponents)
          } else {
            old1.+=(currentIndex)
            //println("Complete " + connectedComponents)
          }

        }
      }

      priorNeigbours.foreach(neigbour => {
        //println("Adding link between " + neigbour.index + "  " + node.index)
        neigbour.lst.+=(currentIndex)
        node.lst.+=(neigbour.index)
      })

      nodes(currentIndex) = node
      currentCol = currentCol + 1
    })
  }

  def dfs(source : Int,dest : Int) : Unit = {
    val visited = new mutable.HashSet[Int]()
    def itr(node : Int) : Boolean = {
      var retValue = false
      if (node == dest) {
        retValue = true
      }else {
        nodes(node).lst.foreach(v => {
          if (retValue == false) {
            if (visited(v) == false) {
              visited.+=(v)
              retValue = itr(v)
            }
          }
        })
      }

      retValue
    }

    val retValue = itr(source)
    if (retValue == false) {
      println("neither")
    } else {
      nodes(source).content match {
        case 0 => {
          println("binary")
        }
        case 1 => {
          println("decimal")
        }
      }

    }
  }

  def checkPath(source : Int,dest : Int): Unit = {
    if (nodes(source).content != nodes(dest).content) {
      println("neither")
    }else {
      val queue = new mutable.Queue[Int]()
      queue.enqueue(source)
      val visited = new mutable.HashSet[Int]()
      var done = false
      while (queue.size > 0 && done == false) {

        val head = queue.dequeue()
        //println(head)
        if (head == dest) {
          done = true
        } else {
          nodes(head).lst.foreach(v => {
            if (visited.contains(v) == false) {
              visited.+=(v)
              queue.enqueue(v)
            }
          })
        }
      }

      if (done == false) {
        println("neither")
      } else {
        nodes(source).content match {
          case 0 => {
            println("binary")
          }
          case 1 => {
            println("decimal")
          }
        }

      }
    }
  }

  def check(source : Int,dest : Int) : Unit = {
    val retValue = connectedComponents.find(component => {
      component.contains(source) && component.contains(dest)
    })
    retValue match {
      case Some(x) => {
        if (nodes(source).content == 0) {
          println("binary")
        }else {
          println("decimal")
        }
      }
      case None => {
        println("neither")
      }
    }
  }



}

object TenKindsOfPeople {
  def main(args: Array[String]) {
    val line = scala.io.StdIn.readLine()
    val xArr = line.split(" ")
    val rows = xArr(0).toInt
    val cols = xArr(1).toInt
    val graph = new Graph(rows,cols)
    for (i <- 1 to rows) {
      val line1 = scala.io.StdIn.readLine()
      graph.fillRow(i,line1)
    }

    //val bfsTree = new BFSTraversal(graph)
    //bfsTree.traveseAll()
    val testCount = scala.io.StdIn.readLine().toInt
    for (j <- 0 to testCount-1) {
      val query = scala.io.StdIn.readLine().split(" ")
      //val source = Tuple2[Int,Int](query(0).toInt,query(1).toInt)
      //val dest = Tuple2[Int,Int](query(2).toInt,query(3).toInt)
      //allPairsShortestPath.query(source,dest)
      val sourceIndex = graph.getIndex(query(0).toInt,query(1).toInt,graph.cols)
      val destIndex = graph.getIndex(query(2).toInt,query(3).toInt,graph.cols)
      //bfsTree.getPath(source,dest)
      graph.check(sourceIndex,destIndex)


    }

    //graph.nodes(graph.getIndex(2,3,graph.cols))

  }
}
