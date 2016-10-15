package ScalaSolutions.WeekOf24

/**
 * Created by Rahul on 10/10/16.
 */
class House(val start : Int,val end : Int) {
  def inrange(point : Int) : Boolean = {
    point >= start && point <= end
  }
}

class Tree(val point : Int) {

}

class PointOfFall(val fallingPoint : Int,val tree : Tree) {
  def getActualPoint() : Int = {
    if (fallingPoint < 0) {
      tree.point - Math.abs(fallingPoint)
    }else {
      tree.point + Math.abs(fallingPoint)
    }

  }
}

object Solution {
  def main(args : Array[String]) : Unit = {
    val sc = new java.util.Scanner (System.in)
    val s = sc.nextInt()
    val t = sc.nextInt()
    val house = new House(s,t)
    val a = sc.nextInt()
    val b = sc.nextInt()
    val appleTree = new Tree(a)
    val orangeTree = new Tree(b)
    val m = sc.nextInt()
    val n = sc.nextInt()
    var applesInRange = 0
    for(apple_i <- 0 to m-1) {
      //apple(apple_i) = sc.nextInt();
      val pointOfFall = new PointOfFall(sc.nextInt(),appleTree)
      //println("For applie " + pointOfFall.getActualPoint() + " " + pointOfFall.fallingPoint + " " + appleTree.point)
      if (house.inrange(pointOfFall.getActualPoint())) {
        applesInRange = applesInRange + 1
      }
    }
    //var orange = new Array[Int](n);
    var orangeInRange = 0
    for(orange_i <- 0 to n-1) {
      val pointOfFall = new PointOfFall(sc.nextInt(),orangeTree)
      //println("For orange " + pointOfFall.getActualPoint() + " " + pointOfFall.fallingPoint + " " + orangeTree.point)
      if (house.inrange(pointOfFall.getActualPoint())) {
        orangeInRange = orangeInRange + 1
      }
    }

    println(applesInRange)
    println(orangeInRange)
  }
}



