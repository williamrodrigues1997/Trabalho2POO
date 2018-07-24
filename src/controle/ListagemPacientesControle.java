package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DAOPaciente;
import modelo.Paciente;
import visao.FrmCadastroPaciente;
import visao.FrmEditarPaciente;
import visao.FrmListagemPacientes;

public class ListagemPacientesControle {

    private DAOPaciente daoPaciente;
    private FrmListagemPacientes visaoListagem;
    private ActionListener actionListener;

    public ListagemPacientesControle(DAOPaciente daoPaciente, FrmListagemPacientes visaoListagem) {
        this.daoPaciente = daoPaciente;
        this.visaoListagem = visaoListagem;
        preencheTabela();
        evtBotaoFechar();
        evtBotaoEditar();
        evtBotaoAtualizar();
        evtBotaoExcluir();
        evtBotaoNovo();
    }

    private void preencheTabela() {
        DefaultTableModel modelo = (DefaultTableModel) visaoListagem.getjTblPacientes().getModel();
        modelo.setNumRows(0);
        daoPaciente.conectar();
        for (Paciente paciente : daoPaciente.getLista()) {
            modelo.addRow(new Object[]{
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getRg(),
                Datas.formatoData.format(paciente.getDataNascimento()),
                paciente.getEndereco(),
                paciente.getTelefoneCelular(),
                paciente.getEmail(),
                paciente.getTipoConvenio().getDescricao()
            });
        }
        daoPaciente.desconectar();
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

    private void evtBotaoEditar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int linha = visaoListagem.getjTblPacientes().getSelectedRow();
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum Paciente selecionado!", "Erro", 0);
                } else {
                    String id = visaoListagem.getjTblPacientes().getValueAt(linha, 0).toString();
                    String nome = visaoListagem.getjTblPacientes().getValueAt(linha, 1).toString();
                    String cpf = visaoListagem.getjTblPacientes().getValueAt(linha, 2).toString();
                    String rg = visaoListagem.getjTblPacientes().getValueAt(linha, 3).toString();
                    String dataNasc = visaoListagem.getjTblPacientes().getValueAt(linha, 4).toString();
                    String endereco = visaoListagem.getjTblPacientes().getValueAt(linha, 5).toString();
                    String celular = visaoListagem.getjTblPacientes().getValueAt(linha, 6).toString();
                    String email = visaoListagem.getjTblPacientes().getValueAt(linha, 7).toString();
                    String convenioString = visaoListagem.getjTblPacientes().getValueAt(linha, 8).toString();

                    FrmEditarPaciente formEditar = new FrmEditarPaciente();
                    formEditar.getTxtId().setText(id);
                    formEditar.getTxtNome().setText(nome);
                    formEditar.getTxtCpf().setText(cpf);
                    formEditar.getTxtRg().setText(rg);
                    formEditar.getTxtDataNasc().setText(dataNasc);
                    formEditar.getTxtEndereco().setText(endereco);
                    formEditar.getTxtCelular().setText(celular);
                    formEditar.getTxtEmail().setText(email);
                    if (convenioString.equals("Particular")) {
                        formEditar.getCmbBoxConvenio().setSelectedIndex(0);
                    } else {
                        formEditar.getCmbBoxConvenio().setSelectedIndex(1);
                    }
                    EditarPacienteControle controleEditar = new EditarPacienteControle(daoPaciente, formEditar);
                    formEditar.setVisible(true);
                    preencheTabela();
                }
            }
        };
        visaoListagem.getBtnEditar().addActionListener(actionListener);
    }

    private void evtBotaoAtualizar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                preencheTabela();
            }
        };
        visaoListagem.getBtnAtualizar().addActionListener(actionListener);
    }

    private void evtBotaoExcluir() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int linha = visaoListagem.getjTblPacientes().getSelectedRow();
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum Paciente selecionado!", "Erro", 0);
                } else {
                    String nome = visaoListagem.getjTblPacientes().getValueAt(linha, 1).toString();
                    int opcao = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do Paciente " + nome + " ?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        Integer id = (Integer) visaoListagem.getjTblPacientes().getValueAt(linha, 0);
                        daoPaciente.conectar();
                        daoPaciente.remover(id);
                        daoPaciente.desconectar();
                        preencheTabela();
                    }
                }
            }
        };
        visaoListagem.getBtnExcluir().addActionListener(actionListener);
    }
    
    private void evtBotaoNovo(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                FrmCadastroPaciente formCadastroPaciente = new FrmCadastroPaciente();
                CadastroPacienteControle controlePaciente = new CadastroPacienteControle(daoPaciente, formCadastroPaciente);
                formCadastroPaciente.setVisible(true);
            }
        };
        visaoListagem.getBtnNovo().addActionListener(actionListener);
    }

}
