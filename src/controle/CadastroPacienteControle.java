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
import visao.FrmCadastroPaciente;

public class CadastroPacienteControle {

    private DAOPaciente daoPaciente;
    private FrmCadastroPaciente visaoCadastroPaciente;
    private ActionListener actionListener;

    public CadastroPacienteControle(DAOPaciente daoPaciente, FrmCadastroPaciente visaoCadastroPaciente) {
        this.daoPaciente = daoPaciente;
        this.visaoCadastroPaciente = visaoCadastroPaciente;
        evtBotaoCadastrar();
        evtBotaoCancelar();
    }

    private void evtBotaoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //Obtendo os dados inseridos na tela
                String nome = visaoCadastroPaciente.getTxtNome().getText();
                String cpf = visaoCadastroPaciente.getTxtCpf().getText();
                String rg = visaoCadastroPaciente.getTxtRg().getText();
                String dataString = visaoCadastroPaciente.getTxtDataNasc().getText();
                Date dataNasc = null;
                try {
                    dataNasc = Datas.formatoData.parse(dataString);
                } catch (ParseException ex) {
                    System.out.println("Erro na conversão (String para Date) formato digitado é incorreto.");
                }
                String endereco = visaoCadastroPaciente.getTxtEndereco().getText();
                String celular = visaoCadastroPaciente.getTxtCelular().getText();
                String email = visaoCadastroPaciente.getTxtEmail().getText();
                Convenio convenio = (Convenio) visaoCadastroPaciente.getCmbBoxConvenio().getSelectedItem();

                //Criando e persistindo um objeto com as informações
                Paciente paciente = new Paciente();
                paciente.setNome(nome);
                paciente.setCpf(cpf);
                paciente.setRg(rg);
                paciente.setDataNascimento(dataNasc);
                paciente.setEndereco(endereco);
                paciente.setTelefoneCelular(celular);
                paciente.setEmail(email);
                paciente.setTipoConvenio(convenio);

                if (validaCadastroPaciente(paciente)) {
                    daoPaciente.conectar();
                    daoPaciente.inserir(paciente);
                    daoPaciente.desconectar();
                    JOptionPane.showMessageDialog(null, "Paciente Cadastrado com Sucesso!", "Sucesso", 1);
                    visaoCadastroPaciente.dispose();
                }

            }
        };
        visaoCadastroPaciente.getBtnCadastrar().addActionListener(actionListener);
    }

    private boolean validaCadastroPaciente(Paciente paciente) {
        
        restauraCorCampos();
        
        if (paciente.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' é obrigatóorio!", "Erro na Validação", 0);
            visaoCadastroPaciente.getTxtNome().requestFocus();
            visaoCadastroPaciente.getTxtNome().setBackground(Color.yellow);
            return false;
        }
        if (paciente.getCpf().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'CPF' é obrigatóorio!", "Erro na Validação", 0);
            visaoCadastroPaciente.getTxtCpf().requestFocus();
            visaoCadastroPaciente.getTxtCpf().setBackground(Color.yellow);
            return false;
        }
        if (paciente.getRg().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'RG' é obrigatóorio!", "Erro na Validação", 0);
            visaoCadastroPaciente.getTxtRg().requestFocus();
            visaoCadastroPaciente.getTxtRg().setBackground(Color.yellow);
            return false;
        }

        if (paciente.getDataNascimento() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Data de Nascimento' é obrigatóorio!"
                    + "\nFormato correto de data: Dia/Mês/Ano", "Erro na Validação", 0);
            visaoCadastroPaciente.getTxtDataNasc().requestFocus();
            visaoCadastroPaciente.getTxtDataNasc().setBackground(Color.yellow);
            return false;
        }

        if (paciente.getEndereco().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Endereço' é obrigatóorio!", "Erro na Validação", 0);
            visaoCadastroPaciente.getTxtEndereco().requestFocus();
            visaoCadastroPaciente.getTxtEndereco().setBackground(Color.yellow);
            return false;
        }

        return true;
    }
    
    private void restauraCorCampos(){
        visaoCadastroPaciente.getTxtNome().setBackground(Color.white);
        visaoCadastroPaciente.getTxtCpf().setBackground(Color.white);
        visaoCadastroPaciente.getTxtRg().setBackground(Color.white);
        visaoCadastroPaciente.getTxtDataNasc().setBackground(Color.white);
        visaoCadastroPaciente.getTxtEndereco().setBackground(Color.white);
        visaoCadastroPaciente.getTxtCelular().setBackground(Color.white);
        visaoCadastroPaciente.getTxtEmail().setBackground(Color.white);
    }

    private void evtBotaoCancelar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoCadastroPaciente.dispose();
                }
            }
        };
        visaoCadastroPaciente.getBtnCancelar().addActionListener(actionListener);
    }

}
