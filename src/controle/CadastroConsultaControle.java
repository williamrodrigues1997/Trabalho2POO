package controle;

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

    public void evtBotaoAgendar() {
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
                    Logger.getLogger(CadastroConsultaControle.class.getName()).log(Level.SEVERE, null, ex);
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
                System.out.println("ID: " + paciente.getId());
                consulta.setTipo(tipoConsulta);

                daoConsulta.conectar();
                daoConsulta.inserir(consulta);
                daoConsulta.desconectar();
                JOptionPane.showMessageDialog(null, "Consulta Agendada com Sucesso!", "Sucesso", 1);
                visaoCadastroConsulta.dispose();
            }
        };
        visaoCadastroConsulta.getBtnAgendar().addActionListener(actionListener);
    }

    public void evtBotaoCancelar() {
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
