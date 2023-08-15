import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Frame4 extends JFrame implements ActionListener {

    // Calling the main method.

    WestminsterSkinConsultationManager w1 = new WestminsterSkinConsultationManager();

    // Setting up buttons,text fields and text area.

    JButton btnBack;
    JTextField textField;
    JButton btnSearch;
    String patID;
    TextArea textAreaInfo;

    JLabel label;
    public Frame4() {

        // Add image icon to the file.
        ImageIcon img = new ImageIcon("doctor.jpeg");
        setIconImage(img.getImage());

        // Main topic of the page.
        JLabel lblMainTopic = new JLabel();
        lblMainTopic.setText("See Patient Details");
        lblMainTopic.setBounds(400,20,550,30);
        lblMainTopic.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
        lblMainTopic.setForeground(new Color(255, 172, 0));

        // Text area for show searched information.
        textAreaInfo = new TextArea(10, 10);
        textAreaInfo.setBounds(350, 200, 500, 500);
        textAreaInfo.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        // Search button of the patient.
        btnSearch = new JButton();
        btnSearch.setBounds(650, 120, 95, 29);
        btnSearch.setText("Search");
        btnSearch.setFocusable(false);
        btnSearch.addActionListener(this);

        // Text field to input infrmation.
        textField = new JTextField();
        textField.setBounds(350, 120, 250, 30);
        textField.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));

        // Label to show what to enter in textfield.
        label = new JLabel("Patient Id");
        label.setBounds(350, 80, 250, 30);
        label.setForeground(new Color(255, 255, 255));

        // Back btnBack of the page.
        btnBack = new JButton();
        btnBack.setText("Back");
        btnBack.setBounds(20,20,75,30);
        btnBack.setForeground(new Color(255, 172, 0));
        btnBack.setBackground(new Color(0, 0, 0));
        btnBack.setBorder(BorderFactory.createLineBorder(new Color(164, 117, 0)));
        btnBack.setFocusable(false);
        btnBack.addActionListener(this);

        // Setting up elements.
        this.setSize(1200, 820);
        this.setTitle("Search Patient Details");
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(200, 0);
        this.getContentPane().setBackground(new Color(41, 44, 41));
        this.add(btnBack);
        this.add(lblMainTopic);
        this.add(btnSearch);
        this.add(textField);
        this.add(textAreaInfo);
        this.add(label);

    }

    // Back button function.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            MainGuI mainGuI = new MainGuI();
        }
        if (e.getSource() == btnSearch) {
            patID = textField.getText();
            displayStats(patID);
        }
    }

    // Display the output.
    public void displayStats(String d) {

        w1.loadPatientInfo();
        textAreaInfo.setText("\n");
        for (Consultation consultation : w1.consultationArrayList) {
            for (int i = 0; i < w1.consultationArrayList.size(); i++) {
                if (w1.consultationArrayList.get(i).getPatientId().equals(d)) {
                    textAreaInfo.append( " Patient id                                   :- " + w1.consultationArrayList.get(i).getPatientId()+"\n"+
                            "Patient surname                      :- " + w1.consultationArrayList.get(i).getPersonSurname()+"\n"+
                            " Patient name                            :- " + w1.consultationArrayList.get(i).getPersonName() +"\n"+
                            " Patient Birth                             :- " + w1.consultationArrayList.get(i).getPersonBirth() +"\n"+
                            " Patient mobile no                    :- " + w1.consultationArrayList.get(i).getPersonMobileNo() +"\n"+
                            " Patient consul time                 :- " + w1.consultationArrayList.get(i).gettxtPatientTime() +"\n"+
                            " Consultation doctor name    :- " + w1.consultationArrayList.get(i).getDocName() +"\n"+
                            " Consultation price                  :- " + w1.consultationArrayList.get(i).getPrice()+"\n\n");
                }
                //
            }
            break;
        }

    }
}
