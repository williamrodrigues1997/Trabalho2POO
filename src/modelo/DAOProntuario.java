package modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Classe responsável pelo CRUD relacionado ao POJO Prontuario.
 */
public class DAOProntuario {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public void conectar() {
        this.emf = Persistence.createEntityManagerFactory("SaudeCiaJPA");
        this.em = emf.createEntityManager();
    }

    public void desconectar() {
        this.em.close();
        this.emf.close();
    }
    
    public void inserir(Date data, String medico, Paciente paciente, String sintomas, String diagnosticoDoenca, String prescricaoTratamento) {
        
    }

    public void alterar(Integer id, Paciente paciente, String sintomas, String diagnosticoDoenca, String prescricaoTratamento) {
        
    }

    public void remover(Integer id) {
        
    }

    public Prontuario getProntuarioPorCpf(String cpf) {
       
        return null;
    }

    public Prontuario getProntuarioPorId(Integer id) {

        return null;
    }
}
