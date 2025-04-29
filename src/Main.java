import java.util.Scanner;
import View.*;
import Controller.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;

        while(true){
            MenuObjetos.ExibirMenuObjetos(); 
            option = sc.nextInt();
            
            switch (option) {
                case 0:
                    System.out.println("Programa Encerrado :).");
                    sc.close();
                    System.exit(0);
                    break;
                case 1:
                    ClienteController.run();
                    break;
                case 2:
                    System.out.println("Cargos ainda não colocados");
                    break;
                case 3:
                    System.out.println("Estoque ainda não colocado");
                    break;
                case 4:
                    System.out.println("Produtos ainda não colocados");
                    break;
                case 5:
                    System.out.println("Funcionários ainda não colocados");
                    break;
                case 6:
                    System.out.println("Fornecedores ainda não colocados");
                    break;
                default:
                    System.out.println("Código inválido, tente novamente");
                    break;
            }
        }
        
    }
}
