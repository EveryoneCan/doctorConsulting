import java.text.ParseException;

// Create interface of main class.
public interface SkinConsultationManager {

    // This will help to call any method in any other class.
    void saveData();
    void loadData();
    void addDoctor() throws ParseException;
    void deleteDoctor();
    void printDoctors();

    void savePatientInfo();

    void loadPatientInfo();

}
