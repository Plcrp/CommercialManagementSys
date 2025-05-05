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

import Model.Funcionario;
import Model.Cargo;

public class FuncionarioRepo {
    private ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    public final SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    //NÃO ESTAVA FUNCIONANDO COM CAMINHO RELATIVO
    public static String path = "/home/policarpo/Documentos/CommercialManagementSys/src/main/resources/Data/funcionarios.csv";

    public void imprimirFuncionarios() {
        if (listaFuncionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
    
        System.out.println("\n=== LISTA DE FUNCIONÁRIOS ===");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-14s | %-12s | %-12s | %-15s | %-10s |\n", 
                          "Nome", "CPF", "Nascimento", "Admissão", "Cargo", "Salário");
        System.out.println("-----------------------------------------------------------------------------------------");
    
        for (Funcionario func : listaFuncionarios) {
            System.out.printf("| %-20s | %-14s | %-12s | %-12s | %-15s | R$%-8.2f |\n",
                func.getNome(),
                func.getID(),
                formatoData.format(func.getDataNascimento()),
                formatoData.format(func.getJoiningDate()),
                func.getCargo(),
                func.getSalario());
        }
    
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Total de funcionários: " + listaFuncionarios.size());
    }

    public void add(String nome, String cpf, Date nascimento, Date admissao, Cargo cargo, double salario) {
        cpf = cpf.replaceAll("[^0-9]", "");

        String str = nome + "," +
                     cpf + "," + 
                     formatoData.format(nascimento) + "," + 
                     formatoData.format(admissao) + "," +
                     cargo.name() + "," +
                     salario;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(str);
            bw.newLine();
            System.out.println("Funcionário cadastrado!");
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }  
    
    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            listaFuncionarios.clear();
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(",");

                String nome = campos[0];
                String cpf = campos[1];
                Date dataNascimento = formatoData.parse(campos[2]);
                Date dataAdmissao = formatoData.parse(campos[3]);
                Cargo cargo = Cargo.valueOf(campos[4]);
                double salario = Double.parseDouble(campos[5]);

                Funcionario funcionario = new Funcionario(nome, cpf, dataNascimento, 
                                                         dataAdmissao, cargo, salario);
                listaFuncionarios.add(funcionario);
            }
        } catch (IOException | ParseException e) {
            System.out.println("Erro ao ler os funcionários: " + e.getMessage());
        }
    }
    
    public boolean removerPorCpf(String cpf) {
        read(); // Atualiza a lista com os dados mais recentes do arquivo
        boolean removido = false;
        
        // Procura o funcionário pelo CPF
        for (int i = 0; i < listaFuncionarios.size(); i++) {
            if (listaFuncionarios.get(i).getID().equals(cpf)) {
                listaFuncionarios.remove(i);
                removido = true;
                break;
            }
        }
        
        if (removido) {
            // Reescreve o arquivo sem o funcionário removido
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
                for (Funcionario func : listaFuncionarios) {
                    String str = func.getNome() + "," + 
                                func.getID() + "," + 
                                formatoData.format(func.getDataNascimento()) + "," + 
                                formatoData.format(func.getJoiningDate()) + "," +
                                func.getCargo().name() + "," +
                                func.getSalario();
                    bw.write(str);
                    bw.newLine();
                }
                System.out.println("Funcionário removido com sucesso!");
                return true;
            } catch (IOException e) {
                System.out.println("Erro ao remover funcionário: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Funcionário com CPF " + cpf + " não encontrado.");
            return false;
        }
    }
}