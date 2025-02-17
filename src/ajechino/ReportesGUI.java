/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;

/**
 *
 * @author Balto
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ReportesGUI extends JFrame {

    static void eliminarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private Usuario usuarioActual;
    private static List<Jugador> rankingJugadores = new ArrayList<>();
    private static List<String> logsJuegos = new ArrayList<>();

    public ReportesGUI(Usuario usuario) {
        this.usuarioActual = usuario;

        setTitle("Reportes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JButton btnRanking = new JButton("Ver Ranking de Jugadores");
        JButton btnLogs = new JButton("Ver Mis Últimos Partidos");
        JButton btnCerrar = new JButton("Cerrar");

        btnRanking.addActionListener(e -> mostrarRanking());
        btnLogs.addActionListener(e -> mostrarLogs());
        btnCerrar.addActionListener(e -> dispose());

        add(btnRanking);
        add(btnLogs);
        add(btnCerrar);
    }

    // Método para mostrar el ranking de jugadores
    private void mostrarRanking() {
        rankingJugadores.sort(Comparator.comparingInt(Jugador::getPuntos).reversed());

        StringBuilder rankingTexto = new StringBuilder("POSICIÓN - USERNAME - PUNTOS\n");
        for (int i = 0; i < rankingJugadores.size(); i++) {
            Jugador jugador = rankingJugadores.get(i);
            rankingTexto.append((i + 1)).append(". ").append(jugador.getNombre())
                    .append(" - ").append(jugador.getPuntos()).append(" pts\n");
        }

        JOptionPane.showMessageDialog(this, rankingTexto.toString(), "Ranking de Jugadores", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para mostrar los logs de los últimos juegos del usuario logueado
    private void mostrarLogs() {
        List<String> logsUsuario = new ArrayList<>();
        for (String log : logsJuegos) {
            if (log.contains(usuarioActual.getNombre())) {
                logsUsuario.add(log);
            }
        }

        Collections.reverse(logsUsuario); // Ordenar del más reciente al más antiguo

        StringBuilder logsTexto = new StringBuilder("Mis Últimos Partidos:\n");
        for (String log : logsUsuario) {
            logsTexto.append(log).append("\n");
        }

        JOptionPane.showMessageDialog(this, logsTexto.toString(), "Logs de Últimos Juegos", JOptionPane.INFORMATION_MESSAGE);
    }

    // Métodos para actualizar el ranking y logs
    public static void agregarJugadorAlRanking(Jugador jugador) {
        rankingJugadores.add(jugador);
    }

    public static void actualizarPuntaje(String nombreJugador, int puntos) {
        for (Jugador jugador : rankingJugadores) {
            if (jugador.getNombre().equals(nombreJugador)) {
                jugador.setPuntos(jugador.getPuntos() + puntos);
                return;
            }
        }
    }

    public static void agregarLog(String log) {
        logsJuegos.add(log);
    }

    public static void eliminarJugadorDelRanking(String nombre) {
        rankingJugadores.removeIf(jugador -> jugador.getNombre().equals(nombre));
    }

    public static void eliminarLogsDeUsuario(String nombre) {
        logsJuegos.removeIf(log -> log.contains(nombre));
    }
}
