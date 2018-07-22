package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAOPaciente;
import visao.frmCadastroPaciente;

public class PacienteControle {
    private DAOPaciente daoPaciente;
    private frmCadastroPaciente visaoCadastroPaciente;
    private ActionListener actionListener;

    public PacienteControle(DAOPaciente daoPaciente, frmCadastroPaciente visaoCadastroPaciente) {
        this.daoPaciente = daoPaciente;
        this.visaoCadastroPaciente = visaoCadastroPaciente;
        evtBotaoCadastrar();
        evtBotaoCancelar();
    }
    
    public void evtBotaoCadastrar(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("CADASTRANDO...");
            }
        };
        visaoCadastroPaciente.getBtnCadastrar().addActionListener(actionListener);
    }
    
    public void evtBotaoCancelar(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("CANCELANDO...");
            }
        };
        visaoCadastroPaciente.getBtnCancelar().addActionListener(actionListener);
    }
    
}
