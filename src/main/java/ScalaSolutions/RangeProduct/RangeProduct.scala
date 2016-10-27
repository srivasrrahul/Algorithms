package ScalaSolutions.RangeProduct

/**
 * Created by rasrivastava on 10/23/16.
 */
class RangeProduct(val a : Int,val b : Int) {
  def getPositives(range: Range) : Int = {
    if (range.min < 0 && range.max < 0) {
      0
    }else {
      range.length
    }
  }

  def getNegatives(range: Range) : Int = {
    if (range.min > 0 && range.max > 0) {
      0
    }else {
      range.length
    }
  }


  def getSign() : String = {
    val range = a to b
    if (range.contains(0)) {
      "Zero"
    }else {
      val positives = getPositives(range)
      val negatives = getNegatives(range)
      if (positives > 0) {
        "Positive"
      }else {
        if (negatives > 0) {
          if (negatives % 2 == 0) {
            "Positive"
          }else {
            "Negative"
          }
        }else {
          "Zero"
        }
      }
    }
  }
}


object Main extends App {
  val line = readLine()
  val lineArr = line.split(" ")
  println(new RangeProduct(lineArr(0).toInt,lineArr(1).toInt).getSign())
}