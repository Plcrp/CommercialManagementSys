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
                    ClienteController.run(sc);
                    break;
                case 2:
                    FuncionarioController.run(sc);
                    break;
                case 3:
                    ProdutoController.run(sc);
                    break;
                case 4:
                    EstoqueController.run(sc);
                    break;
                default:
                    System.out.println("Código inválido, tente novamente");
                    break;
            }
        }
        
    }
}
