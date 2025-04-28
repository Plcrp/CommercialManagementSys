package Model;

import java.util.ArrayList;
import java.util.Date;

public class Cliente extends PID{
    private ArrayList<Produto> produtosComprados;
    private Double totalGasto;
    private Date dataCadastro;

    public Cliente(String name,
                   String ID,
                   Date birthDate,
                   ArrayList<Produto> produtosComprados,
                   Double totalGasto,
                   Date dataCadastro) {
        super(name, ID, birthDate);
        this.produtosComprados = produtosComprados;
        this.totalGasto = totalGasto;
        this.dataCadastro = dataCadastro;
    }
    public Cliente(String name){
        super(name);
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

    public ArrayList<Produto> getProdutosComprados() {
        return produtosComprados;
    }

    public void setProdutosComprados(ArrayList<Produto> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }

}


