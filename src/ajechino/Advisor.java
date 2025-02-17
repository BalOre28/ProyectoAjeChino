/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;

/**
 *
 * @author Balto
 */
public class Advisor extends Piece {
    public Advisor(int row, int col, boolean isRed) {
        super(row, col, isRed);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        if (Math.abs(newRow - row) != 1 || Math.abs(newCol - col) != 1) return false;
        if (newCol < 3 || newCol > 5 || (isRed && newRow < 7) || (!isRed && newRow > 2)) return false;
        return board[newRow][newCol] == null || board[newRow][newCol].isRed() != isRed;
    }

}
