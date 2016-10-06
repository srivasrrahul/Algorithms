package ScalaSolutions.GraphProblems

/**
 * Created by rasrivastava on 10/3/16.
 */
trait GraphType {
  def addEdge(u : Int,v : Int)
  def getVertxList(): Array[Node]

}
