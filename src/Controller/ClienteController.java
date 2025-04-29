package Controller;
import java.util.Scanner;
import java.util.Date;
import Repository.ClientesRepo;
import View.*;

public class ClienteController {
    public static void run(){
        Scanner sc = new Scanner(System.in);
        ClientesRepo clienteRepo = new ClientesRepo();
        int opcao;
        boolean loop = true;
        while(loop){
            ClienteView.show();
            opcao = sc.nextInt();

            switch (opcao) {
                case 0:
                    loop = false;
                    System.out.println(":)");
                    break;
                case 1:
                    clienteRepo.read();
                    clienteRepo.imprimirClientes();
                    break;
                case 2:
                sc.nextLine();
    
                System.out.print("Digite o nome do cliente: ");
                String nome = sc.nextLine();
                
                System.out.print("Digite o CPF do cliente: ");
                String cpf = sc.nextLine();
                
                Date nascimento = null;
                while (nascimento == null) {
                    System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
                    try {
                        nascimento = clienteRepo.formatoData.parse(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Formato de data inválido! Use dd/MM/yyyy");
                    }
                }
                
                double totalGasto = 0;
                
                Date cadastro = new Date();
                
                clienteRepo.add(nome, cpf, nascimento, totalGasto, cadastro);
                    break;
                case 3:
                    System.out.println("Ainda não feito...");
                    break;
                case 4:
                    System.out.println("Ainda não feito...");
                    break;
                default:
                    System.out.println("Código inválido, digite novamente...");
                    break;
            }
        }
        
    }
}
