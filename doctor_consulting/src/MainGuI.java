import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class MainGuI extends JFrame implements ActionListener {

    // Create buttons.
    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;

    public MainGuI() {

        // Add image icon to the file.
        ImageIcon img = new ImageIcon("doctor.jpeg");
        setIconImage(img.getImage());

        // Add image to the frame.
        ImageIcon image = new ImageIcon("mainpg.jpeg");
        JLabel label1 = new JLabel();
        label1.setBounds(80, 20, 695, 430);
        label1.setIcon(image);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.TOP);


        // Add image to the frame.
        ImageIcon image1 = new ImageIcon("mainpg1.jpeg");
        JLabel label2 = new JLabel();
        label2.setBounds(180, 320, 695, 430);
        label2.setIcon(image1);
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label2.setVerticalTextPosition(JLabel.TOP);


        // Add topic to the page.
        JLabel lblTopic = new JLabel();
        lblTopic.setText("Skin Consultation Menu");
        lblTopic.setBounds(550, 100, 500, 100);
        lblTopic.setFont(new Font("SansSerif", Font.BOLD, 38));
        lblTopic.setForeground(new Color(255, 172, 0));

        // Add button to display all doctor details.
        button1 = new JButton();
        button1.setBounds(505, 250, 550, 60);
        button1.setText("View All Doctor Details");
        button1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        button1.setForeground(new Color(255, 172, 0));
        button1.setBackground(new Color(0, 0, 0));
        button1.setBorder(BorderFactory.createLineBorder(new Color(164, 117, 0)));
        button1.setFocusable(false);
        button1.addActionListener(this);

        // Add button to display all doctor details in sorted form.
        button2 = new JButton();
        button2.setBounds(505, 350, 550, 60);
        button2.setText("View All Doctor Sorted in Alphabetical Order");
        button2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        button2.setForeground(new Color(255, 172, 0));
        button2.setBackground(new Color(0, 0, 0));
        button2.setBorder(BorderFactory.createLineBorder(new Color(164, 117, 0)));
        button2.setFocusable(false);
        button2.addActionListener(this);

        // Add button to save patient to the system.
        button3 = new JButton();
        button3.setBounds(505, 450, 550, 60);
        button3.setText("Add Patient");
        button3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        button3.setForeground(new Color(255, 172, 0));
        button3.setBackground(new Color(0, 0, 0));
        button3.setBorder(BorderFactory.createLineBorder(new Color(164, 117, 0)));
        button3.setFocusable(false);
        button3.addActionListener(this);

        // Add button to search patient details.
        button4 = new JButton();
        button4.setBounds(505, 550, 550, 60);
        button4.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        button4.setText("Search a Patient");
        button4.setForeground(new Color(255, 172, 0));
        button4.setBackground(new Color(0, 0, 0));
        button4.setBorder(BorderFactory.createLineBorder(new Color(164, 117, 0)));
        button4.setFocusable(false);
        button4.addActionListener(this);

        // Add elements to the Consultation page.
        this.setSize(1200, 820);
        this.setTitle("Skin Consultation Manager");
        this.getContentPane().setBackground(new Color(41, 44, 41));
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation(200, 0);
        this.add(label1);
        this.add(label2);
        this.add(lblTopic);
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
    }

    // When the button clicked call the separate page.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            this.dispose();
            Frame1 frame1 = new Frame1();
        } else if (e.getSource() == button2) {
            this.dispose();
            Frame2 frame2 = new Frame2();
        } else if (e.getSource() == button3) {
            this.dispose();
            try {
                Frame3 frame3 = new Frame3();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == button4) {
            this.dispose();

            Frame4 frame4 = new Frame4();
        }
    }
}