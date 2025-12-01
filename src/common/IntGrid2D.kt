package common

class IntGrid2D(
    input: List<String>
) {
    /*val width: Int
        get() = grid.width
    val height: Int
        get() = grid.height

    private val grid: Grid2D<Int> = Grid2D(input) { char ->
        char.digitToIntOrNull() ?: throw IllegalArgumentException("Invalid character '$char' in input")
    }

    companion object {
        fun from(input: List<String>): IntGrid2D {
            return IntGrid2D(input)
        }
    }

    fun getNeighbors(x: Int, y: Int): List<Item<Int>> {
        grid.setPosition(x, y)
        return grid.getNeighbors()
    }

    fun getColumn(x: Int): List<Item<Int>> {
        return grid.getColumn(x)
    }

    fun getRow(y: Int): List<Item<Int>> {
        return grid.getRow(y)
    }

    fun getCurrentElement(): Int? = grid.getCurrentElement()

    fun getElementAt(x: Int, y: Int): Int? = grid.getElementAt(x, y)

    fun move(direction: Direction):Boolean = grid.move(direction)

    fun traverse(
        fromX: Int, fromY: Int,
        toX: Int, toY: Int,
        action: (Int, Int, Int) -> Unit
    ) = grid.traverse(fromX, fromY, toX, toY, action)

    fun getCurrentPosition(): Pair<Int, Int> = grid.getCurrentPosition()

    fun setPosition(x: Int, y: Int) = grid.setPosition(x, y)
*/
}