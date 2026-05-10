package application.view;

import controller.LivroController;
import repository.DaoFactoryRepository;
import repository.dao.LivrosDao;
import services.LivroService;

public class MenuLivros {
    private LivrosDao livrosDao;
    private LivroService livroService;
    private LivroController livroController;

    public void menuLivro(){
        LivrosDao livvdao = DaoFactoryRepository.createLivroR();
        LivroService livvService = new LivroService(livvdao);
        LivroController livroController = new LivroController(livvService);

        livroController.menu(); //puxa o menuController
    }
}
