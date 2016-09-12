package ScalaSolutions.ArtificialIntelligence

import ScalaSolutions.ArtificialIntelligence.States.{Dirty, Clean}

import scala.util.Random

/**
 * Created by Rahul on 7/11/16.
 */
object States {
  sealed trait EnumVal
  case object Clean extends EnumVal
  case object Dirty extends EnumVal

}
object Actions {
  sealed trait EnumVal
  case object Right extends EnumVal
  case object Left extends EnumVal
  case object Suck extends EnumVal
  val actionLst = Seq(Right,Left,Suck)
}

object Locations {
  sealed trait EnumVal
  case object LocationA extends EnumVal
  case object LocationB extends EnumVal
  val locationLst = Seq(LocationA,LocationB)
}

class PerceptSequence(val location : Locations.EnumVal,val state : States.EnumVal) {
  override def toString: String = {
    location + " " + state
  }
}
class Tabluation {
  def percept(perceptSequences: List[PerceptSequence]) : Actions.EnumVal= {
    val first = perceptSequences.head
    first.location match {
      case Locations.LocationA => {
        first.state match {
          case States.Clean => Actions.Right
          case States.Dirty => Actions.Suck

        }
      }
      case Locations.LocationB => {
        first.state match {
          case States.Clean => Actions.Left
          case States.Dirty => Actions.Suck

        }
      }
    }
  }

  def updateLocation(locations: Locations.EnumVal,actions: Actions.EnumVal) : Locations.EnumVal= {
    //As of today don't consider current location
    actions match {
      case Actions.Left => {
         Locations.LocationA
      }
      case Actions.Right => {
        Locations.LocationB
      }
      case _ => locations
    }
  }


}

class Sensor {
  def sense() : PerceptSequence = {
    val state = random.nextBoolean()
    val location = random.nextBoolean()
    state match {
      case false => {

        location match {
          case false => new PerceptSequence(Locations.LocationA, Clean)
          case true => new PerceptSequence(Locations.LocationA, Dirty)

        }
      }
      case true => {
        location match {
          case false => new PerceptSequence(Locations.LocationB,Clean)
          case true  => new PerceptSequence(Locations.LocationB,Dirty)
        }
      }
    }
  }

  val random = new Random(System.currentTimeMillis())
}

class VaccumCleaner {
  def experiment() : Unit = {
    def experimentItr(n : Int,lst : List[PerceptSequence],sensor: Sensor,locations: Locations.EnumVal ) : Unit = {
      n match {
        case 0 => println("Done")
        case _ => {
          val updatedPerception = sensor.sense()
          println(updatedPerception)
          val perceptLst = updatedPerception::lst
          val action = new Tabluation().percept(perceptLst)
          println(action)
          experimentItr(n-1,perceptLst,sensor,new Tabluation().updateLocation(locations,action))


        }
      }
    }

    experimentItr(10,List(),new Sensor,Locations.LocationA)
  }
}

object Main extends App {
  val vaccumCleaner = new VaccumCleaner
  vaccumCleaner.experiment()
}
