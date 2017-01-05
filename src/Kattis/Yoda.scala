package Kattis

import scala.collection.mutable.ListBuffer

/**
 * Created by Rahul on 12/28/16.
 */
class Yoda {
  def eval(x : Int, y : Int) : Unit = {
    val x1 = x.toString.toList
    val x2 = y.toString.toList
    val lst1 = new ListBuffer[Int]
    val lst2 = new ListBuffer[Int]


    val itr1 = x1.reverseIterator
    val itr2 = x2.reverseIterator

    while (itr1.hasNext && itr2.hasNext) {
      val ch1 = itr1.next().toInt
      val ch2 = itr2.next().toInt
      if (ch1 == ch2) {
        lst1.append(ch1 - '0')
        lst2.append(ch2 - '0')
      }else {
        if (ch1 < ch2) {
          lst2.append(ch2 - '0')
        }else {
          lst1.append(ch1 - '0')
        }
      }



    }

    while (itr1.hasNext) {
      lst1.append(itr1.next - '0')
    }

    while (itr2.hasNext) {
      lst2.append(itr2.next - '0')
    }

    val lstUpdated1 = lst1.reverse
    val lstUpdated2 = lst2.reverse


    if (lstUpdated1.size != 0) {
      println(lstUpdated1.fold(0){(a : Int,b : Int) => 10*a + b})
    }else {
      println("YODA")
    }

    if (lstUpdated2.size != 0) {
      println(lstUpdated2.fold(0){(a : Int,b : Int) => 10*a + b})
    }else {
      println("YODA")
    }



  }
}


object Yoda {
  def main (args: Array[String]){
    val l1 = scala.io.StdIn.readLine().toInt
    val l2 = scala.io.StdIn.readLine().toInt
    val yoda = new Yoda
    yoda.eval(l1,l2)
  }
}