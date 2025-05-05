package Model;

import java.util.Date;
public class Funcionario extends PID implements Validavel{
    private Date joiningDate;
    private Cargo cargo;
    private double salario; //Passível de modificação futura para LONG(mais precisão)

    public Funcionario(String name,
                       String ID,
                       Date birthDate,
                       Date joiningDate,
                       Cargo cargo,
                       double salario) {
        super(name, ID, birthDate);
        if(!validar(ID)){
            throw new IllegalArgumentException("CPF incorreto");
        }
        this.joiningDate = joiningDate;
        this.cargo = cargo;
        this.salario = salario;
    }

    public boolean validar(String cpf){
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

    public Date getJoiningDate() {
        return joiningDate;
    }
    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }
    public Cargo getCargo() {
        return cargo;
    }
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
}
