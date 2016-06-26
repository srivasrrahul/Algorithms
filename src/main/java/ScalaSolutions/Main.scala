package ScalaSolutions

import scala.collection.immutable.{SortedSet}

/**
 * Created by Rahul on 5/14/16.
 */
object Main extends App {
//  val source = scala.io.Source.fromFile(args(0))
//  val lines = source.getLines.filter(_.length > 0)
//  for (l <- lines) {
//    // Do something with each non-blank line
//  }

  val retValue = maxPrimeWithPalindrome(1000)
  println(retValue)
  def maxPrimeWithPalindrome(n : Int) : Int = {
    def internal(x : Int,sieve : SortedSet[Int]) : (Boolean,SortedSet[Int]) = {
      //println(x)
      if (x > 1000) {
        (true,sieve)
      }else {
        val retValue = isPrime(x, sieve)
        internal(x + 1, retValue._2)
      }

    }



    val finalVal = internal(2,SortedSet(2))
    //println("Hello")
    val primeSet = finalVal._2
    val primeSetWithPalindrome = primeSet.filter(isPalindrome)
    primeSetWithPalindrome.max

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
  def isPalindrome(n : Int) : Boolean = {
    val v = n.toString
    val length = v.length
    for (j <- 0 to (length-1)/2) {
      if (v.charAt(j) != v.charAt(length-j-1)) {
        return false
      }
    }
    true
  }
}


