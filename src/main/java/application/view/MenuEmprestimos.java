package application.view;

import controller.EmprestimoController;
import repository.DaoFactoryRepository;
import repository.dao.EmprestimoDao;
import repository.dao.LivrosDao;
import repository.dao.UsuarioDao;
import services.BibliotecaService;
import services.EmprestimoService;

public class MenuEmprestimos {

    private EmprestimoDao emprestimoDao;
    private EmprestimoService emprestimoService;
    private EmprestimoController emprestimoController;

    public void MenuEmprestimos() {
        LivrosDao livrosDao = DaoFactoryRepository.createLivroR();
        UsuarioDao usdao = DaoFactoryRepository.createClientesR();
        EmprestimoDao empdao = DaoFactoryRepository.createEmprestimoR();

        EmprestimoService empservice = new EmprestimoService(empdao);
        BibliotecaService bibService = new BibliotecaService(livrosDao, empdao,usdao);

        EmprestimoController empcon = new EmprestimoController(empservice,bibService);

        empcon.menu(); //buscando menu
    }
}
