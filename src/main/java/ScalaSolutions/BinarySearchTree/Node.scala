package ScalaSolutions.BinarySearchTree

/**
 * Created by rasrivastava on 10/21/16.
 */


class Node[T](var value : T) {
  var left : Option[Node[T]] = None
  var right : Option[Node[T]] = None

  override def toString: String = {
    value.toString
  }
}

class BinarySearchTree[T <% Ordered[T]] {
  var root : Option[Node[T]] = None
  def addElement(value : T) : Unit = {
    def addElementInternal(node : Option[Node[T]]) : Node[T] = {
      //var currentNode = node
//      if (currentNode == null) {
//        currentNode = new Node[T](value)
//      }else {
//        if (currentNode.value < value) {
//          currentNode.right = addElementInternal(currentNode.right)
//        }else {
//          currentNode.left = addElementInternal(currentNode.left)
//        }
//      }
//
//      currentNode
      node match  {
        case None => {
          val newNode = new Node[T](value)
          newNode
        }
        case Some(currentNode) => {
          if (currentNode.value < value) {
            currentNode.right = Some(addElementInternal(currentNode.right))
          }else {
            currentNode.left = Some(addElementInternal(currentNode.left))
          }

          currentNode

        }
      }

    }

    root = Some(addElementInternal(root))
  }

  def searchElement(value : T) : Option[Node[T]] = {
    def searchInternal(node : Option[Node[T]]) : Option[Node[T]] = {
      node match {
        case None => {
          None
        }
        case Some(currentNode) => {
          if (currentNode.value < value) {
            searchInternal(currentNode.right)
          }else {
            if (currentNode.value > value) {
              searchInternal(currentNode.left)
            }else {
              node
            }
          }
        }
      }

    }

    searchInternal(root)
  }

  def isBST() : Boolean = {
    //result is true/false,min,max
    def isBSTInternal(node : Option[Node[T]]) : Tuple3[Boolean,Option[T],Option[T]] = {
      node match {
        case None => {
          (true,None,None)
        }
        case Some(currentNode) =>
          val leftResult = isBSTInternal(currentNode.left)
          val rightResult = isBSTInternal(currentNode.right)

          if (leftResult._1 == false || rightResult._1 == false) {
            (false,None,None)
          }else {
            var leftMaxValue : Option[T] = None
            var leftMinValue : Option[T] = None
            leftResult match {
              case (_,b : Option[T],c : Option[T]) =>

                leftMinValue = b
                leftMaxValue = c
//              case (_,_,Some(maxValue)) =>
//                leftMaxValue = Some(maxValue)
            }

            var rightMinValue : Option[T] = None
            var rightMaxValue : Option[T] = None
            rightResult match {
              case (_,b : Option[T],c : Option[T]) => {

                rightMinValue = b
                rightMaxValue = c
              }

            }

            var result = true
            leftMaxValue match {
              case None => result = true
              case Some(x) =>  {
//                if (x == 100) {
//                  println(currentNode.value)
//                }
                if (x > currentNode.value) {
                  //println("False here")
                  result = false
                }

              }
            }

            rightMinValue match {
              case None => result = result & true
              case Some(x) => {
                if (currentNode.value > x) {
                  result = false
                }
              }
            }

            if (result == false) {
              (false,None,None)
            }else {
              leftMinValue match {
                case None => {
                  leftMinValue = Some(currentNode.value)
                }
                case _ => {

                }
              }

              rightMaxValue match {
                case None => {
                  rightMaxValue = Some(currentNode.value)
                }
                case _ => {

                }
              }

              (true,leftMinValue,rightMaxValue)
            }
          }
      }

    }

    isBSTInternal(root) match {
      case (true,_,_) => {
        true
      }
      case (_,_,_) => {
        false
      }
    }
  }

  def preOrder() : Unit = {
    def preOrderInternal(node : Option[Node[T]],tabs : String) : Unit = {
      node match {
        case None => {

        }
        case Some(x : Node[T]) => {
          //println(x)
          println(tabs + x.value)
          preOrderInternal(x.left, tabs + " ")

          preOrderInternal(x.right, tabs + " ")


        }
      }
    }

    preOrderInternal(root," ")
  }

}


object BinarySearchTreeTest extends App {
  val bst = new BinarySearchTree[Int]()
  bst.addElement(10)
//  bst.addElement(20)
//  bst.addElement(5)
//  bst.addElement(8)
//  bst.addElement(9)
//  bst.addElement(3)
//  bst.addElement(4)

  bst.addElement(20)
  bst.addElement(5)
  bst.addElement(7)
  bst.addElement(3)
  bst.addElement(11)
  bst.addElement(15)
  bst.addElement(19)
  bst.addElement(1)
  val node = bst.searchElement(1)
  node.get.value = 100
  println(bst.preOrder())

  println(bst.isBST())
}