package ScalaSolutions.Uber

import scala.collection.mutable

/**
 * Created by Rahul on 10/19/16.
 */
class BinaryTreeNode(val  value : Int,var left : BinaryTreeNode,var right : BinaryTreeNode) {

}

class VertexVisitedData {
  var pre = 0
  var post = 0
}


class ValidateTree(lst : List[BinaryTreeNode])  {
  val visited = new mutable.HashSet[Int]()
  val inDegree = new mutable.HashMap[Int,Int]()
  val present = new mutable.HashSet[Int]()
  for (node <- lst) {
    present.+=(node.value)
    inDegree.+=((node.value,0))
  }

  def depthFirst(node : BinaryTreeNode): Unit = {
    println("VisitingNode " + node.value)
    if (visited.contains(node.value) == false) {
      visited.+=(node.value)
      val left = node.left
      val right = node.right
      if (left != null && present(left.value)) {
        //Update indegree
        val indegreeForThisVertex = inDegree.get(left.value).get
        inDegree.put(left.value,indegreeForThisVertex+1)

        depthFirst(left)
      }

      if (right != null && present(right.value)) {
        //Update indegree
        println("Here 2")
        val rightIndexIndegree = inDegree.get(right.value).get
        println("Here 2 " + rightIndexIndegree + " " + (rightIndexIndegree+1))
        inDegree.put(right.value,rightIndexIndegree+1)

        depthFirst(right)
      }


    }else {
      println("Alreadt visited")
    }
  }

  def validTree() : Boolean = {


    //Depth first
    for (node <- lst) {
      depthFirst(node)
    }

    var countZeroVertex = 0
    var countOneVertex = 0
    var retValue = true
    inDegree.foreach(v => {
      if (v._2 == 0) {
        countZeroVertex += 1
      }else {
        if (v._2 == 1) {
          countOneVertex += 1
        }else {
          retValue = false
        }
      }
    })

    println(" test 2 " + countOneVertex + " " + countZeroVertex)
    if (retValue == false) {
      println("False here")
      false
    }else {
      if (countZeroVertex == 1 && countOneVertex == lst.size-1) {
        true
      }else {
        false
      }
    }
  }
}


object Main extends App {
  val threeNode = new BinaryTreeNode(3,null,null)
  val twoNode = new BinaryTreeNode(2,threeNode,null)
  val fiveNode = new BinaryTreeNode(5,null,null)
  val fourNode = new BinaryTreeNode(4,null,fiveNode)
  val oneNode = new BinaryTreeNode(1,twoNode,fourNode)

  threeNode.left = twoNode

  val validateTree = new ValidateTree(List[BinaryTreeNode](oneNode,twoNode,threeNode))

  println(validateTree.validTree())
  println(validateTree.inDegree)

}