package ScalaSolutions.MachineLearning.KMeans

/**
 * Created by Rahul on 7/27/16.
 */
class Point(val x : Double,val y: Double) {

}

class LineSegment(val p1 : Point,val p2 : Point) {
//  val slope = getSlope()
//  val intercept = getIntercept()
//  def getSlope() : Double = {
//    (p2.y-p1.y)/(p2.x-p1.x)
//  }
//
//  def getIntercept() : Double = {
//    p2.y-slope*p2.x
//  }

  //define if the point is left or right

  def distance() : Double = {
    val dx = p2.x-p1.x
    val dy = p2.y-p1.y
    Math.sqrt(dx*dx+dy*dy)
  }
  def isLeftSide(p : Point) : Boolean = {
    val dist = ((p2.y-p1.y) * p.x - (p2.x-p1.x) * p.y + p2.x*p1.y - p2.y*p1.x)
    dist < 0
  }

}



class KMeans {

}
