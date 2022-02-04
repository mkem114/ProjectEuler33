import kotlin.math.pow

private const val SMALLEST_DOUBLE_DIGIT_NUM = 10
private const val LARGEST_DOUBLE_DIGIT_NUM = 99
private const val SMALLEST_PRIME = 2
private const val KNOWN_ANOMALOUS_CANCELLATIONS = 4

fun main() {
    val doubleDigitNumbers = (SMALLEST_DOUBLE_DIGIT_NUM..LARGEST_DOUBLE_DIGIT_NUM).toList()
    val fractions = ArrayList<Pair<Int, Int>>(KNOWN_ANOMALOUS_CANCELLATIONS)
    doubleDigitNumbers.forEach{numerator -> run {
        doubleDigitNumbers.forEach { denominator -> run denominator@{
            if (denominator == numerator) {  // skip anything that == 1
                return@denominator
            }
            val numeratorFirstDigit = nthDigit(numerator, 1)
            val numeratorSecondDigit = nthDigit(numerator, 2)
            val denominatorFirstDigit = nthDigit(denominator, 1)
            val denominatorSecondDigit = nthDigit(denominator, 2)
            if (denominatorFirstDigit == numeratorSecondDigit
                && numeratorFirstDigit.toFloat() / denominatorSecondDigit.toFloat() ==
                numerator.toFloat() / denominator.toFloat()
            ) {
                fractions.add(Pair(numerator, denominator))
            }
        }}
    }}
    println("Remaining fractions: $fractions")

    var productFraction = fractions.reduce { acc, pair -> Pair(acc.first * pair.first, acc.second * pair.second) }
    val largestPossibleFactor = LARGEST_DOUBLE_DIGIT_NUM.toDouble().pow(KNOWN_ANOMALOUS_CANCELLATIONS).toInt()
    (SMALLEST_PRIME..largestPossibleFactor).toList().forEach{ factor ->
        while (productFraction.first.rem(factor) == 0 && productFraction.second.rem(factor) == 0) {
            productFraction = Pair(productFraction.first / factor, productFraction.second / factor)
        }
    }
    println("Remaining fraction: $productFraction")
}

private fun nthDigit(int: Int, nthDigit: Int): Int = int.toString()[nthDigit - 1].digitToInt()
