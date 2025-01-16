import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenScreen extends JFrame {
    private JButton Buyer;
    private JButton Staff;

    public OpenScreen(){
        setTitle("Choose Log-in Type:");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        Buyer = new JButton("Tune Buyer");
        add(Buyer);
        Staff = new JButton("Comp. Staff");
        add(Staff);

        Buyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen login = new LoginScreen("buyer");
                login.setVisible(true);
            }
        });

        Staff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen login = new LoginScreen("staff");
                login.setVisible(true);
                dispose();
            }
        });
    }


}
