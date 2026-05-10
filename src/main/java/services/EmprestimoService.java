package services;

import exceptions.EmprestimoNaoEncontrado;
import model.Emprestimo;
import repository.dao.EmprestimoDao;

import javax.crypto.ExemptionMechanismException;
import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    private EmprestimoDao empdao;

    public EmprestimoService(EmprestimoDao empdao) {
        this.empdao = empdao;
    }

    //metodos que podem ser utilizados para emprestimo...

    public void inserirEmprestimo(Emprestimo emprestimo){
        empdao.insert(emprestimo);
    }

    public void renovarEmprestimo(int id, Emprestimo emprestimo){
        Emprestimo emp = empdao.findById(id); //procurando o id do emprestimo realizado.
        if(emp == null){
            throw new EmprestimoNaoEncontrado("Erro: Nenhum empréstimo foi realizado!");
        }

        emp.setDataDevolucao(java.sql.Date.valueOf(LocalDate.now().plusDays(10)));

        empdao.update(emp); //caso haja emprestimo, ele será atualizado
    }

    public void excluirEmprestimo(int id){
        Emprestimo emp = empdao.findById(id); //procurando o id do emprestimo no banco de dados
        if(emp == null){
            throw new EmprestimoNaoEncontrado("Erro: Nenhum empréstimo foi realizado!");
        }
        empdao.delete(emp.getId()); //deletando o emprestimo com o id achado.
    }


    public Emprestimo buscarEmprestimo(int id){
        Emprestimo emp = empdao.findById(id); //procurando id do emprestimo no banco
        if(emp == null){
            throw new EmprestimoNaoEncontrado("Erro: Nenhum empréstimo encontrado.");
        }
        return emp;
    }

    public List<Emprestimo> listarEmprestimos(){
        for(Emprestimo emp : empdao.findAll()){
            System.out.println(emp);  //retorna cada emprestimo dentro da lista.
        }
        return empdao.findAll();
    }


}
