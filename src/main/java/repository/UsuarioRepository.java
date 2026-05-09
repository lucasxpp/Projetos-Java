package repository;

import dao.UsuarioDao;
import exceptions.DbException;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements UsuarioDao {

    private Connection conn;

    public UsuarioRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Usuario u) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
             ps = conn.prepareStatement("INSERT INTO usuario\n"
                                             +"(nome,email)\n"
                                             +"VALUES (?,?)\n", Statement.RETURN_GENERATED_KEYS);

             ps.setString(1,u.getNome());
             ps.setString(2,u.getEmail());

             int RowsAffected = ps.executeUpdate();

             if(RowsAffected > 0){
                 rs = ps.getGeneratedKeys();

                 if(rs.next()){
                      u.setId(rs.getInt(1)); //adicionando o id ao colocar no banco de dados.

                 }


             }
            CN.closeResultSet(rs);

        }catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(ps);;
        }

    }

    @Override
    public void update(Usuario cliente) {
        PreparedStatement ps = null;

        try{
             ps = conn.prepareStatement("UPDATE usuario "
                                          +"SET nome = ?, email = ?"
                                          +"WHERE id = ?");

             ps.setString(1,cliente.getNome());
             ps.setString(2,cliente.getEmail());
             ps.setInt(3,cliente.getId());

             ps.executeUpdate(); //atualizando dados do usuario

        }catch(SQLException e) {
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
             ps = conn.prepareStatement("DELETE FROM usuario "
                                           +"WHERE id = ?");

             ps.setInt(1, id);

             ps.executeUpdate(); //deleta o usuario que foi inserido no banco de dados.

        }catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(ps);
        }

    }

    @Override
    public Usuario findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            ps = conn.prepareStatement("SELECT * "
                                          + "FROM usuario"
                                          + " WHERE id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if(rs.next()){
                Usuario u = instantialUser(rs); //instanciando o usuario através das informações contidas no rs.
                return u;
            }
            return null;

        }catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            CN.closeStatement(ps);
            CN.closeResultSet(rs);
        }

    }

    private Usuario instantialUser(ResultSet rs) throws SQLException {
        Usuario u = new Usuario(); //nao add id, pois é gerado imediatamente após a criação. Metodo utilizado para instanciar o obj usuario
        u.setId(rs.getInt("id"));  //a parte de cima está ERRADA, preciso sim. Pois depois vou precisar mostrar eles e eles precisam estar instanciados.
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));

        return u;
    }

    @Override
    public List<Usuario> findAll() { //
       PreparedStatement ps = null;
       ResultSet rs = null;

       try{
            ps = conn.prepareStatement("SELECT * "
                                           +"FROM usuario"
                                            +" ORDER BY nome");

            List<Usuario> users = new ArrayList<>();
            rs = ps.executeQuery();

            while(rs.next()){
                Usuario u = instantialUser(rs);
                users.add(u);

            }
            return users;

       }catch(SQLException e) {
           throw new DbException(e.getMessage());
       }
       finally {
           CN.closeStatement(ps);
           CN.closeResultSet(rs);
       }
    }
}
