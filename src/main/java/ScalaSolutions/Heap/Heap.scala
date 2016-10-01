package ScalaSolutions.Heap

/**
 * Created by rasrivastava on 9/27/16.
 */
class MinHeap(n : Int) {
  val arr = new Array[Int](n)
  var heapSize = 0
  def addElement(x : Int): Boolean = {
    if (heapSize >= arr.length) {
      false
    }else {

      arr(heapSize) = x
      heapify(heapSize)
      heapSize = heapSize + 1
      true
    }
  }

  def parent(index : Int ) : Int = index/2
  def rchild(index : Int) : Int = 2*index+1
  def lchild(index : Int) : Int = 2*index

  def swap(i : Int,j : Int) : Unit = {
    val t = arr(i)
    arr(i) = arr(j)
    arr(j) = t
  }
  def validIndex(index : Int) : Boolean = {
    index >=0 && index <= heapSize && index <= arr.length-1
  }
  def heapify(index : Int) : Unit = {
    if (index > 0) {
      val parentIndex = parent(index)
      if (validIndex(parentIndex)) {
        if (arr(parentIndex) > arr(index)) {
          swap(parentIndex,index)
          heapify(parentIndex)
        }
      }
    }
  }

  def heapifyDown(index : Int) : Unit = {
    var lowestIndex = index
    if (validIndex(lchild(index))) {
      if (arr(lowestIndex) > arr(lchild(index))) {
        lowestIndex = lchild(index)
      }
    }

    if (validIndex(rchild(index))) {
      if (arr(lowestIndex) > arr(rchild(index))) {
        lowestIndex = rchild(index)
      }
    }

    if (lowestIndex != index) {
      swap(lowestIndex,index)
      heapifyDown(lowestIndex)
    }
  }

  def extractMin() : Option[Int] = {
    if (heapSize <= 0) {
      None
    }else {
      val retValue = arr(0)
      swap(0,heapSize-1)
      heapifyDown(0)
      heapSize = heapSize-1
      Some(retValue)
    }
  }

  override def toString: String = {
    arr.deep.mkString(",")
  }
}

object Sample extends App {
  val minHeap = new MinHeap(5)
  minHeap.addElement(17)
  minHeap.addElement(15)
  minHeap.addElement(4)
  minHeap.addElement(13)
  minHeap.addElement(7)
  println(minHeap)
  for (i <- 0 to 4) {
    println(minHeap.extractMin())
  }
  println(minHeap)
}
