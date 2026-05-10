package services;
//responsavel por formar os emprestimos...


import exceptions.EmprestimoNaoEncontrado;
import exceptions.LivroNaoEncontrado;
import exceptions.UsuarioNaoEncontrado;
import model.Emprestimo;
import model.Livro;
import model.Usuario;
import repository.dao.EmprestimoDao;
import repository.dao.LivrosDao;
import repository.dao.UsuarioDao;

public class BibliotecaService {
    
     //quem a service biblioteca ira receber a interface e decidir o que vai acontecer...

    private LivrosDao livrosDao;
    private EmprestimoDao emprestimoDao;
    private UsuarioDao usuarioDao;

    public BibliotecaService(LivrosDao livrosDao, EmprestimoDao emprestimoDao, UsuarioDao usuarioDao) {
        this.livrosDao = livrosDao;
        this.emprestimoDao = emprestimoDao;
        this.usuarioDao = usuarioDao;
    }

    //responsavel por criar empréstimos.


    public void criarEmprestimo(int livroId, int usuarioId) {
        Livro livro = livrosDao.findById(livroId); //procurando o livro pelo Id
        if(livro == null){
            throw new LivroNaoEncontrado("Livro não encontrado!");
        }
        Usuario usuario = usuarioDao.findById(usuarioId); //procurando o usuario pelo Id
        if(usuario == null){
            throw new UsuarioNaoEncontrado("Usuario não foi encontrado!");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivroId(livroId);
        emprestimo.setUsuarioId(usuarioId);

        //para marcações após o empréstimo...
        livro.setDisponivel(false);
        livrosDao.update(livro); //mudando a situação do livro

        emprestimoDao.insert(emprestimo); //inserindo novo emprestimo no banco de dados

    }

    public void devolverLivro(int emprestimoId){
        Emprestimo emp =  emprestimoDao.findById(emprestimoId); //procurando o Id do emprestimo no banco
        if(emp == null){
            throw new EmprestimoNaoEncontrado("Empréstimo não foi realizado!.");
        }

       Livro livro =  livrosDao.findById(emp.getLivroId()); // buscando pelo Id do livro
        livro.setDisponivel(true);
        livrosDao.update(livro); //mudando a situação do livro

        emprestimoDao.delete(emp.getId()); //deletando o emprestimo fornecido.

    }



    //metodos especiais

    public LivrosDao getLivrosDao() {
        return livrosDao;
    }

    public void setLivrosDao(LivrosDao livrosDao) {
        this.livrosDao = livrosDao;
    }

    public EmprestimoDao getEmprestimoDao() {
        return emprestimoDao;
    }

    public void setEmprestimoDao(EmprestimoDao emprestimoDao) {
        this.emprestimoDao = emprestimoDao;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
}
