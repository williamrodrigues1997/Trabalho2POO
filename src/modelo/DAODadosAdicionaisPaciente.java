package modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Classe responsável pelo CRUD relacionado ao POJO DadosAdicionaisPaciente.
 */
public class DAODadosAdicionaisPaciente {

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

    public void inserir(DadosAdicionaisPaciente dadosAdicionais) {
        em.getTransaction().begin();
        em.persist(dadosAdicionais);
        em.getTransaction().commit();
    }

    public boolean alterar(DadosAdicionaisPaciente dadosAdicionaisNovo) {
        em.getTransaction().begin();
        DadosAdicionaisPaciente dadosAdicionais = new DadosAdicionaisPaciente();
        dadosAdicionais = em.find(dadosAdicionais.getClass(), dadosAdicionaisNovo.getId());

        if (dadosAdicionais != null) {
            dadosAdicionais.setFuma(dadosAdicionaisNovo.isFuma());
            dadosAdicionais.setBebe(dadosAdicionaisNovo.isBebe());
            dadosAdicionais.setDiabete(dadosAdicionaisNovo.isDiabete());
            dadosAdicionais.setColesterol(dadosAdicionaisNovo.isColesterol());
            dadosAdicionais.setDoencaCardiaca(dadosAdicionaisNovo.isDoencaCardiaca());

            dadosAdicionais.setCirurgias(dadosAdicionaisNovo.getCirurgias());
            dadosAdicionais.setAlergias(dadosAdicionaisNovo.getAlergias());
            em.getTransaction().commit();
            return true;
        }
        //Id nao encontrado
        return false;
    }

    public boolean remover(Integer id) {
        em.getTransaction().begin();
        DadosAdicionaisPaciente dadosAdicionais = new DadosAdicionaisPaciente();
        dadosAdicionais = em.find(dadosAdicionais.getClass(), id);
        if (dadosAdicionais != null) {
            em.remove(dadosAdicionais);
            em.getTransaction().commit();
            return true;
        }
        //Id nao encontrado
        return false;
    }

    public DadosAdicionaisPaciente getDadosAdicionaisPorId(int id) {

        return null;
    }

}
