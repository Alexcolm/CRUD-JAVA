package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Usuario u = new Usuario();


    public List listar() {
        List<Usuario> datos = new ArrayList<>();
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement("select * from usuario");
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt(1));
                u.setUser(rs.getString(2));
                u.setContra(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setRol(rs.getString(5));
                datos.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Agregar manejo de excepciones
        }
        return datos;
    }

    // Agregar un nuevo usuario
    public int agregar(Usuario user) {  
        int r = 0;
        String sql = "INSERT INTO usuario(user, contra, nombre, rol) VALUES(?, ?, ?, ?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);            
            ps.setString(1, user.getUser());
            ps.setString(2, user.getContra());
            ps.setString(3, user.getNombre());
            ps.setString(4, user.getRol());
            r = ps.executeUpdate();    
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();  // Agregar manejo de excepciones
        }  
        return r;
    }


    public int actualizar(Usuario user) {  
        int r = 0;
        String sql = "UPDATE usuario SET user=?, contra=?, nombre=?, rol=? WHERE id=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);            
            ps.setString(1, user.getUser());
            ps.setString(2, user.getContra());
            ps.setString(3, user.getNombre());
            ps.setString(4, user.getRol());
            ps.setInt(5, user.getId());
            r = ps.executeUpdate();    
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
           
        }  
        return r;
    }


    public int eliminar(int id) {
        int r = 0;
        String sql = "DELETE FROM usuario WHERE id=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeUpdate();
        } catch (Exception e) {
           
        }
        return r;
    }
}
