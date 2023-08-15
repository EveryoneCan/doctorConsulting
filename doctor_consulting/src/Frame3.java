import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class Frame3 extends JFrame implements ActionListener {

    // Create new line when storing data.
    private static final String NEW_LINE = System.lineSeparator();

    // Secret key for encrypt and decrypt process.
    private static SecretKeySpec secretKey;
    private static byte[] key;

    // Use AES algorithm to do encrypt and decrypt.
    private static final String ALGORITHM = "AES";

    // Calling WestminsterSkinConsultationManager class.
    WestminsterSkinConsultationManager w1 = new WestminsterSkinConsultationManager();

    // Back button to go previous page.
    JButton btnBack;

    // Buttons for edit and remove details.
    JButton btnEdit,btnRemove;

    // rexr fields for edit and remove details.
    JTextField txtEditId,txtReplaces,txtEdits;

    // Combo box for show names of the editing columns.
    JComboBox selection;
    String[] selections = {"Patient Name", "Patient Surname","Patient mobile no","Patient date of birth"};

    public Frame3() throws ParseException, IOException {

        // Load doctor details
        w1.loadData();

        //Load patient details
        w1.loadPatientInfo();

        //Read unique Id file.
        BufferedReader bufReader = new BufferedReader(new FileReader("src\\uniqueId.txt"));
        ArrayList<String> IDs = new ArrayList<>();
        String line = null;
        try {
            line = bufReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (line != null) {
            IDs.add(line);
            line = bufReader.readLine();
        } bufReader.close();

        // Add image icon to the file.
        ImageIcon img = new ImageIcon("doctor.jpeg");
        setIconImage(img.getImage());

        // Main topic of the page.
        JLabel lblMainTopic = new JLabel("<<  Add Patient Details  >> ");
        lblMainTopic.setBounds(370,20,550,30);
        lblMainTopic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
        lblMainTopic.setForeground(new Color(0, 119, 13));

        // Sub topic of the page.
        JLabel lblSubTopic = new JLabel("<<  Make A Consultation With A Doctor  >> ");
        lblSubTopic.setBounds(300,60,550,30);
        lblSubTopic.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
        lblSubTopic.setForeground(new Color(0, 119, 13));

        // Part of the information to fill data.
        JLabel lblInfo = new JLabel("** Please make sure to fill data with correct information and once ");
        lblInfo.setBounds(500,120,760,80);
        lblInfo.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        lblInfo.setForeground(new Color(0, 0, 0));

        // Part of the information to fill data.
        JLabel lblInfo2 = new JLabel("     you get your unique id please make sure to remember it :)");
        lblInfo2.setBounds(500,140,760,80);
        lblInfo2.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,15));
        lblInfo2.setForeground(new Color(0, 0, 0));

        // Label surname.
        JLabel lblSurname = new JLabel("Surname");
        lblSurname.setBounds(480, 250, 150, 20);
        lblSurname.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        // Text field surname.
        JTextField personSurname = new JTextField();
        personSurname.setBounds(600, 250, 150, 25);
        personSurname.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));

        // Label name.
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(480, 280, 150, 20);
        lblName.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        // Text field name.
        JTextField personName = new JTextField();
        personName.setBounds(600, 280, 150, 25);
        personName.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));

        // Label date of birth.
        JLabel lblDOB = new JLabel("Date of Birth");
        lblDOB.setBounds(480, 310, 150, 20);
        lblDOB.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        JLabel lblDOB1 = new JLabel("yyyy-mm-dd");
        lblDOB1.setBounds(480, 330, 150, 20);

        // Formatted text field date of birth.
        Date dateOfBirth = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfBirthString = formatter.format(dateOfBirth);
        JFormattedTextField personBirth  = new JFormattedTextField(createFormatter("####-##-##"));
        personBirth.setColumns(20);
        setLayout(new BorderLayout());
        personBirth.setBounds(600, 310, 150, 25);
        personBirth.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));

        // Label mobile number.
        JLabel lblPhoneNo = new JLabel("Mobile Number");
        lblPhoneNo.setBounds(820, 250, 150, 20);
        lblPhoneNo.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        // Formatted text field mobile number.
        JFormattedTextField personMobileNo = new JFormattedTextField();
        personMobileNo.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###-#######")));
        personMobileNo.setBounds(940, 250, 150, 25);
        personMobileNo.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));

        // Label of the unique number.
        JLabel lblPatientId = new JLabel("Unique Id");
        lblPatientId.setBounds(820, 280, 150, 20);
        lblPatientId.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        // Text field to add unique number.
        JTextField patientId = new JTextField();
        patientId.setBounds(940, 280, 150, 25);
        patientId.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));

        // Button for add data to the system.
        JButton btnAddData = new JButton("Add consultation");
        btnAddData.setBounds(700, 480, 195, 30);
        btnAddData.setForeground(new Color(255, 172, 0));
        btnAddData.setBackground(new Color(0, 0, 0));
        btnAddData.setBorder(BorderFactory.createLineBorder(new Color(164, 117, 0)));

        // Text field to show unique id of patient.
        JTextField uniqueId = new JTextField();
        uniqueId.setBounds(1000, 120, 65, 20);

        // Label to show unique id of patient.
        JLabel lbluniqueId = new JLabel("Unique id");
        lbluniqueId.setBounds(1000, 100, 65, 20);

        // Label for show the payment amount of the patient.
        JLabel lblPrice = new JLabel();
        lblPrice.setBounds(690, 520, 450, 20);
        lblPrice.setVisible(false);

        // Back btnBack of the page.
        btnBack = new JButton();
        btnBack.setText("Back");
        btnBack.setBounds(20,20,75,30);
        btnBack.setForeground(new Color(255, 172, 0));
        btnBack.setBackground(new Color(0, 0, 0));
        btnBack.setBorder(BorderFactory.createLineBorder(new Color(164, 117, 0)));
        btnBack.setFocusable(false);
        btnBack.addActionListener(this);

        // Label for inform the available doctors.
        JLabel lblSeeDoctors = new JLabel("Available doctors");
        lblSeeDoctors.setBounds(160, 120, 195, 30);
        lblSeeDoctors.setForeground(new Color(141, 0, 0));
        lblSeeDoctors.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        // 10 buttons for 10 doctors.
        JButton btnDoc1 = new JButton();
        btnDoc1.setBounds(50,150,155,30);
        JButton btnDoc2 = new JButton();
        btnDoc2.setBounds(230,150,155,30);
        JButton btnDoc3 = new JButton();
        btnDoc3.setBounds(50,200,155,30);
        JButton btnDoc4 = new JButton();
        btnDoc4.setBounds(230,200,155,30);
        JButton btnDoc5 = new JButton();
        btnDoc5.setBounds(50,250,155,30);
        JButton btnDoc6 = new JButton();
        btnDoc6.setBounds(230,250,155,30);
        JButton btnDoc7 = new JButton();
        btnDoc7.setBounds(50,300,155,30);
        JButton btnDoc8 = new JButton();
        btnDoc8.setBounds(230,300,155,30);
        JButton btnDoc9 = new JButton();
        btnDoc9.setBounds(50,350,155,30);
        JButton btnDoc10 = new JButton();
        btnDoc10.setBounds(230,350,155,30);

        // Text field to input patient date and time.
        Date patientDate = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/mm/yyyy/..hh.mm");
        String patientDateString = formatter1.format(patientDate);
        JFormattedTextField txtPatientTime  = new JFormattedTextField(createFormatter("##/##/####..##.##"));
        txtPatientTime.setColumns(20);
        setLayout(new BorderLayout());
        txtPatientTime.setBounds(50,400,155,30);
        txtPatientTime.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));

        // Label to inform the format.
        JLabel lblFormat=new JLabel("dd/mm/yyyy/..hr.mins");
        lblFormat.setBounds(50,420,155,30);

        // Combo box for select a doctor.
        JComboBox comboDOcNames = new JComboBox();
        comboDOcNames.setBounds(230,400,155,30);

        // Text area for show available doctors dates and times.
        TextArea txtAreaDocDetails=new TextArea();
        txtAreaDocDetails.setBounds(50,450,355,300);
        txtAreaDocDetails.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        // Text area for add text.
        TextArea txtAreaPatient = new TextArea("Write your message");
        txtAreaPatient.setBounds(480, 370, 600, 100);
        txtAreaPatient.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));

        // Label for show consultation time and doctor.
        JLabel lblConsul= new JLabel();
        lblConsul.setBounds(690, 540, 650, 20);

        // Button for add text or image.
        JButton btnAddtxtImage = new JButton("Add text or image");
        btnAddtxtImage.setBounds(820, 310, 195, 30);
        btnAddtxtImage.setForeground(new Color(255, 172, 0));
        btnAddtxtImage.setBackground(new Color(0, 0, 0));
        btnAddtxtImage.setBorder(BorderFactory.createLineBorder(new Color(164, 117, 0)));

        // Line of the page.
        JTextField lblline=new JTextField();
        lblline.setBounds(430, 100, 15, 650);
        lblline.setBackground(new Color(0, 0, 0));

        // Add image to the frame.
        ImageIcon image = new ImageIcon("frame3.jpeg");
        JLabel lblImg = new JLabel();
        lblImg.setBounds(850, 600, 250, 150);
        lblImg.setIcon(image);

        // Read unique ids.
        try {
            bufReader = new BufferedReader(new FileReader("src\\uniqueId.txt"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        try {
            line = bufReader.readLine();
        } catch (IOException j) {
            throw new RuntimeException(j);
        }
        while (line != null) {
            IDs.add(line);
            try {
                line = bufReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            bufReader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // Generate unique id.
        Path path3 = Paths.get("src\\uniqueId.txt");
        try {
            // Append values to the file.
            int aa = Integer.parseInt(IDs.get(IDs.size()-1));
            String newID = appendToFile(path3, aa+1 + NEW_LINE);
            IDs.add(newID);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        for (int i = 0; i < IDs.size(); i++) {
            uniqueId.setText(IDs.get(i));
        }

        // Do not show the all buttons at the first.
        btnDoc1.setVisible(false);
        btnDoc2.setVisible(false);
        btnDoc3.setVisible(false);
        btnDoc4.setVisible(false);
        btnDoc5.setVisible(false);
        btnDoc6.setVisible(false);
        btnDoc7.setVisible(false);
        btnDoc8.setVisible(false);
        btnDoc9.setVisible(false);
        btnDoc10.setVisible(false);

        // According to the length show only the doctors who are in the current system.
        if (w1.docDateTime.size() == 1) {
            btnDoc1.setVisible(true);
        }
        else  if (w1.docDateTime.size() == 2) {
            btnDoc1.setVisible(true); btnDoc2.setVisible(true);
        }
        else  if (w1.docDateTime.size() == 3) {
            btnDoc1.setVisible(true); btnDoc2.setVisible(true);btnDoc3.setVisible(true);
        }
        else  if (w1.docDateTime.size() == 4) {
            btnDoc1.setVisible(true); btnDoc2.setVisible(true);btnDoc3.setVisible(true);btnDoc4.setVisible(true);
        }
        else  if (w1.docDateTime.size() == 5) {
            btnDoc1.setVisible(true); btnDoc2.setVisible(true);btnDoc3.setVisible(true);btnDoc4.setVisible(true);btnDoc5.setVisible(true);
        }
        else  if (w1.docDateTime.size() == 6) {
            btnDoc1.setVisible(true); btnDoc2.setVisible(true);btnDoc3.setVisible(true);btnDoc4.setVisible(true);btnDoc5.setVisible(true);btnDoc6.setVisible(true);
        }
        else  if (w1.docDateTime.size() == 7) {
            btnDoc1.setVisible(true); btnDoc2.setVisible(true);btnDoc3.setVisible(true);btnDoc4.setVisible(true);btnDoc5.setVisible(true);btnDoc6.setVisible(true);btnDoc7.setVisible(true);
        }
        else  if (w1.docDateTime.size() == 8) {
            btnDoc1.setVisible(true); btnDoc2.setVisible(true);btnDoc3.setVisible(true);btnDoc4.setVisible(true);btnDoc5.setVisible(true);btnDoc6.setVisible(true);btnDoc7.setVisible(true);btnDoc8.setVisible(true);
        }
        else  if (w1.docDateTime.size() == 9) {
            btnDoc1.setVisible(true); btnDoc2.setVisible(true);btnDoc3.setVisible(true);btnDoc4.setVisible(true);btnDoc5.setVisible(true);btnDoc6.setVisible(true);btnDoc7.setVisible(true);btnDoc8.setVisible(true);btnDoc9.setVisible(true);

        }
        else  if (w1.docDateTime.size() == 10) {
            btnDoc1.setVisible(true); btnDoc2.setVisible(true);btnDoc3.setVisible(true);btnDoc4.setVisible(true);btnDoc5.setVisible(true);btnDoc6.setVisible(true);btnDoc7.setVisible(true);btnDoc8.setVisible(true);btnDoc9.setVisible(true);btnDoc10.setVisible(true);
        }

        // According to the size of the arraylist show doctor names in seperate buttons.
        if (w1.docDateTime.size() == 1) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0))};
            comboDOcNames.addItem(dates[0]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
        } else if (w1.docDateTime.size() == 2) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0)), String.valueOf(w1.docDateTime.get(1).get(0))};
            comboDOcNames.addItem(dates[0]);
            comboDOcNames.addItem(dates[1]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
            btnDoc2.setText(String.valueOf(w1.docDateTime.get(1).get(0)));
        } else if (w1.docDateTime.size() == 3) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0)), String.valueOf(w1.docDateTime.get(1).get(0)), String.valueOf(w1.docDateTime.get(2).get(0))};
            comboDOcNames.addItem(dates[0]);
            comboDOcNames.addItem(dates[1]);
            comboDOcNames.addItem(dates[2]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
            btnDoc2.setText(String.valueOf(w1.docDateTime.get(1).get(0)));
            btnDoc3.setText(String.valueOf(w1.docDateTime.get(2).get(0)));
        }else if (w1.docDateTime.size() == 4) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0)), String.valueOf(w1.docDateTime.get(1).get(0)), String.valueOf(w1.docDateTime.get(2).get(0)), String.valueOf(w1.docDateTime.get(3).get(0))};
            comboDOcNames.addItem(dates[0]);
            comboDOcNames.addItem(dates[1]);
            comboDOcNames.addItem(dates[2]);
            comboDOcNames.addItem(dates[3]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
            btnDoc2.setText(String.valueOf(w1.docDateTime.get(1).get(0)));
            btnDoc3.setText(String.valueOf(w1.docDateTime.get(2).get(0)));
            btnDoc4.setText(String.valueOf(w1.docDateTime.get(3).get(0)));
        } else if (w1.docDateTime.size() == 5) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0)), String.valueOf(w1.docDateTime.get(1).get(0)), String.valueOf(w1.docDateTime.get(2).get(0)), String.valueOf(w1.docDateTime.get(3).get(0)), String.valueOf(w1.docDateTime.get(4).get(0))};
            comboDOcNames.addItem(dates[0]);
            comboDOcNames.addItem(dates[1]);
            comboDOcNames.addItem(dates[2]);
            comboDOcNames.addItem(dates[3]);
            comboDOcNames.addItem(dates[4]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
            btnDoc2.setText(String.valueOf(w1.docDateTime.get(1).get(0)));
            btnDoc3.setText(String.valueOf(w1.docDateTime.get(2).get(0)));
            btnDoc4.setText(String.valueOf(w1.docDateTime.get(3).get(0)));
            btnDoc5.setText(String.valueOf(w1.docDateTime.get(4).get(0)));
        }else if (w1.docDateTime.size() == 6) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0)), String.valueOf(w1.docDateTime.get(1).get(0)), String.valueOf(w1.docDateTime.get(2).get(0)), String.valueOf(w1.docDateTime.get(3).get(0)), String.valueOf(w1.docDateTime.get(4).get(0)),String.valueOf(w1.docDateTime.get(5).get(0))};
            comboDOcNames.addItem(dates[0]);
            comboDOcNames.addItem(dates[1]);
            comboDOcNames.addItem(dates[2]);
            comboDOcNames.addItem(dates[3]);
            comboDOcNames.addItem(dates[4]);
            comboDOcNames.addItem(dates[5]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
            btnDoc2.setText(String.valueOf(w1.docDateTime.get(1).get(0)));
            btnDoc3.setText(String.valueOf(w1.docDateTime.get(2).get(0)));
            btnDoc4.setText(String.valueOf(w1.docDateTime.get(3).get(0)));
            btnDoc5.setText(String.valueOf(w1.docDateTime.get(4).get(0)));
            btnDoc6.setText(String.valueOf(w1.docDateTime.get(5).get(0)));
        }else if (w1.docDateTime.size() == 7) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0)), String.valueOf(w1.docDateTime.get(1).get(0)), String.valueOf(w1.docDateTime.get(2).get(0)), String.valueOf(w1.docDateTime.get(3).get(0)), String.valueOf(w1.docDateTime.get(4).get(0)),String.valueOf(w1.docDateTime.get(5).get(0)),String.valueOf(w1.docDateTime.get(6).get(0))};
            comboDOcNames.addItem(dates[0]);
            comboDOcNames.addItem(dates[1]);
            comboDOcNames.addItem(dates[2]);
            comboDOcNames.addItem(dates[3]);
            comboDOcNames.addItem(dates[4]);
            comboDOcNames.addItem(dates[5]);
            comboDOcNames.addItem(dates[6]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
            btnDoc2.setText(String.valueOf(w1.docDateTime.get(1).get(0)));
            btnDoc3.setText(String.valueOf(w1.docDateTime.get(2).get(0)));
            btnDoc4.setText(String.valueOf(w1.docDateTime.get(3).get(0)));
            btnDoc5.setText(String.valueOf(w1.docDateTime.get(4).get(0)));
            btnDoc6.setText(String.valueOf(w1.docDateTime.get(5).get(0)));
            btnDoc7.setText(String.valueOf(w1.docDateTime.get(6).get(0)));
        }else if (w1.docDateTime.size() == 8) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0)), String.valueOf(w1.docDateTime.get(1).get(0)), String.valueOf(w1.docDateTime.get(2).get(0)), String.valueOf(w1.docDateTime.get(3).get(0)), String.valueOf(w1.docDateTime.get(4).get(0)),String.valueOf(w1.docDateTime.get(5).get(0)),String.valueOf(w1.docDateTime.get(6).get(0)),String.valueOf(w1.docDateTime.get(7).get(0))};
            comboDOcNames.addItem(dates[0]);
            comboDOcNames.addItem(dates[1]);
            comboDOcNames.addItem(dates[2]);
            comboDOcNames.addItem(dates[3]);
            comboDOcNames.addItem(dates[4]);
            comboDOcNames.addItem(dates[5]);
            comboDOcNames.addItem(dates[6]);
            comboDOcNames.addItem(dates[7]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
            btnDoc2.setText(String.valueOf(w1.docDateTime.get(1).get(0)));
            btnDoc3.setText(String.valueOf(w1.docDateTime.get(2).get(0)));
            btnDoc4.setText(String.valueOf(w1.docDateTime.get(3).get(0)));
            btnDoc5.setText(String.valueOf(w1.docDateTime.get(4).get(0)));
            btnDoc6.setText(String.valueOf(w1.docDateTime.get(5).get(0)));
            btnDoc7.setText(String.valueOf(w1.docDateTime.get(6).get(0)));
            btnDoc8.setText(String.valueOf(w1.docDateTime.get(7).get(0)));
        }else if (w1.docDateTime.size() == 9) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0)), String.valueOf(w1.docDateTime.get(1).get(0)), String.valueOf(w1.docDateTime.get(2).get(0)), String.valueOf(w1.docDateTime.get(3).get(0)), String.valueOf(w1.docDateTime.get(4).get(0)),String.valueOf(w1.docDateTime.get(5).get(0)),String.valueOf(w1.docDateTime.get(6).get(0)),String.valueOf(w1.docDateTime.get(7).get(0)),String.valueOf(w1.docDateTime.get(8).get(0))};
            comboDOcNames.addItem(dates[0]);
            comboDOcNames.addItem(dates[1]);
            comboDOcNames.addItem(dates[2]);
            comboDOcNames.addItem(dates[3]);
            comboDOcNames.addItem(dates[4]);
            comboDOcNames.addItem(dates[5]);
            comboDOcNames.addItem(dates[6]);
            comboDOcNames.addItem(dates[7]);
            comboDOcNames.addItem(dates[8]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
            btnDoc2.setText(String.valueOf(w1.docDateTime.get(1).get(0)));
            btnDoc3.setText(String.valueOf(w1.docDateTime.get(2).get(0)));
            btnDoc4.setText(String.valueOf(w1.docDateTime.get(3).get(0)));
            btnDoc5.setText(String.valueOf(w1.docDateTime.get(4).get(0)));
            btnDoc6.setText(String.valueOf(w1.docDateTime.get(5).get(0)));
            btnDoc7.setText(String.valueOf(w1.docDateTime.get(6).get(0)));
            btnDoc8.setText(String.valueOf(w1.docDateTime.get(7).get(0)));
            btnDoc9.setText(String.valueOf(w1.docDateTime.get(8).get(0)));
        }else if (w1.docDateTime.size() == 10) {
            String[] dates = {String.valueOf(w1.docDateTime.get(0).get(0)), String.valueOf(w1.docDateTime.get(1).get(0)), String.valueOf(w1.docDateTime.get(2).get(0)), String.valueOf(w1.docDateTime.get(3).get(0)), String.valueOf(w1.docDateTime.get(4).get(0)),String.valueOf(w1.docDateTime.get(5).get(0)),String.valueOf(w1.docDateTime.get(6).get(0)),String.valueOf(w1.docDateTime.get(7).get(0)),String.valueOf(w1.docDateTime.get(8).get(0)),String.valueOf(w1.docDateTime.get(9).get(0))};
            comboDOcNames.addItem(dates[0]);
            comboDOcNames.addItem(dates[1]);
            comboDOcNames.addItem(dates[2]);
            comboDOcNames.addItem(dates[3]);
            comboDOcNames.addItem(dates[4]);
            comboDOcNames.addItem(dates[5]);
            comboDOcNames.addItem(dates[6]);
            comboDOcNames.addItem(dates[7]);
            comboDOcNames.addItem(dates[8]);
            comboDOcNames.addItem(dates[9]);
            btnDoc1.setText(String.valueOf(w1.docDateTime.get(0).get(0)));
            btnDoc2.setText(String.valueOf(w1.docDateTime.get(1).get(0)));
            btnDoc3.setText(String.valueOf(w1.docDateTime.get(2).get(0)));
            btnDoc4.setText(String.valueOf(w1.docDateTime.get(3).get(0)));
            btnDoc5.setText(String.valueOf(w1.docDateTime.get(4).get(0)));
            btnDoc6.setText(String.valueOf(w1.docDateTime.get(5).get(0)));
            btnDoc7.setText(String.valueOf(w1.docDateTime.get(6).get(0)));
            btnDoc8.setText(String.valueOf(w1.docDateTime.get(7).get(0)));
            btnDoc9.setText(String.valueOf(w1.docDateTime.get(8).get(0)));
            btnDoc10.setText(String.valueOf(w1.docDateTime.get(9).get(0)));
        }

        // Show details(date and time) of the separate doctors when a separate button is clicked. 
        btnDoc1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(0).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(0).get(x)) + "\n");
                }
            }
        });

        btnDoc2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(1).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(1).get(x)) + "\n");
                }
            }
        });

        btnDoc3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(2).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(2).get(x)) + "\n");
                }
            }
        });

        btnDoc4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(3).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(3).get(x)) + "\n");
                }
            }
        });

        btnDoc5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(4).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(4).get(x)) + "\n");
                }

            }
        });

        btnDoc6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(5).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(5).get(x)) + "\n");
                }
            }
        });

        btnDoc7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(6).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(6).get(x)) + "\n");
                }
            }
        });

        btnDoc8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(7).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(7).get(x)) + "\n");
                }
            }
        });

        btnDoc9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(8).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(8).get(x)) + "\n");
                }
            }
        });

        btnDoc10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaDocDetails.setText("");
                for (int x = 0; x < w1.docDateTime.get(9).size(); x++) {
                    txtAreaDocDetails.append(String.valueOf(w1.docDateTime.get(9).get(x)) + "\n");
                }
            }
        });

        // Button for edit details.
        btnEdit=new JButton("   Edit Details   ");

        // Panel for show editing details.
        JPanel panelEdit=new JPanel();

        // Panel for show removing details.
        JPanel panelRemove=new JPanel();

        // Tabbed pane.
        JTabbedPane tp=new JTabbedPane();

        tp.setBounds(480,600,200,160);

        // JTabbedPane headings.
        tp.add("Edit",panelEdit);
        tp.add("Remove",panelRemove);

        // Selections of the combo box for editing values.
        selection = new JComboBox(selections);
        selection.setBounds(500, 640, 185, 30);

        // Text field for put patient ids.
        txtEditId = new JTextField("Patient Id");
        txtEditId.setBounds(500, 680, 185, 30);

        // Text field for put edit value.
        txtEdits = new JTextField("Edit value");
        txtEdits.setBounds(450, 710, 155, 30);

        // Text field for replace value.
        txtReplaces = new JTextField("Replace value");
        txtReplaces.setBounds(650, 710, 155, 30);

        // Label for show edited information.
        JLabel lblEditInfo= new JLabel();
        lblEditInfo.setBounds(550, 740, 355, 20);

        // Text field to remove patient from the system.
        JTextField txtRemove = new JTextField("Enter patient unique id to remove");
        txtRemove.setBounds(900, 640, 195, 30);

        // Text Area show remove details of the patient.
        JTextArea txtAreaRemDetails=new JTextArea("Removed details");

        // Button for remove patient.
        btnRemove = new JButton("Remove Details");
        btnRemove.setBounds(900, 600, 195, 30);

        // Add details to the panel.
        panelEdit.add(selection);
        panelEdit.add(txtEditId);
        panelEdit.add(txtEdits);
        panelEdit.add(txtReplaces);
        panelEdit.add(btnEdit);
        panelEdit.add(lblEditInfo);
        panelRemove.add(txtRemove);
        panelRemove.add(txtAreaRemDetails);
        panelRemove.add(btnRemove);
        this.add(tp);
        this.setLayout(null);
        this.setVisible(true);


        // Button for edit information of the patient.
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Edit patient details according to the combo box selection and existing value and the replacing value.
                if (selection.getSelectedItem() == "Patient Name") {
                    for (int i = 0; i < w1.consultationArrayList.size(); i++) {
                        if (w1.consultationArrayList.get(i).getPatientId().equals(txtEditId.getText()) && w1.consultationArrayList.get(i).getPersonName().equals(txtEdits.getText())) {
                            lblEditInfo.setText("Your name "+txtEdits.getText()+" replace to value of "+txtReplaces.getText());
                            w1.consultationArrayList.get(i).setPersonName(txtReplaces.getText());
                        }
                    }
                } else if (selection.getSelectedItem() == "Patient Surname") {
                    for (int i = 0; i < w1.consultationArrayList.size(); i++) {
                        if (w1.consultationArrayList.get(i).getPatientId().equals(txtEditId.getText()) && w1.consultationArrayList.get(i).getPersonSurname().equals(txtEdits.getText())) {
                            lblEditInfo.setText("Your surname "+txtEdits.getText()+" replace to value of "+txtReplaces.getText());
                            w1.consultationArrayList.get(i).setPersonSurname(txtReplaces.getText());
                        }
                    }
                } else if (selection.getSelectedItem() == "Patient mobile no") {
                    for (int i = 0; i < w1.consultationArrayList.size(); i++) {
                        if (w1.consultationArrayList.get(i).getPatientId().equals(txtEditId.getText()) && w1.consultationArrayList.get(i).getPersonMobileNo().equals(txtEdits.getText())) {
                            lblEditInfo.setText("Your mobile number "+txtEdits.getText()+" replace to value of "+txtReplaces.getText());
                            w1.consultationArrayList.get(i).setPersonMobileNo(txtReplaces.getText());
                        }
                    }
                } else if (selection.getSelectedItem() == "Patient date of birth") {
                    for (int i = 0; i < w1.consultationArrayList.size(); i++) {
                        if (w1.consultationArrayList.get(i).getPatientId().equals(txtEditId.getText()) && w1.consultationArrayList.get(i).getPersonBirth().equals(txtEdits.getText())) {
                            lblEditInfo.setText("Your date of birth "+txtEdits.getText()+" replace to value of "+txtReplaces.getText());
                            w1.consultationArrayList.get(i).setPersonBirth(txtReplaces.getText());
                        }
                    }
                }
                // Save change details of the patient.
                w1.savePatientInfo();

                // Console output.
                for (int i = 0; i < w1.consultationArrayList.size(); i++) {
                    System.out.println("\n Patient surname   :- " + w1.consultationArrayList.get(i).getPersonSurname() + "\n" +
                            " Patient name      :- " + w1.consultationArrayList.get(i).getPersonName() + "\n" +
                            " Patient Birth     :- " + w1.consultationArrayList.get(i).getPersonBirth() + "\n" +
                            " Patient mobile no :- " + w1.consultationArrayList.get(i).getPersonMobileNo() + "\n" +
                            " Patient id        :- " + w1.consultationArrayList.get(i).getPatientId());
                    System.out.println("\n-------------------------");
                }
            }
        });

        // Button for remove patient from the system.
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRemove.setText("Remove it!!!");

                boolean check = true;
                for (int x = 0; x < w1.consultationArrayList.size(); x++) {
                    if (w1.consultationArrayList.get(x).getPatientId().equalsIgnoreCase(txtRemove.getText())) {
                        // Display removed data.
                        txtAreaRemDetails.append(
                                "\n Patient name                         :- " + w1.consultationArrayList.get(x).getPersonName() +
                                "\n Patient surname                    :- " + w1.consultationArrayList.get(x).getPersonSurname() +
                                "\n Patient's birth                    :- " + w1.consultationArrayList.get(x).getPersonBirth() +
                                "\n Patient mobile number     :- " + w1.consultationArrayList.get(x).getPersonMobileNo() +
                                "\n Patient id                               :- " + w1.consultationArrayList.get(x).getPatientId());

                        // Removing
                        w1.consultationArrayList.remove(x);
                        check = false;
                        break;
                    }
                }
                // Save data to the file.
                w1.savePatientInfo();
            }
        });

        // The things that should do when click the add consultation button.
        btnAddData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                // Validation.
                if (personSurname.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Your surname is not fill !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (personName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Your name is not fill !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (personBirth.getText().equals("    -  -  ")) {
                    JOptionPane.showMessageDialog(null, "Your date of birth is not fill !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (personMobileNo.getText().equals("   -       ")) {
                    JOptionPane.showMessageDialog(null, "Your mobile number is not fill !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (patientId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Your patient id is not fill !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtPatientTime.getText().equals("  /  /    ..  .  ")) {
                    JOptionPane.showMessageDialog(null, "Your consultation fill !!!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Calculating the price of the consultation.
                String price="";

                // Get the payment of the patient and display it in frame.
                int n = w1.consultationArrayList.size();
                int count = 0;
                for (int i = 0; i < n; i++)
                    if (patientId.getText().equals(w1.consultationArrayList.get(i).getPatientId()))
                        count++;
                System.out.println(count);
                if (count >= 1) {
                    lblPrice.setText("Your consultation payment amount £25");
                    price="£25";
                    lblPrice.setVisible(true);
                } else {
                    lblPrice.setText("Your consultation payment amount £15");
                    price="£15";
                    lblPrice.setVisible(true);
                }


                // Get doctor to the cosultation.
                String docName = "";

                // Make a list to get available doctors on the separate date and time to alist.
                List<String> listAvaDocs = new ArrayList<String>();

                // Get user inputted doctor name.
                String userDocName = String.valueOf(comboDOcNames.getSelectedItem());

                int value = 0;

                // Name of the loop.
                nameofloop:
                for (int i = 0; i < w1.docDateTime.size(); i++) {
                    for (int j = 0; j < w1.docDateTime.get(i).size(); j++) {

                        // If doctor is available book it.
                        if (w1.docDateTime.get(i).toString().contains(userDocName) && w1.docDateTime.get(i).get(j).toString().contains(txtPatientTime.getText()) && !w1.docDateTime.get(i).get(j).toString().contains("Booked")) {
                            w1.docDateTime.get(i).set(j, w1.docDateTime.get(i).get(j) + " Booked");
                            docName = userDocName;
                            lblConsul.setText("Your consultation is on " + txtPatientTime.getText() + " with " + docName);
                            value = 0;
                            break nameofloop;
                        } else if ((w1.docDateTime.get(i).get(j).toString().contains(txtPatientTime.getText()) && w1.docDateTime.get(i).get(j).toString().contains("Booked"))) {
                            System.out.println("No space");
                            value = 2;

                            // Get the available doctor names in that time to a list.
                        } else if (w1.docDateTime.get(i).get(j).toString().contains(txtPatientTime.getText()) && !w1.docDateTime.get(i).get(j).toString().contains("Booked")) {
                            listAvaDocs.add(String.valueOf(w1.docDateTime.get(i).get(0)));
                        }
                    }
                }

                if(listAvaDocs.size()==0){
                    System.out.println("Filled");
                }

                else if (value != 0) {
                    // Get a random doctor name.
                    Random r = new Random();
                    int randomNumber = r.nextInt(listAvaDocs.size());
                    String randDoc = listAvaDocs.get(randomNumber);

                    // Assign random doctor to the patient.
                    for (int m = 0; m < w1.docDateTime.size(); m++) {
                        for (int a = 0; a < w1.docDateTime.get(m).size(); a++) {
                            if (w1.docDateTime.get(m).get(a).equals(randDoc)) {
                                String dd = txtPatientTime.getText();
                                int j = w1.docDateTime.get(m).indexOf(dd);

                                if (j == -1) {
                                    System.out.println("LEAVE");
                                } else {
                                    w1.docDateTime.get(m).set(j, w1.docDateTime.get(m).get(j) + " Booked");
                                    w1.savePatientInfo();
                                    docName = randDoc;
                                    lblConsul.setText("Your consultation is on " + txtPatientTime.getText() + " with " + docName);
                                }
                            }
                        }
                    }
                }

                // Passing values to consultaion array list.
                w1.consultationArrayList.add(new Consultation(personName.getText(),personSurname.getText(),personMobileNo.getText(),personBirth.getText(),patientId.getText(),txtPatientTime.getText(),docName,price));

                // Console output.
                for (int i = 0; i < w1.consultationArrayList.size(); i++) {
                    System.out.println("\n Patient surname   :- " + w1.consultationArrayList.get(i).getPersonSurname()+"\n"+
                            " Patient name      :- " + w1.consultationArrayList.get(i).getPersonName() +"\n"+
                            " Patient Birth     :- " + w1.consultationArrayList.get(i).getPersonBirth() +"\n"+
                            " Patient mobile no :- " + w1.consultationArrayList.get(i).getPersonMobileNo() +"\n"+
                            " Patient id        :- " + w1.consultationArrayList.get(i).getPatientId()+"\n"+
                            " Patient consul time:- " + w1.consultationArrayList.get(i).gettxtPatientTime() +"\n"+
                            " Patient doctor name:- " + w1.consultationArrayList.get(i).getDocName() +"\n"+
                            " Patient price      :- " + w1.consultationArrayList.get(i).getPrice());
                    System.out.println("\n-------------------------");
                }


                // Add some note with encryption.
                final String secretKey = "secrete";

               // Get the r=text which is n=in text area.
                String originalString= txtAreaPatient.getText();

                // Encrypt and decrypt methods.
                AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
                String encryptedString = aesEncryptionDecryption.encrypt(originalString, secretKey);
                String decryptedString = aesEncryptionDecryption.decrypt(encryptedString, secretKey);

                // Write encrypted message to the file.
                FileWriter fw = null;
                BufferedWriter bw = null;
                PrintWriter pw = null;
                try {
                    fw = new FileWriter("textMessage.txt", true);
                    bw = new BufferedWriter(fw);
                    pw = new PrintWriter(bw);
                    System.out.println("Data Successfully appended into file");
                    pw.flush();
                } catch (IOException r) {
                    throw new RuntimeException(r);
                } finally {
                    try {
                        pw.close();
                        bw.close();
                        fw.close();
                    } catch (IOException io) {
                    }
                }
                // Append text to the file.
                try (FileWriter f = new FileWriter("textMessage.txt", true);
                     BufferedWriter b = new BufferedWriter(f);
                     PrintWriter p = new PrintWriter(b);) {
                    p.println(patientId.getText());
                    p.println(encryptedString);
                    p.println(secretKey+"\n");

                } catch (IOException i) {
                    i.printStackTrace();
                }

                // Save consultation details.
                w1.savePatientInfo();

                // Save doctor availability details.
                w1.saveData();
            }
        });


        // Button for add image with encryption.
        btnAddtxtImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("button clicked");
                String text="55555";
                int temp=Integer.parseInt(text);
                operate(temp);

            }
        });

        //Setting the elements.
        this.setSize(1200,820);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(200,0);
        this.add(btnBack);
        this.setTitle(" Add a consultation");
        this.getContentPane().setBackground(new Color(71, 148, 211));
        this.add(lblMainTopic);
        this.add(lblSubTopic);
        this.add(lblInfo);
        this.add(lblInfo2);
        this.add(lblSurname);
        this.add(personSurname);
        this.add(personName);
        this.add(lblName);
        this.add(lblDOB);
        this.add(personBirth);
        this.add(lblPhoneNo);
        this.add(personMobileNo);
        this.add(btnAddData);
        this.add(uniqueId);
        this.add(lbluniqueId);
        this.add(lblPatientId);
        this.add(patientId);
        this.add(lblPrice);
        this.add(btnDoc1);
        this.add(btnDoc2);
        this.add(btnDoc3);
        this.add(btnDoc4);
        this.add(btnDoc5);
        this.add(btnDoc6);
        this.add(btnDoc7);
        this.add(btnDoc8);
        this.add(btnDoc9);
        this.add(btnDoc10);
        this.add(txtAreaDocDetails);
        this.add(lblSeeDoctors);
        this.add(txtPatientTime);
        this.add(comboDOcNames);
        this.add(txtAreaPatient);
        this.add(btnAddtxtImage);
        this.add(lblline);
        this.add(lblConsul);
        this.add(lblImg);
        this.add(lblFormat);
        this.add(lblDOB1);
    }

    // Calling main GUI frame.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnBack) {
            this.dispose();
            MainGuI mainGuI = new MainGuI();
        }
    }

    // Append to file methods.
    private static String appendToFile(Path path, String content)
            throws IOException {
        Files.write(path, content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
        return content;
    }
    // Formatted field.
    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    // Encrypting data.
    public static void operate(int key)
    {

        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file=fileChooser.getSelectedFile();
        try
        {
            FileInputStream fis=new FileInputStream(file);

            byte []data=new byte[fis.available()];
            fis.read(data);
            int i=0;
            for(byte b:data)
            {
                System.out.println(b);
                data[i]=(byte)(b^key);
                i++;
            }
            FileOutputStream fos=new FileOutputStream(file);
            System.out.println(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void prepareSecreteKey(String myKey) {
        MessageDigest sec = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sec = MessageDigest.getInstance("SHA-1");
            key = sec.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
