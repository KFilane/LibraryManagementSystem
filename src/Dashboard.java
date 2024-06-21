import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Dashboard extends JFrame {

    private JRadioButton userRadioButton;
    private JRadioButton adminRadioButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel registerLabel;
    private JPanel backgroundPanel;

    public Dashboard() {
        initComponents();
    }

    private void initComponents() {
        userRadioButton = new JRadioButton("USER");
        adminRadioButton = new JRadioButton("ADMIN");
        ButtonGroup group = new ButtonGroup();
        group.add(userRadioButton);
        group.add(adminRadioButton);

        usernameField = new JTextField("Username", 10);
        passwordField = new JPasswordField("Password", 10);
        loginButton = new JButton("LOGIN");
        registerLabel = new JLabel("Register here");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set properties for components
        Color blueColor = new Color(51, 153, 255);
        userRadioButton.setBackground(blueColor);
        userRadioButton.setForeground(Color.WHITE);
        userRadioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        userRadioButton.setFocusPainted(false);

        adminRadioButton.setBackground(blueColor);
        adminRadioButton.setForeground(Color.WHITE);
        adminRadioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        adminRadioButton.setFocusPainted(false);

        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));

        // Register label properties
        registerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        registerLabel.setForeground(Color.RED);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add action listeners
        loginButton.addActionListener(evt -> loginButtonActionPerformed(evt));
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                registerLabelMouseClicked(evt);
            }
        });

        // Add focus listeners for placeholders
        addPlaceholderStyle(usernameField);
        addPlaceholderStyle(passwordField);

        // Create custom panel with background image
        backgroundPanel = new JPanel() {
            private Image backgroundImage;

            {
                String imagePath = "zama.jpg";
                URL imageURL = getClass().getResource(imagePath); // Load image from classpath
                if (imageURL != null) {
                    backgroundImage = new ImageIcon(imageURL).getImage();
                    System.out.println("Image path: " + imageURL.getPath());
                } else {
                    System.err.println("Error: Image not found at " + imagePath);
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    int imageWidth = backgroundImage.getWidth(this);
                    int imageHeight = backgroundImage.getHeight(this);

                    // Calculate the scaling factor to maintain the aspect ratio
                    double widthRatio = (double) panelWidth / imageWidth;
                    double heightRatio = (double) panelHeight / imageHeight;
                    double scaleFactor = Math.max(widthRatio, heightRatio);  // Use max to fill the screen

                    // Calculate the new dimensions
                    int newWidth = (int) (imageWidth * scaleFactor);
                    int newHeight = (int) (imageHeight * scaleFactor);

                    // Center the image
                    int x = (panelWidth - newWidth) / 2;
                    int y = (panelHeight - newHeight) / 2;

                    g.drawImage(backgroundImage, x, y, newWidth, newHeight, this);
                }
            }
        };

        // Add heading label
        JLabel headingLabel = new JLabel("WELCOME TO TECHA-LIBRARY", JLabel.CENTER);
        headingLabel.setFont(new Font("Serif", Font.BOLD, 36)); // Increased font size to 36
        headingLabel.setForeground(Color.WHITE);

        // Set layout and add components to the panel
        GroupLayout layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(headingLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50) // Move the form slightly to the left
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(userRadioButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(adminRadioButton))
                                        .addComponent(usernameField)
                                        .addComponent(passwordField)
                                        .addComponent(loginButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(registerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(headingLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(userRadioButton)
                                        .addComponent(adminRadioButton))
                                .addGap(20, 20, 20)
                                .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(registerLabel)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        // Add the panel to the frame
        add(backgroundPanel);

        pack();
    }

    private void addPlaceholderStyle(JTextComponent textComponent) {
        Font originalFont = textComponent.getFont();
        Color originalColor = textComponent.getForeground();

        textComponent.setForeground(Color.GRAY);
        textComponent.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textComponent.getForeground().equals(Color.GRAY)) {
                    textComponent.setText("");
                    textComponent.setForeground(originalColor);
                    textComponent.setFont(originalFont);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textComponent.getText().isEmpty()) {
                    textComponent.setText(textComponent == usernameField ? "Username" : "Password");
                    textComponent.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty.", "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userRadioButton.isSelected()) {
            // Process user login
            if (authenticateUser(username, password)) {
                // Successful login
                JOptionPane.showMessageDialog(this, "User login successful.", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                // Proceed with user-specific actions
            } else {
                // Failed login
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (adminRadioButton.isSelected()) {
            // Process admin login
            if (authenticateAdmin(username, password)) {
                // Successful login
                JOptionPane.showMessageDialog(this, "Admin login successful.", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                // Proceed with admin-specific actions
            } else {
                // Failed login
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Neither user nor admin selected (should not happen due to radio button group)
            JOptionPane.showMessageDialog(this, "Please select User or Admin.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear password field after login attempt for security
        passwordField.setText("");
    }

    private boolean authenticateUser(String username, String password) {
        // Replace with actual authentication logic for users
        // For demonstration, assume "user" with password "password" is valid
        return username.equals("user") && password.equals("password");
    }

    private boolean authenticateAdmin(String username, String password) {
        // Replace with actual authentication logic for admins
        // For demonstration, assume "admin" with password "admin123" is valid
        return username.equals("admin") && password.equals("admin123");
    }

    private void registerLabelMouseClicked(MouseEvent evt) {
        // Open the register page
        Register registerPage = new Register();
        registerPage.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }
}

