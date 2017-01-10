import scala.annotation.tailrec
import scala.util.Random
import scala.util.control.Breaks._

/**
 * Created by Rahul on 1/8/17.
 */
object LazyLoadingConstant {
  val minWeight = 50

}


class LazyLoading {

  def evalInterface(orderedArray : Array[Int]) : Int = {
    @tailrec
    def evalItr(orderedArray: Array[Int], startIndex: Int, endIndex: Int, finalOutput: Int): Int = {


      //println(" start index " + startIndex + " " + endIndex)
      if (startIndex > endIndex) {
        finalOutput
      } else {
        val maxItem = orderedArray(endIndex)
        var weightEncounterd = maxItem


        var lastIndex = startIndex
        if (weightEncounterd >= LazyLoadingConstant.minWeight) {
          evalItr(orderedArray, startIndex, endIndex - 1,finalOutput + 1)
        } else {
          breakable {
            for (i <- startIndex to endIndex - 1) {
              //val weight = orderedArray(i)
              if (weightEncounterd + maxItem >= LazyLoadingConstant.minWeight) {
                weightEncounterd = weightEncounterd + maxItem
                lastIndex = i + 1
                break()
              } else {
                weightEncounterd = weightEncounterd + maxItem
              }
            }
          }
          //println("Weight " + weightEncounterd)
          if (weightEncounterd < LazyLoadingConstant.minWeight) {
            finalOutput
          } else {
            evalItr(orderedArray, lastIndex, endIndex - 1, finalOutput + 1)
          }
        }


      }

    }

    scala.util.Sorting.quickSort(orderedArray)
    evalItr(orderedArray,0,orderedArray.size-1,0)
  }


}

object LazyLoadingMain extends App {
  def randInRange(low : Int,high : Int,random: Random) : Int = {
    low + random.nextInt(high-low+1)
  }

  def generateTestCases() : Unit = {
    val random = new Random(System.currentTimeMillis())
    for (i <- 0 to 10) {
      val array = new Array[Int](5)
      for (j <- 0 to array.length-1) {
        array(j) = randInRange(1,100,random)
      }

      //println(array.deep.mkString(" "))
      val lazyLoading = new LazyLoading
      println(lazyLoading.evalInterface(array))
      println(array.deep.mkString(" "))
    }
  }

  //generateTestCases()
  val testCases = scala.io.StdIn.readLine().toInt
  for (i <- 0 to testCases-1) {
    val itemCount = scala.io.StdIn.readLine().toInt
    val array = new Array[Int](itemCount)
    for (j <- 0 to itemCount-1) {

      val x = scala.io.StdIn.readLine().toInt
      //println("x is " + x)
      array(j) = x
    }

    //scala.util.Sorting.quickSort(array)
    //println(array.deep.mkString(" "))
    val lazyLoading = new LazyLoading
    //println(lazyLoading.eval(array,0,array.size-1))
    println("Case #" + (i+1) + ": " + lazyLoading.evalInterface(array))
  }
}

