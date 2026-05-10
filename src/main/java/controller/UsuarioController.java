package controller;

import model.Usuario;
import services.UsuarioService;

import java.util.Scanner;

public class UsuarioController {
    Scanner sc = new Scanner(System.in);

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) { //controller recebe o service
        this.usuarioService = usuarioService;
    }

    public void menu() {
        int opcao = 0;

        while (opcao != 6) {

            System.out.println("===========MENU USUARIO============");
            System.out.println("| [1] Inserir Usuário             |");
            System.out.println("| [2] Buscar Usuário              |");
            System.out.println("| [3] Atualizar Dados de Usuário  |");
            System.out.println("| [4] Listar Usuários             |");
            System.out.println("| [5] Excluir Usuário             |");
            System.out.println("| [6] Sair                        |");
            System.out.println("===================================");
            System.out.print("  | Digite a opcao desejada: ");
            opcao = sc.nextInt();

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


            }
        }
    }

    public void inserir() {
        System.out.println("-----inserir usuario-----");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        Usuario usuario = new Usuario(0,nome, email);

        usuarioService.inserirUsuario(usuario); //inserindo usuario no banco de dados.
        System.out.println("User inserted successfully!");
        sc.nextLine();
    }

    public void buscar() {
        System.out.println("-----buscar usuario-----");
        System.out.print("Id do Usuario: ");
        int id = sc.nextInt();
        Usuario usuario = usuarioService.buscarUsuario(id); //buscando o usuario pelo Id no banco de dados.

        System.out.println(usuario);
        sc.nextLine();
    }

    public void atualizar() {
        System.out.println("-----atualizar dados de usuario-----");
        System.out.print("Id do Usuario: ");
        int id = sc.nextInt(); sc.nextLine();

        Usuario usuario = usuarioService.buscarUsuario(id); //buscando o usuario no banco de dados.

        System.out.println("Reescreva os dados do Usuario: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        usuario.setNome(nome);
        usuario.setEmail(email);

        usuarioService.atualizarUsuario(id, usuario); //atualiza de acordo com os novos dados.

    }

    public void listar() {
        System.out.println("-----listar usuario-----");
        usuarioService.buscarUsuarios();
        sc.nextLine();

    }
    public void excluir() {
        System.out.println("-----excluir usuario-----");
        System.out.println("Id do Usuario: ");
        int id = sc.nextInt();

        Usuario usuario = usuarioService.buscarUsuario(id); //buscando usuario com o id mencionado.

        usuarioService.excluirUsuario(usuario.getId()); //excluindo o usuario com o id.
        System.out.println("User deleted successfully!");
        sc.nextLine();
    }









}
