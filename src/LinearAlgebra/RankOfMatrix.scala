package LinearAlgebra

/**
 * Created by Rahul on 1/5/17.
 */

import scala.util.control.Breaks._
class RankOfMatrix(val arr : Array[Array[Double]]) {
  val rows = arr.length
  val cols = arr(0).length

  def reduce() : Unit = {
    println(rows + " " + cols)
    //val pivot = arr(0)(0)
    for (i <- 0 to rows-1) {

      if (isRowExists(i)) {
        if (isValidPivot(i,i) == false) {
          breakable {

            for (k <- i+1 to rows-1) {
              if (isValidPivot(k,i)) {
                exchangeRow(i,k)
                break()
              }
            }
          }
        }
        if (isPivot(i,i) == false) {
          for (j <- i+1 to rows-1) {
            reduceRow(j,i,i)
          }
        }
      }

    }
//    if (isPivot(0,0) == false) {
//      //reduce rows
//      for (i <- 1 to rows-1) {
//        reduceRow(i,0,0)
//      }
//    }

    //println(arr)
  }

  def isRowExists(row : Int) : Boolean = {
    row < rows && row < cols
  }

  def isValidPivot(row : Int,col: Int) : Boolean = {

    arr(row)(col) != 0
  }
  def reduceRow(rowIndex : Int,pivotRow : Int,pivotCol : Int) : Unit = {
    val factor = arr(rowIndex)(pivotCol) / arr(pivotRow)(pivotCol)
    for (j <- pivotCol to cols-1) {
      if (arr(rowIndex)(j) != 0) {
        arr(rowIndex)(j) = arr(rowIndex)(j) - factor * arr(pivotRow)(j)
      }
    }
  }

  def isPivot(row : Int,col : Int) : Boolean = {
    //println("IS pivoy chec " + row + " " + col)

    var retValue = true
    breakable {
      for (i <- row to rows-1) {
        if (i < cols && arr(i)(col) != 0) {
          retValue = false
          break
        }
      }
    }

    retValue
  }

  def exchangeRow(i : Int,j : Int) : Unit =  {
    val x = arr(i)
    val y = arr(j)
    arr(i) = y
    arr(j) = x
  }

  def isZeroRow(localArr : Array[Double]) : Boolean = {
    localArr.filter(x => x != 0).isEmpty
  }
  def getRank() : Int = {
    arr.foldLeft(0)((m : Int,localArr : Array[Double]) =>
      if (isZeroRow(localArr) == false) {
        m + 1
      }else {
        m
      }
    )

  }


}


object Main extends App {
  //val arr = Array(Array(1.0,2.0,3.0),Array(2.0,4.0,6.0),Array(2.0,6.0,8.0),(2.0,8.0,10.0))
  val arr = new Array[Array[Double]](4)
  arr(0) = Array[Double](1.0,2.0,3.0)
  arr(1) = Array[Double](2.0,4.0,6.0)
  arr(2) = Array[Double](2.0,6.0,8.0)
  arr(3) = Array[Double](2.0,8.0,10.0)
  val rank = new RankOfMatrix(arr)
  rank.reduce()
  arr.foreach(x => println(x.deep.mkString(" ")))

  println(rank.getRank())
}