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

import javax.swing.*;

public class Main extends JFrame {

    private Usuario usuario;

    public Main(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Menú Principal");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton btnJugar = new JButton("Jugar Xiangqi");
        JButton btnMiCuenta = new JButton("Mi Cuenta");
        JButton btnReportes = new JButton("Reportes");
        JButton btnLogout = new JButton("Log Out");

        btnJugar.addActionListener(e -> iniciarPartida());
        btnMiCuenta.addActionListener(e -> new MiCuentaGUI(usuario).setVisible(true));
        btnReportes.addActionListener(e -> new ReportesGUI(usuario).setVisible(true));
        btnLogout.addActionListener(e -> cerrarSesion());

        add(btnJugar);
        add(btnMiCuenta);
        add(btnReportes);
        add(btnLogout);
    }

    private void iniciarPartida() {
        JOptionPane.showMessageDialog(this, "Iniciando partida de Xiangqi...");
        javax.swing.SwingUtilities.invokeLater(() -> new XiangqiGUI());
    }

    private void cerrarSesion() {
        JOptionPane.showMessageDialog(this, "Cerrando sesión...");
        this.dispose();
        new LoginRecursivoGUI().setVisible(true);
    }

    public static void main(String[] args) {
        // Iniciar la aplicación mostrando la pantalla de login
        SwingUtilities.invokeLater(() -> new LoginRecursivoGUI().setVisible(true));
    }
}
