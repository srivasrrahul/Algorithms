package ScalaSolutions.FunctionalDataStructures

/**
 * Created by Rahul on 6/11/16.
 */

class BinarySearchTree[T <: Ordering[T]](x : T,y : BinarySearchTree[T],z : BinarySearchTree[T]) {
  val data : T = x
  val left : BinarySearchTree[T]  = y
  val right : BinarySearchTree[T] = z

  def insert(value : T) : BinarySearchTree[T] = {
    if (value.compare(value,data) < 0) {
      if (left != null) {
        new BinarySearchTree[T](data, left.insert(value), right)
      }else {
        new BinarySearchTree[T](data,new BinarySearchTree[T](value,null,null),right)
      }
    }else {
      if (value == data) {
        if (right != null) {
          new BinarySearchTree[T](data, left, right.insert(value))
        }else {
          new BinarySearchTree[T](data,left,new BinarySearchTree[T](value,null,null))
        }
      }else {
        throw new IllegalArgumentException("value already exists");
      }
    }
  }

  def search(value : T) : BinarySearchTree[T] = {
    if (value == data) {
      this
    }else {
      if (value.compare(value,data) < 0) {
        if (left != null) {
          left.search(value)
        }else {
          null
        }
      }else {
        if (right != null) {
          right.search(value)
        }else {
          null
        }
      }
    }
  }

}


object BinarySearchTreeMain {
  //val t1 : BinarySearchTree[Int] = new BinarySearchTree[Int](10,null,null)
  //val t2 = t1.insert(20)
  //val t3 = t2.insert(5)
  //println(t3)
}
