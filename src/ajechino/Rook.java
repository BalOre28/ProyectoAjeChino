/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;

/**
 *
 * @author Balto
 */
public class Rook extends Piece {
    public Rook(int row, int col, boolean isRed) {
        super(row, col, isRed);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        if (row != newRow && col != newCol) return false;
        int stepRow = Integer.compare(newRow, row);
        int stepCol = Integer.compare(newCol, col);
        int r = row + stepRow, c = col + stepCol;
        while (r != newRow || c != newCol) {
            if (board[r][c] != null) return false;
            r += stepRow;
            c += stepCol;
        }
        return board[newRow][newCol] == null || board[newRow][newCol].isRed() != isRed;
    }
}

