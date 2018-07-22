
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Consulta;
import modelo.Convenio;
import modelo.DAOConsulta;
import modelo.DAOPaciente;
import modelo.Paciente;
import modelo.TipoConsulta;

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

        
        Consulta consulta1 = new Consulta();
        consulta1.setHorario("15:30");
        consulta1.setMedico("Dr. Testando");
        consulta1.setPaciente(paciente1);
        consulta1.setTipo(TipoConsulta.RETORNO);
        
        DAOConsulta daoConsulta = new DAOConsulta();
        daoConsulta.conectar();
        daoConsulta.inserir(consulta1);
        daoConsulta.desconectar();
        
    }
}
