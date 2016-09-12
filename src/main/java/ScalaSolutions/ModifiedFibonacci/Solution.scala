package ScalaSolutions.ModifiedFibonacci

/**
 * Created by Rahul on 8/5/16.
 */
object Solution {
  def modifiedFibo(t1 : BigInt,t2 : BigInt,n : Int,current  : Int) : BigInt= {
    if (current == n) {
      t1.+(t2.*(t2))
    }else {
      modifiedFibo(t2,t1.+(t2.*(t2)),n,current+1)
    }
  }
  def main(args : Array[String]) : Unit = {
    val ln = scala.io.StdIn.readLine()

    val values = ln.split(" ")
    val t1 = Integer.parseInt(values(0))
    val t2 = Integer.parseInt(values(1))
    val n = Integer.parseInt(values(2))
    //println(t1 + " " + t2 + " " + n)
    println(modifiedFibo(t1,t2,n,3))

  }

}
