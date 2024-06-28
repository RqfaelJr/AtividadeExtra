package Principal;

import models.Disciplina;
import models.Estudante;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private Disciplina d = new Disciplina();

    public void exibeMenu() {
        d.carregaDados();
        var menu = """
                1 - Cadastrar estudante
                2 - Alterar estudante
                3 - Remover estudante
                4 - Consultar estudante
                5 - Listar estudante de uma disciplina
                6 - Listar estudantes abaixo de nota 6
                7 - Listar estudantes acima ou igual nota 6
                8 - Mostrar média da turma
                
                0 - Sair
                """;
        int opcao = -1;
        while (opcao != 0) {
            System.out.println(menu);
            System.out.println("Escolha sua ação: ");
            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                opcao = -1;
            }
            switch (opcao) {
                case 1:
                    cadastrarEstudante();
                    break;
                case 2:
                    alterarEstudante();
                    break;
                case 3:
                    removerEstudante();
                    break;
                case 4:
                    consultarEstudante();
                    break;
                case 5:
                    listarEstudantes();
                    break;
                case 6:
                    listarEstudantesAbaixo6();
                    break;
                case 7:
                    listarEstudantesAcima6();
                    break;
                case 8:
                    mostrarMediaGeral();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    d.gravar();
                    sc.close();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarEstudante() {
        System.out.println("Informe o nome: ");
        String nome = sc.nextLine();
        System.out.println("Informe o CPF: ");
        String cpf = sc.nextLine();
        System.out.println("Informe a matricula: ");
        String matricula = sc.nextLine();
        try {
            System.out.println("Informe a nota 01: ");
            float nota01 = sc.nextFloat();
            sc.nextLine();
            System.out.println("Informe a nota 02: ");
            float nota02 = sc.nextFloat();
            sc.nextLine();
            Estudante estudante = new Estudante(nome, cpf, matricula, nota01, nota02);

            if (d.insereEstudante(estudante)) {
                System.out.println("Aluno cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar o aluno!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Formato inválido! Cadastro cancelado");
            sc.nextLine();
        }


    }

    private void alterarEstudante() {
        System.out.println("Informe a matricula: ");
        String matricula = sc.nextLine();
        if (d.alteraEstudante(matricula)) {
            System.out.println("Aluno alterado com sucesso!");
        } else {
            System.out.println("Alteração interrompida!");
        }
    }

    private void removerEstudante() {
        System.out.println("Informe a matrícula: ");
        String matricula = sc.nextLine();
        if (d.removeEstudante(matricula)) {
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    private void consultarEstudante() {
        System.out.println("Informe a matrícula: ");
        String matricula = sc.nextLine();
        if (d.getEstudante(matricula) == null) {
            System.out.println("Estudante não encontrado!");
        } else {
            System.out.println(d.getEstudante(matricula));
        }

    }

    private void listarEstudantes() {
        d.listarEstudantes();
    }

    private void listarEstudantesAbaixo6() {
        d.listaEstudantesAbaixo6();
    }

    private void listarEstudantesAcima6() {
        d.listaEstudantesAcima6();
    }

    private void mostrarMediaGeral() {
        System.out.println("A média geral da turma é: " + d.mediaTurma());

    }
}
