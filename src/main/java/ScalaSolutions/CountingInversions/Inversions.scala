package ScalaSolutions.CountingInversions

import scala.collection.mutable.ListBuffer

/**
 * Created by Rahul on 10/15/16.
 */

class ArrayVal(val value : Int,val whichArray : Int) {

}
class Inversions(val arr : Array[Int]) {

  def mergeSort(startIndex : Int,endIndex : Int) : Unit = {
    if (startIndex >= endIndex) {

    }else {
      val mid = startIndex + (endIndex - startIndex)/2
      mergeSort(startIndex,mid)
      mergeSort(mid+1,endIndex)
      merge(startIndex,mid,endIndex)
    }
  }

  def merge(startIndex : Int,midIndex : Int,endIndex : Int) : Unit = {
    //println(startIndex + " " + midIndex + " " + endIndex + " " + arr.deep.mkString(","))
//    val temp1 = new Array[Int](midIndex-startIndex+1)
//    val temp2 = new Array[Int](endIndex-(midIndex+1)+1)
//    Array.copy(arr,startIndex,temp1,0,(midIndex-startIndex+1))
//    Array.copy(arr,midIndex+1,temp2,0,endIndex-(midIndex+1)+1)
//    println("Array1 " + temp1.deep.mkString(","))
//    println("Array2 " + temp2.deep.mkString(","))
    val tempArray = new Array[ArrayVal](endIndex-startIndex+1)
    var i = startIndex
    var j = midIndex+1
    var tempIndex = 0
    var oldIndex = -1

    while (i <= midIndex && j <= endIndex) {
      //println("Index " + "i => arr(i) " + i + " => " + arr(i) + "  " + " j => arr(j) " + j + " => " + arr(j))

      if (arr(i) <= arr(j)) {
        tempArray(tempIndex) = new ArrayVal(arr(i),0)
        tempIndex += 1
        i = i + 1

      }else {
        //println("Index " + tempIndex + " " + tempArray.length)


        tempArray(tempIndex) = new ArrayVal(arr(j),1)
        tempIndex += 1
        j = j + 1

      }
    }

    //println("Index after if " + "i => arr(i) " + i + " => " + arr(i) + "  " + " j => arr(j) " + j + " => " + arr(j))
    var firstTime = true
    while (i <= midIndex) {

      tempArray(tempIndex) = new ArrayVal(arr(i),0)
      tempIndex += 1
      i += 1
      //println("Here")
//      if (firstTime == false) {
//        inversionCount += (endIndex - (midIndex + 1) + 1)
//      }

      firstTime = false
    }
    while (j <= endIndex) {
      tempArray(tempIndex) = new ArrayVal(arr(j),1)
      tempIndex += 1
      j += 1
      //println("Here1")
    }

    var countInFirst = 0
    var countInSecond = 0
    for (k <- 0 to tempArray.length-1) {
      if (tempArray(k).whichArray == 0) {
        inversionCount = inversionCount + countInSecond
        countInFirst = countInFirst + 1
      }else {
        countInSecond = countInSecond + 1
      }
    }
//    if (i < startIndex || j < endIndex) {
//      println("Problem")
//    }

    //println("Sorted temp " + tempArray.deep.mkString(","))
    var k =0
    for (i <- startIndex to endIndex) {
      arr(i) = tempArray(k).value
      k += 1
    }


  }



  var inversionCount : Long = 0
  var inversions = new ListBuffer[Tuple2[Int,Int]]

}


object Solution {
  def naiveCount(arr : Array[Int]) : Tuple2[Int,ListBuffer[Tuple2[Int,Int]]] = {
    var inversions = new ListBuffer[Tuple2[Int,Int]]
    var count = 0
    for (i <- 0 to arr.length-1) {
      //println("Here ")
      for (j <- i+1 to arr.length-1) {
        //println("Here 1 " + arr(i) + "  " + arr(j))
        if (arr(i) > arr(j)) {
          //println(arr(i) + "," + arr(j))
          inversions.+=((arr(i), arr(j)))
          count = count + 1
        }
      }
    }

    //println(inversions.toList)

    (count,inversions)
  }
  def main(args: Array[String]) {
    val sc = new java.util.Scanner (System.in);
    val t = sc.nextInt();
    var  a0 = 0;
    while(a0 < t){
      val n = sc.nextInt();
      val arr = new Array[Int](n);
      for(arr_i <- 0 to n-1) {
        arr(arr_i) = sc.nextInt();
      }
      //val newArray = new Array[Int](arr.length)
      //Array.copy(arr,0,newArray,0,arr.length)
      //println("Input " + arr.deep.mkString(","))
      //println("Input " + newArray.deep.mkString(","))
      val inversion = new Inversions(arr)
      inversion.mergeSort(0,arr.length-1)
      //val res = naiveCount(newArray)
      println(inversion.inversionCount)
      //println(inversion.inversions.size + " " + res._2.size)
      //println("from my method " + inversion.inversions + " ")
      //println("from standard " + res._2.size)

//      val s1 = inversion.inversions.toSet
//      val s2 = res._2.toSet
//      println(s2)
//      val s3 = s1.diff(s2)
//      println(s3)



      a0 += 1
    }
  }
}