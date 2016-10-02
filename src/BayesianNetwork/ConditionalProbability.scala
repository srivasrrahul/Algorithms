package BayesianNetwork

import scala.StringBuilder
import scala.collection.mutable
import scala.util.control.Breaks._


/**
 * Created by Rahul on 9/30/16.
 */
class ConditionalProbability(val posterior : String,val evidence : Array[String],val prob : Array[Array[Double]]) {
  val nameToIndexMap = new mutable.HashMap[String,Int]
  val indexes = new mutable.HashMap[String,Array[Double]]
  init()

  def init() : Unit = {
    println("Init")
    var index = 0
    if (evidence != null) {
      evidence.foreach(name => {
        nameToIndexMap.+=((name, index))
        index = index + 1
      })
    }

    nameToIndexMap.+=((posterior,index))

  }

  //if defined present with value
  def matchRow(arr : Array[Double],possibleValues: mutable.HashMap[Int,Int]): Boolean = {
    println("String " + arr.deep.mkString(","))
    println("Possible Value " + possibleValues)
    var matched = true
    breakable {
      for (j <- 0 to arr.length - 2) {
        //println("In loop " + j)
        if (possibleValues.contains(j) && possibleValues.get(j).get != -1) {
          println("In loop  " + j + " " + possibleValues.get(j).get + " " + arr(j))
          if (possibleValues.get(j).get != arr(j)) {
            println("Break it")
            matched = false
            break()
          }
        }
      }
    }

    matched
  }

  def getProbability(input : Map[String,Int]) : List[Double] = {
    println("Fetching probability for " + input + " Indexes :  " + nameToIndexMap)
    val possibleValuesPresence = new mutable.HashMap[Int,Int]()
    if (evidence != null) {
      evidence.foreach(e => {
        //println()
        possibleValuesPresence.+=((nameToIndexMap.get(e).get, -1))
      })
    }

    input.foreach(event => {
      //println("For Each Event " + event._1 + " " + nameToIndexMap)
      if (nameToIndexMap.contains(event._1)) {
        val eventId = nameToIndexMap.get(event._1).get
        possibleValuesPresence.update(eventId, event._2)
      }

    })

    //println("possible values " + possibleValuesPresence)

    val nrows = prob.size
    val ncols = prob(0).size
    var result = new mutable.MutableList[Double]()
    for (i <- 0 to nrows-1) {
      var matchedEvent = matchRow(prob(i),possibleValuesPresence)
      if (matchedEvent == true) {
        val currentResult = prob(i)(ncols-1)
        result.+=(currentResult)
      }

    }

    result.toList
  }

  override def toString: String = {
    val strBuilder = new StringBuilder()
    strBuilder.append("Posterior : " + posterior + " Evidence : ")
    if (evidence != null) {
      evidence.foreach(e => {
        strBuilder.append(e + ",")
      })
    }

    strBuilder.append("  Values : ")
    strBuilder.append(prob.deep.mkString(","))

    strBuilder.toString()

  }
}

object ConditionalProbabilityTest extends App {
  val matrix = Array.ofDim[Double](2,2)
  matrix(0)(1) = 0.0
  matrix(0)(1) = 0.4
  matrix(1)(0) = 1.0
  matrix(1)(1) = 0.01
  val conditionalProbability = new ConditionalProbability("A",Array[String]("B"),matrix)

  val m  = new mutable.HashMap[String,Int]()
  m.+=(("B",1))
  println(conditionalProbability.getProbability(m.toMap))
}
