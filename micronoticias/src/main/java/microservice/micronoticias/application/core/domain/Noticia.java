package microservice.micronoticias.application.core.domain;

import java.util.List;

public final class Noticia {

    private String chapeu;

    private String titulo;

    private String linhaFina;

    private String lide;

    private String corpo;

    private List<String> autorias;

    private List<String> fontes;

    public String getChapeu() {
        return chapeu;
    }

    public void setChapeu(String chapeu) {


        this.chapeu = chapeu;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLinhaFina() {
        return linhaFina;
    }

    public void setLinhaFina(String linhaFina) {
        this.linhaFina = linhaFina;
    }

    public String getLide() {
        return lide;
    }

    public void setLide(String lide) {
        this.lide = lide;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public List<String> getAutorias() {
        return autorias;
    }

    public void setAutorias(List<String> autorias) {
        this.autorias = autorias;
    }

    public List<String> getFontes() {
        return fontes;
    }

    public void setFontes(List<String> fontes) {
        this.fontes = fontes;
    }
}

