import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends Frame {

    public WelcomePage(User user) {

        super("Welcome");

        setLayout(new GridLayout(2, 1, 10, 10));

        Label welcomeLabel = new Label(
                "Hello, " + user.getUsername(),
                Label.CENTER
        );

        Label roleLabel = new Label(
                "Your role is: " + user.getRole(),
                Label.CENTER
        );

        welcomeLabel.setFont(
                new Font("SansSerif", Font.BOLD, 20)
        );

        roleLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 16)
        );

        add(welcomeLabel);
        add(roleLabel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(350, 180);
        setLocationRelativeTo(null);
    }
}
