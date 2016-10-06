package ScalaSolutions.UnixCommands

import java.io.File

import scala.collection.mutable

/**
 * Created by rasrivastava on 10/5/16.
 */
trait FileVisitor {
  def visit(file : File) : Unit
}
class FindCommand(visitor : FileVisitor) {
  def processFile(dir : File) : Unit = {
    val contents = dir.list()
    contents.foreach(name => {
      val fullPath = dir + "/" + name
      val path = new java.io.File(fullPath).getCanonicalPath
      val fileRep = new java.io.File(path)
      if (fileRep.exists()) {
        if (fileRep.isDirectory) {
          processFile(fileRep)
        }else {
          visitor.visit(fileRep)

        }
      }

    })
  }

  def processBreadthFirst(dir : File) : Unit = {
    val visitedQueue = new mutable.Queue[File]()
    visitedQueue.enqueue(dir)
    //In case of soft-links
    val visitedHistory = new mutable.HashSet[String]
    while (visitedQueue.isEmpty == false) {
      val topDir = visitedQueue.dequeue()
      if (topDir.exists()) {
        val dirContents = topDir.list()
        dirContents.foreach(name => {
          val fullName = topDir.getCanonicalPath + "/" + name
          val contentRep = new File(fullName)
          if (visitedHistory.contains(fullName) == false) {
            visitedHistory.+=(fullName)
            if (contentRep.exists()) {
              if (contentRep.isFile) {
                visitor.visit(contentRep)
              }else {
                if (contentRep.isDirectory) {
                  visitedQueue.enqueue(contentRep)
                }
              }
            }
          }
        })
      }
    }
  }
  def execute(dirPath : String) : Unit = {
    val path = new java.io.File(dirPath).getCanonicalPath
    val dir = new java.io.File(path)
    processBreadthFirst(dir)

  }


}

class FilePrinter extends FileVisitor {
  override def visit(file: File): Unit = {
    println(file)
    visited.add(file.getAbsolutePath)
  }

  val visited = new mutable.HashSet[String]()
}
object FindCommand extends App {
  val filePrinter = new FilePrinter
  val findCommand = new FindCommand(filePrinter)
  findCommand.execute("/Users/rasrivastava/")
  println(filePrinter.visited.size)
//  findCommand.visited.foreach(node => {
//    println(node)
//  })
}
