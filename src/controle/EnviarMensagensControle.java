package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JOptionPane;
import modelo.Consulta;
import modelo.DAOConsulta;
import modelo.DAOPaciente;
import modelo.Email;
import modelo.Paciente;
import modelo.Sms;
import visao.FrmEnviarMensagens;
import visao.FrmMensagensEnviadas;

public class EnviarMensagensControle {

    private FrmEnviarMensagens visaoEnviarMensagens;
    private DAOPaciente daoPaciente;
    private DAOConsulta daoConsulta;
    private ActionListener actionListener;

    public EnviarMensagensControle(FrmEnviarMensagens visaoEnviarMensagens, DAOPaciente daoPaciente, DAOConsulta daoConsulta) {
        this.visaoEnviarMensagens = visaoEnviarMensagens;
        this.daoPaciente = daoPaciente;
        this.daoConsulta = daoConsulta;
        evtBotaoEnviar();
        evtBotaoFechar();
    }

    /**
     * @param opcao true caso queira enviar emails, false para enviar SMS's
     *
     * @param numDias Quantidade de dias que faltam para a consulta
     */
    private String enviarMensagens(boolean opcao, int numDias) {
        daoConsulta.conectar();

        String retorno = "Notificando Pacientes com Consultas\nagendadas para amanhã:\n\n";
        String mensagem;

        Calendar calendario = Calendar.getInstance(); //Intancia um calendario
        calendario.add(Calendar.DATE, numDias); //Adiciona numDias dia(s) a data de hoje

        if (opcao) {
            for (Consulta consulta : daoConsulta.getLista()) {
                //Se paciente tem Email
                if (consulta.getPaciente().getEmail().length() > 0) {
                    if (Datas.formatoData.format(calendario.getTime()).equals(Datas.formatoData.format(consulta.getData()))) {
                        mensagem = enviarEmail(consulta.getPaciente(), consulta);
                        retorno = retorno + mensagem;
                    }
                }
            }
        } else {
            for (Consulta consulta : daoConsulta.getLista()) {
                //Se paciente tem Celular
                if (consulta.getPaciente().getTelefoneCelular().length() > 0) {
                    if (Datas.formatoData.format(calendario.getTime()).equals(Datas.formatoData.format(consulta.getData()))) {
                        mensagem = enviarSms(consulta.getPaciente(), consulta);
                        retorno = retorno + mensagem;
                    }
                }

            }
        }

        daoConsulta.desconectar();
        return retorno;
    }

    //Simulação da operação Enviar E-mail
    private String enviarEmail(Paciente paciente, Consulta consulta) {
        Email email = new Email();
        String mensagem = "Prezado(a) paciente " + paciente.getNome()
                + "\nA clínica Saúde & Cia vem por meio desta mensagem"
                + "\nlembra-lo(a) de que sua consulta está agendada para"
                + "\n" + Datas.formatoData.format(consulta.getData()) + " as " + consulta.getHorario() + ".";
        email.setMensagem(mensagem);
        return ("E-mail enviado para " + paciente.getEmail()
                + "\n(" + paciente.getNome() + ")\n\n");
    }

    //Simulação da operação Enviar SMS
    private String enviarSms(Paciente paciente, Consulta consulta) {
        Sms sms = new Sms();
        String mensagem = "Clínica Saúde & Cia"
                + "\n" + paciente.getNome() + " sua consulta está agendada para"
                + "\n" + Datas.formatoData.format(consulta.getData()) + " as " + consulta.getHorario() + ".";
        sms.setMensagem(mensagem);
        return ("SMS enviado para " + paciente.getTelefoneCelular()
                + "\n(" + paciente.getNome() + ")\n\n");
    }

    private void evtBotaoEnviar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String informacao;
                if (visaoEnviarMensagens.getBtnRadioSms().isSelected()) {
                    informacao = enviarMensagens(false, 1);
                    FrmMensagensEnviadas formMensagensEnviadas = new FrmMensagensEnviadas();
                    formMensagensEnviadas.getTxtAreaInfo().setText(informacao);
                    formMensagensEnviadas.setVisible(true);
                } else if (visaoEnviarMensagens.getBtnRadioEmail().isSelected()) {
                    informacao = enviarMensagens(true, 1);
                    FrmMensagensEnviadas formMensagensEnviadas = new FrmMensagensEnviadas();
                    formMensagensEnviadas.getTxtAreaInfo().setText(informacao);
                    formMensagensEnviadas.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum filtro selecionado!", "Erro", 0);
                }

            }
        };
        visaoEnviarMensagens.getBtnEnviar().addActionListener(actionListener);
    }

    private void evtBotaoFechar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoEnviarMensagens.dispose();
            }
        };
        visaoEnviarMensagens.getBtnFechar().addActionListener(actionListener);
    }
}
