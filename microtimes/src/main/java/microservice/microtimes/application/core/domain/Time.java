package microservice.microtimes.application.core.domain;

import microservice.microtimes.application.core.domain.value_object.TorneioVo;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public final class Time {

    private Long id;

    private String nomeFantasia;

    private String razaoSocial;

    private String cnpj;


    // ---------- SEDE ----------
    private String estado;

    private String cidade;


    // ---------- Fundação ----------
    private LocalDate data;

    private String descricao;


    // ---------- Staff ----------
    private String presidente;

    private String vicePresidente;

    private String headCoach;


    // ---------- Torneios ----------
    private Set<TorneioVo> torneios;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public String getVicePresidente() {
        return vicePresidente;
    }

    public void setVicePresidente(String vicePresidente) {
        this.vicePresidente = vicePresidente;
    }

    public String getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(String headCoach) {
        this.headCoach = headCoach;
    }

    public Set<TorneioVo> getTorneios() {
        return torneios;
    }

    public void setTorneios(Set<TorneioVo> torneios) {
        this.torneios = torneios;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", presidente='" + presidente + '\'' +
                ", vicePresidente='" + vicePresidente + '\'' +
                ", headCoach='" + headCoach + '\'' +
                ", torneios=" + torneios +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(getId(), time.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

