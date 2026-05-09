package repository;

import dao.LivrosDao;
import exceptions.DbException;
import model.Autor;
import model.Livro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroRepository implements LivrosDao {

    private Connection conn;

    public LivroRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Livro livro) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = conn.prepareStatement("INSERT INTO livros \n"
                                          +"(titulo,disponivel,autor_id,data_cadastro,data_atualizacao) \n"
                                          + "VALUES\n"
                                          + "(? ,? ,?, ?, ?) \n",
                                           Statement.RETURN_GENERATED_KEYS);

            st.setString(1, livro.getTitulo());
            st.setBoolean(2, livro.isDisponivel());
            st.setInt(3, livro.getAutor().getId());
            st.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            st.setDate(5, java.sql.Date.valueOf(LocalDate.now()));

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                 rs = st.getGeneratedKeys();
                 if (rs.next()) {
                     livro.setId(rs.getInt(1)); //adicionando id ao novo obj livro
                 }
            }
            CN.closeResultSet(rs);

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(st);
        }
    }

    @Override
    public void update(Livro livro) {
        PreparedStatement st = null;

        try{
             st = conn.prepareStatement("UPDATE livros\n"
                                          + "SET titulo = ?, disponivel = ?, autor_id = ?, data_atualizacao = ?"
                                          +  "WHERE id = ?");

             st.setString(1, livro.getTitulo());
             st.setBoolean(2, livro.isDisponivel());
             st.setInt(3, livro.getAutor().getId());
             st.setDate(4, java.sql.Date.valueOf(LocalDate.now())); //atualizando a data

             st.setInt(5, livro.getId());

             st.executeUpdate(); //executando as mudanças...

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(st);
        }

    }

    @Override
    public void delete(int id) {
        PreparedStatement st = null;

        try{

            st = conn.prepareStatement("DELETE FROM livros \n"
                                            + "WHERE id = ? \n");

            st.setInt(1, id);

            st.executeUpdate(); //removendo o livro do banco livros...

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            CN.closeStatement(st);
        }

    }


    @Override
    public Livro findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{ st = conn.prepareStatement("SELECT livros.*, " +
                "autores.id ," +
                "autores.nome " +
                "FROM livros " +
                "INNER JOIN autores ON livros.autor_id = autores.id " +
                "WHERE livros.id = ?" );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()){

                Autor aut = instantialAutor(rs); //está instanciando o obj aut para ser incrementado em livro.
                Livro livro = instantialLivro(rs,aut); //está instanciando o obj livro

                return livro;
            }
            return null;

        } catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            CN.closeStatement(st);
            CN.closeResultSet(rs);
        }
    }

    private Livro instantialLivro(ResultSet rs, Autor autor) throws SQLException {
        Livro livro = new Livro();
        livro.setId(rs.getInt("id"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setDisponivel(rs.getBoolean("disponivel"));
        livro.setAutor(autor);
        livro.setDataAlteracao(rs.getDate("data_atualizacao"));
        livro.setDataCadastro(rs.getDate("data_cadastro"));

        return livro;
    }

    private Autor instantialAutor(ResultSet rs) throws SQLException {
        Autor autor = new Autor();
        autor.setId(rs.getInt("id"));
        autor.setNome(rs.getString("nome"));

        return autor;
    }

    @Override
    public Livro findByName(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
             st = conn.prepareStatement("SELECT * FROM livros WHERE titulo = ?");

             rs = st.executeQuery();

             if(rs.next()){
                 Autor aut = instantialAutor(rs);
                 Livro livro = instantialLivro(rs,aut);

                 return livro;
             }
             return null;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(st);
            CN.closeResultSet(rs);
        }
    }

    @Override
    public List<Livro> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = conn.prepareStatement("select livros.*, autores.nome \n" +
                    "FROM livros \n" +
                    "inner join autores\n" +
                    "ON livros.autor_id = autores.id ");

            rs = st.executeQuery();

            List<Livro> livros = new ArrayList<>();

            while(rs.next()){

                Autor aut = instantialAutor(rs);
                Livro livro = instantialLivro(rs,aut);
                livros.add(livro);

            }
            return livros;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(st);
            CN.closeResultSet(rs);
        }



    }

}
