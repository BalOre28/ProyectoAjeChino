/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ajechino;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Balto
 */
public class Usuario {
    private String nombre;
    private String password;
    private int puntos;
    private List<String> logs;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.puntos = 0;
        this.logs = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPassword(String nuevaPassword) {
        this.password = nuevaPassword;
    }

    public void incrementarPuntos(int puntosGanados) {
        this.puntos += puntosGanados;
    }

    public void agregarLog(String log) {
        logs.add(0, log); // Agregar en la primera posición para mantener orden reciente
    }

    public List<String> getLogs() {
        return logs;
    }
}



