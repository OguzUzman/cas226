/**
  * Created by oguz on 26.10.2018.
  */
package logic;
class BinarySearchDeluxe[Key](val terms:  Array[Key], ordering: Ordering[Key]) {
  val zippedTerms = terms.zipWithIndex

  val shuffledTerms = scala.util.Random.shuffle(zippedTerms)

  val head = new Node[Key](shuffledTerms(0)._1, shuffledTerms(0)._2)

  for (pair <- shuffledTerms){
    insert(head, pair, ordering)
  }

  //def firstIndexOf(x: Term, y: Term): Int = y.weight - x.weight
  def firstIndexOf[Key](keys: Array[Key], key: Key, ordering: Ordering[Key]): Int = {
    if (keys == null || key == null) throw new IllegalArgumentException("No argument of firstIndexOf can be null")
    if (ordering == null) throw new IllegalArgumentException("No argument of firstIndexOf can be null")
    if (!keys.exists((y:Key)=>ordering.equiv(key, y))){return -1}
    keys.indexWhere(ordering.equiv(_, key))
  }

  def lastIndexOf[Key](keys: Array[Key], key: Key, ordering: Ordering[Key]): Int = {
    if (keys == null || key == null) throw new IllegalArgumentException("No argument of firstIndexOf can be null")
    if (ordering == null) throw new IllegalArgumentException("No argument of firstIndexOf can be null")
    if (!keys.exists((y:Key)=>ordering.equiv(key, y))){return -1}
    keys.length - 1 -keys.reverse.indexWhere(ordering.equiv(_, key))
  }

  def traverse(current: Node[Key],)

  def insert(current: Node[Key], termIndexTuple: (Key, Int), ordering: Ordering[Key]):Unit={
    if (ordering.compare(termIndexTuple._1, current.term) <= 0){
      if (current.left == null){
        current.left = new Node[Key](termIndexTuple._1, termIndexTuple._2)
      } else {
        insert(current.left, termIndexTuple, ordering)
      }
    } else {
      if (current.right == null){
        current.right = new Node[Key](termIndexTuple._1, termIndexTuple._2)
      } else {
        insert(current.right, termIndexTuple, ordering)
      }
    }
  }
}

class Node[Key](val term:Key, val index:Int) {
  var left:Node[Key]  = _
  var right:Node[Key] = _
}
