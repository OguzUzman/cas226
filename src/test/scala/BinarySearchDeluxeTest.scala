import logic.{BinarySearchDeluxe, PrefixOrdering, Term}
import org.scalatest.FlatSpec

/**
  * Created by oguz on 28.10.2018.
  */
class BinarySearchDeluxeTest extends FlatSpec {
  val terms = Array(
    new Term("AAAA", 12),
    new Term("AABB", 7),
    new Term("BBAA", 34),
    new Term("BBBB", 56),
    new Term("AACC", 2),
    new Term("AACC", 4),
    new Term("CCAA", 1),
    new Term("CCCC", 9),
    new Term("DDAA", 3),
    new Term("AADD", 15),
    new Term("DDDD", 156))

  val sortedTerms = terms.sorted

  printArray(sortedTerms)

  assertThrows[IllegalArgumentException](BinarySearchDeluxe.firstIndexOf(null, new Term("a", 1), new PrefixOrdering(1)))
  assertThrows[IllegalArgumentException](BinarySearchDeluxe.firstIndexOf(terms, null, new PrefixOrdering(0)))
  assertThrows[IllegalArgumentException](BinarySearchDeluxe.firstIndexOf(terms, new Term("a", 1), null))

  def printArray(array: Array[Term]): Unit = {
    println("" + array.length + " matches.")
    println("[\n" + array.mkString("\n") + "\n]")
  }

  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("AA", 0), new PrefixOrdering(2)) == 0)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("AAAA", 0), new PrefixOrdering(4)) == 0)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("AABB", 0), new PrefixOrdering(4)) == 1)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("AACC", 0), new PrefixOrdering(4)) == 2)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("BB", 0), new PrefixOrdering(2)) == 5)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("CC", 0), new PrefixOrdering(2)) == 7)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("CCC", 0), new PrefixOrdering(3)) == 8)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("D", 0), new PrefixOrdering(1)) == 9)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("DDD", 0), new PrefixOrdering(3)) == 10)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("nontext", 0), new PrefixOrdering(7)) == -1)


  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("A", 0), new PrefixOrdering(1)) == 4)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("AA", 0), new PrefixOrdering(2)) == 4)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("AAAA", 0), new PrefixOrdering(4)) == 0)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("AABB", 0), new PrefixOrdering(4)) == 1)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("AACC", 0), new PrefixOrdering(4)) == 3)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("BBA", 0), new PrefixOrdering(3)) == 5)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("BB", 0), new PrefixOrdering(2)) == 6)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("CC", 0), new PrefixOrdering(2)) == 8)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("CCC", 0), new PrefixOrdering(3)) == 8)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("D", 0), new PrefixOrdering(1)) == 10)
  assert(BinarySearchDeluxe.lastIndexOf(sortedTerms, new Term("DDD", 0), new PrefixOrdering(3)) == 10)
  assert(BinarySearchDeluxe.firstIndexOf(sortedTerms, new Term("nontext", 0), new PrefixOrdering(7)) == -1)

  object defaultOrdering extends Ordering[Term] {
    override def compare(x: Term, y: Term): Int = x compare y
  }

}
