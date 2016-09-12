package ScalaSolutions.MachineLearning.BayesianLearning

import java.io.File

import scala.collection.immutable.HashMap
import scala.io.Source

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
  val smoothing = 1
  val classCount = 2
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

  def getProbabilityOfWordInSpamLaplaceFiltered(word : String) : Option[Double] = {
    var count = 0
    if (spamMap.contains(word)) {
      count = spamMap.get(word).get.count

    }

    Some((count+smoothing)/(totalSpamCount + smoothing*classCount))

  }

  def getProbabilityOfWordInHam(word : String) : Option[Double] = {
    if (hamMap.contains(word)) {
      Some(hamMap.get(word).get.count/totalHamCount)
    }else {

      None
    }

  }

  def getProbabilityOfWordInHamLaplaceFiltered(word : String) : Option[Double] = {
    var count = 0
    if (hamMap.contains(word)) {
      count = hamMap.get(word).get.count

    }

    Some((count+smoothing)/(totalSpamCount + smoothing*classCount))

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

      val z = getProbabilityOfWordInSpamLaplaceFiltered(x)
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


class FileReaders(val directoryName : String) {
  def processSpamDirectory(bayesianSpamFilter: BayesianSpamFilter) : Unit = {
    val fileLst = new File(directoryName).listFiles()
    fileLst.foreach(fileName => {

      try {
        val wordLst = Source.fromFile(fileName).
          getLines().
          flatMap(_.split("\\W+")).toList

        val bag = new BagOfWords(wordLst)
        //println(wordLst)
        bayesianSpamFilter.addSpam(bag)
      } catch {
        case ex : Exception => println("Exception " + fileName)
      }
    })
  }

  def processHamDirectory(bayesianSpamFilter: BayesianSpamFilter) : Unit = {
    val fileLst = new File(directoryName).listFiles()
    fileLst.foreach(fileName => {

      try {
        val wordLst = Source.fromFile(fileName).
          getLines().
          flatMap(_.split("\\W+")).toList

        val bag = new BagOfWords(wordLst)
        bayesianSpamFilter.addHam(bag)
      }catch  {
        case ex : Exception => println("ham Exception " + fileName)
      }
    })
  }
}

object Main extends App {
  val bayesianSpamFilter = new BayesianSpamFilter(0.30)
  val spamFileReader = new FileReaders("/Users/rasrivastava/Downloads/spam/MAIL/")
  spamFileReader.processSpamDirectory(bayesianSpamFilter)

  val hamFileReader = new FileReaders("/Users/rasrivastava/Downloads/MAIL/")
  hamFileReader.processHamDirectory(bayesianSpamFilter)


//  bayesianSpamFilter.addHam(new BagOfWords(List("how","are","you")))
//  bayesianSpamFilter.addSpam(new BagOfWords(List("lets","kill","folks")))
//  bayesianSpamFilter.addSpam(new BagOfWords(List("work","for","CIA")))
//  bayesianSpamFilter.addSpam(new BagOfWords(List("close","to","CIA")))
//  bayesianSpamFilter.addSpam(new BagOfWords(List("kill","close")))
//  bayesianSpamFilter.addHam(new BagOfWords(List("lets","close","discussions")))
//  bayesianSpamFilter.addHam(new BagOfWords(List("lets","close","it","today")))
//  bayesianSpamFilter.addHam(new BagOfWords(List("kill","this","bug")))
//  bayesianSpamFilter.addSpam(new BagOfWords(List("good","join","CIA")))
//  bayesianSpamFilter.addSpam(new BagOfWords(List("how","you","CIA")))
//
//  bayesianSpamFilter.isSpam(new BagOfWords(List("kill","you","close")))

  println(bayesianSpamFilter.isSpam(new BagOfWords(List("Once","settled","final","method","release"))))



}