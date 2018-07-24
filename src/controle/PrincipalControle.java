package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAOConsulta;
import modelo.DAOPaciente;
import visao.FrmMedico;
import visao.FrmSecretaria;
import visao.FrmPrincipal;

public class PrincipalControle {

    private FrmPrincipal visaoPrincipal;
    private ActionListener actionListener;

    public PrincipalControle(FrmPrincipal visaoPrincipal) {
        this.visaoPrincipal = visaoPrincipal;
        evtBotaoSecretaria();
        evtBotaoMedico();
    }

    private void evtBotaoSecretaria() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FrmSecretaria formSecretaria = new FrmSecretaria();
                DAOPaciente daoPaciente = new DAOPaciente();
                DAOConsulta daoConsulta = new DAOConsulta();
                SecretariaControle secretariaControle = new SecretariaControle(daoPaciente, daoConsulta, formSecretaria);
                formSecretaria.setVisible(true);
            }
        };
        visaoPrincipal.getBtnSecretaria().addActionListener(actionListener);
    }

    public void evtBotaoMedico() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmMedico formMedico = new FrmMedico();
                DAOPaciente daoPaciente = new DAOPaciente();
                DAOConsulta daoConsulta = new DAOConsulta();

                MedicoControle medicoControle = new MedicoControle(daoPaciente, daoConsulta, formMedico);
                formMedico.setVisible(true);
            }
        };
        visaoPrincipal.getBtnMedico().addActionListener(actionListener);
    }

}
