package br.com.dio.desafio.dominio;

import java.util.*;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();
    private int nivel;
    private List<Conquista> conquistas = new ArrayList<>();

    public void inscreverBootcamp(Bootcamp bootcamp) {
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        Optional<Conteudo> conteudo = conteudosInscritos.stream().findFirst();
        conteudo.ifPresent(c -> {
            conteudosConcluidos.add(c);
            conteudosInscritos.remove(c);
        });
    }

    public double calcularTotalXp() {
        return conteudosConcluidos.stream().mapToDouble(Conteudo::calcularXp).sum();
    }

    public void atualizarNivel() {
        double xpTotal = calcularTotalXp();
        if (xpTotal >= 1000) {
            nivel = 3; // Avançado
            conquistas.add(new Conquista("Master Mentor", "Concluiu várias mentorias", 1000));
            System.out.println("Parabéns! Você alcançou o nível Avançado!");
        } else if (xpTotal >= 500) {
            nivel = 2; // Intermediário
            System.out.println("Muito bem! Você está no nível Intermediário.");
        } else {
            nivel = 1; // Iniciante
            System.out.println("Bem-vindo! Você está no nível Iniciante. Continue aprendendo para subir de nível!");
        }
    }

    public void darFeedback(Conteudo conteudo) {
        if (!conteudosConcluidos.contains(conteudo)) {
            System.err.println("Você só pode dar feedback para conteúdos concluídos!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Deixe seu feedback para " + conteudo.getTitulo() + ": ");
        String comentario = scanner.nextLine();
        
        int nota = 0;
        while (nota < 1 || nota > 5) {
            System.out.print("Dê uma nota (1 a 5): ");
            try {
                nota = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nota inválida. Por favor, insira um número entre 1 e 5.");
            }
        }
        
        Feedback feedback = new Feedback(this, comentario, nota);
        conteudo.adicionarFeedback(feedback);
        System.out.println("Feedback recebido: " + comentario + " | Nota: " + nota);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public int getNivel() {
        return nivel;
    }

    public List<Conquista> getConquistas() {
        return conquistas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dev)) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) &&
               Objects.equals(conteudosInscritos, dev.conteudosInscritos) &&
               Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
