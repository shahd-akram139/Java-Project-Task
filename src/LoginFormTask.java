import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoginFormTask extends Frame {

    private ArrayList<User> users = new ArrayList<>();

    private int failedAttempts = 0;

    private TextField username;
    private TextField password;
    private Choice role;
    private Button login;

    // Constructor
    public LoginFormTask() {

        super("Login");

        loadUsers();

        setLayout(new GridBagLayout());

        Panel content = new Panel(
                new BorderLayout(15, 20)
        );

        content.setPreferredSize(
                new Dimension(360, 230)
        );

        // Title
        Label title = new Label(
                "Welcome back",
                Label.CENTER
        );

        title.setFont(
                new Font("SansSerif", Font.BOLD, 22)
        );

        content.add(title, BorderLayout.NORTH);

        // Form
        Panel form = new Panel(
                new GridLayout(3, 2, 10, 8)
        );

        form.add(
                new Label("Username:", Label.RIGHT)
        );

        username = new TextField(18);
        form.add(username);

        form.add(
                new Label("Password:", Label.RIGHT)
        );

        password = new TextField(18);
        password.setEchoChar('*');
        form.add(password);

        form.add(
                new Label("Role:", Label.RIGHT)
        );

        role = new Choice();
        role.add("Student");
        role.add("TA");
        role.add("Professor");

        form.add(role);

        content.add(form, BorderLayout.CENTER);

        // Buttons
        Panel buttons = new Panel(
                new FlowLayout(FlowLayout.CENTER, 15, 0)
        );

        Button cancel = new Button("Cancel");

        login = new Button("Login");

        buttons.add(cancel);
        buttons.add(login);

        content.add(buttons, BorderLayout.SOUTH);

        // Center the content
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(content, gbc);

        // Login Button
        login.addActionListener(new LoginHandler(this));

        // Cancel Button
        cancel.addActionListener(e -> dispose());

        // Close Window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Window Settings
        setSize(440, 300);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // Load Hardcoded Users
    private void loadUsers() {

        users.add(
                new User("shahd", "1234", "Student")
        );

        users.add(
                new User("ahmed", "5678", "TA")
        );

        users.add(
                new User("sara", "9876", "Professor")
        );
    }

    // Find User
    private User findUser(String name) {

        for (User user : users) {

            if (user.getUsername().equals(name)) {
                return user;
            }
        }

        return null;
    }

    // Handle Login
    public void handleLogin() {

        String enteredUsername = username.getText().trim();

        String enteredPassword = password.getText();

        String selectedRole = role.getSelectedItem();

        // Check Empty Fields
        if (enteredUsername.isEmpty()
                || enteredPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Find User
        User user = findUser(enteredUsername);

        // Check Username
        if (user == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Username does not exist.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Check Password
        if (!user.getPassword().equals(enteredPassword)) {

            failedAttempts++;

            int remainingAttempts = 3 - failedAttempts;

            if (remainingAttempts > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Incorrect password. Remaining attempts: "
                                + remainingAttempts,
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Wrong password 3 times. Login is disabled.",
                        "Account Locked",
                        JOptionPane.ERROR_MESSAGE
                );

                login.setEnabled(false);
            }

            return;
        }

        // Check Role
        if (!user.getRole().equals(selectedRole)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Incorrect role selected.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Successful Login
        JOptionPane.showMessageDialog(
                this,
                "Login successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        WelcomePage welcomePage = new WelcomePage(user);

        welcomePage.setVisible(true);

        dispose();
    }

    // Main Method
    public static void main(String[] args) {

        new LoginFormTask().setVisible(true);
    }
}
