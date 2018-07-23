package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAOConsulta;
import modelo.DAOPaciente;
import visao.FrmCadastroConsulta;
import visao.FrmSecretaria;
import visao.FrmCadastroPaciente;

public class SecretariaControle {

    private FrmSecretaria visaoSecretaria;
    private ActionListener actionListener;

    public SecretariaControle(FrmSecretaria visaoSecretaria) {
        this.visaoSecretaria = visaoSecretaria;
        evtBotaoCadastrarPaciente();
        evtBotaoFechar();
        evtBotaoAgendarConsulta();
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

    public void evtBotaoAgendarConsulta() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DAOConsulta daoConsulta = new DAOConsulta();
                FrmCadastroConsulta formCadastroConsulta = new FrmCadastroConsulta();
                CadastroConsultaControle controleConsulta = new CadastroConsultaControle(daoConsulta, formCadastroConsulta);
                formCadastroConsulta.setVisible(true);
            }
        };
        visaoSecretaria.getBtnAgendarConsulta().addActionListener(actionListener);
    }

    public void evtBotaoFechar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoSecretaria.dispose();
            }
        };
        visaoSecretaria.getBtnFechar().addActionListener(actionListener);
    }
}
