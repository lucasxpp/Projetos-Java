package repository;

import dao.AutorDao;
import exceptions.DbException;
import model.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutorRepository implements AutorDao {

    private Connection conn;

    public AutorRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Autor autor) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO autores \n "
                                           + "(nome, data_nascimento) "
                                             + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, autor.getNome());
            ps.setDate(2, new java.sql.Date(autor.getDataNascimento().getTime()));

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    autor.setId(id); //o obj autor irá receber o id.
                }
                CN.closeResultSet(rs);
            } else {
                throw new DbException("Erro ao insert autor.");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }


    @Override
    public void update(Autor autor) {
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("UPDATE autores SET nome = ?, data_nascimento = ? "
                                      +    " WHERE id = ? ");

            ps.setString(1, autor.getNome());
            ps.setDate(2, new java.sql.Date(autor.getDataNascimento().getTime()));

            ps.setInt(3, autor.getId());

            ps.executeUpdate();


        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }


    }

    @Override
    public void delete(int id) {
        PreparedStatement ps = null;

        try{
             ps = conn.prepareStatement("DELETE FROM autores WHERE id = ? ");

             ps.setInt(1, id);

             ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public Autor findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
             ps = conn.prepareStatement("SELECT * FROM autores WHERE id = ? ");

             ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Autor aut = instantialAutor(rs);
                return aut;
            }
            return null;
            

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }


    }

    private Autor instantialAutor(ResultSet st ) throws SQLException {

        Autor aut = new Autor();

    aut.setId(st.getInt("id"));
    aut.setNome(st.getString("nome"));
    aut.setDataNascimento(st.getDate("data_nascimento"));
    return aut;

    }

    @Override
    public List<Autor> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
               ps = conn.prepareStatement("SELECT * "
                                             +" FROM autores"
                                             +" ORDER BY nome ");

               rs = ps.executeQuery();

               List<Autor> list = new ArrayList<>();
               Map<Integer, Autor> map = new HashMap<>();

               while (rs.next()) {
                   Autor autor = map.get(rs.getInt("id"));

                   if(autor == null){

                       autor = instantialAutor(rs);
                       map.put(rs.getInt("id"),autor); //adicionando ao map de autores
                   }
                   list.add(autor); //adicionando a lista de autores.

               }
               return list;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(ps);
            CN.closeResultSet(rs);
        }
    }
}
