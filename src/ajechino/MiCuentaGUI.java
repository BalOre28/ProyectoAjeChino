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


public class MiCuentaGUI extends JFrame {
    private Usuario usuario;
    private JLabel lblNombre, lblEmail, lblPuntos;
    private JButton btnCambiarPassword, btnEliminarCuenta, btnCerrar;

    public MiCuentaGUI(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Mi Cuenta");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        lblNombre = new JLabel("Usuario: " + usuario.getNombre());
        lblPuntos = new JLabel("Puntos: " + usuario.getPuntos());

        btnCambiarPassword = new JButton("Cambiar Password");
        btnEliminarCuenta = new JButton("Eliminar Cuenta");
        btnCerrar = new JButton("Cerrar");

        btnCambiarPassword.addActionListener(e -> cambiarPassword());
        btnEliminarCuenta.addActionListener(e -> eliminarCuenta());
        btnCerrar.addActionListener(e -> dispose());

        add(lblNombre);
        add(lblPuntos);
        add(btnCambiarPassword);
        add(btnEliminarCuenta);
        add(btnCerrar);
    }

    private void cambiarPassword() {
        String actual = JOptionPane.showInputDialog(this, "Ingrese su contraseña actual:");
        if (actual == null || !actual.equals(usuario.getPassword())) {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nueva = JOptionPane.showInputDialog(this, "Ingrese la nueva contraseña (exactamente 5 caracteres):");
        if (nueva == null || nueva.length() != 5) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener exactamente 5 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuario.setPassword(nueva);
        JOptionPane.showMessageDialog(this, "Contraseña cambiada exitosamente.");
    }

 private void eliminarCuenta() {
    String passwordIngresada = JOptionPane.showInputDialog(this, "Ingrese su contraseña para confirmar:");

    if (passwordIngresada == null) return; // Si el usuario cancela, salir

    if (!passwordIngresada.equals(usuario.getPassword())) {
        JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Eliminar usuario de la lista global
    if (LoginRecursivoGUI.listaUsuarios.remove(usuario)) {
        JOptionPane.showMessageDialog(this, "Cuenta eliminada correctamente.");

        // Cerrar todas las ventanas abiertas
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                window.dispose(); // Cierra todas las ventanas JFrame
            }
        }

        // Volver al login
        new LoginRecursivoGUI().setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Error al eliminar la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


}


