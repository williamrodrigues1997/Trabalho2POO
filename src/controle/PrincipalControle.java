package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAOPaciente;
import visao.FrmSecretaria;
import visao.FrmCadastroPaciente;
import visao.FrmPrincipal;

public class PrincipalControle {

    private FrmPrincipal visaoPrincipal;
    private ActionListener actionListener;

    public PrincipalControle(FrmPrincipal visaoPrincipal) {
        this.visaoPrincipal = visaoPrincipal;
        evtBotaoSecretaria();
    }

    public void evtBotaoSecretaria() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FrmSecretaria formSecretaria = new FrmSecretaria();
                SecretariaControle secretariaControle = new SecretariaControle(formSecretaria);
                formSecretaria.setVisible(true);
            }
        };
        visaoPrincipal.getBtnSecretaria().addActionListener(actionListener);
    }

}
