package modelo;

import java.sql.*;

public class AdministradorDAO {

    public Administrador validarCredenciales(String usuario, String contrasena) {
        String sql = "SELECT * FROM administradores WHERE usuario = ? AND contrasena = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario);
            pstmt.setString(2, contrasena);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Administrador(
                        rs.getInt("id_admin"),
                        rs.getString("usuario"),
                        rs.getString("contrasena")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
