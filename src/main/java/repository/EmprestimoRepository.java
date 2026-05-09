package repository;

import dao.EmprestimoDao;
import exceptions.DbException;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
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


        ps.setDate(1, java.sql.Date.valueOf(LocalDate.now())); //envia o dia em que foi calculado
        ps.setDate(2,java.sql.Date.valueOf(LocalDate.now().plusDays(10)));
        ps.setInt(3, emprestimo.getLivroId() );
        ps.setInt(4, emprestimo.getUsuarioId());

        int RowAffected = ps.executeUpdate();
            if(RowAffected > 0) {
                rs = ps.getGeneratedKeys(); //rs recebe a chave
                if (rs.next()) {
                    emprestimo.setId(rs.getInt(1)); //envia o id para o obj empréstimo
                }
            }

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
        PreparedStatement ps = null;

        try{
             ps = conn.prepareStatement("UPDATE emprestimos "
                                           +"SET data_devolucao = ?");

             ps.setDate(1, java.sql.Date.valueOf(LocalDate.now().plusDays(10)));
            //acredito que pegara a data no dia em que precisa renovar e aumentar +10 dias
            ps.executeUpdate();

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(ps);
        }

    }

    @Override
    public void delete(int id) {
        PreparedStatement ps = null;

        try{
             ps = conn.prepareStatement("DELETE FROM emprestimos"
                                            +" WHERE id = ? ");

             ps.setInt(1, id);
             ps.executeUpdate(); //ira excluir o emprestimo do banco de dados...

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(ps);
        }

    }

    @Override
    public Emprestimo findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = conn.prepareStatement("SELECT emprestimos.*, " +
                    "usuario.nome, " +
                    "livros.titulo " +
                    "FROM emprestimos " +
                    "INNER JOIN usuario ON emprestimos.usuario_id = usuario.id " +
                    "INNER JOIN livros ON emprestimos.livro_id = livros.id " +
                    "WHERE emprestimos.id = ?" );

            ps.setInt(1, id);
            rs = ps.executeQuery(); //rs irá receber o resultado...

            if(rs.next()) {

                Emprestimo emprestimo = instantialEmprestimo(rs); //instanciando o emprestimo

                return emprestimo;
            }
            return null;

        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            CN.closeStatement(ps);
            CN.closeResultSet(rs);
        }

    }

    private Emprestimo instantialEmprestimo(ResultSet rs) throws SQLException {
        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setId(rs.getInt("id"));
        emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
        emprestimo.setDataDevolucao(rs.getDate("data_devolucao"));
        emprestimo.setLivroId(rs.getInt("livro_id"));
        emprestimo.setUsuarioId(rs.getInt("usuario_id"));

        emprestimo.setNomeUsuario(rs.getString("nome"));//direto da coluna usuario
        emprestimo.setTituloLivro(rs.getString("titulo"));//direto da coluna livros

        return emprestimo;
    }


    @Override
    public List<Emprestimo> findAll() { //ira retornar o nome(usuario) e titulo(do livro)
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
             ps = conn.prepareStatement("select \n" +
                     "emprestimos.id,\n" +
                     "emprestimos.data_emprestimo,\n" +
                     "emprestimos.data_devolucao,\n" +
                     "usuario.nome,\n" +
                     "livros.titulo\n" +
                     "from emprestimos \n" +
                     "inner join usuario on emprestimos.usuario_id = usuario.id\n" +
                     "inner join livros on emprestimos.livro_id = livros.id;\n" +
                     "\n");

             rs = ps.executeQuery();

             List<Emprestimo> emp = new ArrayList<Emprestimo>();

             while (rs.next()) {

                 Emprestimo emprestimo = instantialEmprestimo(rs);

                 emp.add(emprestimo);

             }
             return emp;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeResultSet(rs);
            CN.closeStatement(ps);
        }


    }


}
