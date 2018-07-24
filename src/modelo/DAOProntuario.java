package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

    public void inserir(Prontuario prontuario) {
        em.getTransaction().begin();
        em.persist(prontuario);
        em.getTransaction().commit();
    }

    public boolean alterar(Prontuario prontuarioNovo) {
        em.getTransaction().begin();
        Prontuario prontuario = new Prontuario();
        prontuario = em.find(prontuario.getClass(), prontuarioNovo.getId());
        if (prontuario != null) {
            prontuario.setPaciente(prontuarioNovo.getPaciente());
            prontuario.setMedico(prontuarioNovo.getMedico());
            prontuario.setData(prontuarioNovo.getData());
            prontuario.setDiagnosticoDoenca(prontuarioNovo.getDiagnosticoDoenca());
            prontuario.setPrescricaoTratamento(prontuarioNovo.getPrescricaoTratamento());
            prontuario.setSintomas(prontuarioNovo.getSintomas());
            em.getTransaction().commit();
            return true;
        }
        //Id nao encontrado
        return false;
    }

    public boolean remover(Integer id) {
        em.getTransaction().begin();
        Prontuario prontuario = new Prontuario();
        prontuario = em.find(prontuario.getClass(), id);
        if (prontuario != null) {
            em.remove(prontuario);
            em.getTransaction().commit();
            return true;
        }
        //Id nao encontrado
        return false;
    }

    public Prontuario getProntuarioPorCpf(String cpf) {
        List<Prontuario> listaDeProntuarios = getLista();
        int tam = listaDeProntuarios.size();
        if (tam > 0) {
            for (Prontuario prontuario : listaDeProntuarios) {
                if (prontuario.getPaciente().getCpf().equals(cpf)) {
                    return prontuario;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public Prontuario getProntuarioPorId(Integer id) {

        return null;
    }

    public List<Prontuario> getLista() {
        em.getTransaction().begin();
        Query query = em.createQuery("select p from Prontuario p");
        List<Prontuario> listaDeProntuarios = query.getResultList();
        em.getTransaction().commit();
        return listaDeProntuarios;
    }
}
