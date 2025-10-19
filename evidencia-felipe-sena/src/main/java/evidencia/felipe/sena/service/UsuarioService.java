package evidencia.felipe.sena.service;

import evidencia.felipe.sena.dao.UsuarioDao;
import evidencia.felipe.sena.dao.jdbc.UsuarioJdbcDao;
import evidencia.felipe.sena.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para encapsular lógica de negocio.
 */
public class UsuarioService {

    private final UsuarioDao usuarioDao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioJdbcDao();
    }

    public Usuario crearUsuario(String nombre, String email) {
        return usuarioDao.create(new Usuario(nombre, email));
    }

    public Optional<Usuario> obtenerUsuario(int id) {
        return usuarioDao.findById(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAll();
    }

    public boolean actualizarUsuario(int id, String nombre, String email) {
        return usuarioDao.update(new Usuario(id, nombre, email));
    }

    public boolean eliminarUsuario(int id) {
        return usuarioDao.deleteById(id);
    }
}
