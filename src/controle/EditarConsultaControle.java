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
import visao.FrmEditarConsulta;

public class EditarConsultaControle {

    private DAOConsulta daoConsulta;
    private FrmEditarConsulta visaoEditarConsulta;
    private ActionListener actionlistener;

    public EditarConsultaControle(DAOConsulta daoConsulta, FrmEditarConsulta visaoEditarConsulta) {
        this.daoConsulta = daoConsulta;
        this.visaoEditarConsulta = visaoEditarConsulta;
        evtBotaoCancelar();
        evtBotaoSalvar();
    }

    private void evtBotaoCancelar() {
        actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar a edição?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoEditarConsulta.dispose();
                }
            }
        };
        visaoEditarConsulta.getBtnCancelar().addActionListener(actionlistener);
    }

    private void evtBotaoSalvar() {
        actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer id = Integer.parseInt(visaoEditarConsulta.getTxtId().getText());
                Date data = null;
                try {
                    data = Datas.formatoData.parse(visaoEditarConsulta.getTxtData().getText());
                } catch (ParseException ex) {
                    System.out.println("Erro na conversão (String para Date) formato digitado é incorreto.");
                }
                String horario = visaoEditarConsulta.getTxtHorario().getText();
                String medico = visaoEditarConsulta.getTxtMedico().getText();
                String cpfString = visaoEditarConsulta.getTxtCpfPaciente().getText();
                DAOPaciente daoPaciente = new DAOPaciente();
                daoPaciente.conectar();
                Paciente paciente = daoPaciente.buscaPorCpf(cpfString);
                TipoConsulta tipoConsulta = (TipoConsulta) visaoEditarConsulta.getCmbBoxTipoConsulta().getSelectedItem();

                Consulta novaConsulta = new Consulta();
                novaConsulta.setId(id);
                novaConsulta.setData(data);
                novaConsulta.setHorario(horario);
                novaConsulta.setMedico(medico);
                novaConsulta.setPaciente(paciente);
                novaConsulta.setTipo(tipoConsulta);

                if (validaEdicaoConsulta(novaConsulta)) {
                    daoConsulta.conectar();
                    daoConsulta.alterar(novaConsulta);
                    daoConsulta.desconectar();
                    daoPaciente.desconectar();
                    JOptionPane.showMessageDialog(null, "Consulta Editada com Sucesso!", "Sucesso", 1);
                    visaoEditarConsulta.dispose();
                }

            }
        };
        visaoEditarConsulta.getBtnSalvar().addActionListener(actionlistener);
    }

    private boolean validaEdicaoConsulta(Consulta consulta) {

        restauraCorCampos();
        
        if (consulta.getData() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Data' é obrigatóorio!"
                    + "\nFormato correto de data: Dia/Mês/Ano", "Erro na Validação", 0);
            visaoEditarConsulta.getTxtData().requestFocus();
            visaoEditarConsulta.getTxtData().setBackground(Color.yellow);
            return false;
        }

        if (consulta.getHorario().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Horário' é obrigatóorio!", "Erro na Validação", 0);
            visaoEditarConsulta.getTxtHorario().requestFocus();
            visaoEditarConsulta.getTxtHorario().setBackground(Color.yellow);
            return false;
        }

        if (consulta.getMedico().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Médico' é obrigatóorio!", "Erro na Validação", 0);
            visaoEditarConsulta.getTxtMedico().requestFocus();
            visaoEditarConsulta.getTxtMedico().setBackground(Color.yellow);
            return false;
        }

        if (consulta.getPaciente() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'CPF Paciente' é obrigatóorio! "
                    + "\nInforme o CPF de um Paciente já cadastrado no sistema.", "Erro na Validação", 0);
            visaoEditarConsulta.getTxtCpfPaciente().requestFocus();
            visaoEditarConsulta.getTxtCpfPaciente().setBackground(Color.yellow);
            return false;
        }

        return true;
    }
    
    private void restauraCorCampos(){
        visaoEditarConsulta.getTxtData().setBackground(Color.white);
        visaoEditarConsulta.getTxtHorario().setBackground(Color.white);
        visaoEditarConsulta.getTxtMedico().setBackground(Color.white);
        visaoEditarConsulta.getTxtCpfPaciente().setBackground(Color.white);
    }
}
