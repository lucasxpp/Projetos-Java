package model;

import java.util.Date;

public class Livro {

    //atributos especificos de cada livro...

    private int id;
    private String titulo;
    private Autor autor;
    private boolean disponivel;
    private Date dataCadastro;
    private Date dataAlteracao;

    //construtor principal da estrutura de um livro

    public Livro(){

    }

    public Livro(int idLivro, String titulo, Autor autor) {
        this.id = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    //metodos especiais de cada livro...


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public String toString() {
        return    "   Informações do Livro: " + titulo
                + "   Id: " + id
                + "   Autor: " + autor
                + "\n Data de Cadastro: " +  dataCadastro
                + "\n Última Alteração: " + dataAlteracao;
    }
}
