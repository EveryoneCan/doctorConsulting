import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    private Patient pa1=new Patient("Amal","Fernando","077-123456765","02/02/1999","11");

    @Test
    void getPatientId() {
        assertEquals("11",pa1.getPatientId());
    }

    @Test
    void setPatientId() {
        pa1.setPatientId("177");
        assertEquals("177",pa1.getPatientId());
    }


}