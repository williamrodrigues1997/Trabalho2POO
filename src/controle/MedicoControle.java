/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Consulta;
import modelo.DAOConsulta;
import modelo.DAODadosAdicionaisPaciente;
import modelo.DAOPaciente;
import modelo.DAOProntuario;
import visao.FrmMedico;
import visao.FrmProntuario;

/**
 *
 * @author Ronny
 */
public class MedicoControle {
    private FrmMedico visaoMedico;
    private DAOConsulta daoConsulta;
    private ActionListener actionListener;

    public MedicoControle(DAOConsulta daoConsulta, FrmMedico visaoMedico) {
        this.daoConsulta = daoConsulta;
        this.visaoMedico = visaoMedico;
        preencherTabela();
        evtBotaoFechar();
        evtBotaoDetalhes();
        evtBotaoNovo();
        evtBotaoRelatorio();
    }
    
    private void preencherTabela(){
        DefaultTableModel modelo = (DefaultTableModel) visaoMedico.getTblConsultas().getModel();
        modelo.setNumRows(0);
        daoConsulta.conectar();
        
        for(Consulta consulta : daoConsulta.getLista()){
            modelo.addRow(new Object[]{
                consulta.getPaciente().getNome(),
                consulta.getHorario(),
                consulta.getPaciente().getTipoConvenio(),
                consulta.getTipo()
            });
                    
        }
    }
    
    private void evtBotaoFechar(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoMedico.dispose();
            }
        };
        visaoMedico.getBtnFechar().addActionListener(actionListener);
    }
    
    private void evtBotaoNovo(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmProntuario  formProntuario = new FrmProntuario();
                DAOConsulta daoConsulta = new DAOConsulta();
                DAOProntuario daoProntuario = new DAOProntuario();
                DAODadosAdicionaisPaciente daoDadosAdicionais = new DAODadosAdicionaisPaciente();
                DAOPaciente daoPaciente = new DAOPaciente();
                
                ProntuarioControle prontuarioControle = new ProntuarioControle(daoConsulta, daoPaciente, daoProntuario, daoDadosAdicionais, formProntuario);
                formProntuario.setVisible(true);
            }
        };
        visaoMedico.getBtnNovo().addActionListener(actionListener);
    }
    
    private void evtBotaoDetalhes(){
        
    }
    
    private void evtBotaoRelatorio(){
        
    }
    
}
