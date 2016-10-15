package ScalaSolutions.Strings

import scala.collection.mutable
import scala.io.Source

/**
 * Created by Rahul on 10/10/16.
 */
//Case sensitive dict
class TrieNode[T] {
  var value : Option[T] = None
  val next = new Array[TrieNode[T]](26+26)
}
class Trie[T] {
  var root : TrieNode[T] = null

  def getIndex(ch : Char) : Int = {
    if (Character.isLowerCase(ch)) {
      ch - 'a'
    }else {
      26 + (ch - 'A')
    }
  }

  def getChar(index : Int) : Char = {
    if (index < 26) {
      ('a'.toInt + index).toChar
    }else {
      ('A'.toInt + index).toChar
    }
  }
  def put(key : String,value : T) : Unit = {
    def putInternal(node : TrieNode[T],key : String,value : T,depth : Int): TrieNode[T] = {
      var tempNode = node
      if (tempNode == null) {
        tempNode = new TrieNode[T]()
      }

      if (depth == key.length) {
        tempNode.value = Some(value)
        tempNode
      }else {
        val ch = key.charAt(depth)
        val index = getIndex(ch)
        //println("Index is " + index + " for ch " + ch)
        tempNode.next(index) = putInternal(tempNode.next(index),key,value,depth+1)
        tempNode
      }
    }

    root = putInternal(root,key,value,0)
  }

  def get(key : String) : Option[T] = {
    def getInternal(node : TrieNode[T],key : String,depth : Int) : Option[T] = {
      if (node == null) {
        None
      }else {
        if (depth == key.length) {
          node.value
        }else {
          val ch = key.charAt(depth)
          val index = getIndex(ch)
          getInternal(node.next(index),key,depth+1)
        }
      }


    }

    getInternal(root,key,0)
  }

  def getAllMatch(prefix : String) : Map[String,T] = {
    def maxMatch(node : TrieNode[T],depth : Int) : TrieNode[T] = {
      if (node == null) {
        null
      }else {
        if (depth == prefix.length) {
          node
        }else {
          val nextNode = node.next(getIndex(prefix.charAt(depth)))
          maxMatch(nextNode,depth+1)
        }
      }
    }

    def iterateAll(node : TrieNode[T],lst : List[Char],map : mutable.HashMap[String,T]) : Unit = {
      //println(lst)
      if (node != null) {
        node.value match {
          case Some(x) => {
            map.put(lst.mkString(""),x)
          }
          case None => {

          }

        }

        for (i <-0 to node.next.length-1) {
          val ch = getChar(i)
          iterateAll(node.next(i),lst ++ ch.toString,map)
        }
      }
    }

    val maxMatched = maxMatch(root,0)
    val map = new mutable.HashMap[String,T]()
    iterateAll(maxMatched,prefix.toList,map)
    map.toMap
  }



}

object DictTest extends App {
//  val trie = new Trie[Int]()
//  trie.put("hello",1)
//  trie.put("hell",2)
//  trie.put("help",3)
//  trie.put("he",4)
//  trie.put("world",10)
//
//  println(trie.get("he"))
  val dictFileName = "/usr/share/dict/words"
  val sourceStream = Source.fromFile(dictFileName).getLines()
  val trie = new Trie[Int]
  var index = 1
  for (line <- sourceStream) {
    trie.put(line,index)
    index = index + 1
  }

  //Command prompt to check if the word exist
//  while (true) {
//    val name = readLine("EnterWord ")
//    println(trie.get(name))
//  }
  //val m = trie.getAllMatch("weak")
  //println(m)
  //println(trie.search("hez"))
}