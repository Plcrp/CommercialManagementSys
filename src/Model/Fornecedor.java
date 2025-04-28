package Model;

import java.util.ArrayList;
import java.util.Date;

public class Fornecedor extends PID{
    private ArrayList<Produto> produtos = new ArrayList<>();
    private String Localizacao;

    public Fornecedor(String name,
                      String ID,
                      Date date,
                      ArrayList<Produto> produtos){
        super(name, ID, date);
        this.produtos.addAll(produtos);
    }
    public Fornecedor(String name,
                      String ID,
                      Date date){
        super(name, ID, date);
        this.produtos = new ArrayList<>();
    }
}
