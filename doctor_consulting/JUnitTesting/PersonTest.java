import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person p1=new Person("Navindya","Denipitiya","071-1234565","02/03/1995");

    @Test
    void getPersonName() {
        assertEquals("Navindya",p1.getPersonName());
    }

    @Test
    void setPersonName() {
        p1.setPersonName("John");
        assertEquals("John",p1.getPersonName());
    }

    @Test
    void getPersonSurname() {
        assertEquals("Denipitiya",p1.getPersonSurname());
    }

    @Test
    void setPersonSurname() {
        p1.setPersonSurname("Wijey");
        assertEquals("Wijey",p1.getPersonSurname());
    }

    @Test
    void getPersonMobileNo() {
        assertEquals("071-123456765",p1.getPersonMobileNo());
    }

    @Test
    void setPersonMobileNo() {
        p1.setPersonMobileNo("071-126786765");
        assertEquals("071-126786765",p1.getPersonMobileNo());
    }

    @Test
    void getPersonBirth() {
        assertEquals("02/03/1995",p1.getPersonBirth());
    }

    @Test
    void setPersonBirth() {
        p1.setPersonBirth("02/03/1995");
        assertEquals("02/03/1995",p1.getPersonBirth());
    }
}