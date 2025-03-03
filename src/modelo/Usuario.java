package modelo;

public class Usuario {
    int id;
    String usuario;
    String contra;
    String nombre;
    String rol;

    public Usuario() {
    }

    public Usuario(int id, String user, String contra, String nombre, String rol) {
        this.id = id;
        this.usuario = user;
        this.contra = contra;
        this.nombre = nombre;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return usuario;
    }

    public void setUser(String user) {
        this.usuario = user;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}