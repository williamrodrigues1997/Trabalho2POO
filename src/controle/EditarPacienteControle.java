package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Convenio;
import modelo.DAOPaciente;
import modelo.Paciente;
import visao.FrmEditarPaciente;

public class EditarPacienteControle {

    private DAOPaciente daoPaciente;    
    private FrmEditarPaciente visaoEditarPaciente;
    private ActionListener actionListener;

    public EditarPacienteControle(DAOPaciente daoPaciente, FrmEditarPaciente visaoEditarPaciente) {
        this.daoPaciente = daoPaciente;
        this.visaoEditarPaciente = visaoEditarPaciente;
        evtBotaoCancelar();
        evtBotaoSalvar();
    }
    
    private void evtBotaoSalvar(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer id = Integer.parseInt(visaoEditarPaciente.getTxtId().getText());
                String nome = visaoEditarPaciente.getTxtNome().getText();
                String cpf = visaoEditarPaciente.getTxtCpf().getText();
                String rg = visaoEditarPaciente.getTxtRg().getText();
                Date dataNasc = null;
                try {
                    dataNasc = Datas.formatoData.parse(visaoEditarPaciente.getTxtDataNasc().getText());
                } catch (ParseException ex) {
                    Logger.getLogger(EditarPacienteControle.class.getName()).log(Level.SEVERE, null, ex);
                }
                String endereco = visaoEditarPaciente.getTxtEndereco().getText();
                String celular = visaoEditarPaciente.getTxtCelular().getText();
                String email = visaoEditarPaciente.getTxtEmail().getText();
                Convenio tipoConvenio = (Convenio) visaoEditarPaciente.getCmbBoxConvenio().getSelectedItem();
                
                Paciente novoPaciente = new Paciente();
                novoPaciente.setId(id);
                novoPaciente.setNome(nome);
                novoPaciente.setCpf(cpf);
                novoPaciente.setRg(rg);
                novoPaciente.setDataNascimento(dataNasc);
                novoPaciente.setEndereco(endereco);
                novoPaciente.setTelefoneCelular(celular);
                novoPaciente.setEmail(email);
                novoPaciente.setTipoConvenio(tipoConvenio);
                
                daoPaciente.conectar();
                daoPaciente.alterar(novoPaciente);
                daoPaciente.desconectar();
                JOptionPane.showMessageDialog(null, "Paciente Editado com Sucesso!", "Sucesso", 1);                
                visaoEditarPaciente.dispose();
                
            }
        };
        visaoEditarPaciente.getBtnSalvar().addActionListener(actionListener);
    }
    
    private void evtBotaoCancelar(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar a edição?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoEditarPaciente.dispose();
                }
            }
        };
        visaoEditarPaciente.getBtnCancelar().addActionListener(actionListener);
    }
}
