import logic.{Autocomplete, ReverseWeightOrdering, Term}
import org.scalatest.FlatSpec

/**
  * Created by oguz on 28.10.2018.
  */
class AutocompleteTest extends FlatSpec{

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
    new Term("ABDD", 35),
    new Term("BAXD", 45),
    new Term("TDDD", 156))

  val shuffledTerms = scala.util.Random.shuffle(terms.toList).toArray
  val reverseWeightSorted = shuffledTerms.sorted
  val matcher = new Autocomplete(reverseWeightSorted)
  assert(matcher.allMatches("AARTY").length == 0)
  assert(matcher.allMatches("AA").length == 4)
  assert(matcher.allMatches("AAC").length == 1)
  assert(matcher.allMatches("T").length == 1)
  assert(matcher.allMatches("X").length == 0)
  assert(matcher.allMatches("").length == 12)

  assert(matcher.numberOfMatches("BB") == 2)
  assert(matcher.numberOfMatches("B") == 3)
  assert(matcher.numberOfMatches("DD") == 1)


}
