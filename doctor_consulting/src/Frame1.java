import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame1 extends JFrame implements ActionListener {

    // Calling class.
    WestminsterSkinConsultationManager w1 = new WestminsterSkinConsultationManager();

    // Make a button.
    JButton btnBack;


    public Frame1() {

        // Load stored data.
        w1.loadData();
        
        // Get number of columns and create a table.
        String[] column = {"Name","Surname","MobileNo","Birth","MedicalLicense","Specialisation"};                        
        DefaultTableModel driverTable = new DefaultTableModel(column,0);
        JTable dTable = new JTable(driverTable);
        dTable.setBounds(250,140,650,550);

        // Get doctors information.
        for (int i=0; i<w1.getDoctorList().size(); i++) {
            String name = w1.getDoctorList().get(i).getPersonName();
            String surname = w1.getDoctorList().get(i).getPersonSurname();
            String mobileNo = w1.getDoctorList().get(i).getPersonMobileNo();
            String birth = w1.getDoctorList().get(i).getPersonBirth();
            String medicalLicense = w1.getDoctorList().get(i).getMedilicesnse();
            String specialisation = w1.getDoctorList().get(i).getSpecialisation();
            
            // Add information to the table.
            Object [] row = {name,surname,mobileNo,birth,medicalLicense,specialisation};
            driverTable.addRow(row);
        }

        // Add image icon to the file.
        ImageIcon img = new ImageIcon("doctor.jpeg");
        setIconImage(img.getImage());

        // Names of the columns of the table.
        JLabel lblColumn = new JLabel();
        lblColumn.setText("Name                       | Surname                | Mobile Number     | Date of Birth         | Medical Licence    | Specialisation");
        lblColumn.setBounds(250,120,650,20);
        lblColumn.setForeground(new Color(255, 172, 0));

        // Topic of the page.
        JLabel lblTopic = new JLabel();
        lblTopic.setText("Doctors all details");
        lblTopic.setBounds(400,20,550,30);
        lblTopic.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
        lblTopic.setForeground(new Color(255, 172, 0));

        // Back button of the page.
        btnBack = new JButton();
        btnBack.setText("Back");
        btnBack.setBounds(20,20,75,30);
        btnBack.setForeground(new Color(255, 172, 0));
        btnBack.setBackground(new Color(0, 0, 0));
        btnBack.setBorder(BorderFactory.createLineBorder(new Color(164, 117, 0)));
        btnBack.setFocusable(false);
        btnBack.addActionListener(this);

        // Add elements to the page.
        this.setSize(1200, 820);
        this.setTitle("View All Doctors");
        this.getContentPane().setBackground(new Color(41, 44, 41));
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(200, 0);
        this.add(btnBack);
        this.add(lblTopic);
        this.add(lblColumn);
        this.add(dTable);
    }

    // Go back to the main page.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnBack) {
            this.dispose();
            MainGuI mainGuI = new MainGuI();
        }
    }
}
