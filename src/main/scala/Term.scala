/**
  * Created by oguz on 26.10.2018.
  */
package logic

class Term(val query: String, val weight: Long) extends Ordered[Term] {

  if (query == null || weight < 0)
    throw new IllegalArgumentException("Null query or negative weight")

  override def compare(that: Term): Int = this.query compare that.query

  override def toString = "%10d\t%s".format(weight, query)
}

object ReverseWeightOrdering extends Ordering[Term] {
  override def compare(x: Term, y: Term): Int = y.weight compare x.weight
}

class PrefixOrdering(val r: Int) extends Ordering[Term] {
  if (r<0) throw new IllegalArgumentException("r cannot be less than 0")
  override def compare(searchTerm: Term, rowTerm: Term): Int = subString(rowTerm.query) compare subString(searchTerm.query)

  /**
    * First r chars of the string is taken. If the string is shorter than r, the whole string is taken.
    * @param str
    * @return
    */
  def subString(str: String): String = {
    str.slice(0, if (r < str.length) r else str.length)
  }
}


