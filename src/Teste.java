
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Convenio;
import modelo.Paciente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ronny
 */
public class Teste {
    public static void main(String[] args) {        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SaudeCiaJPA");
        EntityManager em = emf.createEntityManager();
        
        Paciente p = new Paciente();
        
        p.setNome("aeaeae");
        p.setCpf("061.973.129.05");
        p.setRg("10.072.229-1");
        p.setTipoConvenio(Convenio.PARTICULAR);
        
        Paciente g = new Paciente();
        g.setNome("aeaeae");
        g.setCpf("061.973.129.05");
        g.setRg("10.072.229-1");
        g.setTipoConvenio(Convenio.PLANO_DE_SAUDE);
        
        em.getTransaction().begin();
        em.persist(p);
        em.persist(g);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
}
