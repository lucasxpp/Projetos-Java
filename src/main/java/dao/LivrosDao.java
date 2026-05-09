package dao;

import model.Livro;

import java.util.List;

public interface LivrosDao {
    void insert(Livro livro);
    void update(Livro livro);
    void delete(int id);
    Livro findById(int id);
    Livro findByName(String name);
    List<Livro> findAll();
}
