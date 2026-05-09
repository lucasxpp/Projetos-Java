package dao;


import model.Emprestimo;
import java.util.List;

public interface EmprestimoDao {
    void insert(Emprestimo emprestimo);
    void update(Emprestimo emprestimo);
    void delete(int id);
    Emprestimo findById(int id);
    List<Emprestimo> findAll();

}
