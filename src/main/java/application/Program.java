package application;
//programa da biblioteca: será um programa com CRUD simples de usuario/login e com sistema de empréstimos


import dao.AutorDao;
import dao.EmprestimoDao;
import dao.LivrosDao;
import dao.UsuarioDao;
import model.Autor;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import repository.AutorRepository;
import repository.DaoFactoryRepository;
import repository.LivroRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("=======================1 appplication: autor =======================");


        AutorDao auttdao = DaoFactoryRepository.createAutorR();
      //  Autor autor = new Autor(0,"George RR.Martin", sdf.parse("05/10/1950") );

      //  auttdao.insert(autor);
      //  System.out.println("inserted autor");

       // System.out.println("=============================================================");

        System.out.println("=======================2 appplication: livro =======================");

       // Autor autor = auttdao.findById(1);

       LivrosDao livvdao = DaoFactoryRepository.createLivroR();

      //  Livro livro = new Livro(0,"Cronicas de gelo e fogo", autor, true);
       // livvdao.insert(livro);

        System.out.println("inserted book.");

       // System.out.println("=======================3 appplication: usuario =======================");

        UsuarioDao usdao = DaoFactoryRepository.createClientesR();


       // System.out.println("=======================4 appplication: Emprestimo =======================");
        EmprestimoDao emdao = DaoFactoryRepository.createEmprestimoR();

        emdao.delete(1);
        System.out.println("deleted!");








    }
}
