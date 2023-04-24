import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class RegisterFrame extends JFrame implements ActionListener {
    JLabel emailText, usernameText, password1Text, password2Text, loginText, registerSuccessful;
    JTextField email, username, password1, password2;
    JButton register, loginHere;
    FileWriter writer = new FileWriter("Credentials.txt", true);
    RegisterFrame() throws IOException {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 450);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Register");

        emailText = new JLabel("Email:");
        emailText.setBounds(95, 10, 100, 20);

        usernameText = new JLabel("Username:");
        usernameText.setBounds(95, 90, 100, 20);

        password1Text = new JLabel("Password:");
        password1Text.setBounds(95, 170, 100, 20);

        password2Text = new JLabel("Confirm password:");
        password2Text.setBounds(95, 250, 120, 20);

        email = new JTextField();
        email.setBounds(95, 30, 300, 40);
        email.setFont(new Font("Roboto", Font.PLAIN, 25));

        username = new JTextField();
        username.setBounds(95, 110, 300, 40);
        username.setFont(new Font("Roboto", Font.PLAIN, 25));

        password1 = new JTextField();
        password1.setBounds(95, 190, 300, 40);
        password1.setFont(new Font("Roboto", Font.PLAIN, 25));

        password2 = new JTextField();
        password2.setBounds(95, 270, 300, 40);
        password2.setFont(new Font("Roboto", Font.PLAIN, 25));

        register = new JButton("Sign up");
        register.setBounds(195, 330, 100, 40);
        register.setFocusable(false);
        register.setBackground(new Color(0x62DC3F));
        register.setBorder(BorderFactory.createLineBorder(new Color(0x45982B), 2));
        register.setOpaque(true);
        register.setFont(new Font("Roboto", Font.PLAIN, 18));
        register.addActionListener(this);

        loginText = new JLabel("Already has an account?");
        loginText.setBounds(140, 380, 140, 20);
        loginText.setFont(new Font("Roboto", Font.PLAIN, 12));

        loginHere = new JButton("<html><u>Login here</u?</html>");
        loginHere.setBounds(250, 380, 100, 20);
        loginHere.setContentAreaFilled(false);
        loginHere.setOpaque(false);
        loginHere.setFocusable(false);
        loginHere.setForeground(new Color(0x23B4BE));
        loginHere.setBorder(BorderFactory.createEmptyBorder());
        loginHere.setFont(new Font("Roboto", Font.ITALIC, 12));
        loginHere.addActionListener(this);

        registerSuccessful = new JLabel();
        registerSuccessful.setBounds(0, 0, 500, 450);
        registerSuccessful.setOpaque(true);
        registerSuccessful.setVisible(false);


        this.add(registerSuccessful);
        this.add(loginText);
        this.add(loginHere);
        this.add(register);
        this.add(emailText);
        this.add(usernameText);
        this.add(password1Text);
        this.add(password2Text);
        this.add(email);
        this.add(username);
        this.add(password1);
        this.add(password2);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register) {
            if(email.getText().indexOf('@') != -1) {
                if(checkIfEmailInCredentials(email.getText())) {
                  if(checkIfUsernameInCredentials(username.getText())) {
                        if(password1.getText().length() >= 6) {
                            if(Objects.equals(password1.getText(), password2.getText())) {
                                try {
                                    writer.write(email.getText() + ":" + username.getText() + ":" + password1.getText() + "\n");
                                    writer.close();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                this.dispose();
                                new LoginFrame();
                            } else {
                                JOptionPane.showMessageDialog(null, "Passwords don't match.",
                                        "Password error", JOptionPane.ERROR_MESSAGE);
                                password1.setText("");
                                password2.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters long.",
                                    "Username error", JOptionPane.ERROR_MESSAGE);
                            System.out.println(password1.getText());
                            System.out.println(password1.getText().length());
                            username.setText("");
                            password1.setText("");
                            password2.setText("");
                    }
                  } else {
                      JOptionPane.showMessageDialog(null, "Username already exists.",
                              "Username error", JOptionPane.ERROR_MESSAGE);
                      username.setText("");
                      password1.setText("");
                      password2.setText("");
                  }
                } else {
                    JOptionPane.showMessageDialog(null, "Email is already registered.",
                            "Email error", JOptionPane.ERROR_MESSAGE);
                    email.setText("");
                    password1.setText("");
                    password2.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong email.",
                        "Email error", JOptionPane.ERROR_MESSAGE);
                email.setText("");
                password1.setText("");
                password2.setText("");
            }
        }
        if (e.getSource() == loginHere) {
            this.dispose();
            new LoginFrame();
        }
    }
    public boolean checkIfEmailInCredentials(String email) {
        String filename = "Credentials.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] words = line.split(":");
                if(Objects.equals(words[0], email)) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean checkIfUsernameInCredentials(String username) {
        String filename = "Credentials.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] words = line.split(":");
                if(Objects.equals(words[1], username)) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
