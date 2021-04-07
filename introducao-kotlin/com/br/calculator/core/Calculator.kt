import java.util.*
import kotlin.text.toFloat

const val SUM = "+"
const val SUBTRACTION = "-"
const val MULTIPLICATION = "*"
const val DIVISION = "/"
const val EXIT = "s"
const val ALLOWED_OPERATIONS = "$SUM $SUBTRACTION $MULTIPLICATION $DIVISION"

fun sum(num1: Float, num2: Float): Float = num1 + num2
fun subtraction(num1: Float, num2: Float): Float = num1 - num2
fun multiplication(num1: Float, num2: Float): Float = num1 * num2
fun division(num1: Float, num2: Float): Float = num1 / num2

fun calculate(num1: Float, num2: Float, operator: String, operation:(Float, Float) -> Float) {
  try {
    val result = operation(num1, num2)
    println("$num1 $operator $num2 = $result")
  }
  catch(e: Exception) {
    println("Não é possível realizar esta operação! ($num1 $operator $num2)")
  }
}

fun showMenu() {
  println("\nOperações disponíveis:")
  println("+ (Soma)")
  println("- (Subtração)")
  println("* (Multiplicação)")
  println("/ (Divisão)")
  print("\nInforme a operação ou 's' para sair: ")
}

fun pressEnterToContinue() {
  println("Pressione enter para continuar.")
  readLine()
}

fun readNumbers(): Array<String?> {
  print("Informe o primeiro número: ")
  val number1 = readLine()

  print("Informe o segundo número: ")
  val number2 = readLine()

  return arrayOf(number1, number2)
}

fun isValidNumbers(number1: String?, number2: String?): Boolean {
  try {
    number1!!.toFloat()
    number2!!.toFloat()
    return true
  } catch (e: Exception) {
    return false
  }
}

fun main() {
  var operator = ""
  do {
    showMenu()
    operator = readLine() ?: operator

    if (operator == EXIT) {
      println("Fim a aplicação!")
      break
    }

    if (operator !in ALLOWED_OPERATIONS) {
      println("Operador inválido!")
      pressEnterToContinue()
      continue
    }

    val numbers = readNumbers()
    
    if (!isValidNumbers(numbers[0], numbers[1])) {
      println("Números inválidos!")
      pressEnterToContinue()
      continue
    }

    val number1 = numbers[0]!!.toFloat()
    val number2 = numbers[1]!!.toFloat()
    
    when(operator) {
      SUM -> calculate(number1, number2, operator, ::sum)
      SUBTRACTION -> calculate(number1, number2, operator, ::subtraction)
      MULTIPLICATION -> calculate(number1, number2, operator, ::multiplication)
      else -> calculate(number1, number2, operator, ::division)
    }

    pressEnterToContinue()
  } while (operator.toLowerCase() != EXIT)
}