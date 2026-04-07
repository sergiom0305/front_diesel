package co.edu.unipiloto.diesel_maps;

public class UserAccount {

    private String nombre;
    private String correo;
    private String usuario;
    private String contraseña;

    public UserAccount(String nombre, String correo, String usuario, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
}