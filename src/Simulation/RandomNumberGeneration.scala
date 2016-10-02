package Simulation

/**
 * Created by rasrivastava on 9/25/16.
 */

class MonteCarloMethod {
  val randomVariableX = new scala.util.Random
  val randomVariableY = new scala.util.Random
  def distanceFromOrigin(x : Double,y : Double) : Double = {
    Math.sqrt(x*x + y*y)
  }

  def ifInsideCircle() : Boolean = {
    distanceFromOrigin(randomVariableX.nextDouble(),randomVariableY.nextDouble()) < 1.0

  }

  def estimatePi() : Double = {
    val total = 1000000
    var insideCircle = 0
    for (i <- 0 to total-1) {
      if (ifInsideCircle()) {
        insideCircle = insideCircle+1
      }
    }

    4*(insideCircle*1.0/(total*1.0))
  }


}
object Solution extends  App {
  val v = new MonteCarloMethod
  println(v.estimatePi())
}
