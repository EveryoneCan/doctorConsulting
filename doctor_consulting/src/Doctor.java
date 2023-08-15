public class Doctor extends Person implements Comparable<Doctor> {

    // Get variable names from the main class.
    private String medilicesnse;
    private String specialisation;


    public Doctor(String personName, String personSurname, String personMobileNo, String personBirth, String medilicesnse, String specialisation) {
        //These are the things that get from super class.
        super(personName, personSurname, personMobileNo,personBirth);

        this.medilicesnse = medilicesnse;
        this.specialisation = specialisation;

    }

    // Create getters and setters.
    public String getMedilicesnse() {
        return medilicesnse;
    }

    public void setMedilicesnse(String medilicesnse) {
        this.medilicesnse = medilicesnse;
    }
    public String getSpecialisation() {
        return specialisation;
    }
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    // toString method.
    @Override
    public String toString() {
        return "Doctor{" +
                " getPersonName=" + getPersonName() +
                " getPersonSurname=" + getPersonSurname() +
                " getPersonMobileNo=" + getPersonMobileNo() +
                " getPersonBirth=" + getPersonBirth() +
                " medilicesnse=" + medilicesnse +
                " specialisation=" + specialisation+
                '}';
    }
    @Override
    public int compareTo(Doctor o) {
        return 0;
    }
}

