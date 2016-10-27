package ScalaSolutions.Fences

/**
 * Created by rasrivastava on 10/17/16.
 */

import scala.util.control.Breaks._
object FencesType {
  val empty = 0
  val goat = 1
  val fence = 2
}
class Fences(val config : Array[Array[Int]]) {
  val row = config.length
  val col = config(0).length

  def ifOnTheFence(x : Int,y: Int): Boolean = {
    x == 0 || x == row-1 || y == 0 || y == col-1
  }

  def addFence(x : Int,y : Int) : Int = {

    var fenceRequired = 0
    if (x-1 >= 0 && config(x-1)(y) == FencesType.empty) {
      config(x-1)(y) = FencesType.fence
      fenceRequired += 1
    }

    if (x+1 < row && config(x+1)(y) == FencesType.empty) {
      config(x+1)(y) = FencesType.fence
      fenceRequired += 1
    }

    if (y-1 >=0 && config(x)(y-1) == FencesType.empty) {
      config(x)(y-1) = FencesType.fence
      fenceRequired += 1
    }

    if (y+1 < col && config(x)(y+1) == FencesType.empty) {
      config(x)(y+1) = FencesType.fence
      fenceRequired += 1
    }


    fenceRequired



  }

  def minFence() : Int = {
    //println(row + " " + col)
    var minFence = 0
    breakable {
      for (i <- 0 to row - 1) {
        for (j <- 0 to col - 1) {
          if (ifOnTheFence(i,j) && config(i)(j) == FencesType.goat) {
            minFence = -1
            break()
          }

          val value = config(i)(j)
          value match {
            case FencesType.goat =>
              //println("Found goat for " + i + " " + j  + " " + value)
              minFence += addFence(i,j)
            case _ => {
              //No op
            }
          }
        }
      }
    }

    minFence
  }

}

object Main extends  App {
  val line = readLine
  val lineArr = line.split(" ")
  val row = lineArr(0).toInt
  val col = lineArr(1).toInt
  val config = Array.ofDim[Int](row,col)

  for (i <- 0 to row-1) {
    val arr = new Array[Int](col)
    val lineVal = readLine()
    for (j <- 0 to lineVal.length-1) {
      lineVal.charAt(j) match {
        case '.' => {
          arr(j) =FencesType.empty
        }
        case 'X' => {
          //println("Adding goat for " + i + " " + j)
          arr(j) = FencesType.goat
        }
      }

      config(i) = arr
    }
  }

  val fences = new Fences(config)

  println(fences.minFence())
}
