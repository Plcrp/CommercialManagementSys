package Model;

import java.util.Date;
public class PID {
    private String nome;
    private String ID; //CPF ou CNPJ
    private Date DataNascimento;

    public PID(String nome, String ID, Date DataNascimento) {
        this.nome = nome;
        this.ID = ID;
        this.DataNascimento = DataNascimento;
    }
    public PID(String nome){ //Construtor para testes
        this.nome = nome;
    }

    public String getNome() {return nome;}
    public String getID() {return ID;}
    public Date getDataNascimento() {return new Date(DataNascimento.getTime());}

}
