/**
  * Created by oguz on 26.10.2018.
  */
package logic;
class Autocomplete(val terms:  Array[Term]) {


  // Returns all terms that start with the given prefix, in descending order of weight.
  def allMatches(searchQuery: String): Array[Term] = {
    val searchTerm = new Term(searchQuery, 0)
    val matcher = new PrefixOrdering(searchTerm.query.length)

    val firstMatch = BinarySearchDeluxe.firstIndexOf(terms, searchTerm, matcher)
    val lastMatch = BinarySearchDeluxe.lastIndexOf(terms, searchTerm, matcher)
    // Get all matches in range
    terms.slice(firstMatch, lastMatch + 1)
  }

  // Returns the number of terms that start with the given prefix.
  def numberOfMatches(searchQuery: String): Long = {
    val searchTerm = new Term(searchQuery, 0)
    val matcher = new PrefixOrdering(searchTerm.query.length)

    val firstMatch = BinarySearchDeluxe.firstIndexOf(terms, searchTerm, matcher)
    val lastMatch = BinarySearchDeluxe.lastIndexOf(terms, searchTerm, matcher)
    lastMatch+1-firstMatch
  }
}
