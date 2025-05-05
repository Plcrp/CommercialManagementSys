package Model;

import java.util.Date;
public class Produto {
    private String name; // :/
    private String ID;
    private double preco;
    private Date dataCadastro;
    private Date fabricacao;
    private Date vencimento;

    public Produto(String name,
                   String ID,
                   double preco,
                   Date dataCadastro,
                   Date fabricacao,
                   Date vencimento) {
        this.name = name;
        this.ID = ID;
        this.preco = preco;
        this.dataCadastro = dataCadastro;
        this.fabricacao = fabricacao;
        this.vencimento = vencimento;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataCadastro() {
        return new Date(dataCadastro.getTime());
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getFabricacao() {
        return new Date(fabricacao.getTime());
    }

    public void setFabricacao(Date fabricacao) {
        this.fabricacao = fabricacao;
    }

    public Date getVencimento() {
        return new Date(vencimento.getTime());
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }
}
