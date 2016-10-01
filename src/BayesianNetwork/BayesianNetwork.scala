package BayesianNetwork

import scala.collection.mutable


/**
 * Created by Rahul on 9/26/16.
 */

class BayesianNode(val id : String,val conditionalProbability: ConditionalProbability) {
  def addIncomingNode(bayesianNode: BayesianNode) : BayesianNode = {
    incomingNodeLst.+=(bayesianNode)
    this
  }

  def addOutgoingNodes(bayesianNode: BayesianNode) : BayesianNode = {
    outgoingNodeLst.+=(bayesianNode)
    this
  }

  def getIncomingNodeLst() : List[BayesianNode] = {
    incomingNodeLst.toList
  }

  def getOutgoingNodeLst() : List[BayesianNode] = {
    outgoingNodeLst.toList
  }

  val incomingNodeLst = new mutable.MutableList[BayesianNode]
  val outgoingNodeLst = new mutable.MutableList[BayesianNode]

}


class BayesianNetwork(val nodeLst : List[BayesianNode] ) {
  var bayesianNodes = new mutable.HashMap[String,BayesianNode]()
  nodeLst.foreach(node => {
    addBayesianNode(node)
  })

  def addBayesianNode(bayesianNode : BayesianNode) : Unit = {
    bayesianNodes.+=((bayesianNode.id,bayesianNode))

    bayesianNode.getOutgoingNodeLst().foreach(node => {
      node.addIncomingNode(bayesianNode)
    })
  }

  def getBayesianNode(id : String) : Option[BayesianNode] = {
    bayesianNodes.get(id)
  }



  override def toString: String = {
    val buf = new StringBuilder
    bayesianNodes.foreach(kv => {
      val node = kv._2
      buf.++=(kv._1 + " => ")
      node.getOutgoingNodeLst.foreach(bNode => {
        buf.++=(bNode.id + ",")
      })
      buf.++=("\n")
    })
    buf.toString()
  }
}

class BayesianNetworkProbabilityCalculator(val bayesianNetwork: BayesianNetwork) {
  def getProbabilityOfBayesianNetwork(passedValues: Map[String, Int]): Double = {
    def getProbabilityItr(bayesianNodeItr: List[BayesianNode]): List[Double] = {
      println("Calculating for " + bayesianNodeItr.head.id)
      bayesianNodeItr match {
        case node :: List() => {
          println("Last Node Here" + node.id + " " + node.conditionalProbability.toString)
          val condProb = node.conditionalProbability
          val matchPr = condProb.getProbability(passedValues)
          println("Last Node " + node.id + " resulted in " + " probability of " + matchPr)
          matchPr
        }
        case node :: lst => {
          println("Current Node " + node.id + " " + node.conditionalProbability.toString)
          val condProb = node.conditionalProbability
          val matchPr = condProb.getProbability(passedValues)
          println("Current Node " + node.id + " resulted in " + " probability of " + matchPr)
          var result = 0.0
          val tempValue = getProbabilityItr(lst)
          matchPr.foreach(pr => {

            tempValue.foreach(t => {
              result = result + t * pr
            })
          })

          println("Current Node " + node.id + " resulted in " + " probability of " + matchPr + " Final " + result)
          List(result)
        }

      }
    }

    println("============")
    val retValue = getProbabilityItr(bayesianNetwork.nodeLst).head
    println("============")
    retValue

  }

  def mergeMaps(m1 : Map[String,Int],m2 : Map[String,Int]) : Map[String,Int] = {
    var result = new mutable.HashMap[String,Int]()
    m1.foreach(itr => {
      result.+=((itr._1,itr._2))
    })

    m2.foreach(itr => {
      result.+=((itr._1,itr._2))
    })

    result.toMap


  }

  def evaluate(posterior : Map[String,Int],evidence : Map[String,Int]) : Double = {
    println(" Posterior " + posterior  + " Evidence " + evidence)
    val evidenceProb = getProbabilityOfBayesianNetwork(evidence)
    println("Evidence Probability " + evidenceProb)

    println("merged Valyes " + mergeMaps(evidence,posterior))
    val totalProb = getProbabilityOfBayesianNetwork(mergeMaps(evidence,posterior))
    println("Total Probability " + totalProb)

    totalProb/evidenceProb
    //0.0
  }
}





object BayesianNetworkTest extends App {
  val rainPrMatrx = Array.ofDim[Double](2,2)
  rainPrMatrx(0)(0) = 0.0
  rainPrMatrx(0)(1) = 0.8
  rainPrMatrx(1)(0) = 1.0
  rainPrMatrx(1)(1) = 0.2

  val rainNode = new BayesianNode("Rain",new ConditionalProbability("Rain",null,rainPrMatrx))

  val sprinklerMatrix = Array.ofDim[Double](4,3)
  sprinklerMatrix(0)(0) = 0.0
  sprinklerMatrix(0)(1) = 0.0
  sprinklerMatrix(0)(2) = 0.6

  sprinklerMatrix(1)(0) = 0.0
  sprinklerMatrix(1)(1) = 1.0
  sprinklerMatrix(1)(2) = 0.4

