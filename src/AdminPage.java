import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminPage extends JFrame {

    private JButton loginButton;
    private JLabel backLabel;

    public AdminPage() {
        initComponents();
    }

    private void initComponents() {

        loginButton = new JButton();
        backLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loginButton.setText("LOGIN");
        loginButton.addActionListener(evt -> loginButtonActionPerformed(evt));

        // Set up the back label with an arrow icon
        ImageIcon backIcon = new ImageIcon(getClass().getResource("back.png")); // Ensure this path is correct
        // Resize the ImageIcon to a smaller size
        Image originalImage = backIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        backLabel.setIcon(scaledIcon);
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                backLabelMouseClicked(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(backLabel)
                    .addGap(50, 50, 50)
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(150, 150, 150)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(backLabel)
                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(150, Short.MAX_VALUE))
        );

        pack();
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Open the login page
        new Login().setVisible(true);
        this.dispose();
    }

    private void backLabelMouseClicked(MouseEvent evt) {
        // Go back to the previous page (Dashboard)
        new Dashboard().setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AdminPage().setVisible(true));
    }
}
