package aoc2025.day02

import common.benchmarkTime
import common.println
import common.readInput

var pow10 = LongArray(12)

fun main() {
    pow10[0] = 1L
    for (i in 1..11) {
        pow10[i] = pow10[i - 1] * 10L
    }

    fun part1(input: List<String>): Long {
        val ranges = getRanges(input)
        return ranges.sumOf { range ->
            sumFakeIdsInRange(range, ::isDoubleRepeat)
        }
    }

    fun part2(input: List<String>): Long {
        val ranges = getRanges(input)
        return ranges.sumOf { range ->
            sumFakeIdsInRange(range, ::isPeriodicMod)
        }
    }

    val testInput = readInput("aoc2025/Day02_test")
    val part1Result = part1(testInput)
    part1Result.println()
    check(part1Result == 1227775554L)

    val part2Result = part2(testInput)
    part2Result.println()
    check(part2Result == 4174379265L)

    val input = readInput("aoc2025/Day02")
    benchmarkTime("part1") {
        part1(input).println()
    }

    benchmarkTime("part2") {
        part2(input).println()
    }
}

fun sumFakeIdsInRange(range: Range, predicate: (String) -> Boolean): Long {
    var sum = 0L
    var current = range.start
    while (current <= range.end) {
        val s = current.toString()
        if (predicate(s)) {
            sum += current
        }
        current++
    }
    return sum
}

fun isDoubleRepeat(value: String): Boolean {
    val len = value.length
    if (len % 2 != 0) return false
    val half = len / 2
    return value.regionMatches(0, value, half, half)
}

fun isPeriodic(value: String): Boolean {
    val len = value.length
    for (period in 1..len / 2) {
        if (len % period != 0) continue
        val pattern = value.substring(0, period)

        var ok = true
        var i = period
        while (i < len) {
            if (!value.regionMatches(i, pattern, 0, period)) {
                ok = false
                break
            }
            i += period
        }

        if (ok) return true
    }
    return false
}


fun isPeriodicMod(value: String): Boolean {
    val n = value.toLong()
    val len = value.length

    for (d in len / 2 downTo 1) {
        if (len % d != 0) continue
        val k = len / d

        var factor = 0L
        var p = 1L
        val step = pow10[d]
        repeat(k) {
            factor += p
            p *= step
        }

        if (n % factor != 0L) continue
        val base = n / factor

        val minBase = pow10[d - 1]
        val maxBase = pow10[d]
        if (base in minBase until maxBase) return true
    }

    return false
}


fun getRanges(input: List<String>): List<Range> {
    val values = input[0]
    return values
        .split(",")
        .map { part ->
            val (start, end) = part.split("-")
            Range(start.toLong(), end.toLong())
        }
}

data class Range(
    val start: Long,
    val end: Long,
)
