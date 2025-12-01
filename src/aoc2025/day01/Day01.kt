package aoc2025.day01

import common.benchmarkTime
import common.println
import common.readInput

fun main() {

    fun part1(input: List<String>): Int {
        var cursor = 50
        var decoy = 0

        val movements = getMovements(input)
        movements.forEach { movement->
            cursor = Math.floorMod(cursor + movement, 100)
            if (cursor == 0) {
                decoy++
            }
        }

        return decoy
    }

    fun part2(input: List<String>): Int {

        val movements = getMovements(input)
        var cursor = 50
        var decoy = 0

        movements.forEach { movement->
            val newCursor = cursor + movement

            if (newCursor >= 100) {
                decoy += Math.floorDiv(newCursor, 100)
            }
            if (newCursor < 0 && cursor > 0) {
                decoy++
            }
            if (newCursor < 0){
                decoy += Math.floorDiv(newCursor*-1, 100)
            }
            if (newCursor == 0) decoy++

            cursor = Math.floorMod(newCursor, 100)
        }

        return decoy
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("aoc2025/Day01_test")
    val part1Result = part1(testInput)
    part1Result.println()
    check(part1Result == 3)

    val part2Result = part2(testInput)
    part2Result.println()
    check(part2Result == 16)

    val input = readInput("aoc2025/Day01")
    benchmarkTime("part1") {
        part1(input)
    }

    benchmarkTime("part2") {
        part2(input)
    }
}

fun getMovements(input: List<String>): List<Int> {
    return input.map {
        when {
            it.startsWith("L") -> {
                it.substring(1).toInt()*-1
            }
            it.startsWith("R") -> {
                it.substring(1).toInt()
            }
            else -> {
                0
            }
        }
    }
}
