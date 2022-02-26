@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson3.task1.isPrime
import lesson1.task1.discriminant
import lesson1.task1.lengthInMeters
import kotlin.math.*

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) =
    list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.fold(0.0) { previousResult, element ->
    previousResult + element * element
})

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isNotEmpty()) list.average() else 0.0

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val average = list.average()
    for (i in 0 until list.size) {
        list[i] -= average
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    if (a.isEmpty() || b.isEmpty()) return 0
    var sum = 0
    for (i in a.indices) {
        sum += a[i] * b[i]
    }
    return sum
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    var sum = 0
    var x2 = 1
    for (i in p.indices) {
        sum += p[i] * x2
        x2 *= x
    }
    return sum
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list: MutableList<Int> = mutableListOf()
    val halfN = n / 2
    var m = n
    var i = 2
    while (i <= halfN) {
        if (m % i == 0) {
            list.add(i)
            m /= i
        } else ++i
    }
    return if (list.isEmpty()) listOf(n) else list
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")


/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n < 0 || base < 1) return listOf()
    if (n == 0) return listOf(0)
    val list: MutableList<Int> = mutableListOf()
    var number = n
    while (number >= base) {
        list.add(number % base)
        number /= base
    }
    list.add(number)
    list.reverse()
    return list
}


/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()
//{
//    val list = convert(n, base)
//    var str = ""
//    for (number in list) {
//        if (number < 10) str += number.toString()
//        if (number >= 10) str += number.
//    }
//}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
val roman1 = listOf("I", "X", "C", "M")
val roman5 = listOf("V", "L", "D")

fun roman(n: Int): String {
    val strN = n.toString()
    val result = StringBuilder()

    for ((index, number) in strN.withIndex()) {
        val i = strN.length - index - 1
        val interimResult = StringBuilder()
        result.append(
            when (number) {
                '1' -> roman1[i]
                '2' -> interimResult.append(roman1[i]).append(roman1[i])
                '3' -> interimResult.append(roman1[i]).append(roman1[i]).append(roman1[i])
                '4' -> interimResult.append(roman1[i]).append(roman5[i])
                '5' -> roman5[i]
                '6' -> interimResult.append(roman5[i]).append(roman1[i])
                '7' -> interimResult.append(roman5[i]).append(roman1[i]).append(roman1[i])
                '8' -> interimResult.append(roman5[i]).append(roman1[i]).append(roman1[i]).append(roman1[i])
                '9' -> interimResult.append(roman1[i]).append(roman1[i + 1])
                else -> ""
            }
        )
    }
    return result.toString()
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val thousand: String

    return if (n < 1000) russianTo999(n, thousand = false, needSpace1 = false, needSpace2 = false)
    else {
        val lastThousand = n / 1000 % 100

        thousand = when {
            lastThousand in 11..14 -> "тысяч"
            lastThousand - lastThousand / 10 * 10 == 1 -> "тысяча"
            lastThousand - lastThousand / 10 * 10 in 2..4 -> "тысячи"
            else -> "тысяч"
        }
        (russianTo999(n / 1000, thousand = true, needSpace1 = true, needSpace2 = true) + thousand + russianTo999(
            n % 1000,
            thousand = false,
            needSpace1 = true,
            needSpace2 = false
        ))
    }
}

val numb1 = mapOf(
    1 to "один",
    2 to "два",
    3 to "три",
    4 to "четыре",
    5 to "пять",
    6 to "шесть",
    7 to "семь",
    8 to "восемь",
    9 to "девять"
)
val numb10 = mapOf(
    1 to "десять",
    2 to "двадцать",
    3 to "тридцать",
    4 to "сорок",
    5 to "пятьдесят",
    6 to "шестьдесят",
    7 to "семьдесят",
    8 to "восемьдесят",
    9 to "девяносто"
)
val numb100 = mapOf(
    1 to "сто",
    2 to "двести",
    3 to "триста",
    4 to "четыреста",
    5 to "пятьсот",
    6 to "шестьсот",
    7 to "семьсот",
    8 to "восемьсот",
    9 to "девятьсот"
)
val numb11to19 = mapOf(
    11 to "одиннадцать",
    12 to "двенадцать",
    13 to "тринадцать",
    14 to "четырнадцать",
    15 to "пятнадцать",
    16 to "шестнадцать",
    17 to "семнадцать",
    18 to "восемнадцать",
    19 to "девятнадцать",
)

fun russianTo999(n: Int, thousand: Boolean, needSpace1: Boolean, needSpace2: Boolean): String {
    var result = ""

    val tens = n % 100
    val units = tens % 10

    if (!thousand && needSpace1) result += " "

    if (n / 100 > 0) {
        result += numb100[n / 100]
        if (tens > 0) result += " "
    }

    if (tens > 0)
        if (tens in 11..19)
            return if (needSpace2) result + numb11to19[tens] + " "
            else result + numb11to19[tens]
        else if (tens > 9) {
            if (units > 0) result += numb10[tens / 10] + " "
            else result += numb10[tens / 10]
        }

    if (units > 0)
        if (thousand) result += when (units) {
            1 -> "одна"
            2 -> "две"
            in 3..9 -> numb1[units]
            else -> ""
        } else result += numb1[units]

    return if (result != "" && result != " ") {
        if (thousand) "$result "
        else result
    } else ""
}