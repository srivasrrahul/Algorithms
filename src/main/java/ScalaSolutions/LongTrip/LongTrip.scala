package ScalaSolutions.LongTrip

/**
 * Created by Rahul on 6/21/16.
 */
class LongTrip {
  def calculatePenalty(d1 : Int,d2 : Int) : Option[Int] = {
    val d = d2-d1
    val res = 200-d
      Some(res * res)

  }

  def calculatePenaltyInt(d1 : Int,d2 : Int) : Int = {
    val d = d2-d1
    val res = 200-d
    (res * res)

  }

  def findMin(o1 : Option[Int],o2 : Option[Int]) : Option[Int] = {
    o1 match {
      case None => o2
      case Some(x) => {
        o2 match {
          case None => o1
          case Some(y) => Some(Math.min(x,y))
        }
      }
    }
  }

  def ifIStopHere(index : Int,lastStop : Int,arr : Array[Int]) : Option[Int] = {
    val penalty =  calculatePenalty(arr(index),arr(lastStop))
    penalty match {
      case None => None
      case Some(x) => {
        val prevValue = trip(index-1,index,arr)
        prevValue match {
          case None => None
          case Some(y) => Some(x+y)
        }
      }
    }
  }

  def ifIDontStopHere(index : Int,lastStop : Int,arr : Array[Int]) : Option[Int] = {

    val prevValue = trip(index-1,lastStop,arr)
    prevValue match {
      case None => None
      case Some(y) => Some(y)
    }


  }

  def trip(index : Int,lastStop : Int,arr : Array[Int]) : Option[Int] = {
    //println(index + " " + lastStop)
    val diff = arr(lastStop) - arr(index)
    val penalty =  calculatePenalty(arr(index),arr(lastStop))
    if (index == 0) {
      penalty match {
        case None => None
        case Some(x) => Some(x)
      }
    }else {
      //Stop for today here
      val ifIStopHerePenalty = ifIStopHere(index,lastStop,arr)
      val ifidontStopHere = ifIDontStopHere(index,lastStop,arr)

      findMin(ifidontStopHere,ifIStopHerePenalty)

    }
  }

  def tripRecursive(index : Int,arr : Array[Int]) : Int = {
    //println("Index is " + index)
    //println(index + " " + lastStop)
    if (index == 1) {
      calculatePenaltyInt(0,arr(1))
    }else {
      var minPenalty = Int.MaxValue
      for (i <- index-1 to 1 by -1) {
        val tripCostFromHere = tripRecursive(i,arr)
        //println("Trip Cost index " + index + " " + i + " " + tripCostFromHere)
        val minPenaltyTemp = calculatePenaltyInt(arr(i),arr(index)) + tripCostFromHere
        if (tripCostFromHere != Int.MaxValue && minPenaltyTemp < minPenalty) {
          //println("MinPenaltyTemp index " + index + " " + minPenaltyTemp)
          minPenalty = minPenaltyTemp
        }
      }


      minPenalty
    }


  }

  def getPath(minDistance : Array[Int],arr : Array[Int]) : Array[Int] = {
    var minValue = minDistance.last
    var path = Array[Int]()
    path = path.+:(arr(minDistance.length-1))

    for (j <- minDistance.length-2 to 1 by -1) {
      if (minDistance(j) <= minValue) {
        path = path.+:(arr(j))
        minValue = minDistance(j)


      }
    }

    path
  }
  def tripDP(arr : Array[Int]) : Array[Int] = {
    val costMatrix = Array.ofDim[Int](arr.length,arr.length)
    for (i <- 0 to arr.length-1) {
      for (j<-i+1 to arr.length-1) {
        costMatrix(i)(j) = calculatePenaltyInt(arr(i),arr(j))
      }
    }

    //println(costMatrix.deep.mkString(" "))

    val minDistance = Array.fill[Int](arr.length)(0)
    minDistance(0) = 0
    minDistance(1) = costMatrix(0)(1)
    for (j <- 2 to minDistance.length-1) {
      var minPenalty = Int.MaxValue
      for (k <- j-1 to 1 by -1) {
        val dist = costMatrix(k)(j) + minDistance(k)
        if (dist < minPenalty) {
          minPenalty = dist
        }
      }

      minDistance(j) = minPenalty
    }


    println(minDistance.deep.mkString(","))
    //minDistance(minDistance.length-1)
    getPath(minDistance,arr)

  }

}

object Main extends  App {
  val arr = Array(0,200,201,290,400,402)
  val longTrip = new LongTrip
  println("Value is " + longTrip.tripRecursive(arr.length-1,arr))
  //println("Value is " + longTrip.trip(arr.length-2,arr.length-1,arr))
  println("Value is " + longTrip.tripDP(arr).deep.mkString(","))

}


