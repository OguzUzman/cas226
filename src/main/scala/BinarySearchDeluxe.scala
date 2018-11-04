/**
  * Created by oguz on 26.10.2018.
  */
package logic;
object BinarySearchDeluxe{

  /**
    * Internally calls the recursive binary search function
    * @param keys
    * @param key
    * @param ordering
    * @tparam Key
    * @return
    */
  def firstIndexOf[Key](keys: Array[Key], key: Key, ordering: Ordering[Key]): Int = {
    if (keys == null || key == null || ordering == null) throw new IllegalArgumentException("Arguments cannot be null")
    return firstIndexOf(keys, key, ordering, 0, keys.length-1)
  }

  private def firstIndexOf[Key](keys: Array[Key], key: Key, ordering: Ordering[Key], from:Int, to:Int): Int = {
    // middle point
    val pivot:Int = (from+to+1)/2

    // If two points are left, the result is one of them or there is none. Test both.
    if (to-from<=1 && foundFirstItem(keys, key, from, ordering))  from
    // If pivot element is the result return it.
    else if (foundFirstItem(keys, key, pivot, ordering))  pivot
    // If Two options were left, and none of them match; there are no results.
    else if (to-from<=1)  -1
    else if (ordering.compare(key, keys(pivot))>=0)  firstIndexOf(keys, key, ordering, from, pivot-1)
    else  firstIndexOf(keys, key, ordering, pivot+1, to)
  }

  /**
    * Checks if the element is the final result. If it matches, either it has to be the first element in the list or
    * the previous element must not match.
    * @param keys the data
    * @param key the key to match.
    * @param index index to check
    * @param ordering PrefixOrdering can be used
    * @tparam Key 'Term' can be used
    * @return true if it is the first item
    */
  def foundFirstItem[Key](keys:Array[Key], key: Key, index:Int, ordering: Ordering[Key]): Boolean ={
    if (index == 0) ordering.equiv(key, keys(0))
    else ordering.equiv(key, keys(index)) && !ordering.equiv(key, keys(index-1))
  }

  def lastIndexOf[Key](keys: Array[Key], key: Key, ordering: Ordering[Key]): Int = {
    if (keys == null || key == null || ordering == null) throw new IllegalArgumentException("Arguments cannot be null")
    lastIndexOf(keys, key, ordering, 0, keys.length-1)
  }

  private def lastIndexOf[Key](keys: Array[Key], key: Key, ordering: Ordering[Key], from:Int, to:Int): Int = {
    val pivot:Int = (from+to+1)/2
    if (to-from<=1 && foundLastItem(keys, key, from, ordering))  from
    else if (foundLastItem(keys, key, pivot, ordering))  pivot
    else if (to-from<=1)  -1
    else if (ordering.compare(key, keys(pivot))>0)  lastIndexOf(keys, key, ordering, from, pivot-1)
    else  lastIndexOf(keys, key, ordering, pivot+1, to)
  }

  def foundLastItem[Key](keys:Array[Key], key: Key, index:Int, ordering: Ordering[Key]): Boolean ={
    if (index == keys.length-1)
      ordering.equiv(key, keys(index))
    else ordering.equiv(key, keys(index)) && !ordering.equiv(key, keys(index+1))
  }

}