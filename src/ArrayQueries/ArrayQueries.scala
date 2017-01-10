package ArrayQueries

import scala.collection.mutable

/**
 * Created by Rahul on 1/9/17.
 */

class Node(val index : Int,val value : Int) extends Ordered[Node]{
  override def compare(that: Node): Int = index.compare(that.index)
}
class ArrayQueries (val tree : mutable.TreeSet[Node]) {
  def commandPutFront(x : Int, y : Int) : Unit = {
    val set = tree.range(new Node(x,-1),new Node(y,-1))
    val lst : List[Node] = set.toList
    tree.--=(set)


  }
}

object Main extends App {
  val arr = Array(1, 2, 3, 4, 5, 6, 7, 8)
  val treeSet = new mutable.TreeSet[Node]()
  for (i <- 1 to arr.length) {
    treeSet.+=(new Node(i,arr(i)))
  }
}
