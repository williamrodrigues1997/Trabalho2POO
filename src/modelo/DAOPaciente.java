package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * Classe responsável pelo CRUD relacionado ao POJO Paciente.
 */
public class DAOPaciente {

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

    public void inserir(Paciente paciente) {
        em.getTransaction().begin();
        em.persist(paciente);
        em.getTransaction().commit();
    }

    public boolean alterar(Paciente pacienteNovo) {
        em.getTransaction().begin();
        Paciente paciente = new Paciente();
        paciente = em.find(paciente.getClass(), pacienteNovo.getId());
        if (paciente != null) {
            paciente.setNome(pacienteNovo.getNome());
            paciente.setCpf(pacienteNovo.getCpf());
            paciente.setRg(pacienteNovo.getRg());
            paciente.setDataNascimento(pacienteNovo.getDataNascimento());
            paciente.setEndereco(pacienteNovo.getEndereco());
            paciente.setTelefoneCelular(pacienteNovo.getTelefoneCelular());
            paciente.setEmail(pacienteNovo.getEmail());
            paciente.setTipoConvenio(pacienteNovo.getTipoConvenio());
            em.getTransaction().commit();
            return true;
        }
        //Id nao encontrado
        return false;
    }

    public boolean remover(Integer id) {
        em.getTransaction().begin();
        Paciente paciente = new Paciente();
        paciente = em.find(paciente.getClass(), id);
        if (paciente != null) {
            em.remove(paciente);
            em.getTransaction().commit();
            return true;
        }
        //Id nao encontrado
        return false;
    }

    public Paciente buscaPorId(Integer id) {
        em.getTransaction().begin();
        Paciente paciente = new Paciente();
        paciente = em.find(paciente.getClass(), id);
        if (paciente != null) {
            em.getTransaction().commit();
            return paciente;
        }
        //Id nao encontrado
        return null;
    }

    public Paciente buscaPorCpf(String cpf) {
        List<Paciente> listaDePacientes = getLista();
        int tam = listaDePacientes.size();
        if (tam > 0) {
            for (Paciente paciente : listaDePacientes) {
                if (paciente.getCpf().equals(cpf)) {
                    return paciente;
                }
            }
            return null;
        }else{
            return null;
        }
    }

    public List<Paciente> getLista() {        
        em.getTransaction().begin();
        Query query = em.createQuery("select p from Paciente p");
        List<Paciente> listaDePacientes = query.getResultList();
        em.getTransaction().commit();
        return listaDePacientes;
    }

}
