package ScalaSolutions.Strings.SubStrings

import scala.util.control.Breaks._
/**
 * Created by rasrivastava on 10/11/16.
 */
class SuffixArray(val str : String) {
  val suffixArr = new Array[String](str.length)
  for (i <- 0 to str.length-1) {
    suffixArr(i) = str.substring(i)
  }

  scala.util.Sorting.quickSort(suffixArr)
  println(suffixArr.deep.mkString(","))
  var subStringsCount = 0
  for (i <-1 to suffixArr.length-1) {
    val lcsCount = lcs(suffixArr(i-1),suffixArr(i))
    println(" For " + suffixArr(i-1) + " " + suffixArr(i) + " " + lcsCount)
    subStringsCount = subStringsCount +  suffixArr(i).length - lcsCount
  }

  def getSubStringCount() : Int = {
    subStringsCount
  }


  def lcs(str1 : String,str2 : String): Int = {
    var count = 0
    breakable {
      for (i <- 0 to str1.length - 1) {
        if (i < str2.length) {
          if (str1(i) == str2(i)) {
            count = count + 1
          }else {
            break()
          }
        }
      }
    }

    count
  }
}

object SubString extends App {
  val str = "BANANA"
  val suffixArr = new SuffixArray(str)
  println(suffixArr.getSubStringCount())
}
