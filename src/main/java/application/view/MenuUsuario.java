package application.view;

import controller.UsuarioController;
import repository.DaoFactoryRepository;
import repository.dao.UsuarioDao;
import services.UsuarioService;

public class MenuUsuario {
    private UsuarioDao usuarioDao;
    private UsuarioService usuarioService;
    private UsuarioController usuarioController;


    public void menuUsuario() {

            UsuarioDao usdao = DaoFactoryRepository.createClientesR();
            UsuarioService usservice = new UsuarioService(usdao);
            UsuarioController uscontroller = new UsuarioController(usservice);

            uscontroller.menu();
    }

}
