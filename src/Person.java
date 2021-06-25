import java.io.Serializable;

public interface Person extends Serializable {
    String getName();
    String getSurname();
    String getID();
    String getPhoneNum();
    String getPassword();
    void setName(String name);
    void setSurname(String surname);
    void setID(String ID);
    void setPhoneNum(String phoneNum);
    void setPassword(String password);
}
