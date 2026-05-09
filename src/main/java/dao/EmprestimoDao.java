package dao;


import model.Emprestimo;
import java.util.List;

public interface EmprestimoDao {
    void insert(Emprestimo emprestimo);
    void update(Emprestimo emprestimo);
    void delete(Emprestimo emprestimo);
    Emprestimo findById(Emprestimo emprestimo);
    List<Emprestimo> findAll();

}
