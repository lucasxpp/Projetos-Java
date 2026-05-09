package repository;

import dao.EmprestimoDao;
import exceptions.DbException;
import model.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class EmprestimoRepository implements EmprestimoDao {

    private Connection conn;

    public EmprestimoRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Emprestimo emprestimo) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{  ps = conn.prepareStatement("INSERT INTO emprestimos"
                                             +"(data_emprestimo,data_devolucao,livro_id,usuario_id)"
                                             +"VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


            









        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(ps);
            CN.closeResultSet(rs);
        }

    }

    @Override
    public void update(Emprestimo emprestimo) {

    }

    @Override
    public void delete(Emprestimo emprestimo) {

    }

    @Override
    public Emprestimo findById(Emprestimo emprestimo) {
        return null;
    }

    @Override
    public List<Emprestimo> findAll() { //ira retornar o nome(usuario) e titulo(do livro)
        return List.of();
    }


}
