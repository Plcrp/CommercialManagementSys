package Controller;

import java.util.Scanner;
import Repository.EstoqueRepo;

public class EstoqueController {
    public static void run(Scanner sc) {
        EstoqueRepo estoqueRepo = new EstoqueRepo();
        int opcao;
        boolean loop = true;

        while(loop) {
            System.out.println("\n=== MENU ESTOQUE ===");
            System.out.println("1 - Listar estoque");
            System.out.println("2 - Adicionar produto ao estoque");
            System.out.println("3 - Remover produto do estoque");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    loop = false;
                    break;
                case 1:
                    estoqueRepo.read();
                    estoqueRepo.imprimirEstoque();
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Digite o ID do produto: ");
                    String produtoIdAdicionar = sc.nextLine();
                    
                    int quantidadeAdicionar = 0;
                    while (quantidadeAdicionar <= 0) {
                        System.out.print("Digite a quantidade a adicionar: ");
                        try {
                            quantidadeAdicionar = Integer.parseInt(sc.nextLine());
                            if (quantidadeAdicionar <= 0) {
                                System.out.println("Quantidade deve ser maior que zero!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido! Digite apenas números inteiros.");
                        }
                    }
                    
                    estoqueRepo.adicionarProduto(produtoIdAdicionar, quantidadeAdicionar);
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Digite o ID do produto: ");
                    String produtoIdRemover = sc.nextLine();
                    
                    int quantidadeRemover = 0;
                    while (quantidadeRemover <= 0) {
                        System.out.print("Digite a quantidade a remover: ");
                        try {
                            quantidadeRemover = Integer.parseInt(sc.nextLine());
                            if (quantidadeRemover <= 0) {
                                System.out.println("Quantidade deve ser maior que zero!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido! Digite apenas números inteiros.");
                        }
                    }
                    
                    estoqueRepo.removerProduto(produtoIdRemover, quantidadeRemover);
                    break;
                default:
                    System.out.println("Código inválido, digite novamente...");
                    break;
            }
        }
    }
}