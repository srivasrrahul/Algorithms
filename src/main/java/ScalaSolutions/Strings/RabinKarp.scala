package ScalaSolutions.Strings

/**
 * Created by rasrivastava on 10/13/16.
 */
import scala.util.control.Breaks._
//Assume all small case
class RabinKarp(val text : String) {
  val prime : Long = 101
  def getHash(str : String,start : Int,end : Int) : Long = {
    //println(str + " " + start + " " + end)
    var hash : Long = 0
    for (i <- start to end) {
      hash += (str.charAt(i) - 'a') * Math.pow(prime,(i-start)).toLong
    }

    hash
  }

  def rollForwardHash(hash : Long,nextIndex : Int,startIndex : Int) : Long = {
    var updatedHash = hash
    updatedHash = (updatedHash - (text.charAt(startIndex) - 'a'))/prime
    updatedHash = updatedHash + (text.charAt(nextIndex)-'a') * Math.pow(prime,nextIndex-startIndex-1).toLong
    updatedHash
  }
  def find(pattern : String) : Int = {
    val len = pattern.length
    if (pattern.length > text.length) {
      -1
    }else {
      val patternHash = getHash(pattern,0,pattern.length-1)
      //println("PatternHash " + patternHash)
      var currentHash = getHash(text,0,pattern.length-1)
      if (currentHash == patternHash) {
        0
      }else {
        var j = 0
        var index = -1
        breakable {
          //println(pattern.length + " " + text.length)
          for (i <- pattern.length to text.length - 1) {
            //println(" Index = " + i)
            currentHash = rollForwardHash(currentHash, i, j)
            if (currentHash == patternHash) {
              //val subStr = text.substring(i+1-pattern.length,i+1)
              //if (subStr.equals(currentHash)) {
                index = i
                break()
              //}
            }
            j = j + 1
          }
        }

        index

      }
    }
  }
}

object RabinKarpTest extends App {

  def naiveTest(pattern : String,text : String): Int = {
    val len = pattern.length
    var index = -1
    breakable {
      for (i <- 0 to text.length - 1) {
        val x = text.substring(i, i + len)
        if (x.equals(pattern)) {
          index = i
          break()
        }
      }
    }

    index
  }
  val pattern = "bcd"
  val text = "quyweiqweqiyeyqieyiqwyeyqiuyeiuyqweyuquyeiuyqeyuiquyeyuqyeiqyewyqiueyuqyiueyiqweiyuqwiuyewyiuqweyuquiyeqiyeyqbcdqyweuiqyewiuqyeyqyeyqyeqye"
  val rk = new RabinKarp(text)
  val t1 = System.currentTimeMillis()
  val index1 = rk.find(pattern)
  val t2 = System.currentTimeMillis()
  //println(t2-t1)
  val t3 = System.currentTimeMillis()
  val index2 = naiveTest(pattern,text)
  val t4 = System.currentTimeMillis()

  println((t2-t1) + " " + (t4-t3))
  println(index1 + " " + index2)

}
