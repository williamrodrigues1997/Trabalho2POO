
import java.util.List;
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

        Paciente paciente2 = new Paciente();
        paciente2.setNome("Ronny");

        DAOPaciente daoPaciente = new DAOPaciente();
        daoPaciente.conectar();
        
        daoPaciente.inserir(paciente1);
        daoPaciente.inserir(paciente2);
        
        System.out.println("Teste busca: " + daoPaciente.buscaPorId(1).getNome());

        List<Paciente> listaPacientes;
        listaPacientes = daoPaciente.getLista();
        System.out.println("\nTeste lista: " + listaPacientes.get(1).getNome());
        System.out.println("Teste lista: " + listaPacientes.get(0).getNome());
        
        daoPaciente.desconectar();

    }
}
