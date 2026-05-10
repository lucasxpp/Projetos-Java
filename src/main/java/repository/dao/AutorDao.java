package repository.dao;

import model.Autor;
import java.util.List;

public interface AutorDao {
    void insert(Autor autor);
    void update(Autor autor);
    void delete(int id);
    Autor findById(int id);
    List<Autor> findAll();
}
