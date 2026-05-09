package model;

import java.util.Date;

public class Emprestimo {

    //atributos principais de um emprestimo...
    private int id;
    private Livro livro;
    private String nomeCliente;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    //construtor
    public Emprestimo() {

    }

    public Emprestimo(int idEmprestimo, Livro livro, String nomeCliente) {
        this.id = idEmprestimo;
        this.livro = livro;
        this.nomeCliente = nomeCliente;
    }

    //metodos especiais

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }


}
