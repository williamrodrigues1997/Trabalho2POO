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
                    Logger.getLogger(EditarPacienteControle.class.getName()).log(Level.SEVERE, null, ex);
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

                daoConsulta.conectar();
                daoConsulta.alterar(novaConsulta);
                daoConsulta.desconectar();
                daoPaciente.desconectar();
                JOptionPane.showMessageDialog(null, "Consulta Editada com Sucesso!", "Sucesso", 1);
                visaoEditarConsulta.dispose();

            }
        };
        visaoEditarConsulta.getBtnSalvar().addActionListener(actionlistener);
    }
}
