/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;

/**
 *
 * @author Balto
 */
public class Knight extends Piece {
    public Knight(int row, int col, boolean isRed) {
        super(row, col, isRed);
    }

    @Override
    public boolean isValidMove(int newRow, int newCol, Piece[][] board) {
        int dx = Math.abs(newRow - row);
        int dy = Math.abs(newCol - col);

        // Movimiento en "L", pero verificando bloqueo
        if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)) {
            int blockRow = row + (newRow - row) / 2;
            int blockCol = col + (newCol - col) / 2;
            if (dx == 2 && board[blockRow][col] != null) return false;
            if (dy == 2 && board[row][blockCol] != null) return false;
            return board[newRow][newCol] == null || board[newRow][newCol].isRed() != isRed;
        }
        return false;
    }
}

