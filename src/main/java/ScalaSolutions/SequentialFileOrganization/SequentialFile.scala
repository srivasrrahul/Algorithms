package ScalaSolutions.SequentialFileOrganization

import java.io.FileWriter
import scala.io.Source
import scala.reflect.internal.util.StringOps
import scala.util.Random

/**
 * Created by Rahul on 6/15/16.
 */
class Record(val empName : String,val empNumber : Long,val title : String) extends Serializable{
  override def toString: String = {
    val str = empName + ","+empNumber+","+title+"\n"
    str
  }




}

object RecordHelper {
  def serialize(record : Record) : Array[Byte] = {
    val str = record.empName + ","+record.empNumber+","+record.title
    val strBuilder = new StringBuilder

    val lengthStr = BinarySearchTreeNodeHelper.encodeIntToFourByteArray(str.length)
    strBuilder.append(lengthStr).append(str).toString().getBytes()
  }


}

class BinarySearchTreeNode(val data : Record,val left : BinarySearchTreeNode,val right : BinarySearchTreeNode) extends Serializable{
  def addRecord(record: Record) : Unit = {

  }

  def serialize() : String = {
    var strBuilder : StringBuilder = new StringBuilder
    if (left != null) {
      strBuilder = strBuilder.append(left.serialize())

    }

    strBuilder = strBuilder.append(data.toString)
    if (right != null) {
      strBuilder = strBuilder.append(right.serialize())
    }

    var retValue = new StringBuilder
    retValue = retValue.append(strBuilder.length)
    retValue = retValue.append(strBuilder.toString())
    retValue.toString()
  }



}

object BinarySearchTreeNodeHelper {


  def serialize( node : BinarySearchTreeNode) : String  = {
    var strBuilder : StringBuilder = new StringBuilder
    if (node.left != null) {
      strBuilder = strBuilder.append(node.left.serialize())

    }

    strBuilder = strBuilder.append(node.data.toString)
    if (node.right != null) {
      strBuilder = strBuilder.append(node.right.serialize())
    }

    var retValue = new StringBuilder
    retValue = retValue.append(strBuilder.length)
    retValue = retValue.append(strBuilder.toString())
    retValue.toString()
  }

  def encodeIntToFourByteArray(n : Int) : Array[Byte] = {
    val bytearray : Array[Byte] = BigInt(n).toByteArray
    val diff = 4-bytearray.length

    val t1 = Array.fill[Byte](diff)(0)
    t1 ++ bytearray


  }
}


class SequentialFile(fileName : String) {
  def addRecord(record : String)  : Unit = {
    val fw = new FileWriter(fileName,true)
    fw.write(record)
    fw.close()
  }

  //Asssume its un-sorted
  //Baiscally a linear search
  def searchRecordByName(name : String) : Record = {
    try {
      var recordValue: Record = null
      for (line <- Source.fromFile(fileName).getLines()) {
        val lineValue = line.split(",")
        val lineName = lineValue(0)
        if (lineName == name) {
          recordValue = new Record(lineValue(0), Integer.parseInt(lineValue(1)), lineValue(2))
        }
      }

      recordValue
    }catch {
      case e : Exception => {
        null
      }
    }
  }

  def getHash(name :String ) : Int = {

    val code = name.hashCode
    if (code < 0) {
      code * -1
    }else {
      code
    }
  }

  def formFileName(targetDirectory : String,hashCode : Int) : String = {
    targetDirectory + hashCode + ".txt"
  }

  def addRecordForHash(fileName : String,serializedData : String) = {
    new SequentialFile(fileName).addRecord(serializedData)
  }
  def createHashBasedIndexes(targetDirectory : String) : Unit = {
    for (line <- Source.fromFile(fileName).getLines()) {
       val lineValue = line.split(",")
       val hashCode = getHash(lineValue(0))
       addRecordForHash(formFileName(targetDirectory,hashCode),line)
    }
  }

  def searchRecordBasedOnHash(name : String,targetDirectory : String) : Record = {
    val hashValue = getHash(name)
    val hashedFileName = formFileName(targetDirectory,hashValue)
    println(hashedFileName)
    val record = new SequentialFile(hashedFileName).searchRecordByName(name)
    record
  }


}

object Main extends App {
//  val seqentialFile = new SequentialFile("/Users/rasrivastava/FILES/test.txt")
//  seqentialFile.addRecord(new Record("Rahul",123,"MD").toString)
//  seqentialFile.addRecord(new Record("Rohit",345,"MD1").toString)

  //generateTestRecords("/Users/rasrivastava/FILES/names1.txt")

  //searchRecord("NANNY")
  //new SequentialFile("/Users/rasrivastava/FILES/test.txt").createHashBasedIndexes("/Users/rasrivastava/FILES/HASH_DB/")
  val r = new SequentialFile(null).searchRecordBasedOnHash("NANNY","/Users/rasrivastava/FILES/HASH_DB/")
  println(r)
  def generateTestRecords(fileName : String) = {
    val seqentialFile = new SequentialFile("/Users/rasrivastava/FILES/test.txt")
    val rnd = new Random()
    val range = 1 to 2147483647
    val position = Array("Worker","Manager","Director","SeniorDirector","VP")
    for (line <- Source.fromFile(fileName).getLines()) {
      val randomValue = rnd.nextInt(Integer.MAX_VALUE)
      val randomValueForTitle = rnd.nextInt(Integer.MAX_VALUE)
      val record = new Record(line,rnd.nextInt(Integer.MAX_VALUE),position(randomValueForTitle % 5))
      seqentialFile.addRecord(record.toString)

    }
  }

  def searchRecord(recordName : String) : Unit = {
    val record = new SequentialFile("/Users/rasrivastava/FILES/test.txt").searchRecordByName(recordName)
    println(record)
  }

}

