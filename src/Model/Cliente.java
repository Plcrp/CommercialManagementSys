package Model;

import java.util.Date;

public class Cliente extends PID{
    private Double totalGasto;
    private Date dataCadastro;

    public Cliente(String nome,
                   String ID,
                   Date DataNascimento,
                   Double totalGasto,
                   Date dataCadastro) {
        super(nome, ID, DataNascimento);
        this.totalGasto = totalGasto;
        this.dataCadastro = dataCadastro;
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


