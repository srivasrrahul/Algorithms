package ScalaSolutions.STring

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * Created by Rahul on 10/23/16.
 */

class StringManipulation(val str : String) {
  def removePattern() : Int = {
    val lstBuffer = new ListBuffer[Int]
    val stack  = new mutable.Stack[Int]()
    for (i <- str.length-1 to 0 by -1) {

      val currentNode = str.charAt(i)
      //println("For index " + i + " " + currentNode)
      currentNode match {
        case 'S' => {
          if (stack.size == 0) {

            lstBuffer.prepend(i)
          }else {
            stack.pop()
            lstBuffer.remove(0)
          }
        }
        case 'T' => {
          stack.push(i)
          lstBuffer.prepend(i)
        }
      }
    }

    lstBuffer.size



  }


}

object Main extends App {
  val line = readLine()
  val stringManipulator  = new StringManipulation(line)
  println(stringManipulator.removePattern())
}
