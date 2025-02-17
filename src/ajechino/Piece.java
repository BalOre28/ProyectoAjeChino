/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;

/**
 *
 * @author Balto
 */
public abstract class Piece {
    protected int row, col;
    protected boolean isRed;

    public Piece(int row, int col, boolean isRed) {
        this.row = row;
        this.col = col;
        this.isRed = isRed;
    }

    public boolean isRed() {
        return isRed;
    }

    public abstract boolean isValidMove(int newRow, int newCol, Piece[][] board);

    public boolean moveTo(int newRow, int newCol, Piece[][] board) {
        if (isValidMove(newRow, newCol, board)) {
            board[row][col] = null;
            row = newRow;
            col = newCol;
            board[row][col] = this;
            return true;
        }
        return false;
    }
}


