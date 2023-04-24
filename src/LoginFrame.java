import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LoginFrame extends JFrame implements ActionListener {
    JLabel loginText, passwordText, registerText, loginSuccessful;
    JTextField login, password;
    JButton accept, registerHere;
    JProgressBar redirect;
    int lineIterated = 0;
    LoginFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 300);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Login");

        loginText = new JLabel("Login:");
        loginText.setBounds(100, 20, 200, 20);

        passwordText = new JLabel("Password:");
        passwordText.setBounds(100, 100, 200, 20);

        login = new JTextField();
        login.setBounds(100, 40, 300, 40);
        login.setFont(new Font("Roboto", Font.PLAIN, 25));

        password = new JTextField();
        password.setBounds(100, 120, 300, 40);
        password.setFont(new Font("Roboto", Font.PLAIN, 25));

        accept = new JButton("Sign in");
        accept.setBounds(200, 185, 100, 40);
        accept.setFocusable(false);
        accept.setBackground(new Color(0x62DC3F));
        accept.setBorder(BorderFactory.createLineBorder(new Color(0x45982B), 2));
        accept.setOpaque(true);
        accept.setFont(new Font("Roboto", Font.PLAIN, 18));
        accept.addActionListener(this);

        registerText = new JLabel("Doesn't have an account yet?");
        registerText.setBounds(130, 230, 170, 20);
        registerText.setFont(new Font("Roboto", Font.PLAIN, 12));

        registerHere = new JButton("<html><u>Register here.</u></html>");
        registerHere.setBounds(275, 230, 100, 20);
        registerHere.setContentAreaFilled(false);
        registerHere.setOpaque(false);
        registerHere.setFocusable(false);
        registerHere.setForeground(new Color(0x23B4BE));
        registerHere.setBorder(BorderFactory.createEmptyBorder());
        registerHere.setFont(new Font("Roboto", Font.ITALIC, 12));
        registerHere.addActionListener(this);

        redirect = new JProgressBar(0, 100);
        redirect.setBounds(100, 100, 300, 40);
        redirect.setStringPainted(true);
        redirect.setBackground(Color.black);
        redirect.setForeground(Color.green);
        redirect.setOpaque(true);
        redirect.setVisible(false);

        loginSuccessful = new JLabel();
        loginSuccessful.setBounds(0, 0, 500, 300);
        loginSuccessful.setOpaque(true);
        loginSuccessful.setVisible(false);


        this.add(redirect);
        this.add(loginSuccessful);
        this.add(registerText);
        this.add(registerHere);
        this.add(loginText);
        this.add(passwordText);
        this.add(login);
        this.add(password);
        this.add(accept);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == accept) {
            if(checkLogin(login.getText())) {
                if(checkPassword(password.getText())) {
                    this.dispose();
                    new FinalFrame(login.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Password is incorrect.",
                            "Password error", JOptionPane.ERROR_MESSAGE);
                    password.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username does not exist.",
                        "Username error", JOptionPane.ERROR_MESSAGE);
                login.setText("");
                password.setText("");
            }
        }
        if(e.getSource() == registerHere) {
            this.dispose();
            try {
                new RegisterFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public boolean checkLogin(String login) {
        String filename = "Credentials.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                lineIterated++;
                String[] words = line.split(":");
                if(Objects.equals(words[1], login)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkPassword(String password) {
        String filename = "Credentials.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int currentLine = 1;
            while((line = br.readLine()) != null) {
                if (currentLine == lineIterated) {
                    String[] words = line.split(":");
                    if(Objects.equals(words[2], password)) {
                        return true;
                    }
                    break;
                }
                currentLine++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
