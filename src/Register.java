import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {

    private JTextField fullNameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JCheckBox passwordStrengthCheckbox;
    private JButton registerButton;

    public Register() {
        initComponents();
    }

    private void initComponents() {
        setTitle("User Registration");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the window on screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10)); // 8 rows, 2 columns with gaps of 10 pixels

        // Labels and Text Fields
        panel.add(new JLabel("Enter Full Name:"));
        fullNameField = new JTextField();
        panel.add(fullNameField);

        panel.add(new JLabel("Enter Address:"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Enter Phone number:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Enter Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Enter Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        panel.add(confirmPasswordField);

        // Password strength checkbox
        panel.add(new JLabel("Password Strength:"));
        passwordStrengthCheckbox = new JCheckBox("Follow password requirements");
        passwordStrengthCheckbox.setEnabled(false); // Initially disabled
        panel.add(passwordStrengthCheckbox);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButtonActionPerformed(e);
            }
        });
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    private void registerButtonActionPerformed(ActionEvent evt) {
        String fullName = fullNameField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Basic validation
        if (fullName.isEmpty() || address.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check password strength
        boolean isPasswordStrong = isPasswordStrong(password);
        if (!isPasswordStrong) {
            JOptionPane.showMessageDialog(this, "Password does not meet requirements.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If all checks pass, proceed with registration logic (not implemented here)
        JOptionPane.showMessageDialog(this, "Registration Successful!", "Registration Success", JOptionPane.INFORMATION_MESSAGE);
        // Example: Save user details to database or perform further actions

        // Clear fields after registration
        clearFields();
    }

    private boolean isPasswordStrong(String password) {
        // Example of password strength criteria: At least 8 characters, including uppercase, lowercase, and digits
        return password.length() >= 8 &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*");
    }

    private void clearFields() {
        fullNameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        passwordStrengthCheckbox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Register();
            }
        });
    }
}
