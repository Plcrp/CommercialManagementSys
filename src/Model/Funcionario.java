package Model;

import java.util.Date;
public class Funcionario extends PID {
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
        this.joiningDate = joiningDate;
        this.cargo = cargo;
        this.salario = salario;
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
