package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import modelo.DAOPaciente;
import modelo.Paciente;
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
    }
    
    public void preencheTabela(){
        DefaultTableModel modelo = (DefaultTableModel) visaoListagem.getjTblPacientes().getModel();
        modelo.setNumRows(0);
        daoPaciente.conectar();
        for(Paciente paciente: daoPaciente.getLista()){
            modelo.addRow(new Object[]{
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getRg(),
                paciente.getDataNascimento(),
                paciente.getEndereco(),
                paciente.getTelefoneCelular(),
                paciente.getEmail(),
                paciente.getTipoConvenio()
            });
        }
        daoPaciente.desconectar();
    }
    
    public void evtBotaoFechar(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoListagem.dispose();
            }
        };
        visaoListagem.getBtnFechar().addActionListener(actionListener);
    }
}
