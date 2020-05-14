package ba.unsa.etf.rs.tutorijal8;

import java.time.LocalDate;

public class Driver {
    String name,surname,jmb;
    LocalDate Birthday,EmployeadDate;
    public Driver(String name, String surname, String jmb, LocalDate birthday, LocalDate employedDate) {
        this.name = name;
        this.surname = surname;
        this.jmb = jmb;
        Birthday = birthday;
        EmployeadDate = employedDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getJmb() {
        return jmb;
    }
    public void setJmb(String jmb) {
        this.jmb = jmb;
    }
    public LocalDate getBirthday() {
        return Birthday;
    }
    public void setBirthday(LocalDate birthday) {
        Birthday = birthday;
    }
    public LocalDate getEmployeadDate() {
        return EmployeadDate;
    }
    public void setEmployeadDate(LocalDate employeadDate) {
        EmployeadDate = employeadDate;
    }
    @Override
    public String toString(){
        return name+' '+ surname+" ( "+ jmb+ " )";
    }
}