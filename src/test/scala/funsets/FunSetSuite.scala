package funsets

import org.junit._

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remvoe the
   * @Ignore annotation.
   */
  @Test def `singleton set one contains one`: Unit = {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  @Test def `intersect contains only elements that are in both sets`: Unit = {
    new TestSets {
      def onetohundred(x: Int): Boolean = (x >= 1 && x <= 100)
      def twototen(x: Int): Boolean = (x >= 2 && x <= 10)
      val s = intersect(onetohundred, twototen)
      assert(contains(s, 3), "Intersect 1")
      assert(contains(s, 10), "Intersect 2")
      assert(!contains(s, 1), "Intersect 3")
    }
  }

  @Test def `filter selects only elements that satisfy the predicate`: Unit = {
    new TestSets {
      def onetohundred(x: Int): Boolean = (x >= 1 && x <= 100)
      def twototen(x: Int): Boolean = (x >= 2 && x <= 10)
      def greterthanfifty(x: Int): Boolean = (x > 50)
      val s = filter(onetohundred, greterthanfifty)
      assert(contains(s, 60), "Filter 1")
      assert(!contains(s, 40), "Filter 2")
      assert(!contains(s, -20), "Filter 3")
    }
  }

  @Test def `forall checks if all elements in a set in the given bounds satisfy a predicate`: Unit = {
    new TestSets {
      val s = forall((x: Int) => (x >= 1 && x <= 100), (x: Int) => (x > 50))
      val ss = forall((x: Int) => (x > 50), (x: Int) => (x >= 1 && x <= 100))
      assert(!s, "Not all elements satisfy predicate")
      //assert(ss, "All elements satisfy predicate")
    }
  }


  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
