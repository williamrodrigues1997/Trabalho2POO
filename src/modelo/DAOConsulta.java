package modelo;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Classe responsável pelo CRUD relacionado ao POJO Consulta.
 */
public class DAOConsulta {

    private EntityManagerFactory emf;
    private EntityManager em;
    
    public void conectar(){
        this.emf = Persistence.createEntityManagerFactory("SaudeCiaJPA");
        this.em = emf.createEntityManager();
    }
    
    public void desconectar(){
        this.em.close();
        this.emf.close();
    }

    public void inserir(Consulta consulta) {
        em.getTransaction().begin();
        em.persist(consulta);
        em.getTransaction().commit();   
    }

    public boolean alterar(Integer id, Consulta novaConsulta) {
        em.getTransaction().begin();
        Consulta consulta = new Consulta();
        consulta = em.find(consulta.getClass(), id);
        if( consulta != null){
            consulta.setData(novaConsulta.getData());
            consulta.setHorario(novaConsulta.getHorario());
            consulta.setMedico(novaConsulta.getMedico());
            consulta.setPaciente(novaConsulta.getPaciente());
            consulta.setTipo(novaConsulta.getTipo());
            return true;
        }
        //Id nao encontrado
        return false;        
    }

    public boolean remover(Integer id) {
        em.getTransaction().begin();
        Consulta consulta = new Consulta(); 
        consulta = em.find(consulta.getClass(), id);
        if(consulta != null){
            em.remove(consulta);
            em.getTransaction().commit();
            return true;
        }
        //Id nao encontrado
        return false;
    }

    public Consulta buscaPorId(Integer id){
        em.getTransaction().begin();
        Consulta consulta = new Consulta(); 
        consulta = em.find(consulta.getClass(), id);
        if(consulta != null){
            em.getTransaction().commit();
            return consulta;
        }
        //Id nao encontrado
        return null;
    }
}
