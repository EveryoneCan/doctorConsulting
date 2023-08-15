public class Consultation extends Patient {
    // Get variable names from separate class.
    private String txtPatientTime;

    private String docName;

    private String price;

    public Consultation(String personName, String personSurname, String personMobileNo, String personBirth, String patientId,String txtPatientTime,String docName,String price) {
        //These are the things that get from super class.
        super(personName, personSurname, personMobileNo, personBirth, patientId);

        this.txtPatientTime=txtPatientTime;
        this.docName=docName;
        this.price=price;
    }

    // Create getters and setters.
    public String gettxtPatientTime() {return txtPatientTime;}
    public void setTxtPatientTime(String txtPatientTime) {this.txtPatientTime = txtPatientTime;}
    public String getDocName() {return docName;}
    public void setDocName(String docName) {this.docName = docName;}
    public String getPrice() {return price;}
    public void setPrice(String price) {this.price = price;}

    // toString method.
    @Override
    public String toString() {
        return "Doctor{" +
                " getPersonName=" + getPersonName() +
                " getPersonSurname=" + getPersonSurname() +
                " getPersonMobileNo=" + getPersonMobileNo() +
                " getPersonBirth=" + getPersonBirth() +
                " docName=" + docName+
                " txtPatientTime=" + txtPatientTime+
                " price=" + price+
                '}';
    }

}
