package Kattis

import scala.collection.mutable
import scala.util.control.Breaks._

/**
 * Created by Rahul on 1/2/17.
 */
class SymbolTable {
  def addSymbols(key : String,value : Int): Unit = {
    getValue(key) match {
      case Some(x) => {
        forwardTable.remove(key)
        reverseTable.remove(x)
      }
      case None => {
      }
    }
    forwardTable.put(key,value)
    reverseTable.put(value,key)
  }

  def getValue(key : String) : Option[Int] = {
    forwardTable.get(key)
  }

  def getSymbol(value : Int) : Option[String] = {
    reverseTable.get(value)
  }

  def clear() : Unit = {
    reverseTable.clear()
    forwardTable.clear()
  }

  val forwardTable = new mutable.HashMap[String,Int]()
  val reverseTable = new mutable.HashMap[Int,String]()
}
class AddingWords {
  val symbolTable = new SymbolTable

  def handleDefCommand(defStr : Array[String]) : Unit = {
    val key = defStr(1)
    val value = defStr(2)
    symbolTable.addSymbols(key,value.toInt)
  }

  def handleCalculateComamnd(defStr : Array[String]) : Unit  = {

    def internal() : Option[String] = {

      var retValue: Option[Int] = Some(0)
      var state = 0
      breakable {
        for (i <- 1 to defStr.length - 2) {
          defStr(i) match {
            case "+" => {
              state = 0
            }
            case "-" => {
              state = 1
            }

            case default => {

              symbolTable.getValue(default) match {
                case None => {
                  retValue = None
                  break
                }
                case Some(x) => {
                  if (state == 0) {
                    retValue = Some(retValue.get + x)
                  } else {
                    retValue = Some(retValue.get - x)
                  }
                }
              }

            }


          }
        }
      }

      retValue match {
        case None => None
        case Some(x) => {
          symbolTable.getSymbol(x)
        }
      }

    }

    internal() match {
      case None => {
        println(defStr.slice(1,defStr.length).deep.mkString(" ") + " " + "unknown")
      }
      case Some(result) => {
        println(defStr.slice(1,defStr.length).deep.mkString(" ") + " " + result)
      }

    }




    //println(defStr.deep.mkString(" "))

  }
  def addCommand(cmd : String) : Unit = {
    val cmdStr = cmd.split(" ")
    cmdStr(0) match {
      case "def" => {
        handleDefCommand(cmdStr)
      }

      case "calc" => {
        handleCalculateComamnd(cmdStr)
      }

      case "clear" => {
        symbolTable.clear()
      }
    }
  }
}


object AddingWords {
  def main(args: Array[String]) {
    val in = new java.util.Scanner(System.in)
    val words = new AddingWords
    var line = in.nextLine()
    while (line != "clear") {
      words.addCommand(line)
      line = in.nextLine()
    }
  }
}