package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAOConsulta;
import modelo.DAOPaciente;
import visao.FrmCadastroConsulta;
import visao.FrmSecretaria;
import visao.FrmCadastroPaciente;
import visao.FrmListagemPacientes;

public class SecretariaControle {

    private DAOPaciente daoPaciente;
    private DAOConsulta daoConsulta;
    private FrmSecretaria visaoSecretaria;
    private ActionListener actionListener;

    public SecretariaControle(DAOPaciente daoPaciente, DAOConsulta daoConsulta, FrmSecretaria visaoSecretaria) {
        this.daoPaciente = daoPaciente;
        this.daoConsulta = daoConsulta;
        this.visaoSecretaria = visaoSecretaria;
        evtBotaoCadastrarPaciente();
        evtBotaoFechar();
        evtBotaoAgendarConsulta();
        evtBotaoPacientesCadastrados();
    }

    public void evtBotaoCadastrarPaciente() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                daoPaciente = new DAOPaciente();
                FrmCadastroPaciente formCadastroPaciente = new FrmCadastroPaciente();
                CadastroPacienteControle controlePaciente = new CadastroPacienteControle(daoPaciente, formCadastroPaciente);
                formCadastroPaciente.setVisible(true);
            }
        };
        visaoSecretaria.getBtnCadastrarPaciente().addActionListener(actionListener);
    }
    
    public void evtBotaoPacientesCadastrados(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                daoPaciente = new DAOPaciente();
                FrmListagemPacientes formListagemPacientes = new FrmListagemPacientes();
                ListagemPacientesControle controleListagem = new ListagemPacientesControle(daoPaciente, formListagemPacientes);
                formListagemPacientes.setVisible(true);
                
            }
        };
        visaoSecretaria.getBtnPacientesCadastrados().addActionListener(actionListener);
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
