package model;

import java.util.List;

public class Usuario {

    //atributos de cada cliente
    private int id;
    private String nome;
    private String email;
    List<Emprestimo> emprestimos;

    //construtor da classe cliente

    public Usuario() {

    }

    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    @Override
    public String toString() {
        return "Usuario: Id: " + id + ", Nome: " + nome + ", Email: " + email;
    }
}