  sprinklerMatrix(2)(0) = 1.0
  sprinklerMatrix(2)(1) = 0.0
  sprinklerMatrix(2)(2) = 0.99

  sprinklerMatrix(3)(0) = 1.0
  sprinklerMatrix(3)(1) = 1.0
  sprinklerMatrix(3)(2) = 0.01

  val sprinklerNode = new BayesianNode("Sprinkler",new ConditionalProbability("Sprinkler",Array[String]("Rain"),sprinklerMatrix))

  val grassWetMatrix = Array.ofDim[Double](8,4)

  grassWetMatrix(0)(0) = 0
  grassWetMatrix(0)(1) = 0
  grassWetMatrix(0)(2) = 0
  grassWetMatrix(0)(3) = 1.0

  grassWetMatrix(1)(0) = 0
  grassWetMatrix(1)(1) = 0
  grassWetMatrix(1)(2) = 1
  grassWetMatrix(1)(3) = 0.0

  grassWetMatrix(2)(0) = 0
  grassWetMatrix(2)(1) = 1
  grassWetMatrix(2)(2) = 0
  grassWetMatrix(2)(3) = 0.2

  grassWetMatrix(3)(0) = 0
  grassWetMatrix(3)(1) = 1
  grassWetMatrix(3)(2) = 1
  grassWetMatrix(3)(3) = 0.8

  grassWetMatrix(4)(0) = 1
  grassWetMatrix(4)(1) = 0
  grassWetMatrix(4)(2) = 0
  grassWetMatrix(4)(3) = 0.1

  grassWetMatrix(5)(0) = 1
  grassWetMatrix(5)(1) = 0
  grassWetMatrix(5)(2) = 1
  grassWetMatrix(5)(3) = 0.9

  grassWetMatrix(6)(0) = 1
  grassWetMatrix(6)(1) = 1
  grassWetMatrix(6)(2) = 0
  grassWetMatrix(6)(3) = 0.01

  grassWetMatrix(7)(0) = 1
  grassWetMatrix(7)(1) = 1
  grassWetMatrix(7)(2) = 1
  grassWetMatrix(7)(3) = 0.99

  val grassWetNode =
    new BayesianNode("GrassWet",new ConditionalProbability("GrassWet",Array[String]("Srinkler","Rain"),grassWetMatrix))


  rainNode.addOutgoingNodes(sprinklerNode)
  rainNode.addOutgoingNodes(grassWetNode)
  sprinklerNode.addOutgoingNodes(grassWetNode)

  val bayesianNetwork = new BayesianNetwork(List(rainNode,sprinklerNode,grassWetNode))

  println(bayesianNetwork.toString)

  val bayesianNetworkProbabilityCal = new BayesianNetworkProbabilityCalculator(bayesianNetwork)
  val evidence = new mutable.HashMap[String,Int]()
  //eventsOfInterest.+=(("Rain",1))
  evidence.+=(("GrassWet",1))

  val posterior = new mutable.HashMap[String,Int]()
  posterior.+=(("Rain",1))

  println(bayesianNetworkProbabilityCal.evaluate(posterior.toMap,evidence.toMap))

  //println(bayesianNetworkProbabilityCal.getProbabilityOfBayesianNetwork(eventsOfInterest.toMap))














}


//object Sample extends App {
//
////  val raninPr = new mutable.HashMap[List[Tuple2[String,Integer]],Double]()
////  raninPr.+=((List(new Tuple2("Rain",0)),0.2))
////  raninPr.+=((List(new Tuple2("Rain",1)),0.8))
////
////  val rainPrConditionalProbability = new ConditionalProbability(raninPr,null,)
////
////
////
////  val sprinklerPrEvidence = new mutable.HashMap[List[Tuple2[String,Int]],Double]
//////  sprinklerPr(0)(0) = 0.4
//////  sprinklerPr(0)(1) = 0.6
//////  sprinklerPr(1)(0) = 0.01
//////  sprinklerPr(1)(1) = 0.99
////
////
////  val grassWetPr = Array.ofDim[Double](4,2)
////  grassWetPr(0)(0) = 0.0
////  grassWetPr(0)(1) = 1.0
////  grassWetPr(1)(0) = 0.8
////  grassWetPr(1)(1) = 0.2
////  grassWetPr(2)(0) = 0.9
////  grassWetPr(2)(1) = 0.1
////  grassWetPr(3)(0) = 0.99
////  grassWetPr(3)(1) = 0.01
////
////  val sprinklerNode = new BayesianNode("Sprinkler",new ConditionalProbability(raninPr))
////  val rainNode = new BayesianNode("Rain")
////  val grassWet = new BayesianNode("GrassWet")
//////  rainNode.setProbability(new SingleValuedProbability(rainPr))
//////  sprinklerNode.setProbability(new DoubleValuedProbability(sprinklerPr))
//////  grassWet.setProbability(new TripleValuedProbability(grassWetPr))
////
////  rainNode.addOutgoingNodes(sprinklerNode).addOutgoingNodes(grassWet)
////  sprinklerNode.addOutgoingNodes(grassWet)
////
////  val bayesianNetwork = new BayesianNetwork(List(rainNode,sprinklerNode,grassWet))
////  println(bayesianNetwork.toString)
//
//
//
//}
