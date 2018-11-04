import logic.{BinarySearchDeluxe, PrefixOrdering, ReverseWeightOrdering, Term}
import org.scalatest.FlatSpec

class TermTest extends FlatSpec{

  // Tests for Term.scala

  def printArray(array:Array[Term]):Unit={
    println("[\n" + array.mkString("\n") + "]")
  }


  new Term("qwe", 3)
  assertThrows[IllegalArgumentException]{new Term(null, 1)}
  assertThrows[IllegalArgumentException]{new Term("test", -1)}
  new PrefixOrdering(1)
  assertThrows[IllegalArgumentException]{new PrefixOrdering(-1)}

  val terms = Array(
    new Term("AAAA", 12),
    new Term("AABB", 7),
    new Term("BBAA", 34),
    new Term("BBBB", 56),
    new Term("AACC", 2),
    new Term("CCAA", 1),
    new Term("CCCC", 9),
    new Term("DDAA", 3),
    new Term("AADD", 15),
    new Term("DDDD", 156))

  val shuffledTerms = scala.util.Random.shuffle(terms.toList).toArray

  println("shuffled terms")
  printArray(shuffledTerms)
  println
  println("ordered terms, alphabetical")
  val alphabeticalSorted = shuffledTerms.sorted
  printArray(alphabeticalSorted)
  assert(alphabeticalSorted(0).query == "AAAA")
  assert(alphabeticalSorted(1).query == "AABB")
  assert(alphabeticalSorted(2).query == "AACC")
  assert(alphabeticalSorted(3).query == "AADD")
  assert(alphabeticalSorted(4).query == "BBAA")
  assert(alphabeticalSorted(5).query == "BBBB")
  assert(alphabeticalSorted(6).query == "CCAA")
  assert(alphabeticalSorted(7).query == "CCCC")
  assert(alphabeticalSorted(8).query == "DDAA")
  assert(alphabeticalSorted(9).query == "DDDD")
  println
  println("ordered terms, reverse weight ordering")
  val reverseWeightSorted = shuffledTerms.sorted(ReverseWeightOrdering)
  printArray(reverseWeightSorted)
  assert(reverseWeightSorted(0).weight == 156)
  assert(reverseWeightSorted(1).weight == 56)
  assert(reverseWeightSorted(2).weight == 34)
  assert(reverseWeightSorted(3).weight == 15)
  assert(reverseWeightSorted(4).weight == 12)
  assert(reverseWeightSorted(5).weight == 9)
  assert(reverseWeightSorted(6).weight == 7)
  assert(reverseWeightSorted(7).weight == 3)
  assert(reverseWeightSorted(8).weight == 2)
  assert(reverseWeightSorted(9).weight == 1)
  println
  println("ordered terms, prefix ordering r = 1")
  printArray(shuffledTerms.sorted(new PrefixOrdering(1)))
  println
  println("ordered terms, prefix ordering r = 2")
  printArray(shuffledTerms.sorted(new PrefixOrdering(2)))
  println
  println("ordered terms, prefix ordering r = 3")
  printArray(shuffledTerms.sorted(new PrefixOrdering(3)))
  println
  println("ordered terms, prefix ordering r = 4")
  printArray(shuffledTerms.sorted(new PrefixOrdering(4)))
  println
  println("ordered terms, prefix ordering r = 5")
  printArray(shuffledTerms.sorted(new PrefixOrdering(5)))

  val terms2 = Array(new Term("afrv", 534532))

  println
  println("ordered terms single element, alphabetical")
  printArray(terms2.sorted)
  assert(terms2.sorted.length == 1)
  println
  println("ordered terms single element, reverse weight ordering")
  printArray(terms2.sorted(ReverseWeightOrdering))
  assert(terms2.sorted(ReverseWeightOrdering).length == 1)
  println
  println("ordered terms single element,  prefix ordering r = 2")
  printArray(terms2.sorted(new PrefixOrdering(2)))
  assert(terms2.sorted(new PrefixOrdering(2)).length == 1)

  val terms3 = new Array[Term](0)
  assert(terms3.sorted.length == 0)
  assert(terms3.sorted(ReverseWeightOrdering).length == 0)
  assert(terms3.sorted(new PrefixOrdering(2)).length == 0)


}
