package ScalaSolutions.LongTrip

/**
 * Created by Rahul on 6/21/16.
 */
class LongTrip {
  def calculatePenalty(d1 : Int,d2 : Int) : Option[Int] = {
    val d = d2-d1
    val res = 200-d
    if (res < 0) {
      None
    }else {
      Some(res * res)
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
      val ifIDontStopHere = ifIDontStopHere(index,lastStop,arr)

      ifIStopHerePenalty match {
        case None => ifIDontStopHere
        case Some(x) => {
          ifIStopHerePenalty match {
            case None => ifIStopHerePenalty
            case Some(y) => Some(Math.min(x,y))
          }
        }
      }
    }
  }

}


