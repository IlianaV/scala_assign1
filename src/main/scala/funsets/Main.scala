package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
}

////negative integers
//(x: Int) => x<0
//
////all numbers between 1 and 100
//def onetohundred(x: Int): Boolean = {
//if (x >= 1 && x <= 100) true else false
//}
//
////all numbers between 2 and 10
//def twototen(x: Int): Boolean = {
//if (x >= 2 && x <= 10) true else false
//}
//
//union(onetohundred, twototen)

