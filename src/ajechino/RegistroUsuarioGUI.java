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
import java.util.List;

public class RegistroUsuarioGUI extends JFrame {
    private JTextField txtNombre;
    private JPasswordField txtPassword;
    private JButton btnRegistrar, btnCancelar;
    private List<Usuario> listaUsuarios;

    public RegistroUsuarioGUI(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        setTitle("Registro de Usuario");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        txtNombre = new JTextField();
        txtPassword = new JPasswordField();

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");

        add(new JLabel("Nombre de Usuario:"));
        add(txtNombre);
        add(new JLabel("Contraseña (5 caracteres):"));
        add(txtPassword);
        add(btnRegistrar);
        add(btnCancelar);

        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (nombre.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puedes dejar campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() != 5) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener exactamente 5 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre)) {
                JOptionPane.showMessageDialog(this, "Este usuario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        listaUsuarios.add(new Usuario(nombre, password,0));
        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
        dispose();
    }
}
