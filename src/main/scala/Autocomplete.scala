/**
  * Created by oguz on 26.10.2018.
  */
package logic;
class Autocomplete(val terms:  Array[Term]) {

  // Returns all terms that start with the given prefix, in descending order of weight.
  def allMatches(searchQuery: String): Array[Term] = {
    val searchTerm = new Term(searchQuery, 0)
    val matcher = new PrefixOrdering(searchTerm.query.length)
    terms.filter((that: Term) => matcher.equiv(searchTerm, that))}

  // Returns the number of terms that start with the given prefix.
  def numberOfMatches(searchQuery: String): Int = {
    val term = new Term(searchQuery, 0)
    val matcher = new PrefixOrdering(term.query.length)
    terms.count((that: Term) => matcher.equiv(term, that))
  }
}
