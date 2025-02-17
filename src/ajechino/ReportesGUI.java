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

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ReportesGUI extends JFrame {
    private Usuario usuario;

    public ReportesGUI(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Reportes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton btnRanking = new JButton("Ranking de Jugadores");
        JButton btnLogs = new JButton("Logs de mis últimos juegos");
        JButton btnCerrar = new JButton("Cerrar");

        btnRanking.addActionListener(e -> mostrarRanking());
        btnLogs.addActionListener(e -> mostrarLogs());
        btnCerrar.addActionListener(e -> dispose());

        add(btnRanking);
        add(btnLogs);
        add(btnCerrar);
    }

    private void mostrarRanking() {
        List<Usuario> ranking = LoginRecursivoGUI.listaUsuarios.stream()
                .sorted((u1, u2) -> Integer.compare(u2.getPuntos(), u1.getPuntos()))
                .collect(Collectors.toList());

        StringBuilder mensaje = new StringBuilder("Ranking de Jugadores:\n");
        int posicion = 1;
        for (Usuario u : ranking) {
            mensaje.append(posicion++).append(". ").append(u.getNombre()).append(" - ").append(u.getPuntos()).append(" puntos\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Ranking", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarLogs() {
        List<String> logs = usuario.getLogs();

        if (logs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay logs disponibles.", "Logs", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder mensaje = new StringBuilder("Logs de tus últimos juegos:\n");
        for (String log : logs) {
            mensaje.append(log).append("\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Logs", JOptionPane.INFORMATION_MESSAGE);
    }
}
