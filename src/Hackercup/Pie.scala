package Hackercup

import scala.util.Random

/**
 * Created by Rahul on 1/8/17.
 */
sealed trait Quadrant
case object FirstQuadrant extends Quadrant
case object SecondQuadrant extends Quadrant
case object ThirdQuadrant extends Quadrant
case object FourthQuadrant extends Quadrant


object Constant {
  val threshold = Math.pow(10,-6)
  val circle = new CirclePolar(50,new Point(0,0))
}

class Point(val x : Int,val y : Int) {
  val polarRadian = Math.atan2(y , x)

  override def toString = s"Point($x, $y)"
}


class CirclePolar(val radius : Int,val centre : Point ) {

}
class PointInsideCircle(val p : Point,val circle : CirclePolar) {
  val distance = getDistance(p,circle.centre)
  //println(p + " " + circle.centre + " " + distance)
  val result = distance <= circle.radius || Math.abs(distance-circle.radius) < Constant.threshold


  def getDistance(p1 : Point,p2 : Point) : Double = {
    Math.sqrt(Math.pow(p2.x - p1.x,2) + Math.pow(p2.y-p1.y,2))
  }


}



class RangeComparator(val pie: Pie,val point : Point) {
  def getQuadrant(p : Point) : Quadrant = {
    if (p.x >=0 && p.y >= 0) {
      FirstQuadrant
    }else {
      if (p.x < 0 && p.y >= 0) {
        SecondQuadrant
      }else {
        if (p.x < 0 && p.y < 0) {
          ThirdQuadrant
        }else {
          FourthQuadrant
        }
      }
    }
  }
  def check() : Boolean = {
    val pointInsideCircle = new PointInsideCircle(point,Constant.circle)
    if (pointInsideCircle.result == false) {
      //println("bad")
      false
    }else {
      //println("radius is less at least")
      getQuadrant(point) match  {
        case FirstQuadrant => {
          //println("First quadrant")
          if (pie.completionPercentange >= 25) {
            true
          }else {
            //println(1)
            val pointPolarAngle = point.polarRadian
            val piePolarAngle = pie.destRadian
            //println(" 1 " + pointPolarAngle + " " + pie.sourceRadian + " " + pie.destRadian)
            piePolarAngle < pointPolarAngle || Math.abs(piePolarAngle - pointPolarAngle) < Constant.threshold
          }
        }
        case SecondQuadrant => {
          if (pie.completionPercentange == 100) {
            true
          }else {
            //println("Second ")
            val pointPolarAngle = point.polarRadian
            val piePolarAngle = pie.destRadian
            //println(pointPolarAngle + "  " + pie.sourceRadian + " " + pie.destRadian)
            piePolarAngle < pointPolarAngle || Math.abs(piePolarAngle - pointPolarAngle) < Constant.threshold
          }
        }

        case ThirdQuadrant => {
          //println("third")
          if (pie.completionPercentange >= 75) {
            true
          }else {
            val pointPolarAngle = point.polarRadian
            val piePolarAngle = pie.destRadian
            Math.abs(piePolarAngle) > Math.abs(pointPolarAngle) || Math.abs(piePolarAngle - pointPolarAngle) < Constant.threshold
          }
        }

        case FourthQuadrant => {
          if (pie.completionPercentange >=50 ) {
            true
          }else {
            val pointPolarAngle = point.polarRadian
            val piePolarAngle = pie.destRadian
            //println(pointPolarAngle + " " + pie.sourceRadian + " " + pie.destRadian)
            Math.abs(piePolarAngle) > Math.abs(pointPolarAngle) || Math.abs(piePolarAngle - pointPolarAngle) < Constant.threshold
          }
        }
      }
    }
  }
}
class Pie(val completionPercentange : Int) {
  val sourceRadian = Math.toRadians(90.0)
  val destRadian = getDestRadian()
  def getDestRadian() : Double = {
    if (completionPercentange <= 25) {
      //first quadrant
      //println("dest radian 1st")
      val diff = 25-completionPercentange
      //println(diff)
      Math.toRadians(90.0* (diff/25.0))
    }else {
      //>25 and <= 50
      if (completionPercentange <= 50) {
        val pending = completionPercentange - 25
        Math.toRadians(-90.0*(pending/25.0))
      }else {
        if (completionPercentange <= 75) {
          val pending = completionPercentange - 25
          Math.toRadians(-180.0*(pending/50.0))
        }else {

          val pending = 50 - (completionPercentange - 75)
          Math.toRadians(180.0*(pending/50.0))
        }
      }
    }
  }
}

class ShiftedEnd
object PieMain extends App {

  def randInRange(low : Int,high : Int,random: Random) : Int = {
    low + random.nextInt(high-low+1)
  }
  def generateTestCases() : Unit = {
    val randomForPercentage = new Random(System.currentTimeMillis())
    val randomForX = new Random(randomForPercentage.nextInt())
    val randomForY = new Random(randomForX.nextInt())
    for (i <- 0 to 10) {
      val percentage = randInRange(0,100,randomForPercentage)
      val pointX = randInRange(0,100,randomForX)
      val pointY = randInRange(0,100,randomForY)
      val pie = new Pie(percentage)
      val rangeComparator = new RangeComparator(pie,new Point(pointX-50,pointY-50))
      println(percentage + " " + (pointX,pointY) + " " + rangeComparator.check())

    }
  }

  //generateTestCases()
  val testCases = scala.io.StdIn.readLine().toInt
  //val circle = new CirclePolar(50,new Point(50,50))
  for (i <- 0 to testCases-1) {
    val lineStr = scala.io.StdIn.readLine().split(" ")
    val percentage = lineStr(0).toInt
    val pointX = lineStr(1).toInt
    val pointY = lineStr(2).toInt

    val pie = new Pie(percentage)
    val rangeComparator = new RangeComparator(pie,new Point(pointX-50,pointY-50))
    val checkValued = rangeComparator.check()
    //println(rangeComparator.check())
    if (checkValued == true) {
      println("Case #" + (i+1) + ": " + "black")
    }else {
      println("Case #" + (i+1) + ": " + "white")
    }

  }
}