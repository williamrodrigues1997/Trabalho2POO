
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Convenio;
import modelo.DAOPaciente;
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
public class PrincipalTeste {
    public static void main(String[] args) {        
        
        Paciente paciente1 = new Paciente();
        
        paciente1.setNome("William");
        paciente1.setCpf("061.973.129.05");
        paciente1.setRg("10.072.229-1");
        paciente1.setTipoConvenio(Convenio.PARTICULAR);
        
        Paciente paciente2 = new Paciente();
        paciente2.setNome("Ronny");
        paciente2.setCpf("061.973.129.05");
        paciente2.setRg("10.072.229-1");
        paciente2.setTipoConvenio(Convenio.PLANO_DE_SAUDE);
        
        Paciente paciente3 = new Paciente();
        paciente3.setNome("Donizete");
        paciente3.setCpf("061.973.129.05");
        paciente3.setRg("10.072.229-1");
        paciente3.setTipoConvenio(Convenio.PLANO_DE_SAUDE);
        
        DAOPaciente daoPaciente = new DAOPaciente();
        daoPaciente.conectar();
        daoPaciente.inserir(paciente1);
        daoPaciente.inserir(paciente3);
        daoPaciente.alterar(paciente1.getId(), paciente2); //Substitui os dados do paciente 1 pelos dados do paciente 2
        daoPaciente.buscaPorId(paciente3.getId());
        daoPaciente.remover(paciente3.getId()); //Remove paciente 3
        daoPaciente.desconectar();
    }
}
