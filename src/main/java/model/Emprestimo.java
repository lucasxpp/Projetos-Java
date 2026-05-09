package model;

import java.util.Date;

public class Emprestimo {

    //atributos principais de um emprestimo...
    private int id;
    private String tituloLivro;
    private String nomeUsuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private int LivroId;
    private int UsuarioId;

    //construtor
    public Emprestimo() {

    }

    public Emprestimo( int livroId, int usuarioId) {
       this.LivroId = livroId;
       this.UsuarioId = usuarioId;
    }

    //metodos especiais

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getLivroId() {
        return LivroId;
    }

    public void setLivroId(int livroId) {
        LivroId = livroId;
    }

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        UsuarioId = usuarioId;
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


    @Override
    public String toString() {
        return    " Emprestimo: "
                +"\n Id: "+id
                +"\n Livro: "+ tituloLivro
                +"\n Usuario: " + nomeUsuario
                +"\n Data de Emprestimo: "+dataEmprestimo
                +"\n Data de Devolucao: "+dataDevolucao;
    }
}
