package models;

import java.io.*;
import java.util.*;

public class Disciplina {

    private ArrayList<Estudante> turma = new ArrayList<>();
    private final File ARQUIVO = new File("turma.csv");

    public ArrayList<Estudante> getTurma() {
        return turma;
    }

    public void listarEstudantes() {
        turma.forEach(System.out::println);
    }

    public float mediaTurma() {
        float media = 0;
        for (int i = 0; i < turma.size(); i++) {
            media += turma.get(i).getMedia();
        }
        return media / turma.size();
    }

    public Estudante getEstudante(String matricula) {
        Optional<Estudante> estudanteEncontrado = turma.stream()
                .filter(e -> e.getMatricula().equals(matricula))
                .findFirst();
        if (estudanteEncontrado.isPresent()) {
            return estudanteEncontrado.get();
        }
            return null;

    }

    public boolean insereEstudante(Estudante novo) {
        return turma.add(novo);
    }

    public boolean alteraEstudante(String matricula) {
        var sc = new Scanner(System.in);
        var estudante = getEstudante(matricula);
        if (estudante != null) {
            System.out.println("Processo de alteração iniciado!");
            System.out.println("Informe o nome: ");
            estudante.setNome(sc.nextLine());
            System.out.println("Informe o CPF: ");
            estudante.setCpf(sc.nextLine());
            System.out.println("Informe a matricula: ");
            estudante.setMatricula(sc.nextLine());
            try {
                System.out.println("Informe a nota 01: ");
                estudante.setNota01(sc.nextFloat());
                sc.nextLine();
                System.out.println("Informe a nota 02: ");
                estudante.setNota02(sc.nextFloat());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Formato não aceito! Processo de alteração cancelado!");
                sc.nextLine();
                return false;
            }


            return true;
        } else {
            System.out.println("Estudante não encontrado!");
            return false;
        }
    }

    public boolean removeEstudante(String matricula) {
        for (int i = 0; i < turma.size(); i++) {
            if (turma.get(i).getMatricula().equals(matricula)) {
                turma.remove(i);
                return true;
            }
        }
        return false;
    }

    public void listaEstudantesAcima6() {
        List<Estudante> estudantes = turma.stream()
                .filter(e -> e.getMedia() >= 6)
                .toList();
        if (estudantes.isEmpty()) {
            System.out.println("Nenhum estudante com nota acima ou igual a 6!");
        } else {
            estudantes.forEach(System.out::println);
        }
    }

    public void listaEstudantesAbaixo6() {
        List<Estudante> estudantes = turma.stream()
                .filter(e -> e.getMedia() < 6)
                .toList();
        if (estudantes.isEmpty()) {
            System.out.println("Nenhum estudante com nota abaixo de 6!");
        } else {
            estudantes.forEach(System.out::println);
        }
    }

    public void gravar() {
        try {
            FileWriter fw = new FileWriter(ARQUIVO);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("Nome;CPF;Matrícula;Nota 01; Nota 02; Média");
            pw.flush();
            for (int i = 0; i < turma.size(); i++) {
                pw.println(turma.get(i).getEstudanteCSV());
                pw.flush();
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("a");
        }
    }

    public void carregaDados() {
        try {
            FileReader fr = new FileReader(ARQUIVO);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            br.readLine();
            linha = br.readLine();
            while (linha != null) {
                if (!linha.equals("")) {
                    Estudante estudante = new Estudante("", "", "", 0, 0);
                    insereEstudante(estudante.setEstudanteCSV(linha));
                }
                linha = br.readLine();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
