// DataTypes/Task2.kt
package dataTypesExercise2

fun main() {
  val int: Int = 10
  val double: Double = 1.1
  val boolean: Boolean = false
  val string: String = "abc"
  val character: Char = 'a'

  // Can be combined:
  val intAndDouble = int + double
  val stringAndChar = string + character
  val stringAndInt = string + int
  val stringAndDouble = string + double
  val charAndInt = character + int

  println("The type that can be combined " +
    "with every other type using '+':")
  println("String")

  // Can't be combined:

}