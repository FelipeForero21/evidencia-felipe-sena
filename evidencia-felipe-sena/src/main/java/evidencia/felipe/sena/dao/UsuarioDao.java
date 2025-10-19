package evidencia.felipe.sena.dao;

import evidencia.felipe.sena.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDao {
    Usuario create(Usuario usuario);
    Optional<Usuario> findById(int id);
    List<Usuario> findAll();
    boolean update(Usuario usuario);
    boolean deleteById(int id);
}
