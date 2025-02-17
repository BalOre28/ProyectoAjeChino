/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.List;

/**
 *
 * @author Balto
 */
public class LoginRecursivoGUI extends JFrame {
    private JTextField txtNombre;
    private JPasswordField txtPassword;
    protected static List<Usuario> listaUsuarios = new ArrayList<>();
    private int intentos = 3;

    public LoginRecursivoGUI() {
        setTitle("Login");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        txtNombre = new JTextField();
        txtPassword = new JPasswordField();

        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnRegistro = new JButton("Crear Usuario");
        JButton btnSalir = new JButton("Salir");

        add(new JLabel("Nombre de Usuario:"));
        add(txtNombre);
        add(new JLabel("Contraseña:"));
        add(txtPassword);
        add(btnLogin);
        add(btnRegistro);
        add(btnSalir);

        btnLogin.addActionListener(e -> loginRecursivo());
        btnRegistro.addActionListener(e -> new RegistroUsuarioGUI(listaUsuarios).setVisible(true));
        btnSalir.addActionListener(e -> System.exit(0));
}

    private void loginRecursivo() {
        String nombre = txtNombre.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (nombre.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puedes dejar campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equals(nombre) && u.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Bienvenido, " + nombre);
                new Main(u).setVisible(true);
                this.dispose();
                return;
            }
        }

        intentos--;
        if (intentos > 0) {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos. Intentos restantes: " + intentos, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Demasiados intentos fallidos. Cerrando...", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
