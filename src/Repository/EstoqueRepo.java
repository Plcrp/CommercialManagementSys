package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Model.Produto;
import Repository.ProdutosRepo;

public class EstoqueRepo {
    private Map<Produto, Integer> estoque = new HashMap<>();
    private ProdutosRepo produtosRepo = new ProdutosRepo();
    public final SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    public static String path = "/home/policarpo/Documentos/CommercialManagementSys/Data/estoque.csv";

    public void imprimirEstoque() {
        if (estoque.isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }
    
        System.out.println("\n=== ESTOQUE DE PRODUTOS ===");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("| %-20s | %-10s | %-10s | %-12s |\n", 
                         "Nome", "ID", "Preço", "Quantidade");
        System.out.println("---------------------------------------------------------------");
    
        for (Map.Entry<Produto, Integer> entry : estoque.entrySet()) {
            Produto produto = entry.getKey();
            System.out.printf("| %-20s | %-10s | R$%-8.2f | %-12d |\n",
                produto.getName(),
                produto.getID(),
                produto.getPreco(),
                entry.getValue());
        }
    
        System.out.println("---------------------------------------------------------------");
        System.out.println("Total de itens no estoque: " + estoque.size());
    }

    public void adicionarProduto(String produtoId, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }
        
        produtosRepo.read();
        Produto produto = encontrarProdutoPorId(produtoId);
        
        if (produto == null) {
            System.out.println("Produto com ID " + produtoId + " não encontrado!");
            return;
        }
        
        estoque.merge(produto, quantidade, Integer::sum);
        salvarEstoque();
        System.out.println(quantidade + " unidades do produto " + produto.getName() + " adicionadas ao estoque!");
    }

    public boolean removerProduto(String produtoId, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }
        
        produtosRepo.read();
        Produto produto = encontrarProdutoPorId(produtoId);
        
        if (produto == null) {
            System.out.println("Produto com ID " + produtoId + " não encontrado!");
            return false;
        }
        
        int quantidadeAtual = estoque.getOrDefault(produto, 0);
        if (quantidadeAtual < quantidade) {
            System.out.println("Quantidade insuficiente em estoque! Disponível: " + quantidadeAtual);
            return false;
        }
        
        estoque.put(produto, quantidadeAtual - quantidade);
        if (estoque.get(produto) == 0) {
            estoque.remove(produto);
        }
        
        salvarEstoque();
        System.out.println(quantidade + " unidades do produto " + produto.getName() + " removidas do estoque!");
        return true;
    }

    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            estoque.clear();
            produtosRepo.read();
            
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");
                String produtoId = campos[0];
                int quantidade = Integer.parseInt(campos[1]);
                
                Produto produto = encontrarProdutoPorId(produtoId);
                if (produto != null) {
                    estoque.put(produto, quantidade);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler o estoque: " + e.getMessage());
        }
    }

    private void salvarEstoque() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            for (Map.Entry<Produto, Integer> entry : estoque.entrySet()) {
                String str = entry.getKey().getID() + "," + entry.getValue();
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o estoque: " + e.getMessage());
        }
    }

    private Produto encontrarProdutoPorId(String id) {
        for (Produto produto : produtosRepo.getListaProdutos()) {
            if (produto.getID().equals(id)) {
                return produto;
            }
        }
        return null;
    }

    public Map<Produto, Integer> getEstoque() {
        return new HashMap<>(estoque);
    }
}