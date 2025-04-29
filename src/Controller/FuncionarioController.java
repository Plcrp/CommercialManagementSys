package Controller;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import Model.Cargo;
import Repository.FuncionarioRepo;

import java.util.Date;

import View.FuncionarioView;

public class FuncionarioController {
    public static void run(Scanner sc){
        FuncionarioRepo fr = new FuncionarioRepo();
        int opcao;
        boolean loop = true;

        while(loop){
            FuncionarioView.show();
            opcao = sc.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Até a próxima :)");
                    loop = false;
                case 1:
                    fr.read();
                    fr.imprimirFuncionarios();
                    break;
                case 2:
                    sc.nextLine();
                    
                    System.out.print("Digite o nome do funcionário: ");
                    String nome = sc.nextLine();
                    
                    System.out.print("Digite o CPF do funcionário: ");
                    String cpf = sc.nextLine();
                    
                    Date nascimento = null;
                    while (nascimento == null) {
                        System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
                        try {
                            nascimento = fr.formatoData.parse(sc.nextLine());
                        } catch (Exception e) {
                            System.out.println("Formato de data inválido! Use dd/MM/yyyy");
                        }
                    }
                    
                    Date admissao = new Date(); // Data atual
                    
                    System.out.println("Cargos disponíveis:");
                    for (Cargo cargo : Cargo.values()) {
                        System.out.println("- " + cargo.name());
                    }
                    Cargo cargo = null;
                    while (cargo == null) {
                        System.out.print("Digite o cargo: ");
                        try {
                            cargo = Cargo.valueOf(sc.nextLine().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Cargo inválido! Digite novamente.");
                        }
                    }
                    
                    double salario = 0;
                    while (salario <= 0) {
                        System.out.print("Digite o salário: ");
                        try {
                            salario = Double.parseDouble(sc.nextLine());
                            if (salario <= 0) {
                                System.out.println("Salário deve ser maior que zero!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido! Digite apenas números.");
                        }
                    }
                    
                    fr.add(nome, cpf, nascimento, admissao, cargo, salario);
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Digite o CPF do funcionário a ser removido: ");
                    String cpfRemover = sc.nextLine();
                    fr.removerPorCpf(cpfRemover);
                    break;
                default:
                    System.out.println("Código inválido, digite novamente...");
                    break;
            }
        }
    }
}
