import logic.{BinarySearchDeluxe, PrefixOrdering, ReverseWeightOrdering, Term}
import org.scalatest.FlatSpec

/**
  * Created by oguz on 28.10.2018.
  */
class BinarySearchDeluxeTest extends FlatSpec{
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
    val reverseWeightSorted = shuffledTerms.sorted(ReverseWeightOrdering)

    assertThrows[IllegalArgumentException](BinarySearchDeluxe.firstIndexOf(null, new Term("a", 1), ReverseWeightOrdering))
    assertThrows[IllegalArgumentException](BinarySearchDeluxe.firstIndexOf(terms, null, ReverseWeightOrdering))
    assertThrows[IllegalArgumentException](BinarySearchDeluxe.firstIndexOf(terms, new Term("a", 1), null))

    object defaultOrdering extends Ordering[Term]{override def compare(x: Term, y: Term): Int = x compare y}
    assert(BinarySearchDeluxe.firstIndexOf(reverseWeightSorted, new Term("AA", 0), new PrefixOrdering(2))==3)
    assert(BinarySearchDeluxe.firstIndexOf(reverseWeightSorted, new Term("AAAA", 0), new PrefixOrdering(4))==4)
    assert(BinarySearchDeluxe.firstIndexOf(reverseWeightSorted, new Term("AABB", 0), new PrefixOrdering(4))==6)
    assert(BinarySearchDeluxe.firstIndexOf(reverseWeightSorted, new Term("BB", 0), new PrefixOrdering(1))==1)
    assert(BinarySearchDeluxe.firstIndexOf(reverseWeightSorted, new Term("CCC", 0), new PrefixOrdering(3))==5)

    assert(BinarySearchDeluxe.lastIndexOf(reverseWeightSorted, new Term("AA", 0), new PrefixOrdering(2))==8)
    assert(BinarySearchDeluxe.lastIndexOf(reverseWeightSorted, new Term("AAAA", 0), new PrefixOrdering(4))==4)
    assert(BinarySearchDeluxe.lastIndexOf(reverseWeightSorted, new Term("AABB", 0), new PrefixOrdering(4))==6)
    assert(BinarySearchDeluxe.lastIndexOf(reverseWeightSorted, new Term("BB", 0), new PrefixOrdering(1))==2)
    assert(BinarySearchDeluxe.lastIndexOf(reverseWeightSorted, new Term("CCC", 0), new PrefixOrdering(3))==5)
    assert(BinarySearchDeluxe.lastIndexOf(reverseWeightSorted, new Term("CC", 0), new PrefixOrdering(2))==9)

}
