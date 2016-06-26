package ScalaSolutions.SumFirstNPrimes

import scala.collection.immutable.SortedSet

/**
 * Created by Rahul on 5/14/16.
 */
object Main extends App{
  val retValue = primeSums(1000)
  println(retValue)
  def primeSums(n : Int) : Int = {
    def internal(x : Int,sieve : SortedSet[Int],sumTillNow : Int) : Int = {
      //println(x)
      if (sieve.size >= 1000) {
        sumTillNow
      }else {
        val retValue = isPrime(x, sieve)
        if (retValue._1 == false) {
          internal(x+1,sieve,sumTillNow)
        }else {
          internal(x+1,retValue._2,sumTillNow+x)
        }
      }

    }



    val finalVal = internal(2,SortedSet(2),0)
    //println("Hello")
    finalVal

  }
  def isPrime(n : Int,sieve : SortedSet[Int]) : (Boolean,SortedSet[Int]) = {
    if (n == 2) {
      (true,sieve)
    }else {

      sieve.foreach(x =>{
        if (n % x == 0) {
          return (false,sieve)
        }
      })

      val updatedSieve = sieve.+(n)
      (true,updatedSieve)


    }
  }

}
