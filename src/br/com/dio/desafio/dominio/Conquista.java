package br.com.dio.desafio.dominio;

public class Conquista {
    private String nome;
    private String descricao;
    private double xpNecessario;

    public Conquista(String nome, String descricao, double xpNecessario) {
        this.nome = nome;
        this.descricao = descricao;
        this.xpNecessario = xpNecessario;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getXpNecessario() {
        return xpNecessario;
    }
}
