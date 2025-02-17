/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;

/**
 *
 * @author Balto
 */
public class Cannon extends Piece {
    public Cannon(int row, int col, boolean isRed) {
        super(row, col, isRed);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        if (row != newRow && col != newCol) return false;

        int stepRow = Integer.compare(newRow, row);
        int stepCol = Integer.compare(newCol, col);
        int r = row + stepRow, c = col + stepCol;
        int count = 0;

        while (r != newRow || c != newCol) {
            if (board[r][c] != null) count++;
            r += stepRow;
            c += stepCol;
        }

        if (board[newRow][newCol] == null) return count == 0;
        return count == 1 && board[newRow][newCol].isRed() != isRed;
    }

}
