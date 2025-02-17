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
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class XiangqiGUI {
    private Usuario jugadorActual;  // Usar Usuario en lugar de String
    private List<Usuario> oponentes;
    private JLabel turnLabel;
    private JPanel boardPanel;
    private JButton[][] boardButtons;
    private static final int ROWS = 10;
    private static final int COLS = 9;
    private String turnoActual;
    private int puntosJugadorRojo = 0;
    private int puntosJugadorNegro = 0;

    public XiangqiGUI(Usuario jugadorActual, List<Usuario> oponentes) {
        this.jugadorActual = jugadorActual;
        this.oponentes = oponentes;
        this.oponentes.remove(jugadorActual);  // Eliminar el jugador actual de los oponentes disponibles

        turnoActual = "Rojo";  // Suponiendo que "Rojo" es el jugador inicial
        initializeGame();
    }

    private void initializeGame() {
        JFrame frame = new JFrame("Xiangqi - Ajedrez Chino");
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        boardButtons = new JButton[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                boardButtons[row][col] = new JButton();
                boardButtons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                boardButtons[row][col].setFocusPainted(false);
                final int r = row, c = col;
                boardButtons[row][col].addActionListener(e -> handleCellClick(r, c));
                boardPanel.add(boardButtons[row][col]);
            }
        }

        setupInitialPieces();

        turnLabel = new JLabel("Turno de: " + jugadorActual.getNombre() + " (Rojo)");
        frame.add(turnLabel, BorderLayout.NORTH);

        JButton surrenderButton = new JButton("Rendirse");
        surrenderButton.addActionListener(e -> handleSurrender(frame));
        frame.add(surrenderButton, BorderLayout.SOUTH);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void setupInitialPieces() {
        // Inicializa las piezas (esto es solo un ejemplo)
        boardButtons[0][0].setText("♜");
        boardButtons[0][1].setText("♞");
        boardButtons[0][2].setText("♝");
        boardButtons[0][3].setText("♛");
        boardButtons[0][4].setText("♚");
        boardButtons[0][5].setText("♝");
        boardButtons[0][6].setText("♞");
        boardButtons[0][7].setText("♖");

        // Agregar más piezas según el juego de Xiangqi
    }

    private void handleCellClick(int row, int col) {
        // Manejar el clic en una celda (mueve la pieza si es válido)
        if (!boardButtons[row][col].getText().isEmpty()) {
            highlightValidMoves(row, col);
        }
    }

    private void highlightValidMoves(int row, int col) {
        clearHighlights();
        // Resaltar movimientos válidos (esto es solo un ejemplo, ajusta según el juego)
        if (row - 1 >= 0) boardButtons[row - 1][col].setBackground(Color.GREEN);
        if (row + 1 < ROWS) boardButtons[row + 1][col].setBackground(Color.GREEN);
        if (col - 1 >= 0) boardButtons[row][col - 1].setBackground(Color.GREEN);
        if (col + 1 < COLS) boardButtons[row][col + 1].setBackground(Color.GREEN);
    }

    private void clearHighlights() {
        // Limpiar los resaltes de las celdas
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                boardButtons[row][col].setBackground(null);
            }
        }
    }

    private void handleSurrender(JFrame frame) {
    // Manejar la rendición
    if (turnoActual.equals("Jugador Rojo")) {
        // Si el jugador rojo se rinde, el jugador negro gana
        puntosJugadorNegro += 3; // El jugador negro recibe 3 puntos
        JOptionPane.showMessageDialog(frame, "Jugador Rojo se ha rendido. Gana el Jugador Negro!", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
    } else {
        // Si el jugador negro se rinde, el jugador rojo gana
        puntosJugadorRojo += 3; // El jugador rojo recibe 3 puntos
        JOptionPane.showMessageDialog(frame, "Jugador Negro se ha rendido. Gana el Jugador Rojo!", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
    }

    // Cerrar la ventana de juego
    frame.dispose();

    // Regresar al menú principal con el jugador actual
    new Main(jugadorActual); // Se pasa el jugador actual con los puntos actualizados
}


    private void showWinner(boolean redWins, JFrame frame) {
        // Mostrar el ganador y regresar al menú principal
        JOptionPane.showMessageDialog(frame, (redWins ? "Jugador Rojo" : "Jugador Negro") + " ha ganado!", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();  // Cerrar la ventana de juego
        new Main(jugadorActual);  // Regresar al menú principal
    }
    
}
