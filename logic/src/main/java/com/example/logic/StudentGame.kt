package com.example.logic
import com.example.library.GameInterface
import java.lang.IllegalArgumentException

class StudentGame (columns: Int = 7, rows: Int = 10) : GameInterface {
    override val mColumns: Int = columns
    override val mRows: Int = rows
    // Set up two-dimensional array of integer, of size columns x rows
    // And fill it with zeros
    override var mData: Array<IntArray> = Array(columns) { IntArray(rows, {0}) }

    // Not specified in the interface - just to show that other things can be added
    var playerTurn: Int = 1

    init {
        // Place a couple of random tokens to test the user interface

        // mData[5][5] = 1
        // mData[6][3] = 2
    }

    override fun getToken(column: Int, row: Int): Int {
        return mData[column][row]
    }

    //Changes the contents of the game board at a specified row and column

    override fun playToken(column: Int, player: Int): Boolean {
        if (player <= 0) {
            throw IllegalArgumentException("Player numbers start with 1")
        }

        if (playerTurn == 1)
            playerTurn = 2
        else
            playerTurn = 1

        for (row in 0 until mRows) {
            if (mData[column][row] === 0) {
                mData[column][row] = player
                return true
            }
        }
        return false // illegal move
    }
}
