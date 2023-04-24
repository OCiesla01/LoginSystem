import javax.swing.*;
import java.awt.*;

public class FinalFrame extends JFrame {
    JLabel text;
    FinalFrame(String username) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Main Frame");

        text = new JLabel("Welcome " + username + "!");
        text.setBounds(200, 160, 400, 100);
        text.setFont(new Font("MV Boli", Font.PLAIN, 40));

        this.add(text);
        this.setVisible(true);
    }
}
