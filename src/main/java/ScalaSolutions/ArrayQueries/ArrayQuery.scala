package ScalaSolutions.ArrayQueries

/**
 * Created by rasrivastava on 10/27/16.
 */
//class IntList(val value : Int) {
//  var next : Option[IntList] = None
//}
//class ArrayQuery(val arr : Array[Int]) {
//  var root = new IntList(arr(0))
//  var end : IntList = null
//  var itr = root
//  for (i <- 1 to arr.length-1) {
//    val node = new IntList(arr(0))
//    itr.next = Some(node)
//    itr = node
//  }
//
//  end = itr
//
//  def getNode(sourceIndex : Int,destIndex : Int) : Tuple2[IntList,IntList] = {
//
//    def getNodeItr(currentNode : IntList,currentIndex : Int) : IntList = {
//      currentIndex match {
//        case sourceIndex => {
//          currentNode
//        }
//        case _ => {
//          getNodeItr(currentNode.next.get,currentIndex+1)
//        }
//      }
//    }
//
//    val firstNode = getNodeItr(root,0)
//    val secondNode = getNodeItr(firstNode,destIndex)
//    (firstNode,secondNode)
//  }
//
//  def addFront(x : Int,y : Int) : Unit = {
//    if (x != 0) {
//      val nodes = getNode(x-1,y)
//      val lastNode = nodes._2
//      val zerothNode = nodes._1
//      val firstNode = zerothNode.next.get
//      lastNode.next match {
//        case Some(lastPlusOneNode) => {
//          zerothNode.next = Some(lastPlusOneNode)
//          lastNode.next = Some(zerothNode)
//          root = firstNode
//        }
//        case None => {
//          zerothNode.next = None
//          lastNode.next = Some(zerothNode)
//          root = firstNode
//
//        }
//      }
//    }
//  }
//
//  def addBack(x : Int,y : Int) : Unit = {
//    if (y < arr.length-1) {
//      val nodes = getNode(x-1,y)
//      val lastNode = nodes._2
//      val zerothNode = nodes._1
//      val firstNode = zerothNode.next.get
//      end.get.next = Some(firstNode)
//      lastNode.next = None
//      zerothNode.next = Some()
//    }
//  }
//}
