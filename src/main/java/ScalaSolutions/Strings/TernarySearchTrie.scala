package ScalaSolutions.Strings

import scala.io.Source

/**
 * Created by Rahul on 10/11/16.
 */
class TernarySearchTrieNode[T] {
  var key : Char = _
  var value : Option[T] = None
  var leftNode : TernarySearchTrieNode[T] = _
  var straightNode : TernarySearchTrieNode[T] = _
  var rightNode : TernarySearchTrieNode[T] = _

  override def toString: String = key.toString
}
class TernarySearchTrie[T] {
  var root : TernarySearchTrieNode[T] = _
  def put(key : String,value : T) : Unit = {
    def putItr(node: TernarySearchTrieNode[T],depth : Int) : TernarySearchTrieNode[T] = {
      var currentNode = node
      if (depth == key.length) {
        node
      } else {

        if (currentNode == null) {
          //println("Current node null " + key.charAt(depth))
          currentNode = new TernarySearchTrieNode[T]
          currentNode.key = key.charAt(depth)
        }

        if (currentNode.key == key.charAt(depth)) {
          //println("Matched going straight")
          currentNode.straightNode = putItr(currentNode.straightNode, depth + 1)
        } else {
          if (currentNode.key > key.charAt(depth)) {
            //println("Less than going Left")
            currentNode.leftNode = putItr(currentNode.leftNode, depth)
          } else {
            currentNode.rightNode = putItr(currentNode.rightNode, depth)
          }
        }
        if (depth == key.length-1) {
          currentNode.value = Some(value)
        }
        currentNode
      }
    }

    root = putItr(root,0)
  }

  def get(key : String) : Option[T] = {
    def getItr(node : TernarySearchTrieNode[T],depth : Int) : Option[T] = {
      //println(" Node is " + node)
      if (node == null) {
        None
      } else {

        val ch = key.charAt(depth)
        if (ch == node.key) {
          if (depth == key.length - 1) {
            node.value
          } else {
            getItr(node.straightNode, depth + 1)
          }

        } else {
          if (ch < node.key) {
            //println("Going to left")
            getItr(node.leftNode, depth)
          } else {
            //println("Going to right")
            getItr(node.rightNode, depth)
          }
        }

      }
    }

    getItr(root,0)
  }

}

//class TernarySearchTrieRoot[T] {
//  val root = new Array[TernarySearchTrie[T]](26 + 26)
//  def getIndex(ch : Char) : Int = {
//    if (Character.isLowerCase(ch)) {
//      ch - 'a'
//    }else {
//      26 + (ch - 'A')
//    }
//  }
//
//  def getChar(index : Int) : Char = {
//    if (index < 26) {
//      ('a'.toInt + index).toChar
//    }else {
//      ('A'.toInt + index).toChar
//    }
//  }
//
//  def getTree(key : String) : Option[TernarySearchTrie[T]] = {
//    val firstChar = key.charAt(0)
//    val index = getIndex(firstChar)
//    if (root(index) == null) {
//      None
//    }else {
//      Some(root(index))
//    }
//  }
//
//  def putTree(key : String,ternarySearchTrie: TernarySearchTrie[T]) : Unit = {
//    val firstChar = key.charAt(0)
//    val index = getIndex(firstChar)
//    root(index) = ternarySearchTrie
//  }
//  def put(key : String,value : T): Unit = {
//    val tree = getTree(key)
//    tree match {
//      case Some(tr) => {
//        tr.put(key,value)
//      }
//      case None => {
//        val tr = new TernarySearchTrie[T]
//        tr.put(key,value)
//        putTree(key,tr)
//      }
//    }
//
//  }
//
//  def get(key : String) : Option[T] = {
//    val tree = getTree(key)
//    tree match {
//      case Some(tr) => {
//        tr.get(key)
//      }
//      case None => {
//        None
//      }
//    }
//
//  }
//}

object TernarySearchTrieTest extends  App {
//  val ternarySearchTrie = new TernarySearchTrie[Int]
//  ternarySearchTrie.put("Hello",1)
//  println("Done 1")
//  ternarySearchTrie.put("Help",2)
//  println("Done 2")
//  ternarySearchTrie.put("Hell",3)
//  println("Done 3")
//  ternarySearchTrie.put("World",4)
//  println("Done 4")
//  println(ternarySearchTrie.get("World"))
  //println(ternarySearchTrie.get("World"))
//  val trRoot = new Ter[Int]
//  trRoot.put("Hello",1)
//  trRoot.put("Help",2)
//  trRoot.put("Hell",3)
//  trRoot.put("World",4)
//  println(trRoot.get("World"))
  val fileName = "/usr/share/dict/words"
  val sourceStream = Source.fromFile(fileName).getLines()
  val tree = new TernarySearchTrie[Int]
  var index = 1
  for (line <- sourceStream) {
    tree.put(line,index)
    index = index + 1
  }

  while (true) {
    val entry = Console.readLine("Enter the word")
    println(tree.get(entry))

  }
}