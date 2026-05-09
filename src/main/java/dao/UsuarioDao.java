package dao;

import model.Usuario;
import java.util.List;

public interface UsuarioDao {
    void insert(Usuario cliente);
    void update(Usuario cliente);
    void delete(int id);
    Usuario findById(int id);
    List<Usuario> findAll();
}
