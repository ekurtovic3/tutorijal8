package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Driver {
    SimpleStringProperty name,surname,jmb;
    SimpleStringProperty Birthday,EmployeadDate;



    public Driver(String name, String surname, String jmb, LocalDate birthday, LocalDate employedDate) {
        this.name =new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.jmb = new SimpleStringProperty(jmb);
        this.Birthday = new SimpleStringProperty(birthday.toString());
        this.EmployeadDate= new SimpleStringProperty(employedDate.toString());
    }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getSurname() {
        return surname.get();
    }
    public void setSurname(String surname) {
        this.surname.set(surname);
    }
    public String getJmb() {
        return jmb.get();
    }
    public void setJmb(String jmb) {
        this.jmb.set(jmb);
    }
    public LocalDate getBirthday() {
        return this.getBirthday();
    }
    public void setBirthday(LocalDate birthday) {
        Birthday.set(String.valueOf(Birthday));
    }
    public LocalDate getEmployeadDate() {
        return this.getEmployeadDate();
    }

    public SimpleStringProperty birthdayProperty() {
        return Birthday;
    }

    public SimpleStringProperty employeadDateProperty() {
        return EmployeadDate;
    }

    public void setEmployeadDate(LocalDate employeadDate) {
        EmployeadDate.set(String.valueOf(EmployeadDate));
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public SimpleStringProperty jmbProperty() {
        return jmb;
    }

    @Override
    public String toString(){
        return this.getName()+' '+ this.getSurname()+" ( "+ this.getJmb()+ " )";
    }
}
