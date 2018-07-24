/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ronny
 */
public class DAOAdicionais {

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
}
