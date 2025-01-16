import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JLabel username;
    private JLabel password;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton Enter;


    public LoginScreen(String type){
        setTitle("Log-in");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        username = new JLabel("username");
        add(username);

        usernameField = new JTextField();
        add(usernameField);


        password = new JLabel("password");
        add(password);

        passwordField = new JPasswordField();
        add(passwordField);


        Enter = new JButton("Enter");
        add(Enter);

        Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (type.equals("buyer")) {
                    MusicScreen musicScreen = new MusicScreen();
                    musicScreen.setVisible(true);
                } else if (type.equals("staff")) {
                    PromoteScreen promote = new PromoteScreen();
                    promote.setVisible(true);
                }
            }
        });
    }
}
