package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAOPaciente;
import visao.FrmSecretaria;
import visao.FrmCadastroPaciente;

public class SecretariaControle {
    
    private FrmSecretaria visaoSecretaria;
    private ActionListener actionListener;
    
    public SecretariaControle(FrmSecretaria visaoSecretaria) {
        this.visaoSecretaria = visaoSecretaria;
        evtBotaoCadastrarPaciente();
    }
    
    public void evtBotaoCadastrarPaciente() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DAOPaciente daoPaciente = new DAOPaciente();
                FrmCadastroPaciente formCadastroPaciente = new FrmCadastroPaciente();
                CadastroPacienteControle controlePaciente = new CadastroPacienteControle(daoPaciente, formCadastroPaciente);                
                formCadastroPaciente.setVisible(true);
            }
        };
        visaoSecretaria.getBtnCadastrarPaciente().addActionListener(actionListener);
    }
}
