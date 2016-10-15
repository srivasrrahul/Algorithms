package ScalaSolutions.ManyFormulas

import scala.collection.mutable.ListBuffer


class Sum(str : String) {

  def eval(expr : String) : Long = {
    val arr = expr.split("\\+")
    var sum : Long = 0
    arr.foreach(s => {
      sum += s.toLong
    })

    sum
  }

  def getSum(): Long = {
    def getSumItr(index : Int) : List[String] = {
      if (index == str.length-1) {
        List(str.last.toString)
      }else {
        val current = str.charAt(index).toString
        val subLst = getSumItr(index+1)
        val lstBuffer = new ListBuffer[String]
        subLst.foreach(subElement => {
          val updatedElement = current + subElement
          lstBuffer.+=(updatedElement)
          val newElement = current + "+" + subElement
          lstBuffer.+=(newElement)
        })
        lstBuffer.toList
      }
    }

    val lst = getSumItr(0)
    var sum : Long = 0
    lst.foreach(elem => {
      sum += eval(elem)
    })

    sum

  }
}

object Main extends  App {
//  val s = new Sum("9999999999")
//  println(s.getSum())
  var a = readLine()
//  var num = readLine
//  var s = readLine
//  var sum = a + num.split(" ")(0).toInt + num.split(" ")(1).toInt
  val s  = new Sum(a)
  println(s.getSum())
}
