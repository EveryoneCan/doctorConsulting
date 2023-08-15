import java.io.*;
import java.text.ParseException;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    // Create LinkedList to store doctor information.
    public LinkedList<Doctor> doctorList = new LinkedList<>();

    // Create LinkedList to store consultation information.
    public ArrayList<Consultation> consultationArrayList = new ArrayList<>();

    // Create LinkedList to store doctor dates and times.
    public LinkedList<List> docDateTime = new LinkedList<>();

    // This will support to call the methods in any time.
    public static WestminsterSkinConsultationManager w1 = new WestminsterSkinConsultationManager();

    public static void main(String[] args) throws ParseException, IOException {

        // Load the data that stored in the ser file.
        w1.loadData();


        // Create scanner method.
        Scanner input = new Scanner(System.in);
        String userChoice;

        // Display options to the user.
        while (true) {
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|           Westminster Skin Consultation Manager                  |");
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|    Enter 1 to add new doctor to the system.                      |");
            System.out.println("|    Enter 2 to delete a doctor from the system.                   |");
            System.out.println("|    Enter 3 to print the list of doctors in alphabetical order.   |");
            System.out.println("|    Enter 4 to save doctor information to the file.               |");
            System.out.println("|    Enter 5 to see all doctor details.                            |");
            System.out.println("|    Enter 6 to add patient details in GUI application.            |");
            System.out.println("|    Enter 7 to exit from the program.                             |");
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|------------------------------------------------------------------|");

            // Get user input.
            System.out.print("\nPlease enter your option : ");
            userChoice = input.next();

            // Called the methods according to user input.
            // Add doctor section.
            if (userChoice .equals( "1")) {
                w1.addDoctor();
            }

            // Delete doctor section.
            else if (userChoice.equals("2")) {
                w1.deleteDoctor();
            }

            // Sort doctor surnames section.
            else if (userChoice.equals("3")) {
                w1.printDoctors();
                System.out.println();
            }

            // Save data to the file.
            else if (userChoice.equals("4")) {
                w1.saveData();
            }

            // Save all doctor details.
            else if (userChoice.equals("5")) {
                w1.allDocDetails();
            }

            // Display the GUI.
            else if (userChoice.equals("6")) {
               MainGuI mainGuI = new MainGuI();
            }

            // Exit from the program.
            else if (userChoice.equals("7")){
                break;
            }

            // If user input wrong number this will display.
            else {
                System.out.println("Please enter correct input from above.");
            }
        }
    }

    // Method for add doctor to the system.
    public void addDoctor() throws ParseException {
        System.out.println("\n----You are in add doctors to the system section---\n");
        if (doctorList.size() >= 10) {
            System.out.println(" Sorry it' s fill.");
        } else {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter the name of the doctor                                       : ");
            String personName = input.next();


            System.out.print("Please enter the surname of the doctor                                    : ");
            String personSurname = input.next();

            System.out.print("Please enter the mobile number of the doctor                              : ");
            String personMobileNo = input.next();

            System.out.print("Please enter the date of birth of the doctor format(dd/mm/yyyy/)         : ");
            String personBirth = input.next();

            System.out.print("Please enter the medical license number of the doctor                     : ");
            String medilicesnse = input.next();

            System.out.print("Please enter the specialisation of the doctor                             : ");
            String specialisation = input.next();

            List docDateTimeList = new ArrayList();
            System.out.println("\nPlease make sure to add your name as the first element and add time in 24 hours clock time");
            System.out.println("\nPlease enter how many times you can add consultations : ");
            int num=0;

            try {
            num = input.nextInt();
            for (int x = 0; x < num; x++) {
                System.out.println("Please enter the date and time format(dd/mm/yyyy/..hr.mins):  ");
                String dateTime = input.next();
                docDateTimeList.add(dateTime);
            } }
            catch (Exception e) {
                System.out.println("Something went wrong.Please reenter your information");
            }

            docDateTime.add(docDateTimeList);

            // Passing values to the Doctor class.
            doctorList.add(new Doctor(personName, personSurname, personMobileNo, personBirth, medilicesnse, specialisation));

            System.out.println(" Details are stored.");
        }
    }
    // Method for delete doctor from the system.
    public void deleteDoctor() {
        System.out.println("\n----You are in delete doctors from the system section---\n");

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the medical license number of the doctor that you want to delete: ");
        String deletemedical = input.next();
        System.out.println("Enter the name of the  doctor to clarify : ");
        String deletename = input.next();
        boolean check = true;
        for (int x = 0; x < doctorList.size(); x++) {
            if (doctorList.get(x).getMedilicesnse().equalsIgnoreCase(deletemedical)) {
                System.out.println("\n Doctor name                   :- " + doctorList.get(x).getPersonName() +
                        "\n Doctor surname                :- " + doctorList.get(x).getPersonSurname() +
                        "\n Doctor mobile number          :- " + doctorList.get(x).getPersonMobileNo() +
                        "\n Doctor's birth                :- " + doctorList.get(x).getPersonBirth() +
                        "\n Doctor medical license number :- " + doctorList.get(x).getMedilicesnse() +
                        "\n Doctor's specialisation       :- " + doctorList.get(x).getSpecialisation()+
                        "\n has been deleted from the system" );
                doctorList.remove(x);
                check = false;
                break;
            }
        }
        for(int i = 0; i < docDateTime.size(); i++) {
            for (int j = 0; j < docDateTime.get(i).size(); j++) {
                if (docDateTime.get(i).get(j).equals(deletename)) {
                    docDateTime.remove(i).remove(j);
                    check = false;
                    break;
                }
            }
        }
        System.out.println(docDateTime);
        if (check) {
            System.out.println("\nCould not find the license number called " + deletemedical + " Please enter the  license number again!");

        }
        int count = 0;
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getPersonName() != null) {
                count++;
            }
        }
        System.out.println("\nThere are " + count + " doctor remain in the system.");
    }

    // Method for sort doctor names.
    public void printDoctors() {
        System.out.println("----You are in sort doctors name in alphabetically order section---\n");

        // Create string array called names.
        String names[] = new String[doctorList.size()];

        for (int x = 0; x < doctorList.size(); x++) {
            names[x] ="\n Doctor surname                :- " + doctorList.get(x).getPersonSurname() +
                    "\n Doctor name                   :- " +doctorList.get(x).getPersonName()+
                    "\n Doctor mobile number          :- " + doctorList.get(x).getPersonMobileNo() +
                    "\n Doctor's birth                :- " + doctorList.get(x).getPersonBirth() +
                    "\n Doctor medical license number :- " + doctorList.get(x).getMedilicesnse() +
                    "\n Doctor's specialisation       :- " + doctorList.get(x).getSpecialisation();
        }

        // inbuilt sort function
        Arrays.sort(names );

        // print output array
        System.out.println("The doctor details in alphabetical order are : ");
        for (int i = 0; i < doctorList.size(); i++) {
            System.out.println(names[i]);

        }
    }

    // Method for see all doctor details.
    public void  allDocDetails() {
        // Display all doctor details.
        for (int i = 0; i < doctorList.size(); i++) {
            System.out.println("\n Doctor name                   :- " + doctorList.get(i).getPersonName() +
                    "\n Doctor surname                :- " + doctorList.get(i).getPersonSurname() +
                    "\n Doctor mobile number          :- " + doctorList.get(i).getPersonMobileNo() +
                    "\n Doctor's birth                :- " + doctorList.get(i).getPersonBirth() +
                    "\n Doctor medical license number :- " + doctorList.get(i).getMedilicesnse() +
                    "\n Doctor's specialisation       :- " + doctorList.get(i).getSpecialisation());
        }
    }

    // Method for save details.
    public void saveData() {
        // Save all doctor details.
        String file = "DoctorDetails.ser";
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(doctorList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save all doctor date time details.
        String files = "DoctorTimes.ser";
        try {
            FileOutputStream fileOut = new FileOutputStream(files);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(docDateTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Your data saved to the system.");
    }
    @SuppressWarnings("unchecked")
    // Load all doctor date time details.
    public void loadData() {
        // Save all doctor details.
        String file = "DoctorDetails.ser";
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            doctorList = (LinkedList) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Save all doctor date time details.
        String files = "DoctorTimes.ser";
        try {
            FileInputStream fileIn = new FileInputStream(files);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            docDateTime = (LinkedList) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Save all patient details.
    public void savePatientInfo() {
        String file = "patient.ser";
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(consultationArrayList);
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    // Load all patient details.
    public void loadPatientInfo() {
        String file = "patient.ser";
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            consultationArrayList = (ArrayList<Consultation>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Getter and setter methods for doctorlist.
    public LinkedList<Doctor> getDoctorList() {
        return doctorList;
    }
    public void setDoctorList(LinkedList<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
}
