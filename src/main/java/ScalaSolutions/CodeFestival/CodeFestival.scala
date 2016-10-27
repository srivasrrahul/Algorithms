package ScalaSolutions.CodeFestival

/**
 * Created by rasrivastava on 10/17/16.
 */
class CodeFestival(val destination: String) {
  def inputStr(source : String) : Int = {
    var count = 0
    for (i <- 0 to source.length-1) {
      val sch = source.charAt(i)
      val dch = destination.charAt(i)
      if (sch != dch) {
        count += 1
      }
    }

    count
  }

}

object Main extends App {
  val line = readLine()
  val codeFestival = new CodeFestival("CODEFESTIVAL2016")
  println(codeFestival.inputStr(line))
}
