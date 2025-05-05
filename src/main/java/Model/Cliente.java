package Model;

import java.util.Date;

public class Cliente extends PID implements Validavel{
    private Double totalGasto;
    private Date dataCadastro;

    public Cliente(String nome,
                   String ID,
                   Date DataNascimento,
                   Double totalGasto,
                   Date dataCadastro) {
        super(nome, ID, DataNascimento);
        if(!validar(ID)){
            throw new IllegalArgumentException("CPF inválido");
        }
        this.totalGasto = totalGasto;
        this.dataCadastro = dataCadastro;
    }

    public boolean validar(String cpf){
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        int soma1 = 0, soma2 = 0;
        for (int i = 0; i < 9; i++) {
            int digito = cpf.charAt(i) - '0';
            soma1 += digito * (10 - i);
            soma2 += digito * (11 - i);
        }

        int digito1 = 11 - (soma1 % 11);
        digito1 = (digito1 > 9) ? 0 : digito1;
        soma2 += digito1 * 2;

        int digito2 = 11 - (soma2 % 11);
        digito2 = (digito2 > 9) ? 0 : digito2;

        return cpf.charAt(9) - '0' == digito1 && cpf.charAt(10) - '0' == digito2;
    }

    public Cliente(String nome){
        super(nome);
    }

    public Double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(Double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}


