package ScalaSolutions.UnixCommands

/**
 * Created by rasrivastava on 10/5/16.
 */
class ListCommand(val args: Array[String]) {
   def executePlain() : Array[String] = {
     val currentDir = new java.io.File(".").getCanonicalPath
     val dir = new java.io.File(currentDir)
     if (dir.exists()) {
       dir.list()
     }else {
       Array()
     }
   }


}

object ListTest extends App {
  val listCommand = new ListCommand(Array())
  println(listCommand.executePlain().deep.mkString(","))
}
