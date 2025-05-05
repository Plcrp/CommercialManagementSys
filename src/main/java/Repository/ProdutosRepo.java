package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.Produto;

public class ProdutosRepo {
    private ArrayList<Produto> listaProdutos = new ArrayList<>();
    public final SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    public static String path = "/home/policarpo/Documentos/CommercialManagementSys/src/main/resources/Data/produtos.csv";

    public void imprimirProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
    
        System.out.println("\n=== LISTA DE PRODUTOS ===");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-10s | %-10s | %-12s | %-12s | %-12s |\n", 
                         "Nome", "ID", "Preço", "Cadastro", "Fabricação", "Vencimento");
        System.out.println("--------------------------------------------------------------------------------");
    
        for (Produto produto : listaProdutos) {
            System.out.printf("| %-20s | %-10s | R$%-8.2f | %-12s | %-12s | %-12s |\n",
                produto.getName(),
                produto.getID(),
                produto.getPreco(),
                formatoData.format(produto.getDataCadastro()),
                formatoData.format(produto.getFabricacao()),
                produto.getVencimento() != null ? formatoData.format(produto.getVencimento()) : "N/A");
        }
    
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Total de produtos: " + listaProdutos.size());
    }

    public void add(String nome, String id, double preco, Date dataCadastro, Date fabricacao, Date vencimento) {
        String str = nome + "," + 
                     id + "," + 
                     preco + "," + 
                     formatoData.format(dataCadastro) + "," +
                     formatoData.format(fabricacao) + "," +
                     (vencimento != null ? formatoData.format(vencimento) : "N/A");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(str);
            bw.newLine();
            System.out.println("Produto cadastrado!");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }
    
    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            listaProdutos.clear();
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");

                String nome = campos[0];
                String id = campos[1];
                double preco = Double.parseDouble(campos[2]);
                Date dataCadastro = formatoData.parse(campos[3]);
                Date fabricacao = formatoData.parse(campos[4]);
                Date vencimento = campos[5].equals("N/A") ? null : formatoData.parse(campos[5]);

                Produto produto = new Produto(nome, id, preco, dataCadastro, fabricacao, vencimento);
                listaProdutos.add(produto);
            }
        } catch (IOException | ParseException e) {
            System.out.println("Erro ao ler os produtos: " + e.getMessage());
        }
    }
    
    public boolean removerPorId(String id) {
        read(); 
        boolean removido = false;
        
        for (int i = 0; i < listaProdutos.size(); i++) {
            if (listaProdutos.get(i).getID().equals(id)) {
                listaProdutos.remove(i);
                removido = true;
                break;
            }
        }
        
        if (removido) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
                for (Produto produto : listaProdutos) {
                    String str = produto.getName() + "," + 
                                 produto.getID() + "," + 
                                 produto.getPreco() + "," + 
                                 formatoData.format(produto.getDataCadastro()) + "," +
                                 formatoData.format(produto.getFabricacao()) + "," +
                                 (produto.getVencimento() != null ? formatoData.format(produto.getVencimento()) : "N/A");
                    bw.write(str);
                    bw.newLine();
                }
                System.out.println("Produto removido com sucesso!");
                return true;
            } catch (IOException e) {
                System.out.println("Erro ao remover produto: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
            return false;
        }
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }
    
}