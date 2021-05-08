import java.io.Serializable;

public abstract class AbstractPerson implements Person, Serializable{
    String name;
    String surname;
    String ID;
    String phoneNum;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getID() {
        return ID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public AbstractPerson(String name, String surname, String ID, String phoneNum) {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.phoneNum = phoneNum;
    }
}
