import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FinalFrame extends JFrame implements ActionListener {
    JLabel text, oldDataText, newDataText, confirmNewDataText, changeDataText;
    JTextField oldData, newData, confirmNewData;
    JPanel changeDataPanel;
    JButton logout, hideData, changeData;
    JRadioButton usernameRadio, passwordRadio, emailRadio;
    ButtonGroup dataSelection;
    ImageIcon arrowDown = new ImageIcon("images/arrow-down.png");
    Image arrowDownImage = arrowDown.getImage().getScaledInstance(Image.SCALE_SMOOTH, 20, 20);
    ImageIcon resizedArrowDown = new ImageIcon(arrowDownImage);
    FinalFrame(String username) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Main Frame");

        text = new JLabel("Welcome " + username);
        text.setBounds(160, 150, 400, 100);
        text.setFont(new Font("MV Boli", Font.PLAIN, 25));
        text.setVerticalAlignment(SwingConstants.CENTER);
        text.setHorizontalAlignment(SwingConstants.CENTER);

        logout = new JButton("Logout");
        logout.setBounds(10, 10, 100, 25);
        logout.setFocusable(false);
        logout.addActionListener(this);

        changeDataPanel = new JPanel();
        changeDataPanel.setBounds(300, 350, 400, 150);
        changeDataPanel.setBackground(new Color(0xCDCDCD));
        changeDataPanel.setBorder(BorderFactory.createLineBorder(new Color(0xA4A4A4), 3, true));
        changeDataPanel.setLayout(null);

        hideData = new JButton();
        hideData.setBounds(5, 5, 20, 20);
        hideData.setIcon(resizedArrowDown);
        hideData.setFocusable(false);
        hideData.setBackground(new Color(0xA4A4A4));
        hideData.setVisible(false);
        hideData.addActionListener(this);

        changeData = new JButton();
        changeData.setBounds(140, 305, 140, 20);
        changeData.setFocusable(false);
        changeData.setVisible(false);
        changeData.addActionListener(this);

        changeDataText = new JLabel("Change your data");
        changeDataText.setBounds(145, 10, 175, 50);
        changeDataText.setFont(new Font("Roboto", Font.PLAIN, 20));

        usernameRadio = new JRadioButton("username");
        usernameRadio.setBounds(50, 60, 100, 20);
        usernameRadio.setFocusable(false);
        usernameRadio.setBackground(new Color(0xCDCDCD));
        usernameRadio.addActionListener(this);

        passwordRadio = new JRadioButton("password");
        passwordRadio.setBounds(175, 60, 100, 20);
        passwordRadio.setFocusable(false);
        passwordRadio.setBackground(new Color(0xCDCDCD));
        passwordRadio.addActionListener(this);

        emailRadio = new JRadioButton("email");
        emailRadio.setBounds(300, 60, 100, 20);
        emailRadio.setFocusable(false);
        emailRadio.setBackground(new Color(0xCDCDCD));
        emailRadio.addActionListener(this);

        dataSelection = new ButtonGroup();
        dataSelection.add(usernameRadio);
        dataSelection.add(passwordRadio);
        dataSelection.add(emailRadio);

        oldData = new JTextField();
        oldData.setBounds(60, 115, 300, 30);
        oldData.setVisible(false);

        newData = new JTextField();
        newData.setBounds(60, 190, 300, 30);
        newData.setVisible(false);

        confirmNewData = new JTextField();
        confirmNewData.setBounds(60, 265, 300, 30);
        confirmNewData.setVisible(false);

        oldDataText = new JLabel("Enter your current data:");
        oldDataText.setBounds(60, 90, 200, 25);
        oldDataText.setVisible(false);

        newDataText = new JLabel("Enter new data:");
        newDataText.setBounds(60, 165, 200, 25);
        newDataText.setVisible(false);

        confirmNewDataText = new JLabel("Confirm new data:");
        confirmNewDataText.setBounds(60, 240, 200, 25);
        confirmNewDataText.setVisible(false);

        changeDataPanel.add(changeData);
        changeDataPanel.add(hideData);
        changeDataPanel.add(changeDataText);
        changeDataPanel.add(usernameRadio);
        changeDataPanel.add(passwordRadio);
        changeDataPanel.add(emailRadio);
        changeDataPanel.add(oldData);
        changeDataPanel.add(newData);
        changeDataPanel.add(confirmNewData);
        changeDataPanel.add(oldDataText);
        changeDataPanel.add(newDataText);
        changeDataPanel.add(confirmNewDataText);

        this.add(changeDataPanel);
        this.add(logout);
        this.add(text);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logout) {
            this.dispose();
            new LoginFrame();
        }
        if (e.getSource() == usernameRadio) {
            showDataFields();
            setDataText("username");

        } else if (e.getSource() == passwordRadio) {
            showDataFields();
            setDataText("password");

        } else if (e.getSource() == emailRadio) {
            showDataFields();
            setDataText("email");

        }
        if (e.getSource() == hideData) {
            hideDataM();
        }
        if (e.getSource() == changeData) {
            if (usernameRadio.isSelected()) {
                try {
                    changeUsername();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (passwordRadio.isSelected()) {
                try {
                    changePassword();
                    System.out.println("Password changed");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (emailRadio.isSelected()) {
                try {
                    changeEmail();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private void hideDataM() {
        hideData.setVisible(false);
        changeDataPanel.setBounds(300, 350, 400, 150);
        text.setBounds(160, 150, 400, 100);
        dataSelection.clearSelection();
        oldDataText.setVisible(false);
        changeData.setVisible(false);
        changeData.setText("");
    }

    private void setDataText(String dataType) {
        oldDataText.setText("Enter your current " + dataType);
        newDataText.setText("Enter your new " + dataType);
        confirmNewDataText.setText("Confirm your new " + dataType);
        changeData.setText("Change " + dataType);
    }

    private void showDataFields() {
        changeDataPanel.setBounds(300, 130, 400, 340);
        text.setBounds(160, 30, 400, 100);
        hideData.setVisible(true);
        oldData.setVisible(true);
        newData.setVisible(true);
        confirmNewData.setVisible(true);
        oldDataText.setVisible(true);
        newDataText.setVisible(true);
        confirmNewDataText.setVisible(true);
        changeData.setVisible(true);
    }

    public void setTextBlankOnDataChange() {
        oldData.setText("");
        newData.setText("");
        confirmNewData.setText("");
    }

    public String getCurrentUsername() {
        return text.getText().substring(8);
    }

    public void changeUsername() throws IOException {
        String oldUsername = oldData.getText();
        String newUsername = newData.getText();
        String confirmNewUsername = confirmNewData.getText();

        try {
            File file = new File("Credentials.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");

                if (parts.length == 3 && parts[1].equals(getCurrentUsername())) {
                    if (oldUsername.equals(parts[1]) && newUsername.equals(confirmNewUsername)) {
                        parts[1] = newUsername;
                        JOptionPane.showMessageDialog(null, "Username changed successfully.",
                                "Username change", JOptionPane.INFORMATION_MESSAGE);
                        setTextBlankOnDataChange();
                        text.setText("Welcome " + newUsername);
                        hideDataM();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect data.",
                                "Username change", JOptionPane.ERROR_MESSAGE);
                        setTextBlankOnDataChange();
                    }
                }

                String updatedLine = String.join(":", parts);
                content.append(updatedLine).append(System.lineSeparator());
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePassword() throws IOException {
        String oldPassword = oldData.getText();
        String newPassword = newData.getText();
        String confirmNewPassword = confirmNewData.getText();

        try {
            File file = new File("Credentials.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");

                if (parts.length == 3 && parts[1].equals(getCurrentUsername())) {
                    if (oldPassword.equals(parts[2]) && newPassword.equals(confirmNewPassword)) {
                        parts[2] = newPassword;
                        JOptionPane.showMessageDialog(null, "Password changed successfully.",
                                "Password change", JOptionPane.INFORMATION_MESSAGE);
                        setTextBlankOnDataChange();
                        hideDataM();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect data.",
                                "Password change", JOptionPane.ERROR_MESSAGE);
                        setTextBlankOnDataChange();
                    }
                }

                String updatedLine = String.join(":", parts);
                content.append(updatedLine).append(System.lineSeparator());
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeEmail() throws IOException {
        String oldEmail = oldData.getText();
        String newEmail = newData.getText();
        String confirmNewEmail = confirmNewData.getText();

        try {
            File file = new File("Credentials.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");

                if (parts.length == 3 && parts[1].equals(getCurrentUsername())) {
                    if (oldEmail.equals(parts[0]) && newEmail.equals(confirmNewEmail) && newEmail.indexOf('@') != -1) {
                        parts[0] = newEmail;
                        JOptionPane.showMessageDialog(null, "Email changed successfully.",
                                "Email change", JOptionPane.INFORMATION_MESSAGE);
                        setTextBlankOnDataChange();
                        hideDataM();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect data.",
                                "Email change", JOptionPane.ERROR_MESSAGE);
                        setTextBlankOnDataChange();
                    }
                }

                String updatedLine = String.join(":", parts);
                content.append(updatedLine).append(System.lineSeparator());
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}