/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;
import javax.swing.*;
import java.util.List;
/**
 *
 * @author Balto
 */
public class Main extends JFrame {
    private Usuario usuario;

    public Main(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Menú Principal");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton btnNuevaPartida = new JButton("Nueva Partida");
        JButton btnMiCuenta = new JButton("Mi Cuenta");
        JButton btnReportes = new JButton("Reportes");
        JButton btnLogout = new JButton("Log Out");

        btnNuevaPartida.addActionListener(e -> {
            new SeleccionarOponenteGUI(this, usuario, LoginRecursivoGUI.listaUsuarios).setVisible(true);
        });

        btnMiCuenta.addActionListener(e -> new MiCuentaGUI(usuario).setVisible(true));
        btnReportes.addActionListener(e -> new ReportesGUI(usuario).setVisible(true));
        btnLogout.addActionListener(e -> {
            dispose(); // Cierra el menú principal antes de regresar al login
            new LoginRecursivoGUI().setVisible(true);
        });

        add(btnNuevaPartida);
        add(btnMiCuenta);
        add(btnReportes);
        add(btnLogout);
        
          JLabel puntosLabel = new JLabel("Puntos: " + usuario.getPuntos());
        add(puntosLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginRecursivoGUI().setVisible(true));
    }
}

