import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Frame2 extends JFrame implements ActionListener {
    // Calling main class.
    WestminsterSkinConsultationManager w1 = new WestminsterSkinConsultationManager();

    // Button for go back.
    JButton btnBack;

    // Constructor.
    public Frame2() {

        // Load data from the file.
        w1.loadData();

        // Add image icon to the file.
        ImageIcon img = new ImageIcon("doctor.jpeg");
        setIconImage(img.getImage());

        // Topic of the page.
        JLabel lblTopic = new JLabel();
        lblTopic.setText("Sort doctors in alphabetical order");
        lblTopic.setBounds(300,20,650,30);
        lblTopic.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
        lblTopic.setForeground(new Color(255, 172, 0));

        // Create text area for display data.
        TextArea txtArea=new TextArea();
        txtArea.setBounds(350,100,475,630);
        txtArea.setFont(new Font(Font.SANS_SERIF,Font.BOLD,10));

        // Create string array called names.
        String names[] = new String[w1.doctorList.size()];

        // Get details to the array.
        for (int x = 0; x < w1.doctorList.size(); x++) {
            names[x] ="\n Doctor surname                          :- "+ w1.doctorList.get(x).getPersonSurname() +
                      "\n Doctor name                                :- "+ w1.doctorList.get(x).getPersonName()    +
                      "\n Doctor mobile number                 :- "+ w1.doctorList.get(x).getPersonMobileNo()+
                      "\n Doctor's birth                                :- " + w1.doctorList.get(x).getPersonBirth() +
                      "\n Doctor medical license number   :- " + w1.doctorList.get(x).getMedilicesnse() +
                      "\n Doctor's specialisation                 :- " + w1.doctorList.get(x).getSpecialisation();
        }

        // Inbuilt sort function.
        Arrays.sort(names );

        // Append to the text area.
        for (int i = 0; i < w1.doctorList.size(); i++) {
            txtArea.append(names[i]+"\n\n");

        }

        // Back btnBack of the page.
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
        this.setTitle("View Doctors in Alphabetical order");
        this.getContentPane().setBackground(new Color(41, 44, 41));
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(200, 0);
        this.add(btnBack);
        this.add(lblTopic);
        this.add(txtArea);
    }

    // Go to the main gui page.

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnBack) {
            this.dispose();
            MainGuI mainGuI = new MainGuI();
        }
    }
}
