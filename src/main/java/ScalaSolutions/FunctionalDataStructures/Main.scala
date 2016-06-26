package ScalaSolutions.FunctionalDataStructures

/**
 * Created by rasrivastava on 6/11/16.
 */
object Main extends App {

  var lst = List(1,2,3,4)
  var suffixValues = suffixes(lst)
  print(suffixValues)
  def suffixes[T](lst : List[T]) : List[List[T]] = {
    lst match {
      case Nil => List(List())
      case x::xs => {
        val pendingLst = suffixes(xs)
        List(lst) ++ pendingLst
      }
    }
  }

}
