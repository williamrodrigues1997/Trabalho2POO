package controle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Consulta;
import modelo.DAOConsulta;
import modelo.DAOPaciente;
import modelo.Paciente;
import modelo.TipoConsulta;
import visao.FrmCadastroConsulta;

public class CadastroConsultaControle {

    private DAOConsulta daoConsulta;
    private FrmCadastroConsulta visaoCadastroConsulta;
    private ActionListener actionListener;

    public CadastroConsultaControle(DAOConsulta daoConsulta, FrmCadastroConsulta visaoCadastroCOnsulta) {
        this.daoConsulta = daoConsulta;
        this.visaoCadastroConsulta = visaoCadastroCOnsulta;
        evtBotaoCancelar();
        evtBotaoAgendar();
    }

    private void evtBotaoAgendar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Cadastrando consulta...");

                //Obtendo os dados inseridos na tela
                String dataString = visaoCadastroConsulta.getTxtData().getText();
                Date data = null;
                try {
                    data = Datas.formatoData.parse(dataString);
                } catch (ParseException ex) {
                    System.out.println("Erro na conversão (String para Date) formato digitado é incorreto.");
                }
                String horario = visaoCadastroConsulta.getTxtHorario().getText();
                String medico = visaoCadastroConsulta.getTxtMedico().getText();
                String cpfPaciente = visaoCadastroConsulta.getTxtPaciente().getText();
                DAOPaciente daoPaciente = new DAOPaciente();
                daoPaciente.conectar();
                Paciente paciente = daoPaciente.buscaPorCpf(cpfPaciente);
                daoPaciente.desconectar();
                TipoConsulta tipoConsulta = (TipoConsulta) visaoCadastroConsulta.getCmbBoxTipoConsulta().getSelectedItem();

                //Criando e persistindo um objeto com as informações
                Consulta consulta = new Consulta();
                consulta.setData(data);
                consulta.setHorario(horario);
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consulta.setTipo(tipoConsulta);

                if (validaCadastroConsulta(consulta)) {
                    daoConsulta.conectar();
                    daoConsulta.inserir(consulta);
                    daoConsulta.desconectar();
                    JOptionPane.showMessageDialog(null, "Consulta Agendada com Sucesso!", "Sucesso", 1);
                    visaoCadastroConsulta.dispose();
                }
            }
        };
        visaoCadastroConsulta.getBtnAgendar().addActionListener(actionListener);
    }

    private boolean validaCadastroConsulta(Consulta consulta) {
        
        restauraCorCampos();
        
        if(consulta.getData() == null){
            JOptionPane.showMessageDialog(null, "O campo 'Data' é obrigatóorio!"
                    + "\nFormato correto de data: Dia/Mês/Ano", "Erro na Validação", 0);
            visaoCadastroConsulta.getTxtData().requestFocus();
            visaoCadastroConsulta.getTxtData().setBackground(Color.yellow);
            return false;
        }
        
        if(consulta.getHorario().equals("")){
            JOptionPane.showMessageDialog(null, "O campo 'Horário' é obrigatóorio!", "Erro na Validação", 0);
            visaoCadastroConsulta.getTxtHorario().requestFocus();
            visaoCadastroConsulta.getTxtHorario().setBackground(Color.yellow);
            return false;
        }
        
        if(consulta.getMedico().equals("")){
            JOptionPane.showMessageDialog(null, "O campo 'Médico' é obrigatóorio!", "Erro na Validação", 0);
            visaoCadastroConsulta.getTxtMedico().requestFocus();
            visaoCadastroConsulta.getTxtMedico().setBackground(Color.yellow);
            return false;
        }
        
        if(consulta.getPaciente() == null){
            JOptionPane.showMessageDialog(null, "O campo 'CPF Paciente' é obrigatóorio! "
                    + "\nInforme o CPF de um Paciente já cadastrado no sistema.", "Erro na Validação", 0);
            visaoCadastroConsulta.getTxtHorario().requestFocus();
            visaoCadastroConsulta.getTxtHorario().setBackground(Color.yellow);
            return false;
        }
        
        return true;
    }
    
    private void restauraCorCampos(){
        visaoCadastroConsulta.getTxtData().setBackground(Color.white);
        visaoCadastroConsulta.getTxtHorario().setBackground(Color.white);
        visaoCadastroConsulta.getTxtMedico().setBackground(Color.white);
        visaoCadastroConsulta.getTxtPaciente().setBackground(Color.white);
    }

    private void evtBotaoCancelar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoCadastroConsulta.dispose();
                }

            }
        };
        visaoCadastroConsulta.getBtnCancelar().addActionListener(actionListener);
    }
}
