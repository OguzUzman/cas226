/**
  * Created by oguz on 26.10.2018.
  */
package logic

import java.util.Scanner

object Main {

  def printArray(array:Array[Term]):Unit={
    println(""+array.length+" matches.")
    println("[\n" + array.mkString("\n") + "\n]")
  }
  def main(args: Array[String]): Unit = {
    val terms = readFile("/Users/oguz/Desktop/" + args(0) + ".txt").sorted(ReverseWeightOrdering)

    val autocompleter = new Autocomplete(terms)
    val userInput = new Scanner(System.in)
    do {
      println("Enter search term, press enter.")
      val prefix = userInput.nextLine()
      val matches = autocompleter.allMatches(prefix)
      printArray(matches)
    } while (userInput.hasNextLine)

  }

  def readFile(filePath : String): Array[Term] = {
    val bufferedSource = io.Source.fromFile(filePath)
    bufferedSource
      .getLines
      .drop(1) // Skip the header line
      .map((a:String) => {val cols = a.trim.split("\t"); new Term(cols(1), cols(0).toInt)}) // Parse the line into Terms
      .toArray
  }


}
