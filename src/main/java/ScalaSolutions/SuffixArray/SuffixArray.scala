package ScalaSolutions.SuffixArray


/**
 * Created by Rahul on 9/28/16.
 */
class SuffixArray(val str : String) {
  val arr = new Array[String](str.length)
  def createSuffixArray() : Unit = {
    for (i <-0 to str.length-1) {
      arr(i) = str.substring(i)
    }

    scala.util.Sorting.quickSort(arr)
  }

  def getArr(): Array[String] = {
    arr
  }

  createSuffixArray()


}

class SuffixArrayOperation(val suffixArray: SuffixArray) {
  def getCommonLength(str1 : String,str2 : String) : Int = {
    var commonLength = 0
    for (i <- 0 to str1.length-1 if i < str2.length-1) {
      if (str1(i) == str2(i)) {
        commonLength  = commonLength + 1
      }
    }

    commonLength
  }
  def getLargestRepeatedSubstring() : String = {
    val arr = suffixArray.getArr()
    var maxLength = 0
    var maxString = ""
    for (i <- 0 to arr.length-2) {
      val tempLength = getCommonLength(arr(i),arr(i+1))
      if (tempLength > maxLength) {
        maxLength = tempLength
        maxString = arr(i).substring(0,maxLength)
      }
    }

    maxString
  }
}

object SuffixArrayTest extends App {
  val s = new SuffixArray("banana")
  println(s.getArr().deep.mkString(","))
  println(new SuffixArrayOperation(s).getLargestRepeatedSubstring())
}
