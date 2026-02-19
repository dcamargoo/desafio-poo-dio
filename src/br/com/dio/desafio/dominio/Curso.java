package br.com.dio.desafio.dominio;

public class Curso extends Conteudo{

    private int cargaHoraria;

    public Curso(){}

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public double calcularXP() {
        return XP_PADRAO + 40d;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "titulo=" + getTitulo() + " / " +
                "descricao=" + getDescricao() + " / " +
                "cargaHoraria=" + this.cargaHoraria +
                '}';
    }
}