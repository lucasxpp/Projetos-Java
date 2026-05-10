package controller;

import model.Autor;
import services.AutorService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class AutorController {
    Scanner sc  = new Scanner(System.in);

    private AutorService autorService;

    public AutorController(AutorService autorService) {  //o AutorController recebe o AutorService
        this.autorService = autorService;
    }

    public void menu() {
        int opcao = 0;

        while (opcao != 6) {

            System.out.println("==========MENU AUTOR==========");
            System.out.println("| [1] Inserir Autor          |");
            System.out.println("| [2] Buscar Autor           |");
            System.out.println("| [3] Atualizar Autores      |");
            System.out.println("| [4] Listar Autores         |");
            System.out.println("| [5] Excluir Autor          |");
            System.out.println("| [6] Sair                   |");
            System.out.println("==============================");
            System.out.print("  | Digite a opcao desejada: ");
            opcao = sc.nextInt(); sc.nextLine();

            switch (opcao) {

                case 1:
                    inserir();
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    atualizar();
                    break;
                case 4:
                    listar();
                    break;
                case 5:
                    excluir();
                    break;
                case 6:
                    System.out.println("Saindo...");
                default:
                    System.out.println("Opção inválida!");

            }
        }
    }

        private void inserir() {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("-----insert-----");
            System.out.print("Nome: ");
            String nome = sc.next(); sc.nextLine();
            System.out.print("Data de nascimento(dd/MM/yyyy): ");

            LocalDate localDate = LocalDate.parse(sc.nextLine(), dtf);
            Date dataNascimento = java.sql.Date.valueOf(localDate); //estou instanciando LocalDate em Date


            Autor autor = new Autor(0,nome,dataNascimento ); //instanciando novo autor.
            autorService.inserirAutor(autor); //inserindo autor no banco de dados.

            System.out.println("Autor inserted successfully!");
            sc.nextLine();
        }

        private void buscar() {
            System.out.println("-----Buscar Autor------");
            System.out.print("Id do Autor: ");
            int id = sc.nextInt(); sc.nextLine();

             Autor autor = autorService.buscarAutor(id); //buscando o Id no banco...
             System.out.println(autor);
             sc.nextLine();
        }

        public void atualizar() {
            System.out.println("-----Atualizar Dados de Autor------");
            System.out.print("Id do Autor: ");
            int id = sc.nextInt(); sc.nextLine();
            Autor autor = autorService.buscarAutor(id); //buscando autor pelo Id.
            System.out.println("Reescreva os dados do autor: ");
            System.out.print("Nome: ");

            String nome = sc.nextLine();
            autor.setNome(nome);

            autorService.atualizarAutor(id, autor); //repassando os dados novos para o banco.
            System.out.println("Autor updated successfully!");
            sc.nextLine();
        }

        public void listar() {
            System.out.println("-----Listar Autores------");
            autorService.listarAutores();
            sc.nextLine();
        }

        public void excluir() {
            System.out.println("-----Excluir Autor------");
            System.out.print("Id do Autor: ");
            int id = sc.nextInt(); sc.nextLine();
            Autor autor = autorService.buscarAutor(id); //buscando o autor no banco de dados

            autorService.excluirAutor(autor.getId()); // excluindo o autor instanciado pelo id.
            System.out.println("Autor deleted successfully!");
            sc.nextLine();
        }



}
