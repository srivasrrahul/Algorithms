package Kattis



import scala.collection.mutable


/**
 * Created by Rahul on 1/4/17.
 */

class Element(val count : Int ,val ch: Char)  {

}

object ElementOrdering extends Ordering[Element] {
  override def compare(x: Element, y: Element): Int = Integer.compare(x.count,y.count)
}
class Simplicity(val str  : String) {

  def calculate(): Int = {

    val charMap = new mutable.HashMap[Char,Int]()
    str.foreach(ch => {
      if (charMap.contains(ch) == false) {
        charMap.+=((ch,0))
      }

      charMap.put(ch,charMap.get(ch).get+1)
    })

    val array = new Array[Element](charMap.size)
    var index = 0
    charMap.foreach(entry => {
      array(index) = new Element(entry._2,entry._1)
      index = index + 1
    })

    scala.util.Sorting.quickSort(array)(ElementOrdering)

    if (array(0).count == 1) {
      array.size-2
    }else {
      array.size-1
    }


  }
}


object SimplicityMain {
  def main (args: Array[String]){
    //val s = new Simplicity("aaaaaa")
    //println(s.calculate())
    val s = scala.io.StdIn.readLine()
    println(new Simplicity(s).calculate())
  }
}