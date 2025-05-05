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

import Model.Cliente;

public class ClientesRepo{
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    public final SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    //NÃO ESTAVA FUNCIONANDO COM CAMINHO RELATIVO
    public static String path = "/home/policarpo/Documentos/CommercialManagementSys/src/main/resources/Data/clientes.csv";

    public void imprimirClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
    
        System.out.println("\n=== LISTA DE CLIENTES ===");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("| %-20s | %-14s | %-12s | %-11s | %-12s |\n", 
                          "Nome", "CPF", "Nascimento", "Total Gasto", "Data Cadastro");
        System.out.println("-------------------------------------------------------------");
    
        for (Cliente cliente : listaClientes) {
            System.out.printf("| %-20s | %-14s | %-12s | R$%-9.2f | %-12s |\n",
                cliente.getNome(),
                cliente.getID(),
                formatoData.format(cliente.getDataNascimento()),
                cliente.getTotalGasto(),
                formatoData.format(cliente.getDataCadastro()));
        }
    
        System.out.println("-------------------------------------------------------------");
        System.out.println("Total de clientes: " + listaClientes.size());
    }

    public void add(String nome, String cpf, Date nascimento, double totalGasto, Date cadastro) {
        cpf = cpf.replaceAll("[^0-9]", "");

        String str = nome + "," +
        cpf + "," + 
        formatoData.format(nascimento) + "," + 
        totalGasto + "," +
        formatoData.format(cadastro);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(str);
            bw.newLine();
            System.out.println("Cliente cadastrado!");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }  
    
    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            listaClientes.clear();
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");

                String nome = campos[0];
                String cpf = campos[1];
                Date dataNascimento = formatoData.parse(campos[2]);
                double totalGasto = Double.parseDouble(campos[3]);
                Date dataCadastro = formatoData.parse(campos[4]);

                Cliente cliente = new Cliente(nome, cpf, dataNascimento, totalGasto, dataCadastro);
                listaClientes.add(cliente);
            }
        } catch (IOException | ParseException e) {
            System.out.println("Erro ao ler os clientes: " + e.getMessage());
        }
    }
    
    public boolean removerCliente(String cpf) {
        read();
        boolean removido = false;
        
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getID().equals(cpf)) {
                listaClientes.remove(i);
                removido = true;
                break;
            }
        }
        
        if (removido) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
                for (Cliente cliente : listaClientes) {
                    String str = cliente.getNome() + "," + 
                                cliente.getID() + "," + 
                                formatoData.format(cliente.getDataNascimento()) + "," + 
                                cliente.getTotalGasto() + "," +
                                formatoData.format(cliente.getDataCadastro());
                    bw.write(str);
                    bw.newLine();
                }
                System.out.println("Cliente removido com sucesso!");
                return true;
            } catch (IOException e) {
                System.out.println("Erro ao remover cliente: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Cliente com CPF " + cpf + " não encontrado.");
            return false;
        }
    }
}
