package modelo;

public class Administrador {
    private int idAdmin;
    private String usuario;
    private String contrasena;

    public Administrador(int idAdmin, String usuario, String contrasena) {
        this.idAdmin = idAdmin;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public int getIdAdmin() { return idAdmin; }
    public void setIdAdmin(int idAdmin) { this.idAdmin = idAdmin; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
