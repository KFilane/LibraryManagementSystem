import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.awt.Color;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends javax.swing.JFrame {

    public Register() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Register = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField(); // Confirm Password field
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton(); // New button for login link

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        Register.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        Register.setText("REGISTER");

        username.setText("Username: ");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Password :");

        jPasswordField1.setText("");

        jLabel3.setText("Confirm Password:");

        jPasswordField2.setText("");

        jButton1.setText("Register");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // Set button text using HTML to make it blue and underlined
        jButton2.setText("<html><font color='blue'><u>Already have an account? Login here</u></font></html>");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(username)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(20, 20, 20)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jPasswordField1)
                                .addComponent(jPasswordField2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(143, 143, 143)
                            .addComponent(jButton1))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(162, 162, 162)
                            .addComponent(Register, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(jButton2)))
                    .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(Register)
                    .addGap(42, 42, 42)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(username)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton2)
                    .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String username = jTextField1.getText();
        String password = new String(jPasswordField1.getPassword());
        String confirmPassword = new String(jPasswordField2.getPassword());

        // Check for empty fields
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username or password fields cannot be empty");
            return;
        }

        // Password strength check
        if (!isPasswordStrong(password)) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long and contain a mix of letters, numbers, and special characters");
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match. Please re-enter.");
            jPasswordField2.setText("");
            return;
        }

        // Check if username already exists
        if (userExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists, please choose another one");
            return;
        }

        // Register user
        if (registerUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Registration successful");
            Login loginForm = new Login();
            loginForm.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to register user");
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        Login loginForm = new Login();
        loginForm.setVisible(true);
        this.dispose();
    }

    private boolean userExists(String username) {
        try (FileInputStream fis = new FileInputStream("users.json")) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject jsonObject = new JSONObject(tokener);
            JSONArray users = jsonObject.getJSONArray("users");

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                if (user.getString("username").equals(username)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            return false; // File doesn't exist, so the user can't exist
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean registerUser(String username, String password) {
        JSONObject jsonObject;
        JSONArray users;

        try (FileInputStream fis = new FileInputStream("users.json")) {
            JSONTokener tokener = new JSONTokener(fis);
            jsonObject = new JSONObject(tokener);
            users = jsonObject.getJSONArray("users");
        } catch (FileNotFoundException e) {
            // If file doesn't exist, create a new JSON structure
            jsonObject = new JSONObject();
            users = new JSONArray();
            jsonObject.put("users", users);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Check if username already exists
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.getString("username").equals(username)) {
                return false; // User already exists
            }
        }

        // Add new user
        JSONObject newUser = new JSONObject();
        newUser.put("username", username);
        newUser.put("password", password);
        users.put(newUser);

        // Write back to the file
        try (FileWriter fileWriter = new FileWriter("users.json")) {
            fileWriter.write(jsonObject.toString(4)); // Indented for readability
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isPasswordStrong(String password) {
        // Password must be at least 8 characters long and contain at least one digit, one letter, and one special character
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    private javax.swing.JLabel Register;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2; // New button for login link
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3; // Label for confirm password
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2; // Confirm Password field
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel username;
}
