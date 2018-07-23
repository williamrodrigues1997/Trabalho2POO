package modelo;

import controle.Datas;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
/**
 *
 * Classe POJO que reresenta um Prontuário.
 * Gerenciado por um Médico.
 */

@Entity
@Table(name = "tbl_prontuario")
public class Prontuario {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    
    @Column(length = 100, name = "medico")
    private String medico;
    
    @Column(length = 300, name = "sintomas")
    private String sintomas;
    
    @Column(length = 300, name = "diagnosticos")
    private String diagnosticoDoenca;
    
    @Column(length = 300, name = "prescricao_tratamento")
    private String prescricaoTratamento;

    //Construtor
    public Prontuario() {
    }

    //Metodos
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnosticoDoenca() {
        return diagnosticoDoenca;
    }

    public void setDiagnosticoDoenca(String diagnosticoDoenca) {
        this.diagnosticoDoenca = diagnosticoDoenca;
    }

    public String getPrescricaoTratamento() {
        return prescricaoTratamento;
    }

    public void setPrescricaoTratamento(String prescricaoTratamento) {
        this.prescricaoTratamento = prescricaoTratamento;
    }

    @Override
    public String toString() {
        return "\nID: " + this.id
                + "\nPaciente: " + this.paciente.getNome()
                + "\nData: " + Datas.formatoData.format(this.data)
                + "\nMedico: " + this.medico;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prontuario other = (Prontuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
