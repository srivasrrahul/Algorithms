package ScalaSolutions.MachineLearning.BayesianLearning

import scala.collection.immutable.HashMap

/**
 * Rahul
 */
class WordCount(val name: String,val count : Int) {
  def increment() : WordCount = {
    new WordCount(name,count+1)
  }
}

class BagOfWords(val names : List[String]) {

}
class BayesianSpamFilter(val threshold : Double) {
  var totalSpamCount : Double = 0.0
  var totalHamCount : Double = 0.0
  var spamMap = new HashMap[String,WordCount]()
  var hamMap = new HashMap[String,WordCount]()

  def addSpam(bagOfWords: BagOfWords) : Unit = {
    bagOfWords.names.foreach(word => {
      if (spamMap.contains(word)) {
        val updatedCount = spamMap.get(word).get.increment()
        spamMap = spamMap + (word -> updatedCount)
      }else {
        //spamMap.+(word,new WordCount(word,1))
        spamMap = spamMap + (word -> new WordCount(word,1))
      }
    })

    totalSpamCount = totalSpamCount + 1
  }

  def addHam(bagOfWords: BagOfWords) : Unit = {
    bagOfWords.names.foreach(word => {
      if (hamMap.contains(word)) {
        val updatedCount = hamMap.get(word).get.increment()
        hamMap = hamMap + (word -> updatedCount)
      }else {
        //spamMap.+(word,new WordCount(word,1))
        hamMap = hamMap + (word -> new WordCount(word,1))
      }
    })

    totalHamCount = totalHamCount+1
  }

  def getProbabilityOfWordInSpam(word : String) : Option[Double] = {
    if (spamMap.contains(word)) {
      Some(spamMap.get(word).get.count/totalSpamCount)
    }else {
      println("Not found in spam db" + word)
      None
    }

  }

  def getProbabilityOfWordInHam(word : String) : Option[Double] = {
    if (hamMap.contains(word)) {
      Some(hamMap.get(word).get.count/totalHamCount)
    }else {

      None
    }

  }

  def getSpamProbability() : Double = {
    totalSpamCount/(totalHamCount+totalSpamCount)
  }

  def getHamProbability() : Double = {
    totalHamCount/(totalHamCount+totalSpamCount)
  }
  def isSpam(bagOfWords: BagOfWords) : Boolean = {
    val p1 = getWordsProbabilityInSpam(bagOfWords) * getSpamProbability()
    val p2 = getWordsProbabilityInHam(bagOfWords) * getHamProbability()

    val p = p1/(p1+p2)

    println(p)

    p > threshold
  }

  def getWordsProbabilityInSpam(bagOfWords: BagOfWords) : Double = {

    var pr = 1.0
    bagOfWords.names.foreach(x => {

      val z = getProbabilityOfWordInSpam(x)
      z match {
        case Some(y) => {
          pr = pr * y
        }
        case None => {
          pr = 0
        }
      }
    })

    println(bagOfWords.names + " " + pr)
    pr
  }

  def getWordsProbabilityInHam(bagOfWords: BagOfWords) : Double = {
    var pr = 1.0
    bagOfWords.names.foreach(x => {
      val z = getProbabilityOfWordInHam(x)
      z match {
        case Some(y) => {
          pr = pr * y
        }
        case None => {
          pr = 0
        }
      }
    })

    println(bagOfWords.names + " " + pr)

    pr
  }


}


object Main extends App {
  val bayesianSpamFilter = new BayesianSpamFilter(0.10)
  bayesianSpamFilter.addHam(new BagOfWords(List("how","are","you")))
  bayesianSpamFilter.addSpam(new BagOfWords(List("lets","kill","folks")))
  bayesianSpamFilter.addSpam(new BagOfWords(List("work","for","CIA")))
  bayesianSpamFilter.addSpam(new BagOfWords(List("close","to","CIA")))
  bayesianSpamFilter.addSpam(new BagOfWords(List("kill","close")))
  bayesianSpamFilter.addHam(new BagOfWords(List("lets","close","discussions")))
  bayesianSpamFilter.addHam(new BagOfWords(List("lets","close","it","today")))
  bayesianSpamFilter.addHam(new BagOfWords(List("kill","this","bug")))
  bayesianSpamFilter.addSpam(new BagOfWords(List("good","join","CIA")))
  bayesianSpamFilter.addSpam(new BagOfWords(List("how","you","CIA")))

  bayesianSpamFilter.isSpam(new BagOfWords(List("kill","you","close")))


}