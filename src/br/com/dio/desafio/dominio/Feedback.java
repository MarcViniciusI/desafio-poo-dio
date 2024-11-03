package br.com.dio.desafio.dominio;

public class Feedback {
    private Dev dev;
    private String comentario;
    private int nota; // De 1 a 5

    public Feedback(Dev dev, String comentario, int nota) {
        this.dev = dev;
        this.comentario = comentario;
        this.nota = nota;
    }

    public Dev getDev() {
        return dev;
    }

    public String getComentario() {
        return comentario;
    }

    public int getNota() {
        return nota;
    }
}
