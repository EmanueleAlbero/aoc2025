package aoc2025.day03

import common.benchmarkTime
import common.println
import common.readInput

fun main() {

    fun part1(input: List<String>): Long {
        return input.sumOf { bank->
            bank.getHighestJolt(2)
        }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { bank->
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

private fun String.getHighestJolt(batteryCount: Int): Long {
    val selectedValues = StringBuilder(batteryCount)
    var startPosition = 0

    repeat(batteryCount) {
        val remainingBatteries = batteryCount - selectedValues.length
        val maxPosition = this.length - remainingBatteries

        var foundPosition = -1
        for (value in '9' downTo '1') {
            foundPosition = this.indexOf(value, startPosition)
            if (foundPosition in startPosition..maxPosition) {
                selectedValues.append(value)
                startPosition = foundPosition + 1
                break
            }
        }

        if (foundPosition < 0) return selectedValues.toString().toLongOrNull() ?: 0L
    }

    return selectedValues.toString().toLong()
}
