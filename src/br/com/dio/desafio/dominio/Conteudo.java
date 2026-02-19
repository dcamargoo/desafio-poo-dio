package br.com.dio.desafio.dominio;

public abstract class Conteudo {

    protected final double XP_PADRAO = 10d;

    private String titulo;
    private String descricao;

    public double getXP_PADRAO() {
        return XP_PADRAO;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public abstract double calcularXP();
}
