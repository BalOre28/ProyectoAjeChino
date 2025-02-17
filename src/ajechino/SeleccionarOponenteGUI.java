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
import java.util.ArrayList;
import java.util.List;

public class SeleccionarOponenteGUI extends JDialog {
    private JComboBox<String> comboOponentes;
    private JButton btnIniciar, btnCancelar;
    private Usuario jugadorActual;
    private List<Usuario> listaUsuarios;

    public SeleccionarOponenteGUI(JFrame parent, Usuario jugadorActual, List<Usuario> listaUsuarios) {
        super(parent, "Seleccionar Oponente", true);
        this.jugadorActual = jugadorActual;
        this.listaUsuarios = listaUsuarios;

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 1));

        comboOponentes = new JComboBox<>();

        for (Usuario u : listaUsuarios) {
            if (!u.getNombre().equals(jugadorActual.getNombre())) {
                comboOponentes.addItem(u.getNombre());
            }
        }

        if (comboOponentes.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay oponentes disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        btnIniciar = new JButton("Iniciar Partida");
        btnCancelar = new JButton("Cancelar");

        btnIniciar.addActionListener(e -> iniciarPartida());
        btnCancelar.addActionListener(e -> dispose());

        add(new JLabel("Selecciona un oponente:"));
        add(comboOponentes);
        add(btnIniciar);
        add(btnCancelar);
    }

private void iniciarPartida() {
    // Obtener el oponente seleccionado
    String oponenteSeleccionado = (String) comboOponentes.getSelectedItem();
    if (oponenteSeleccionado != null) {
        // Convertir el nombre del oponente seleccionado a un objeto Usuario
        Usuario oponenteUsuario = null;
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equals(oponenteSeleccionado)) {
                oponenteUsuario = u;
                break;
            }
        }
        
        if (oponenteUsuario != null) {
            // Aquí pasas el jugadorActual y el oponente como objetos Usuario
            List<Usuario> oponentes = new ArrayList<>();
            oponentes.add(oponenteUsuario); // Agregar el oponente seleccionado

            new XiangqiGUI(jugadorActual, oponentes); // Pasar la lista de oponentes
            dispose();
        }
    }
  }
}

