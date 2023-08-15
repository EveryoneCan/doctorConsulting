import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsultationTest {

    private Consultation c1=new Consultation("Simon","Afsa","071-12340765","02/03/1994","346","13/02/2023..14.00","Dr.John","£25");
    @Test
    void gettxtPatientTime() {
        assertEquals("13/02/2023..14.00",c1.gettxtPatientTime());
    }

    @Test
    void setTxtPatientTime() {
        c1.setTxtPatientTime("14/02/2023..16.00");
        assertEquals("14/02/2023..16.00",c1.gettxtPatientTime());
    }

    @Test
    void getDocName() {
        assertEquals("Dr.John",c1.getDocName());
    }

    @Test
    void setDocName() {
        c1.setDocName("Dr.Johnny");
        assertEquals("Dr.Johnny",c1.getDocName());
    }

    @Test
    void getPrice() {
        assertEquals("£25",c1.getPrice());
    }

    @Test
    void setPrice() {
        c1.setTxtPatientTime("£15");
        assertEquals("£15",c1.gettxtPatientTime());
    }

}