package modelo;

/**
 *
 * Enumeração dos tipos de Consulta que podem ser agendadas para um Paciente.
 */
public enum TipoConsulta {
    NORMAL("Normal (1h)"),
    RETORNO("Retorno (30m)");

    private String descricao;

    private TipoConsulta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
