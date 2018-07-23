package Main;

import controle.PrincipalControle;
import visao.FrmPrincipal;

public class PrincipalTeste {

    public static void main(String[] args) {

        FrmPrincipal formPrincipal = new FrmPrincipal();
        PrincipalControle controlePrincipal = new PrincipalControle(formPrincipal);
        formPrincipal.setVisible(true);

    }

}
