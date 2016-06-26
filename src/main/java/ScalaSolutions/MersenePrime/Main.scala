package ScalaSolutions.MersenePrime



import scala.collection.immutable.SortedSet

/**
 * Created by Rahul on 5/15/16.
 */
object Main extends App {

  val source = scala.io.Source.fromFile(args(0))
  val lines = source.getLines.filter(_.length > 0)
  for (l <- lines) {
    //println(l.toInt + " => " + mersene(l.toInt).-(2).mkString(","))
    println(mersene(l.toInt).-(2).mkString(", "))
    //println(l.toInt)
  }

  def mersene(limit : Int) : SortedSet[Int] = {
    def findPrime(x : Int,mersenePrime : SortedSet[Int],sieve : SortedSet[Int]) : (Boolean,SortedSet[Int]) = {
      //println(x)
      if (x >= limit) {
        (false,mersenePrime)
      }else {
        val retValue = isMersenePrime(x,mersenePrime,sieve)
        findPrime(x+1,retValue._2._1,retValue._2._2)

      }

    }

    val retValue = findPrime(3,SortedSet(2),SortedSet(2))
    retValue._2

  }
  def isMersenePrime(n : Int,mersenePrime : SortedSet[Int],sieve : SortedSet[Int]) : (Boolean,(SortedSet[Int],SortedSet[Int])) = {
    if (n == 2) {
      (true,(mersenePrime,sieve))
    }else {


      sieve.foreach(x =>{
        if (n % x == 0) {
          return (false,(mersenePrime,sieve))
        }
      })

      val z = n+1
      val result = z & n
      if (result == 0) {


        (true, (mersenePrime.+(n),sieve.+(n)))
      }else {
        (false,(mersenePrime,sieve.+(n)))
      }


    }
  }

}
