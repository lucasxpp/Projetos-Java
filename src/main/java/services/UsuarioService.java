package services;

import exceptions.UsuarioNaoEncontrado;
import model.Usuario;
import repository.UsuarioRepository;
import repository.dao.UsuarioDao;
import java.util.List;

public class UsuarioService {

    //atributos da classe UsuarioService
    private UsuarioDao usuarioDao;

    //metodos que um Usuario pode fazer:

    public UsuarioService(UsuarioDao dao){ //vou estar utilizando a interface...
        this.usuarioDao = dao;
    }

    public void inserirUsuario(Usuario usuario){
       usuarioDao.insert(usuario);
    }

    public void atualizarUsuario(int id, Usuario dadosNovos){
       Usuario uss = usuarioDao.findById(id);

        if(uss == null){
            throw new UsuarioNaoEncontrado("Usuario não foi encontrado!");
        }

       uss.setNome(dadosNovos.getNome());
       uss.setEmail(dadosNovos.getEmail());

      usuarioDao.update(uss);
    }

    public void excluirUsuario(int id){
        Usuario usuario = usuarioDao.findById(id);

        if(usuario == null){
            throw new UsuarioNaoEncontrado("Usuario não foi encontrado!");
        }

       usuarioDao.delete(id);

    }

    public Usuario buscarUsuario(int id){
        Usuario usuario = usuarioDao.findById(id);

        if(usuario == null){
            throw new UsuarioNaoEncontrado("Usuario não foi encontrado!");
        }

        return usuario;
    }

    public List<Usuario> buscarUsuarios(){
       for(Usuario usuarios: usuarioDao.findAll()){ //para retornar cada usuario
           System.out.println(usuarios);
       }
       return usuarioDao.findAll();
    }
}
