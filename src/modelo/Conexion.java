package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    Connection conectar = null;

    String usuario = "postgres";
    String contraseña = "admin";
    String bd = "bd_ejemplo";
    String ip = "localhost";
    String puerto = "5432";

    String cadena = "jdbc:postgresql://" + ip + ":" + puerto + "/" + bd;

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            JOptionPane.showMessageDialog(null, "Se conectó correctamente a la Base de Datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos, error: " + e.toString());
        }
        return conectar;
    }
}