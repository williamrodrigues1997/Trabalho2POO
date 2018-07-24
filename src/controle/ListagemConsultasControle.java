package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Consulta;
import modelo.DAOConsulta;
import modelo.DAOPaciente;
import visao.FrmCadastroConsulta;
import visao.FrmEditarConsulta;
import visao.FrmListagemConsultas;

public class ListagemConsultasControle {

    private DAOConsulta daoConsulta;
    private DAOPaciente daoPaciente;
    private FrmListagemConsultas visaoListagem;
    private ActionListener actionListener;

    public ListagemConsultasControle(DAOConsulta daoConsulta, DAOPaciente daoPaciente, FrmListagemConsultas visaoListagem) {
        this.daoConsulta = daoConsulta;
        this.daoPaciente = daoPaciente;
        this.visaoListagem = visaoListagem;
        preencheTabela();
        evtBotaoEditar();
        evtBotaoFechar();
        evtBotaoDesmarcar();
        evtBotaoNova();
        evtBotaoAtualizar();
    }

    private void preencheTabela() {
        DefaultTableModel modelo = (DefaultTableModel) visaoListagem.getjTblConsultas().getModel();
        modelo.setNumRows(0);
        daoConsulta.conectar();
        for (Consulta consulta : daoConsulta.getLista()) {
            modelo.addRow(new Object[]{
                consulta.getId(),
                Datas.formatoData.format(consulta.getData()),
                consulta.getHorario(),
                consulta.getMedico(),
                consulta.getPaciente().getNome(),
                consulta.getPaciente().getCpf(),
                consulta.getTipo().getDescricao()
            });
        }
        daoConsulta.desconectar();
    }

    private void evtBotaoEditar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int linha = visaoListagem.getjTblConsultas().getSelectedRow();
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Consulta selecionada!", "Erro", 0);
                } else {
                    String id = visaoListagem.getjTblConsultas().getValueAt(linha, 0).toString();
                    String data = visaoListagem.getjTblConsultas().getValueAt(linha, 1).toString();
                    String horario = visaoListagem.getjTblConsultas().getValueAt(linha, 2).toString();
                    String medico = visaoListagem.getjTblConsultas().getValueAt(linha, 3).toString();
                    String cpfPaciente = visaoListagem.getjTblConsultas().getValueAt(linha, 5).toString();
                    String tipoConsultaString = visaoListagem.getjTblConsultas().getValueAt(linha, 6).toString();

                    FrmEditarConsulta formEditarConsulta = new FrmEditarConsulta();
                    formEditarConsulta.getTxtId().setText(id);
                    formEditarConsulta.getTxtData().setText(data);
                    formEditarConsulta.getTxtHorario().setText(horario);
                    formEditarConsulta.getTxtMedico().setText(medico);
                    formEditarConsulta.getTxtCpfPaciente().setText(cpfPaciente);
                    if (tipoConsultaString.equals("Normal (1h)")) {
                        formEditarConsulta.getCmbBoxTipoConsulta().setSelectedIndex(0);
                    } else {
                        formEditarConsulta.getCmbBoxTipoConsulta().setSelectedIndex(1);
                    }
                    EditarConsultaControle controleEditar = new EditarConsultaControle(daoConsulta, formEditarConsulta);
                    formEditarConsulta.setVisible(true);
                }
            }
        };
        visaoListagem.getBtnEditar().addActionListener(actionListener);
    }

    private void evtBotaoFechar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoListagem.dispose();
            }
        };
        visaoListagem.getBtnFechar().addActionListener(actionListener);
    }

    private void evtBotaoDesmarcar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int linha = visaoListagem.getjTblConsultas().getSelectedRow();
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Consulta selecionada!", "Erro", 0);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Confirma a exclusão da Consulta?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        Integer id = (Integer) visaoListagem.getjTblConsultas().getValueAt(linha, 0);
                        daoConsulta.conectar();
                        daoConsulta.remover(id);
                        daoConsulta.desconectar();
                        preencheTabela();
                    }
                }
            }
        };
        visaoListagem.getBtnDesmarcar().addActionListener(actionListener);
    }
    
    private void evtBotaoNova(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FrmCadastroConsulta formCadastroConsulta = new FrmCadastroConsulta();
                CadastroConsultaControle controleConsulta = new CadastroConsultaControle(daoConsulta, formCadastroConsulta);
                formCadastroConsulta.setVisible(true);
            }
        };
        visaoListagem.getBtnNova().addActionListener(actionListener);
    }
    
    private void evtBotaoAtualizar(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                preencheTabela();
            }
        };
        visaoListagem.getBtnAtualizar().addActionListener(actionListener);
    }

}
