package application;
//programa da biblioteca: será um programa com CRUD simples de usuario/login e com sistema de empréstimos


import controller.AutorController;
import controller.LivroController;
import controller.UsuarioController;
import repository.AutorRepository;
import repository.dao.AutorDao;
import repository.dao.EmprestimoDao;
import repository.dao.LivrosDao;
import repository.dao.UsuarioDao;
import repository.DaoFactoryRepository;
import services.AutorService;
import services.LivroService;
import services.UsuarioService;

import java.text.ParseException;
import java.text.SimpleDateFormat;



public class Program {
    public static void main(String[] args) throws ParseException {

      //  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

      //  System.out.println("=======================1 appplication: autor =======================");


     //   AutorDao auttdao = DaoFactoryRepository.createAutorR();
      //  Autor autor = new Autor(0,"George RR.Martin", sdf.parse("05/10/1950") );

      //  auttdao.insert(autor);
      //  System.out.println("inserted autor");

       // System.out.println("=============================================================");

     //   System.out.println("=======================2 appplication: livro =======================");

       // Autor autor = auttdao.findById(1);

      // LivrosDao livvdao = DaoFactoryRepository.createLivroR();

        //  Livro livro = new Livro(0,"Cronicas de gelo e fogo", autor, true);
       // livvdao.insert(livro);

       // System.out.println("inserted book.");

        System.out.println("=======================3 appplication: usuario =======================");

       // UsuarioDao usdao = DaoFactoryRepository.createClientesR();
       // UsuarioService uss = new UsuarioService(usdao);
       // UsuarioController uc = new UsuarioController(uss);

       // uc.menu();

        System.out.println("=======================4 appplication: Livro =======================");

        LivrosDao livroDao = DaoFactoryRepository.createLivroR();
        LivroService ls = new LivroService(livroDao);
        LivroController lc = new LivroController(ls);

       // lc.menu();

        System.out.println("=======================5 appplication: autor =======================");

        AutorDao autorDao = DaoFactoryRepository.createAutorR();
        AutorService autores = new AutorService(autorDao);
        AutorController ac = new AutorController(autores);

        ac.menu();







    }
}
