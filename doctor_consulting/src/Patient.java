public class Patient extends Person implements Comparable<Patient> {

    // Get variable names from separate class.
    private String patientId;

    public Patient(String personName,String personSurname, String personMobileNo,String personBirth,String patientId   ) {
        //These are the things that get from super class.
        super(personName,personSurname, personMobileNo,personBirth);

        this.patientId = patientId;
    }

    // Create getters and setters.
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    // toString method.
    @Override
    public String toString() {
        return "Doctor{" +
                " getPersonName=" + getPersonName() +
                " getPersonSurname=" + getPersonSurname() +
                " getPersonMobileNo=" + getPersonMobileNo() +
                " getPersonBirth=" + getPersonBirth() +
                " patientId=" + patientId +
                '}';
    }
    @Override
    public int compareTo(Patient o) {
        return 0;
    }
}