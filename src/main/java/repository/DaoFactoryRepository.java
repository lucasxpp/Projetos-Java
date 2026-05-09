package repository;

import dao.AutorDao;
import dao.UsuarioDao;

public class DaoFactoryRepository {

    //estabelece a conexão com o banco de dados para as classes..

    public static UsuarioDao createClientesR(){
        return new UsuarioRepository(CN.getConnection());
    }

    public static AutorDao createAutorR(){
        return new AutorRepository(CN.getConnection());
    }

    public static LivroRepository createLivroR(){
        return new LivroRepository(CN.getConnection());
    }

}
