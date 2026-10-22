import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginHandler implements ActionListener {

    private final LoginFormTask loginFormTask;

    public LoginHandler(LoginFormTask loginFormTask) {
        this.loginFormTask = loginFormTask;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loginFormTask.handleLogin();
    }
}
