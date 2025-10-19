package evidencia.felipe.sena.dao.jdbc;

import evidencia.felipe.sena.config.ConnectionManager;
import evidencia.felipe.sena.dao.UsuarioDao;
import evidencia.felipe.sena.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioJdbcDao implements UsuarioDao {

    @Override
    public Usuario create(Usuario usuario) {
        final String sql = "INSERT INTO usuario(nombre, email) VALUES(?, ?)";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando usuario: " + e.getMessage(), e);
        }
        return usuario;
    }

    @Override
    public Optional<Usuario> findById(int id) {
        final String sql = "SELECT id, nombre, email FROM usuario WHERE id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando usuario por id: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> findAll() {
        final String sql = "SELECT id, nombre, email FROM usuario ORDER BY id";
        List<Usuario> list = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando usuarios: " + e.getMessage(), e);
        }
        return list;
    }

    @Override
    public boolean update(Usuario usuario) {
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("El id es requerido para actualizar");
        }
        final String sql = "UPDATE usuario SET nombre = ?, email = ? WHERE id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        final String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando usuario: " + e.getMessage(), e);
        }
    }

    private Usuario mapRow(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email")
        );
    }
}
