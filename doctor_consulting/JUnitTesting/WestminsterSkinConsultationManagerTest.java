import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterSkinConsultationManagerTest {

    private static final ArrayList<Doctor> DoctorArrayList=new ArrayList<>();
    @Test
    void addDoctor() {
        Doctor doctor=new Doctor("Dr.John","Krippy","071-4567313","12/12/1973","aqw1232","cosmetic");
        DoctorArrayList.add(doctor);
        assertTrue(DoctorArrayList.contains(doctor));
    }

    @Test
    void deleteDoctor() {
        Doctor doctor=new Doctor("Dr.John","Krippy","071-4567313","12/12/1973","aqw1232","cosmetic");
        DoctorArrayList.add(doctor);
        DoctorArrayList.remove(doctor);
        assertFalse(DoctorArrayList.contains(doctor));
    }

    @Test
    void printDoctors() {
        Doctor doctor=new Doctor("Dr.John","Krippy","071-4567313","12/12/1973","aqw1232","cosmetic");
        Doctor doctor1=new Doctor("Dr.Kamal","Afsa","071-4445413","12/12/1983","avf1232","cosmetic");
        DoctorArrayList.add(doctor);
        DoctorArrayList.add(doctor1);
        String names[] = new String[DoctorArrayList.size()];
        names[0] =DoctorArrayList.get(0).getPersonSurname()+" "+DoctorArrayList.get(0).getPersonName()+" "+DoctorArrayList.get(0).getPersonMobileNo() + " " + DoctorArrayList.get(0).getPersonBirth() + " " + DoctorArrayList.get(0).getMedilicesnse() + " " + DoctorArrayList.get(0).getSpecialisation();
        names[1] =DoctorArrayList.get(1).getPersonSurname()+" "+DoctorArrayList.get(1).getPersonName()+" "+DoctorArrayList.get(1).getPersonMobileNo() + " " + DoctorArrayList.get(1).getPersonBirth() + " " + DoctorArrayList.get(1).getMedilicesnse() + " " + DoctorArrayList.get(1).getSpecialisation();
        Arrays.sort(names);
        for (int i = 0; i < DoctorArrayList.size(); i++) {
            System.out.println(names[i]);
        }
        assertTrue(DoctorArrayList.contains(doctor));
    }

    @Test
    void allDocDetails() {
        Doctor doctor = new Doctor("Dr.John", "Krippy", "071-4567313", "12/12/1973", "aqw1232", "cosmetic");
        Doctor doctor1 = new Doctor("Dr.Kamal", "Afsa", "071-4445413", "12/12/1983", "avf1232", "cosmetic");
        DoctorArrayList.add(doctor);
        DoctorArrayList.add(doctor1);
        for (int i = 0; i < DoctorArrayList.size(); i++) {
            System.out.println(DoctorArrayList.get(i).getPersonName() + " " + DoctorArrayList.get(i).getPersonSurname() + " " + DoctorArrayList.get(i).getPersonMobileNo() + " " + DoctorArrayList.get(i).getPersonBirth() + " " + DoctorArrayList.get(i).getMedilicesnse() + " " + DoctorArrayList.get(i).getSpecialisation());
        }
        assertTrue(DoctorArrayList.contains(doctor));
        assertTrue(DoctorArrayList.contains(doctor1));
    }
}