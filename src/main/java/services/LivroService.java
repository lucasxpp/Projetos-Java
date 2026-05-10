package services;

import exceptions.LivroNaoEncontrado;
import model.Livro;
import repository.AutorRepository;
import repository.LivroRepository;
import repository.dao.LivrosDao;
import java.util.List;

public class LivroService {
    //atributos da classe LivroService

   private LivrosDao livrosDao;

   public LivroService(LivrosDao dao){ //implementando a interface
        this.livrosDao = dao;
   }

   //metodos que pode ser utilizado para o livro.

   public void inserirLivro(Livro livro){
       livrosDao.insert(livro);
   }

   public void atualizarLivro(int id, Livro dadosNovos){
       Livro liv = livrosDao.findById(id);
       if(liv == null){
           throw new LivroNaoEncontrado("Livro não foi encontrado!");

       }
       //apenas irei mudar o titulo e o nome do autor do livro, se necessário.
       liv.setTitulo(dadosNovos.getTitulo());
       liv.setAutor(dadosNovos.getAutor());

       livrosDao.update(liv); //fazendo as mudanças
   }

   public void excluirLivro(int id){
       Livro liv = livrosDao.findById(id);
       if(liv == null){
           throw new LivroNaoEncontrado("Erro: Livro não foi encontrado!");
       }

       livrosDao.delete(liv.getId());
   }

   public Livro buscarLivro(int id){
     Livro livv = livrosDao.findById(id);
       if(livv == null){
           throw new LivroNaoEncontrado("Erro: Livro não foi encontrado!");
       }
       return livv;
   }

   public Livro buscaLivro(String titulo){
       Livro livv = livrosDao.findByName(titulo);
       if(livv == null){
           throw new LivroNaoEncontrado("Erro: Livro não foi encontrado!");
       }
       return livv;
   }

   public List<Livro> buscarLivros(){
       return livrosDao.findAll();
   }

}
