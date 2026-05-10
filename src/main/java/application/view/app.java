package application.view;

import java.util.Scanner;

public class app {

    MenuUsuario menuUsuario;

    public void menu(){
        Scanner sc = new Scanner(System.in);
        int t = 0;

        while (t != 6) {
            System.out.println("===========BIBLIOTECA=============");
            System.out.println("| [1] Gerenciar Usuarios          |");
            System.out.println("| [2] Gerenciar Livros            |");
            System.out.println("| [3] Autores Cadastrados         |");
            System.out.println("| [4] Empréstimos                 |");
            System.out.println("| [5] Sair                        |");
            System.out.println("===================================");
            t = sc.nextInt();

            if (t == 1) {

                MenuUsuario menuUsuario = new MenuUsuario();
                menuUsuario.menuUsuario();

            } else if (t == 2) {

                MenuLivros menuLivros = new MenuLivros();
                menuLivros.menuLivro();

            } else if (t == 3) {

                MenuAutor menuAutor = new MenuAutor();
                menuAutor.menuAutor();

            } else if (t == 4) {

                MenuEmprestimos menuEmprestimos = new MenuEmprestimos();

                menuEmprestimos.MenuEmprestimos(); //retornando menu

            } else if (t == 5) {
                System.out.println("Fechando...") ; break;

            }
        }
    }
}


