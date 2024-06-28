package models;

public class Estudante extends Pessoa{
    private String matricula;
    private float nota01;
    private float nota02;

    public Estudante(String nome, String cpf, String matricula, float nota01, float nota02) {
        super(nome, cpf);
        this.matricula = matricula;
        this.nota01 = nota01;
        this.nota02 = nota02;
    }
    public String getMatricula() {
        return matricula;
    }

    public float getNota01() {
        return nota01;
    }

    public float getNota02() {
        return nota02;
    }

    public float getMedia() {
        return (nota01 + nota02) / 2;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNota01(float nota01) {
        this.nota01 = nota01;
    }

    public void setNota02(float nota02) {
        this.nota02 = nota02;
    }

    public String getEstudanteCSV() {
        return getNome()+";"+getCpf()+";"+getMatricula()+";"+getNota01()+";"+getNota02()+";"+getMedia();
    }

    public Estudante setEstudanteCSV(String linha) {
        String[] linhaNova = linha.split(";");
        return new Estudante(linhaNova[0], linhaNova[1], linhaNova[2], Float.parseFloat(linhaNova[3]), Float.parseFloat(linhaNova[4]));
    }


    @Override
    public String toString() {
        return super.toString() +
                " | Matrícula: " + matricula +
                " | Nota 01: " + nota01 +
                " | Nota 02: " + nota02 +
                " | Média: " + getMedia();
    }
}
