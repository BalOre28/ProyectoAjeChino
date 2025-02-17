/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;

/**
 *
 * @author Balto
 */
public class Elephant extends Piece {
    public Elephant(int row, int col, boolean isRed) {
        super(row, col, isRed);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        if (Math.abs(newRow - row) != 2 || Math.abs(newCol - col) != 2) return false;
        if (isRed && newRow < 5) return false; // Rojo no cruza el río
        if (!isRed && newRow > 4) return false; // Negro no cruza el río
        int blockRow = (row + newRow) / 2;
        int blockCol = (col + newCol) / 2;
        return board[blockRow][blockCol] == null && (board[newRow][newCol] == null || board[newRow][newCol].isRed() != isRed);
    }

}

