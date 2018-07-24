package controle;

import java.awt.Color;
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

    private void evtBotaoSalvar() {
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
                    System.out.println("Erro na conversão (String para Date) formato digitado é incorreto.");
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

                if (validaEdicaoPaciente(novoPaciente)) {
                    daoPaciente.conectar();
                    daoPaciente.alterar(novoPaciente);
                    daoPaciente.desconectar();
                    JOptionPane.showMessageDialog(null, "Paciente Editado com Sucesso!", "Sucesso", 1);
                    visaoEditarPaciente.dispose();
                }

            }
        };
        visaoEditarPaciente.getBtnSalvar().addActionListener(actionListener);
    }

    private boolean validaEdicaoPaciente(Paciente paciente) {
        
        restauraCorCampos();
        
        if (paciente.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' é obrigatóorio!", "Erro na Validação", 0);
            visaoEditarPaciente.getTxtNome().requestFocus();
            visaoEditarPaciente.getTxtNome().setBackground(Color.yellow);
            return false;
        }
        if (paciente.getCpf().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'CPF' é obrigatóorio!", "Erro na Validação", 0);
            visaoEditarPaciente.getTxtCpf().requestFocus();
            visaoEditarPaciente.getTxtCpf().setBackground(Color.yellow);
            return false;
        }
        if (paciente.getRg().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'RG' é obrigatóorio!", "Erro na Validação", 0);
            visaoEditarPaciente.getTxtRg().requestFocus();
            visaoEditarPaciente.getTxtRg().setBackground(Color.yellow);
            return false;
        }

        if (paciente.getDataNascimento() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Data de Nascimento' é obrigatóorio!"
                    + "\nFormato correto de data: Dia/Mês/Ano", "Erro na Validação", 0);
            visaoEditarPaciente.getTxtDataNasc().requestFocus();
            visaoEditarPaciente.getTxtDataNasc().setBackground(Color.yellow);
            return false;
        }

        if (paciente.getEndereco().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Endereço' é obrigatóorio!", "Erro na Validação", 0);
            visaoEditarPaciente.getTxtEndereco().requestFocus();
            visaoEditarPaciente.getTxtEndereco().setBackground(Color.yellow);
            return false;
        }

        return true;
    }
    
    private void restauraCorCampos(){
        visaoEditarPaciente.getTxtNome().setBackground(Color.white);
        visaoEditarPaciente.getTxtCpf().setBackground(Color.white);
        visaoEditarPaciente.getTxtRg().setBackground(Color.white);
        visaoEditarPaciente.getTxtDataNasc().setBackground(Color.white);
        visaoEditarPaciente.getTxtEndereco().setBackground(Color.white);
        visaoEditarPaciente.getTxtCelular().setBackground(Color.white);
        visaoEditarPaciente.getTxtEmail().setBackground(Color.white);
    }

    private void evtBotaoCancelar() {
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
