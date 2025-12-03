package aoc2025.day03

import common.benchmarkTime
import common.println
import common.readInput

fun main() {

    fun part1(input: List<String>): Long {
        val banks = input.map {
            it.toCharArray()
        }
        return banks.sumOf { bank->
            bank.getHighestJolt(2)
        }
    }

    fun part2(input: List<String>): Long {
        val banks = input.map {
            it.toCharArray()
        }
        return banks.sumOf { bank->
            bank.getHighestJolt(12)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("aoc2025/Day03_test")
    val part1Result = part1(testInput)
    part1Result.println()
    check(part1Result == 357L)

    val part2Result = part2(testInput)
    part2Result.println()
    check(part2Result == 3121910778619L)

    val input = readInput("aoc2025/Day03")
    benchmarkTime("part1") {
        part1(input)
    }

    benchmarkTime("part2") {
        part2(input)
    }
}

private fun CharArray.getHighestJolt(batteryCount: Int): Long {
    val selectedValues = StringBuilder()
    var startPosition = 0

    while (selectedValues.length < batteryCount) {
        val remainingBatteries = batteryCount - selectedValues.length
        val maxPosition = this.size - remainingBatteries

        var foundPosition = -1
        for (value in '9' downTo '1') {
            foundPosition = this.isThereAValueInRangePosition(value, startPosition, maxPosition)
            if (foundPosition >= 0) {
                selectedValues.append(value.toString())
                startPosition = foundPosition + 1
                break
            }
        }

        if (foundPosition < 0) {
            break
        }
    }

    return selectedValues.toString().toLong()
}

private fun CharArray.isThereAValueInRangePosition(value: Char, startPosition: Int, stopPosition: Int): Int {
    if (startPosition > stopPosition) return -1
    for (i in startPosition..stopPosition) {
        if (this[i] == value) return i
    }
    return -1
}
