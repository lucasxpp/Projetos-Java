package services;

import exceptions.AutorNaoEncontrado;
import model.Autor;
import model.Usuario;
import repository.AutorRepository;
import repository.dao.AutorDao;
import java.util.List;

public class AutorService {

    //atribubtos da classe AutorService
    private AutorDao autorDao;

    public AutorService(AutorDao dao){
        this.autorDao = dao;
    }

    //metodos
    public void inserirAutor(Autor autor){ //recebe dados do novo autor e insere
       autorDao.insert(autor);
    }

    public void atualizarAutor(int id, Autor dadosNovos){
        Autor aut =  autorDao.findById(id);

        if(aut == null){
            throw new AutorNaoEncontrado("Autor não foi encontrado!");
        }

         //apenas irei alterar o nome do usuario, a data de nascimento permanecerá constante.
        aut.setNome(dadosNovos.getNome());

        autorDao.update(aut);
    }

    public void excluirAutor(int id){
        Autor aut = autorDao.findById(id);

        if(aut == null){
            throw new AutorNaoEncontrado("Autor não foi encontrado!");
        }

        autorDao.delete(id);
    }

   public Autor buscarAutor(int id){
        Autor aut = autorDao.findById(id);

        if(aut == null){
            throw new AutorNaoEncontrado("Autor não foi encontrado!");
        }

        return aut;
   }

   public List<Autor> listarAutores(){

        for(Autor autores : autorDao.findAll()){
            System.out.println(autores);
        }
        return autorDao.findAll();
   }
}
