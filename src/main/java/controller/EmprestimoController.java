package controller;

import services.BibliotecaService;
import services.EmprestimoService;

public class EmprestimoController {

    private EmprestimoService emprestimoService;
    private BibliotecaService bibliotecaService;

    public EmprestimoController(EmprestimoService emprestimoService, BibliotecaService bibliotecaService) {
        this.emprestimoService = emprestimoService;
        this.bibliotecaService = bibliotecaService;
    }



}
