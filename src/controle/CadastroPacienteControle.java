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

    public void evtBotaoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Cadastrando Paciente...");

                //Obtendo os dados inseridos na tela
                String nome = visaoCadastroPaciente.getTxtNome().getText();
                String cpf = visaoCadastroPaciente.getTxtCpf().getText();
                String rg = visaoCadastroPaciente.getTxtRg().getText();
                String dataString = visaoCadastroPaciente.getTxtDataNasc().getText();
                Date dataNasc = null;
                try {
                    dataNasc = Datas.formatoData.parse(dataString);
                } catch (ParseException ex) {
                    Logger.getLogger(CadastroPacienteControle.class.getName()).log(Level.SEVERE, null, ex);
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

                daoPaciente.conectar();
                daoPaciente.inserir(paciente);
                daoPaciente.desconectar();
                JOptionPane.showMessageDialog(null, "Paciente Cadastrado com Sucesso!", "Sucesso", 1);
                visaoCadastroPaciente.dispose();

            }
        };
        visaoCadastroPaciente.getBtnCadastrar().addActionListener(actionListener);
    }

    public void evtBotaoCancelar() {
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
