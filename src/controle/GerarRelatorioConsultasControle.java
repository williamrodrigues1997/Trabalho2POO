package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Consulta;
import modelo.DAOConsulta;
import visao.FrmGerarRelatorioConsultas;
import visao.FrmRelatorioConsultas;

public class GerarRelatorioConsultasControle {

    private FrmGerarRelatorioConsultas visaoGerarRelatorio;
    private ActionListener actionListener;

    public GerarRelatorioConsultasControle(FrmGerarRelatorioConsultas visaoGerarRelatorio) {
        this.visaoGerarRelatorio = visaoGerarRelatorio;
        evtBotaoGerarRelatorio();
        evtBotaoFechar();
    }

    private void evtBotaoGerarRelatorio(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer numDias = Integer.parseInt(visaoGerarRelatorio.getCmbBoxNumDias().getSelectedItem().toString());
                if(visaoGerarRelatorio.getRadioBtnComInfo().isSelected()){
                    String relatorio = gerarRelatorio(true, numDias);
                    FrmRelatorioConsultas formRelatorio = new FrmRelatorioConsultas();
                    formRelatorio.getTxtAreaRelatorio().setText(relatorio);
                    formRelatorio.setVisible(true);
                }else if(visaoGerarRelatorio.getRadioBtnSemInfo().isSelected()){
                    String relatorio = gerarRelatorio(false, numDias);
                    FrmRelatorioConsultas formRelatorio = new FrmRelatorioConsultas();
                    formRelatorio.getTxtAreaRelatorio().setText(relatorio);
                    formRelatorio.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Nenhum filtro selecionado!", "Erro", 0);
                }
                
            }
        };
        visaoGerarRelatorio.getBtnGerarRelatorio().addActionListener(actionListener);
    }
    
    private void evtBotaoFechar(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoGerarRelatorio.dispose();
            }
        };
        visaoGerarRelatorio.getBtnFechar().addActionListener(actionListener);
    }
    
    
    /**
     * @param opcao true caso queria um relatório de pacientes COM informações
     * de contato; false caso queria um relatório de pacientes SEM infotmações
     * de contato.
     *
     * @param numDias quantidade de dias seguintes após o dia atual que o
     * relatório exibirá consultas referentes.
     *
     * @return Retorna um relatório (String) armazenado no atributo relatorio
     * das consultas agendadas para os numDias dias seguintes.
     */
     private String gerarRelatorio(boolean opcao, int numDias) {
        String relatorio = "";
        Calendar calendario = Calendar.getInstance(); //Intancia um calendario
        calendario.add(Calendar.DATE, numDias); //Adiciona numDias dia(s) a data de hoje
        
        DAOConsulta daoConsulta = new DAOConsulta();
        daoConsulta.conectar();        
        List <Consulta> listaDeConsultas = daoConsulta.getLista();
        daoConsulta.desconectar();

        if (opcao) {
            relatorio = "Pacientes COM Info de Contato";
            for (Consulta consulta : listaDeConsultas) {
                if ((consulta.getPaciente().getTelefoneCelular().length() > 0)
                        || (consulta.getPaciente().getEmail().length() > 0)) {
                    if (Datas.formatoData.format(calendario.getTime()).equals(Datas.formatoData.format(consulta.getData()))) {
                        relatorio += "\n-------------------------------\n" + consulta.toString();
                    }
                }
            }
            relatorio += "\n-------------------------------";
        } else {
            relatorio = "Pacientes SEM Info de Contato";
            for (Consulta consulta : listaDeConsultas) {
                if ((consulta.getPaciente().getTelefoneCelular().length() == 0)
                        && (consulta.getPaciente().getEmail().length() == 0)) {
                    if (Datas.formatoData.format(calendario.getTime()).equals(Datas.formatoData.format(consulta.getData()))) {
                        relatorio += "\n-------------------------------\n" + consulta.toString();
                    }
                }
            }
            relatorio += "\n-------------------------------";
        }
        
        return relatorio;        
    }
    
}
