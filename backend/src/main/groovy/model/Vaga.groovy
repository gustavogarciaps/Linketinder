package model


import java.time.LocalDate

class Vaga {

    private Empresa empresa;
    private Integer id;
    private String titulo;
    private String descricao;
    private Integer modalidade;
    private Integer cidade;
    private LocalDate dataCriacao;
    private ArrayList<Competencia> competencias;

    public Vaga() {}

    public Vaga(Integer id) {
        this.id = id;
    }

    public Vaga(
            Empresa empresa,
            Integer id,
            String titulo,
            String descricao,
            Integer modalidade,
            Integer cidade,
            ArrayList<Competencia> competencias
    ) {
        this.empresa = empresa;
        this.id = id
        this.titulo = titulo;
        this.descricao = descricao;
        this.modalidade = modalidade;
        this.cidade = cidade;
        this.dataCriacao = LocalDate.now();
        this.competencias = competencias;
    }

    Empresa getEmpresa() {
        return empresa
    }

    void setEmpresa(Empresa empresa) {
        this.empresa = empresa
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getTitulo() {
        return titulo
    }

    void setTitulo(String titulo) {
        this.titulo = titulo
    }

    String getDescricao() {
        return descricao
    }

    void setDescricao(String descricao) {
        this.descricao = descricao
    }

    Integer getModalidade() {
        return modalidade
    }

    void setModalidade(Integer modalidade) {
        this.modalidade = modalidade
    }

    Integer getCidade() {
        return cidade
    }

    void setCidade(Integer cidade) {
        this.cidade = cidade
    }

    LocalDate getDataCriacao() {
        return dataCriacao
    }

    void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao
    }

    void setCompetencias(ArrayList<Competencia> competencias) {
        this.competencias = competencias
    }

    ArrayList<Competencia> getCompetencias() {
        return competencias
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "empresa=" + empresa +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", modalidade=" + modalidade +
                ", cidade=" + cidade +
                ", dataCriacao=" + dataCriacao +
                ", competencias=" + competencias +
                '}';
    }
}
