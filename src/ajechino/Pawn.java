/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;

/**
 *
 * @author Balto
 */
public class Pawn extends Piece {
    public Pawn(int row, int col, boolean isRed) {
        super(row, col, isRed);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        int forward = isRed ? -1 : 1;

        if (newRow == row + forward && newCol == col) return true; // Movimiento hacia adelante

        if (isRed && row < 5 || !isRed && row > 4) {
            return (newRow == row && Math.abs(newCol - col) == 1);
        }

        return false;
    }
}

