import java.io.Serializable;

public abstract class AbstractPerson implements Person, Serializable{
    String name;
    String surname;
    String ID;
    String phoneNum;
    String password;

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

    public String getPassword() {
        return password;
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

    public void setPassword(String password){
        this.password = password;
    }

    public AbstractPerson(String name, String surname, String ID, String phoneNum, String password) {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    @Override
    public String toString() {

        return
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + ID + '\'' +
                ", phoneNum='" + phoneNum + '\'';
    }
}
