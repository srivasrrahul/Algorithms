package ScalaSolutions.CorruptedWord

import scala.collection.mutable
import scala.util.control.Breaks
import util.control.Breaks._

/**
 * Created by Rahul on 6/26/16.
 */

class Interval(val start: Int,val end : Int) {

}
class CorruptedWord(val dict : Set[String]) {
  def validString(str : String) : Boolean = {
    def validStringRecur(str : String,x : Int,y : Int) : Boolean = {
      println(x + " " + y)
      if (x>y) {
        println("Very bad " + x + " "  + y)
        false
      }else {
        val subStr = str.substring(x, y + 1)
        //println(subStr)
        if (x == y) {
          dict.contains(subStr)
        } else {
          if (dict.contains(subStr)) {
            //println("Valid")
            true
          } else {
            var success = false
            breakable {
              for (i <- x to y) {
                val res = validStringRecur(str, x, i-1) && validStringRecur(str, i, y)
                if (res == true) {
                  success = res
                  Breaks.break()
                }
              }
            }

            success
          }
        }
      }
    }

    validStringRecur(str,0,str.length-1)
  }

  def validStringDP(str : String) : Boolean = {
    val size = str.length
    val matrix = Array.ofDim[Int](size,size)
    for (i <- 0 to size-1) {
      for (j <- i to size-1) {
        val subStr = str.substring(i,j+1)
        if (dict.contains(subStr)) {
          matrix(i)(j) = 1
        }else {
          matrix(i)(j) = 0
        }

      }
    }

   // println(matrix.deep.mkString(","))

    var endsWithMap = new mutable.HashMap[Int,Interval]()

    for (i <- 0 to size-1) {
      if (matrix(0)(i) == 0) {
        breakable {
          for (j <- 0 to i - 1) {

            if (matrix(0)(j) == 1 && matrix(j + 1)(i) == 1) {
              //println("Test " + i + " " + j)
              matrix(0)(i) = 1
              //println(str.substring(0,j+1) + " " + str.substring(j+1,i+1))
              val interval1 = new Interval(j+1,i)

              endsWithMap = endsWithMap.+=((i,interval1))


              break()
            }
          }
        }
      }else {

      }
    }

    //println(endsWithMap.keys)

    //println(matrix.deep.mkString(","))
    var j = size-1
    while (j >= 0) {
      val interval  = endsWithMap.get(j)
      interval match {
        case Some(inter : Interval) => {
          println(str.substring(inter.start,inter.end+1))
          j = inter.start-1
        }
        case _ =>
          //This means this is full word from start
          //println("Junk")
          println(str.substring(0,j+1))
          j = -1


      }
    }

    matrix(0)(size-1) == 1
  }

}

object Main extends App {
  val s = Set("he","she","is","a","shell","hell","there","the","her")
  val p = new CorruptedWord(s)
  println(p.validStringDP("thereisahell"))
}
