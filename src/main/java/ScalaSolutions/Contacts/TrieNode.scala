package ScalaSolutions.Contacts

/**
 * Created by rasrivastava on 10/16/16.
 */
class TrieNode[T] {
  var associatedValue : Option[T] = None
  var numberOfChildren : Int = _
  val links = new Array[TrieNode[T]](26)
}

class Trie[T] {
  var root : TrieNode[T] = _
  def getIndex(ch : Char) : Int = {
    ch - 'a'
  }
  def addString(key : String,value : T) : Unit = {

    def addStringInternal(node : TrieNode[T],depth : Int) : Tuple2[TrieNode[T],Boolean] = {

      var localNode = node
      if (localNode == null) {
        localNode = new TrieNode[T]
      }

      var insertedOrNot = false
      if (depth == key.size) {
        //println("Adding final value for " + key)
//        if (localNode.associatedValue == null) {
//          localNode.associatedValue = Some(value)
//          insertedOrNot = true
//        }
        val localValue = localNode.associatedValue
        localValue match {
          case Some(x) => {
            //No insertion
          }case None => {
            //println("Value added")
            localNode.associatedValue = Some(value)
            localNode.numberOfChildren = localNode.numberOfChildren + 1
            insertedOrNot = true
          }
        }
      }else {
        //println("Adding key for " + key.charAt(depth))
        val ch = key.charAt(depth)
        val res = addStringInternal(localNode.links(getIndex(ch)),depth+1)
        if (res._2) {
          localNode.numberOfChildren = localNode.numberOfChildren + 1
          insertedOrNot = true
        }

        localNode.links(getIndex(ch)) = res._1

      }

      (localNode,insertedOrNot)
    }

    val res = addStringInternal(root,0)
    root = res._1


  }

  def getPrefixValue(key : String) : Int = {
    def getPrefixValueItr(node : TrieNode[T],depth : Int) : Int = {
      if (depth == key.size) {
        if (node != null) {
          node.numberOfChildren
        }else {
          0
        }
      }else {
        //println("Extracting link for " + key.charAt(depth))
        if (node == null) {
          0
        }else {

          val index = getIndex(key.charAt(depth))
          val link = node.links(index)
          getPrefixValueItr(link,depth+1)
        }

      }
    }
    getPrefixValueItr(root,0)
  }


}


object Solution {
  def main(args : Array[String]) : Unit = {
//    val trie = new Trie[Int]
//    trie.addString("hack",1)
//    trie.addString("hackerrank",2)
//    //println(trie.root)
//    println(trie.getPrefixValue("hacke"))
     val sc = new java.util.Scanner (System.in);
     val n = sc.nextInt();
     var a0 = 0;
     val trie = new Trie[Int]
     while(a0 < n){
       val op = sc.next()
       val contact = sc.next()
       op match {
         case "add" => {
           trie.addString(contact,1)
         }
         case "find" => {
           println(trie.getPrefixValue(contact))
         }
       }
       a0+=1;
     }
  }
}