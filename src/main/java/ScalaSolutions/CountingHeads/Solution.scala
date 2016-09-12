package ScalaSolutions.CountingHeads

/**
 * Created by rasrivastava on 9/12/16.
 */
object Solution extends App {

  def countProbability(probabilityDist: Array[Double] , k : Int) : Double = {
    val rows = probabilityDist.length+1
    val cols = probabilityDist.length+1
    println(rows + " " + cols )
    val arr = Array.ofDim[Double](rows,rows)
    //For first row when none of them is selected
    arr(0)(0) = 1.0
    for (i <- 1 to cols-1) {
      arr(0)(i) = 0
    }

    //println("test")

//    arr(1)(0) = 1.0 - probabilityDist(0)
    for (j<-1 to rows-1) {
      arr(j)(0) = (1.0 - probabilityDist(j-1))*(arr(j-1)(0))
    }

    //println("test1")

    for (i <- 1 to rows-1) {
      for {j <- 1 to cols-1
           if (j <= i)} {
        //println(i + " " + j)
        val old = arr(i-1)(j)
        val old1 = arr(i-1)(j-1)
        val updatedValue = old* (1.0-probabilityDist(i-1)) + old1*probabilityDist(i-1)
        arr(i)(j) = updatedValue
      }
    }

    println(arr.deep.mkString(","))
    arr(rows-1)(k)
  }

  val probability = Array[Double](0.6,0.7)
  println(countProbability(probability,1))



}
