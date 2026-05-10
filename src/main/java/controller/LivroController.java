package controller;

import model.Autor;
import model.Livro;
import repository.DaoFactoryRepository;
import services.LivroService;
import repository.dao.AutorDao;

import java.util.Scanner;

public class LivroController {
    Scanner sc = new Scanner(System.in);

    private LivroService livroService;

    public LivroController(LivroService livroService) { //recebe o Service
        this.livroService = livroService;
    }

    public void menu(){
        int opcao = 0;

        while(opcao!=6) {

            System.out.println("============MENU LIVROS=============");
            System.out.println("| [1] Inserir Livro                |");
            System.out.println("| [2] Busca de Livro(pelo Id)      |");
            System.out.println("| [3] Busca de Livro(Pelo titulo)  |");
            System.out.println("| [3] Atualizar Dados de Livro     |");
            System.out.println("| [4] Listar Todos os Livros       |");
            System.out.println("| [5] Excluir Livro(s)             |");
            System.out.println("| [6] Sair                         |");
            System.out.println("====================================");
            opcao = sc.nextInt(); sc.nextLine();

            switch (opcao) {

                case 1:
                    inserir();
                    break;
                case 2:
                    buscaPorId();
                    break;

            }

        }

    }



     public void inserir(){
         AutorDao auttdao = DaoFactoryRepository.createAutorR();
         System.out.println("--------insert livro----------");
         System.out.println("Titulo: ");
         String titulo = sc.nextLine();
         System.out.println("Id Autor: ");
         int id  = sc.nextInt();

         Autor aut = auttdao.findById(id); //procura o autor pelo Id.

         Livro livro = new Livro(0,titulo, aut); //instancia um novo livro

         livroService.inserirLivro(livro); //adiciona ao banco de dados

         System.out.println("Book inserted successfully!");
         sc.nextLine();

     }

     public void buscaPorId(){






     }




























}
