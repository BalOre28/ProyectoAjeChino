/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Balto
 */
public class XiangqiGUI extends JFrame {
    private static final int ROWS = 10;
    private static final int COLS = 9;
    private JButton[][] boardButtons = new JButton[ROWS][COLS];
    private boolean isRedTurn = true; // Control de turnos
    private JButton selectedPiece = null; // Pieza seleccionada
    private List<JButton> highlightedMoves = new ArrayList<>();
    private int redScore = 0;
    private int blackScore = 0;

    public XiangqiGUI() {
        setTitle("Xiangqi (Ajedrez Chino)");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(ROWS, COLS));
        initializeButtons(boardPanel);
        placePieces();

        JButton surrenderButton = new JButton("Rendirse");
        surrenderButton.addActionListener(e -> surrender());

        add(boardPanel, BorderLayout.CENTER);
        add(surrenderButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void initializeButtons(JPanel panel) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                final int r = row;
                final int c = col;

                JButton button = new JButton();
                button.setFont(new Font("Serif", Font.BOLD, 28));
                button.addActionListener(e -> handleCellClick(r, c));

                if (row == 4) {
                    button.setBackground(new Color(173, 216, 230)); // Río
                } else {
                    button.setBackground(Color.LIGHT_GRAY);
                }

                boardButtons[row][col] = button;
                panel.add(button);
            }
        }
    }

    private void placePieces() {
        // Negras
        String[] blackPieces = {"♜", "♞", "⛉", "♝", "♚", "♝", "⛉", "♞", "♜"};
        for (int i = 0; i < COLS; i++) {
            boardButtons[0][i].setText(blackPieces[i]);
        }
        boardButtons[2][0].setText("♟");
        boardButtons[2][2].setText("♟");
        boardButtons[2][4].setText("♟");
        boardButtons[2][6].setText("♟");
        boardButtons[2][8].setText("♟");

        // Rojas
        String[] redPieces = {"♖", "♘", "⛊", "♗", "♔", "♗", "⛊", "♘", "♖"};
        for (int i = 0; i < COLS; i++) {
            boardButtons[9][i].setText(redPieces[i]);
        }
        boardButtons[7][0].setText("♙");
        boardButtons[7][2].setText("♙");
        boardButtons[7][4].setText("♙");
        boardButtons[7][6].setText("♙");
        boardButtons[7][8].setText("♙");

        revalidate();
        repaint();
    }

    private void handleCellClick(int row, int col) {
        JButton clickedButton = boardButtons[row][col];

        if (selectedPiece == null) {
            if (!clickedButton.getText().isEmpty() && isCurrentPlayerPiece(clickedButton)) {
                selectedPiece = clickedButton;
                highlightValidMoves(row, col);
            }
        } else {
            resetHighlightedMoves();

            if (clickedButton.getText().isEmpty() || !isSameTeam(selectedPiece, clickedButton)) {
                clickedButton.setText(selectedPiece.getText());
                selectedPiece.setText("");
                selectedPiece.setBackground(Color.LIGHT_GRAY);
                selectedPiece = null;

                checkVictory();

                isRedTurn = !isRedTurn; // Cambiar turno
            } else {
                selectedPiece.setBackground(Color.LIGHT_GRAY);
                selectedPiece = null;
            }
        }
    }

   private void highlightValidMoves(int row, int col) {
    resetHighlightedMoves();
    selectedPiece.setBackground(Color.GREEN);

    String piece = selectedPiece.getText();
    
    switch (piece) {
        case "♔": // General
        case "♚":
            highlightGeneralMoves(row, col);
            break;
        case "♗": // Consejero
        case "♝":
            highlightAdvisorMoves(row, col);
            break;
        case "♖": // Carro
        case "♜":
            highlightRookMoves(row, col);
            break;
        case "♘": // Caballo
        case "♞":
            highlightHorseMoves(row, col);
            break;
        case "⛊": // Elefante
        case "⛉":
            highlightElephantMoves(row, col);
            break;
        case "♙": // Soldado
        case "♟":
            highlightSoldierMoves(row, col);
            break;
        case "⚒": // Cañón
        case "🛠️":
            highlightCannonMoves(row, col);
            break;
    }
}

    private void highlightGeneralMoves(int row, int col) {
        // General solo se mueve dentro del palacio (3x3 en el centro)
        if (row >= 7 || row <= 2) {
            // Movimiento vertical
            if (row > 0 && boardButtons[row - 1][col].getText().isEmpty()) {
                boardButtons[row - 1][col].setBackground(Color.GREEN);
            }
            if (row < 9 && boardButtons[row + 1][col].getText().isEmpty()) {
                boardButtons[row + 1][col].setBackground(Color.GREEN);
            }

            // Movimiento horizontal
            if (col < 8 && boardButtons[row][col + 1].getText().isEmpty()) {
                boardButtons[row][col + 1].setBackground(Color.GREEN);
            }
            if (col > 0 && boardButtons[row][col - 1].getText().isEmpty()) {
                boardButtons[row][col - 1].setBackground(Color.GREEN);
            }
        }
    }

    private void highlightAdvisorMoves(int row, int col) {
        // El consejero solo se mueve dentro de su palacio en diagonal
        if (row >= 7 || row <= 2) {
            if (row > 0 && col > 0 && boardButtons[row - 1][col - 1].getText().isEmpty()) {
                boardButtons[row - 1][col - 1].setBackground(Color.GREEN);
            }
            if (row > 0 && col < 8 && boardButtons[row - 1][col + 1].getText().isEmpty()) {
                boardButtons[row - 1][col + 1].setBackground(Color.GREEN);
            }
            if (row < 9 && col > 0 && boardButtons[row + 1][col - 1].getText().isEmpty()) {
                boardButtons[row + 1][col - 1].setBackground(Color.GREEN);
            }
            if (row < 9 && col < 8 && boardButtons[row + 1][col + 1].getText().isEmpty()) {
                boardButtons[row + 1][col + 1].setBackground(Color.GREEN);
            }
        }
    }

    private void highlightRookMoves(int row, int col) {
        // El carro se mueve horizontal o verticalmente sin límite, pero no puede cruzar otras piezas
        for (int r = row - 1; r >= 0; r--) {
            if (boardButtons[r][col].getText().isEmpty()) {
                boardButtons[r][col].setBackground(Color.GREEN);
            } else {
                break;
            }
        }

        for (int r = row + 1; r < ROWS; r++) {
            if (boardButtons[r][col].getText().isEmpty()) {
                boardButtons[r][col].setBackground(Color.GREEN);
            } else {
                break;
            }
        }

        for (int c = col - 1; c >= 0; c--) {
            if (boardButtons[row][c].getText().isEmpty()) {
                boardButtons[row][c].setBackground(Color.GREEN);
            } else {
                break;
            }
        }

        for (int c = col + 1; c < COLS; c++) {
            if (boardButtons[row][c].getText().isEmpty()) {
                boardButtons[row][c].setBackground(Color.GREEN);
            } else {
                break;
            }
        }
    }

    private void highlightHorseMoves(int row, int col) {
        // El caballo se mueve en forma de L (dos casillas en una dirección y luego una casilla en otra)
        int[][] moves = {
            {row - 2, col - 1}, {row - 2, col + 1}, {row + 2, col - 1}, {row + 2, col + 1},
            {row - 1, col - 2}, {row - 1, col + 2}, {row + 1, col - 2}, {row + 1, col + 2}
        };

        for (int[] move : moves) {
            int r = move[0];
            int c = move[1];

            if (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
                if (boardButtons[r][c].getText().isEmpty()) {
                    boardButtons[r][c].setBackground(Color.GREEN);
                }
            }
        }
    }

    private void highlightElephantMoves(int row, int col) {
        // El elefante se mueve dos casillas en diagonal, pero no puede cruzar el río
        int[][] moves = {
            {row - 2, col - 2}, {row - 2, col + 2}, {row + 2, col - 2}, {row + 2, col + 2}
        };

        for (int[] move : moves) {
            int r = move[0];
            int c = move[1];

            if (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
                if (boardButtons[r][c].getText().isEmpty()) {
                    boardButtons[r][c].setBackground(Color.GREEN);
                }
            }
        }
    }

    private void highlightSoldierMoves(int row, int col) {
        // El soldado avanza una casilla hacia adelante, y después de cruzar el río, también se mueve lateralmente
        if (isRedPiece(selectedPiece) && row > 4 || !isRedPiece(selectedPiece) && row < 5) {
            if (row > 0 && boardButtons[row - 1][col].getText().isEmpty()) {
                boardButtons[row - 1][col].setBackground(Color.GREEN);
            }
        }

        if (isRedPiece(selectedPiece) && row > 4 || !isRedPiece(selectedPiece) && row < 5) {
            if (col > 0 && boardButtons[row][col - 1].getText().isEmpty()) {
                boardButtons[row][col - 1].setBackground(Color.GREEN);
            }
            if (col < COLS - 1 && boardButtons[row][col + 1].getText().isEmpty()) {
                boardButtons[row][col + 1].setBackground(Color.GREEN);
            }
        }
    }

    private void highlightCannonMoves(int row, int col) {
        // Movimiento horizontal
        for (int c = col - 1; c >= 0; c--) {
            if (boardButtons[row][c].getText().isEmpty()) {
                boardButtons[row][c].setBackground(Color.GREEN);
            } else {
                // Verificar si hay exactamente una pieza en el camino
                for (int i = c - 1; i >= 0; i--) {
                    if (boardButtons[row][i].getText().isEmpty()) {
                        break;
                    }
                    if (!boardButtons[row][i].getText().isEmpty() && !boardButtons[row][i].getText().equals(boardButtons[row][col].getText())) {
                        boardButtons[row][i].setBackground(Color.GREEN);
                        break;
                    }
                }
                break;
            }
        }

        for (int c = col + 1; c < COLS; c++) {
            if (boardButtons[row][c].getText().isEmpty()) {
                boardButtons[row][c].setBackground(Color.GREEN);
            } else {
                // Verificar si hay exactamente una pieza en el camino
                for (int i = c + 1; i < COLS; i++) {
                    if (boardButtons[row][i].getText().isEmpty()) {
                        break;
                    }
                    if (!boardButtons[row][i].getText().isEmpty() && !boardButtons[row][i].getText().equals(boardButtons[row][col].getText())) {
                        boardButtons[row][i].setBackground(Color.GREEN);
                        break;
                    }
                }
                break;
            }
        }

        // Movimiento vertical
        for (int r = row - 1; r >= 0; r--) {
            if (boardButtons[r][col].getText().isEmpty()) {
                boardButtons[r][col].setBackground(Color.GREEN);
            } else {
                // Verificar si hay exactamente una pieza en el camino
                for (int i = r - 1; i >= 0; i--) {
                    if (boardButtons[i][col].getText().isEmpty()) {
                        break;
                    }
                    if (!boardButtons[i][col].getText().isEmpty() && !boardButtons[i][col].getText().equals(boardButtons[row][col].getText())) {
                        boardButtons[i][col].setBackground(Color.GREEN);
                        break;
                    }
                }
                break;
            }
        }

        for (int r = row + 1; r < ROWS; r++) {
            if (boardButtons[r][col].getText().isEmpty()) {
                boardButtons[r][col].setBackground(Color.GREEN);
            } else {
                // Verificar si hay exactamente una pieza en el camino
                for (int i = r + 1; i < ROWS; i++) {
                    if (boardButtons[i][col].getText().isEmpty()) {
                        break;
                    }
                    if (!boardButtons[i][col].getText().isEmpty() && !boardButtons[i][col].getText().equals(boardButtons[row][col].getText())) {
                        boardButtons[i][col].setBackground(Color.GREEN);
                        break;
                    }
                }
                break;
            }
        }
    }

   private void resetHighlightedMoves() {
    // Recorremos todas las celdas del tablero y restablecemos su fondo al color predeterminado
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 9; j++) {
            if (boardButtons[i][j].getText().isEmpty()) {
                boardButtons[i][j].setBackground(null); // Vuelve al color original si está vacío
            } else {
                boardButtons[i][j].setBackground(Color.LIGHT_GRAY); // Cambiar al color predeterminado si tiene una pieza
            }
        }
    }
}

    private boolean isSameTeam(JButton piece1, JButton piece2) {
        return (isRedPiece(piece1) && isRedPiece(piece2)) || (isBlackPiece(piece1) && isBlackPiece(piece2));
    }

    private boolean isRedPiece(JButton button) {
        String text = button.getText();
        return text.equals("♖") || text.equals("♘") || text.equals("⛊") || text.equals("♗") || text.equals("♔") || text.equals("♙");
    }

    private boolean isBlackPiece(JButton button) {
        String text = button.getText();
        return text.equals("♜") || text.equals("♞") || text.equals("⛉") || text.equals("♝") || text.equals("♚") || text.equals("♟");
    }

    private boolean isCurrentPlayerPiece(JButton button) {
        return (isRedTurn && isRedPiece(button)) || (!isRedTurn && isBlackPiece(button));
    }

    private void checkVictory() {
        boolean redKingAlive = false;
        boolean blackKingAlive = false;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (boardButtons[row][col].getText().equals("♔")) {
                    redKingAlive = true;
                }
                if (boardButtons[row][col].getText().equals("♚")) {
                    blackKingAlive = true;
                }
            }
        }

        if (!redKingAlive) {
            blackScore += 3;
            showWinner(false);
        } else if (!blackKingAlive) {
            redScore += 3;
            showWinner(true);
        }
    }

    private void showWinner(boolean redWins) {
        JOptionPane.showMessageDialog(this,
                (redWins ? "Jugador Rojo" : "Jugador Negro") + " ha ganado!\nPuntuación:\nRojo: " + redScore + " - Negro: " + blackScore,
                "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        returnToMenu();
    }

    private void surrender() {
        if (isRedTurn) {
            blackScore += 3;
            JOptionPane.showMessageDialog(this,
                    "Jugador Rojo se ha rendido.\nGana el Jugador Negro!\nPuntuación:\nRojo: " + redScore + " - Negro: " + blackScore,
                    "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        } else {
            redScore += 3;
            JOptionPane.showMessageDialog(this,
                    "Jugador Negro se ha rendido.\nGana el Jugador Rojo!\nPuntuación:\nRojo: " + redScore + " - Negro: " + blackScore,
                    "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        }
        returnToMenu();
    }
    
    private void returnToMenu() {
        this.dispose(); // Cierra la ventana del juego
    }
}
