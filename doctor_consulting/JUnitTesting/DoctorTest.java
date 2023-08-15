import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    private Doctor d1=new Doctor("Kamal","Silva","077-1234566765","12/12/1998","qw1235y","cosmetic");
    @Test
    void getMedilicesnse() {
        assertEquals("qw1235y",d1.getMedilicesnse());
    }

    @Test
    void setMedilicesnse() {
        d1.setMedilicesnse("acd3456");
        assertEquals("acd3456",d1.getMedilicesnse());
    }

    @Test
    void getSpecialisation() {
        assertEquals("cosmetic",d1.getSpecialisation());
    }

    @Test
    void setSpecialisation() {
        d1.setSpecialisation("medical");
        assertEquals("medical",d1.getSpecialisation());
    }


}