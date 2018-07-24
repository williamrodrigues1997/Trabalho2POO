package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * Classe POJO que reresenta os dados adicionais de um Paciente da clínica.
 * Utilizado e gerenciado por um Médico.
 */

@Entity
@Table(name = "tbl_dados_adicionais")
public class DadosAdicionaisPaciente {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    @Column(length = 100, name = "fuma")
    private boolean fuma;
    
    @Column(length = 100, name = "bebe")
    private boolean bebe;
    
    @Column(length = 100, name = "colesterol")
    private boolean colesterol;
    
    @Column(length = 100, name = "diabete")
    private boolean diabete;
    
    @Column(length = 100, name = "doenca_cardiaca")
    private boolean doencaCardiaca;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adicionais")
    private List<Adicionais> cirurgias = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_adicionais")
    private List<Adicionais> alergias = new ArrayList<>();

    //Construtor
    public DadosAdicionaisPaciente() {
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

    public boolean isFuma() {
        return fuma;
    }

    public void setFuma(boolean fuma) {
        this.fuma = fuma;
    }

    public boolean isBebe() {
        return bebe;
    }

    public void setBebe(boolean bebe) {
        this.bebe = bebe;
    }

    public boolean isColesterol() {
        return colesterol;
    }

    public void setColesterol(boolean colesterol) {
        this.colesterol = colesterol;
    }

    public boolean isDiabete() {
        return diabete;
    }

    public void setDiabete(boolean diabete) {
        this.diabete = diabete;
    }

    public boolean isDoencaCardiaca() {
        return doencaCardiaca;
    }

    public void setDoencaCardiaca(boolean doencaCardiaca) {
        this.doencaCardiaca = doencaCardiaca;
    }

    public List<Adicionais> getCirurgias() {
        return cirurgias;
    }

    public void setCirurgias(List<Adicionais> cirurgias) {
        this.cirurgias = cirurgias;
    }

    public List<Adicionais> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<Adicionais> alergias) {
        this.alergias = alergias;
    }

    @Override
    public String toString() {
        return "ID: " + this.id
                + "\nNome: " + this.paciente
                + "\nFuma: " + this.fuma
                + "\nBebe: " + this.bebe
                + "\nColesterol: " + this.colesterol
                + "\nDiabete: " + this.diabete
                + "\nDoença Cardiaca: " + this.doencaCardiaca
                + "\nCirurgias: " + this.cirurgias.toString()
                + "\nAlergias: " + this.alergias.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.paciente);
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
        final DadosAdicionaisPaciente other = (DadosAdicionaisPaciente) obj;
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        return true;
    }
}
