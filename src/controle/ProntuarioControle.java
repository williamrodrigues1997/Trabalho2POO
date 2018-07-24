/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modelo.DAOConsulta;
import modelo.DAODadosAdicionaisPaciente;
import modelo.DAOPaciente;
import modelo.DAOProntuario;
import visao.FrmProntuario;

/**
 *
 * @author Ronny
 */
public class ProntuarioControle {
    private FrmProntuario visaoProntuario;
    private DAOConsulta daoConsulta;
    private DAOPaciente daoPaciente;
    private DAOProntuario daoProntuario;
    private DAODadosAdicionaisPaciente daoDadosAdicionaisPaciente;
    
    public ProntuarioControle(DAOConsulta daoConsulta, DAOPaciente daoPaciente, DAOProntuario daoProntuario, DAODadosAdicionaisPaciente daoDadosAdicionaisPaicnete, FrmProntuario visaoProntuario){
        this.daoConsulta = daoConsulta;
        this.daoDadosAdicionaisPaciente = daoDadosAdicionaisPaicnete;
        this.daoPaciente = daoPaciente;
        this.daoProntuario = daoProntuario;
        this.visaoProntuario = visaoProntuario;
    }
    
    
}
