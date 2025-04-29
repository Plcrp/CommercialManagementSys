package Controller;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import Repository.ProdutosRepo;
import View.ProdutoView;

public class ProdutoController {
    public static void run(Scanner sc) {
        ProdutosRepo pr = new ProdutosRepo();
        int opcao;
        boolean loop = true;

        while(loop) {
            ProdutoView.show();
            opcao = sc.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    loop = false;
                    break;
                case 1:
                    pr.read();
                    pr.imprimirProdutos();
                    break;
                case 2:
                    sc.nextLine();
                    
                    System.out.print("Digite o nome do produto: ");
                    String nome = sc.nextLine();
                    
                    System.out.print("Digite o ID do produto: ");
                    String id = sc.nextLine();
                    
                    double preco = 0;
                    while (preco <= 0) {
                        System.out.print("Digite o preço do produto: ");
                        try {
                            preco = Double.parseDouble(sc.nextLine());
                            if (preco <= 0) {
                                System.out.println("Preço deve ser maior que zero!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido! Digite apenas números.");
                        }
                    }
                    
                    Date fabricacao = null;
                    while (fabricacao == null) {
                        System.out.print("Digite a data de fabricação (dd/MM/yyyy): ");
                        try {
                            fabricacao = pr.formatoData.parse(sc.nextLine());
                        } catch (Exception e) {
                            System.out.println("Formato de data inválido! Use dd/MM/yyyy");
                        }
                    }
                    
                    Date vencimento = null;
                    System.out.print("Digite a data de vencimento (dd/MM/yyyy) ou deixe em branco: ");
                    String vencimentoStr = sc.nextLine();
                    if (!vencimentoStr.isEmpty()) {
                        try {
                            vencimento = pr.formatoData.parse(vencimentoStr);
                        } catch (Exception e) {
                            System.out.println("Formato de data inválido! Vencimento não será cadastrado.");
                        }
                    }
                    
                    Date cadastro = new Date();
                    
                    pr.add(nome, id, preco, cadastro, fabricacao, vencimento);
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Digite o ID do produto a ser removido: ");
                    String idRemover = sc.nextLine();
                    pr.removerPorId(idRemover);
                    break;
                default:
                    System.out.println("Código inválido, digite novamente...");
                    break;
            }
        }
    }
}