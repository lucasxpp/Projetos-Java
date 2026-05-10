package application.view;

import controller.AutorController;
import controller.UsuarioController;
import repository.DaoFactoryRepository;
import repository.dao.AutorDao;
import services.AutorService;

public class MenuAutor {

    private AutorDao autorDao;
    private AutorService autorService;
    private UsuarioController usuarioController;

    public void menuAutor() {
        try {
            AutorDao autdao = DaoFactoryRepository.createAutorR();
            AutorService autSer = new AutorService(autdao);
            AutorController autController = new AutorController(autSer);

            autController.menu();

        }catch(IllegalArgumentException e){
            e.getMessage();
        }
    }
}
