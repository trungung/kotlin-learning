package games.game2048

import board.Cell
import board.GameBoard
import java.util.*

interface Game2048Initializer<T> {
    /*
     * Specifies the cell and the value that should be added to this cell.
     */
    fun nextValue(board: GameBoard<T?>): Pair<Cell, T>?
}

object RandomGame2048Initializer : Game2048Initializer<Int> {
    private val random = Random()
    private fun generateRandomStartValue(): Int =
            if (random.nextInt(10) == 9) 4 else 2

    /*
     * Generate a random value and a random cell (among free squareCells)
     * that given value should be added to.
     * The value should be 2 for 90% cases, and 4 for the rest of the cases.
     * Use the 'generateRandomStartValue' function above.
     * If the games.board is full return null.
     */
    override fun nextValue(board: GameBoard<Int?>): Pair<Cell, Int>? {

        val randomCell = getRandomCell(board)
        val randomValue = generateRandomStartValue()

        var pair: Pair<Cell, Int>? = null
        if (randomCell != null) {
            pair = Pair(randomCell, randomValue)
        }
        return pair
    }

    private fun getRandomCell(board: GameBoard<Int?>): Cell? {

        val emptyCells = board.getAllCells()
                .filter { board[it] == null }

        if (emptyCells.isEmpty()) {
            return null
        }
        val cell = emptyCells.shuffled(random).first()
        return cell
    }
}

//import board.Cell
//import board.GameBoard
//import kotlin.random.Random
//
//interface Game2048Initializer<T> {
//    /*
//     * Specifies the cell and the value that should be added to this cell.
//     */
//    fun nextValue(board: GameBoard<T?>): Pair<Cell, T>?
//}
//
//object RandomGame2048Initializer: Game2048Initializer<Int> {
//
//    private fun generateRandomStartValue(): Int =
//            if (Random.nextInt(10) == 9) 4 else 2
//
//    /*
//     * Generate a random value and a random cell among free cells
//     * that given value should be added to.
//     * The value should be 2 for 90% cases, and 4 for the rest of the cases.
//     * Use the 'generateRandomStartValue' function above.
//     * If the board is full return null.
//     *
//     *  Solution
//     *      1. Find only cells that are null.
//     *      2. Ensure that board is more than 1 space
//     *      3. If there is more than one blank, select one randomly and return with 2 or 4.
//     *      4. If space is zero, return null
//     */
//    override fun nextValue(board: GameBoard<Int?>): Pair<Cell, Int>? {
//        board.filter { it == null }
//                .takeIf {
//                    it.isNotEmpty()
//                }?.let {
//                    return it.random() to generateRandomStartValue()
//                }
//        return null
//    }
//}
