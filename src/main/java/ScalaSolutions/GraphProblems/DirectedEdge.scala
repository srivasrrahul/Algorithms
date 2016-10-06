package ScalaSolutions.GraphProblems

/**
 * Created by Rahul on 10/6/16.
 */
class DirectedEdge(val source : Int,val dest : Int,val weight : Double) {
  override def toString: String = {
    val str = new StringBuilder
    str.append(source)
    str.append("=>")
    str.append(dest)
    str.append(": ")
    str.append(weight)
    str.toString()
  }

}
