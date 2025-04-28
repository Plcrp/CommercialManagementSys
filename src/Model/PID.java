package Model;

import java.util.Date;
public class PID {
    private String name;
    private String ID; //CPF ou CNPJ
    private Date birthDate;

    public PID(String name, String ID, Date birthDate) {
        this.name = name;
        this.ID = ID;
        this.birthDate = birthDate;
    }
    public PID(String name){ //Construtor para testes
        this.name = name;
    }

    public String getName() {return name;}
    public String getID() {return ID;}
    public Date getBirthDate() {return new Date(birthDate.getTime());}

}
