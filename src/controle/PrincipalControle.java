package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAOConsulta;
import modelo.DAOPaciente;
import visao.FrmSecretaria;
import visao.FrmPrincipal;

public class PrincipalControle {

    private FrmPrincipal visaoPrincipal;
    private ActionListener actionListener;

    public PrincipalControle(FrmPrincipal visaoPrincipal) {
        this.visaoPrincipal = visaoPrincipal;
        evtBotaoSecretaria();
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

}
