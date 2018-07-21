package modelo;

import controle.Datas;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * Classe POJO que reresenta um Paciente da clínica.
 * Gerenciado por uma Secretária.
 */

@Entity
@Table(name = "tbl_paciente")
public class Paciente {
    
    //Atributos    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Column(length = 100, name = "nome")
    private String nome;
    
    @Column(length = 15, name = "cpf")
    private String cpf;
    
    @Column(length = 15, name = "rg")
    private String rg;
    
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    
    @Column(length = 150, name = "endereco")
    private String endereco;
    
    @Column(length = 20, name = "telefone_celular")
    private String telefoneCelular;
    
    @Column(length = 100, name = "email")
    private String email;
    
    private Convenio tipoConvenio;

    //Construtor
    public Paciente() {
    }

    //Metodos
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Convenio getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(Convenio tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }

    @Override
    public String toString() {
        return "ID: " + this.id
                + "\nNome: " + this.nome
                + "\nCPF: " + this.cpf
                + "\nRG: " + this.rg
                + "\nData de Nascimento: " + Datas.formatoData.format(dataNascimento)
                + "\nEndereço: " + this.endereco
                + "\nTelefone Celular: " + this.telefoneCelular
                + "\nE-Mail: " + this.email
                + "\nConvenio: " + this.tipoConvenio.getDescricao();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
