package ScalaSolutions.NumberPairs

/**
 * Created by Rahul on 5/14/16.
 */

object Main extends App{

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (l <- lines) {
      // Do something with each non-blank line
    val arr  = l.split(";")
    val n = arr(1).toInt
    val lst = parseResult(arr(0))

    //println(lst.mkString(" "))
    var res = search(n,lst)
    if (res.size > 0) {
      var count = 0
      res.foreach(x => {
        if (count < res.size-1) {
          print(x._1 + "," + x._2 + ";")
        }else {
          print(x._1 + "," + x._2)
        }

        count += 1

      })

      println()
    }else {
      println("NULL")
    }

  }

  def parseResult(strLst : String) : Array[Int] = {
    def itr(str : Array[String],index : Int,lst : Array[Int]) : Array[Int] = {
      if (index == str.length) {
        lst
      }else {
        itr(str,index+1,lst.:+(str(index).toInt))
      }
    }

    itr(strLst.split(","),0,Array())
  }
  def search(n : Int,lst : Array[Int]) : Array[(Int,Int)]= {

    var index = 0
    var result : Array[(Int,Int)] = Array()
    for (x <- lst) {
      //println("Iterating " + x + " " + (n-x))
      if (index < lst.size-1) {
        val resultIndex = java.util.Arrays.binarySearch(lst, index+1, lst.size - 1, n - x)
        if (resultIndex >= 0) {
          //println("found " + resultIndex)
          //println(x + " " + (n-x))
          if (x < n - x) {
            result = result.:+(x, n - x)
          }
          else {
            result = result.:+(n - x, x)
          }
        }
      }

      index += 1
    }

    result
  }

}
