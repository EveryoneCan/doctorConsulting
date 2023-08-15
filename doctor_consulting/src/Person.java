import java.io.Serializable;

// Used abstract class.
public class Person implements Serializable {

    // Get names from the main class.
    private String personName;
    private String personSurname;
    private String personMobileNo;
    private String personBirth;

    // Create method for get data.
    public Person(String personName, String personSurname, String personMobileNo,String personBirth) {
        this.personName = personName;
        this.personSurname = personSurname;
        this.personMobileNo = personMobileNo;
        this.personBirth = personBirth;
    }

    // Getters and setters.
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public String getPersonMobileNo() {
        return personMobileNo;
    }

    public void setPersonMobileNo(String personMobileNo) {
        this.personMobileNo = personMobileNo;
    }


    public String getPersonBirth() {
        return personBirth;
    }

    public void setPersonBirth(String personBirth) {
        this.personBirth = personBirth;
    }
}


