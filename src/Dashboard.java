import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.JOptionPane;

/**
 * Dashboard GUI for managing books and users.
 */
public class Dashboard extends javax.swing.JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logoutMenuItem;
    private JPanel jPanel1;
    private JLabel Dashboard;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton6;
    private JButton jButton7;
    private JScrollPane jScrollPane1;
    private JList<String> bookList;
    private JPanel bookDetailsPanel; // Added panel to display book details

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        setupMenu();
        displayBooks();
        setupBookDetailsPanel(); // Initialize book details panel
    }

    /**
     * Sets up the menu bar and menu items.
     */
    private void setupMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Home");
        logoutMenuItem = new JMenuItem("Logout");

        logoutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(Dashboard.this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Login loginForm = new Login();
                    loginForm.setVisible(true);
                    dispose();
                }
            }
        });

        menu.add(logoutMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    /**
     * This method initializes the GUI components.
     */
    private void initComponents() {
        jPanel1 = new JPanel();
        Dashboard = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();
        jScrollPane1 = new JScrollPane();
        bookList = new JList<>();
        bookDetailsPanel = new JPanel(); // Initialize book details panel

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        Dashboard.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        Dashboard.setText("DASHBOARD");

        jButton1.setText("ADD BOOK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);

                String title = JOptionPane.showInputDialog(this, "Enter book title:");
                String author = JOptionPane.showInputDialog(this, "Enter author:");
                String code = JOptionPane.showInputDialog(this, "Enter Code:");
            }
        });

        jButton2.setText("REMOVE BOOK");

        jButton3.setText("BOOKS AVAILABLE");

        jButton4.setText("ADD ADMIN");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);

                String title = JOptionPane.showInputDialog(this, "Enter book title:");
                String author = JOptionPane.showInputDialog(this, "Enter author:");
                String code = JOptionPane.showInputDialog(this, "Enter Code:");
            }
        });

        jButton6.setText("REMOVE ADMIN");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("ADMINISTRATORS");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(Dashboard)
                                            .addGap(12, 12, 12)))
                            .addGap(17, 17, 17)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(26, 26, 26)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(Dashboard)
                            .addGap(33, 33, 33)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(65, 65, 65)
                            .addGap(41, 41, 41))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addContainerGap())
        );

        jScrollPane1.setViewportView(bookList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(bookDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bookDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
<<<<<<< HEAD
    }
=
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Prompt for admin username to remove
        String username = JOptionPane.showInputDialog(this, "Enter admin username to remove:");
        if (username == null || username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.");
            return;
        }

        // Load the users.json file
       

            
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed


    /**
     * Action performed when "ADD ADMIN" button is clicked.
     * Allows adding a new admin user to the system.
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String username = JOptionPane.showInputDialog(this, "Enter new admin username:");
        String password = JOptionPane.showInputDialog(this, "Enter new admin password:");

        if (usernameExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose another username.");
            return;
        }

        JSONObject newAdmin = new JSONObject();
        newAdmin.put("username", username);
        newAdmin.put("password", password);
        newAdmin.put("role", "admin");

        JSONArray usersArray = getUsersJSONArray();
        usersArray.put(newAdmin);

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("users.json"), StandardCharsets.UTF_8)) {
            JSONObject updatedJson = new JSONObject();
            updatedJson.put("users", usersArray);
            writer.write(updatedJson.toString(4));
            writer.flush();
            JOptionPane.showMessageDialog(this, "Admin added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add admin. Please try again later.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * Action performed when "REMOVE ADMIN" button is clicked.
     * Allows removing an admin user from the system.
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String username = JOptionPane.showInputDialog(this, "Enter username of admin to remove:");
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid username.");
            return;
        }

        JSONArray usersArray = getUsersJSONArray();
        boolean found = false;
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject user = usersArray.getJSONObject(i);
            if (user.getString("username").equals(username) && user.getString("role").equals("admin")) {
                usersArray.remove(i);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Admin user not found.");
            return;
        }

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("users.json"), StandardCharsets.UTF_8)) {
            JSONObject updatedJson = new JSONObject();
            updatedJson.put("users", usersArray);
            writer.write(updatedJson.toString(4));
            writer.flush();
            JOptionPane.showMessageDialog(this, "Admin removed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to remove admin. Please try again later.");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * Action performed when "ADMINISTRATORS" button is clicked.
     * Displays the list of all administrators.
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JSONArray usersArray = getUsersJSONArray();
        StringBuilder adminList = new StringBuilder("Administrators:\n");

        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject user = usersArray.getJSONObject(i);
            if (user.getString("role").equals("admin")) {
                adminList.append(user.getString("username")).append("\n");
            }
        }

        if (adminList.toString().equals("Administrators:\n")) {
            JOptionPane.showMessageDialog(this, "No administrators found.");
        } else {
            JOptionPane.showMessageDialog(this, adminList.toString());
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * Action performed when "ADD BOOK" button is clicked.
     * Allows adding a new book to the system.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String name = JOptionPane.showInputDialog(this, "Enter book name:");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid book name.");
            return;
        }

        String author = JOptionPane.showInputDialog(this, "Enter book author:");
        if (author == null || author.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid author name.");
            return;
        }

        String quantityStr = JOptionPane.showInputDialog(this, "Enter quantity:");
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity. Please enter a valid positive number.");
            return;
        }

        String category = JOptionPane.showInputDialog(this, "Enter category:");
        if (category == null || category.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid category.");
            return;
        }

        JSONObject newBook = new JSONObject();
        newBook.put("id", generateBookId()); // Generate unique book ID
        newBook.put("name", name);
        newBook.put("author", author);
        newBook.put("quantity", quantity);
        newBook.put("category", category);

        JSONArray booksArray = getBooksJSONArray();
        booksArray.put(newBook);

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("books.json"), StandardCharsets.UTF_8)) {
            JSONObject updatedJson = new JSONObject();
            updatedJson.put("books", booksArray);
            writer.write(updatedJson.toString(4));
            writer.flush();
            JOptionPane.showMessageDialog(this, "Book added successfully.");
            displayBooks(); // Refresh the list after adding the book
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add book. Please try again later.");
        }
    }

    /**
     * Generate a unique book ID.
     * This fetches the highest ID from existing books and increments it.
     */
    private String generateBookId() {
        JSONArray booksArray = getBooksJSONArray();
        int maxId = 0;

        for (int i = 0; i < booksArray.length(); i++) {
            JSONObject book = booksArray.getJSONObject(i);
            int bookId = book.getInt("id");
            if (bookId > maxId) {
                maxId = bookId;
            }
        }

        // Increment the maxId to generate a new ID
        return Integer.toString(maxId + 1);
    }

    /**
     * Checks if a username already exists in the system.
     *
     * @param username The username to check.
     * @return true if the username exists, false otherwise.
     */
    private boolean usernameExists(String username) {
        JSONArray usersArray = getUsersJSONArray();
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject user = usersArray.getJSONObject(i);
            if (user.getString("username").equals(username)) {
                return true; // Username already exists
            }
        }
        return false; // Username does not exist
    }

    /**
     * Retrieves the JSON array of users from the file.
     *
     * @return The JSONArray of users.
     */
    private JSONArray getUsersJSONArray() {
        try (FileInputStream fis = new FileInputStream("users.json")) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject jsonObject = new JSONObject(tokener);
            return jsonObject.getJSONArray("users");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONArray(); // Return empty array in case of error
    }

    /**
     * Retrieves the JSON array of books from the file.
     *
     * @return The JSONArray of books.
     */
    private JSONArray getBooksJSONArray() {
        try (FileInputStream fis = new FileInputStream("books.json")) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject jsonObject = new JSONObject(tokener);
            return jsonObject.getJSONArray("books");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONArray(); // Return empty array in case of error
    }

    /**
     * Displays the list of books in the bookList component.
     */
    private void displayBooks() {
        JSONArray booksArray = getBooksJSONArray();
        String[] bookTitles = new String[booksArray.length()];

        for (int i = 0; i < booksArray.length(); i++) {
            JSONObject book = booksArray.getJSONObject(i);
            String bookInfo = String.format("%s | %s | Author: %s | Quantity: %d | %s",
                    book.getString("id"), book.getString("name"), book.getString("author"),
                    book.getInt("quantity"), book.getString("category"));
            bookTitles[i] = bookInfo;
        }

        bookList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = bookTitles;

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });

        // Add selection listener to bookList
        bookList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = bookList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        JSONObject selectedBook = booksArray.getJSONObject(selectedIndex);
                        updateBookDetails(selectedBook); // Update book details panel
                    }
                }
            }
        });
    }

    /**
     * Update the book details panel with the details of the selected book.
     *
     * @param book The JSONObject representing the selected book.
     */
    private void updateBookDetails(JSONObject book) {
        bookDetailsPanel.removeAll(); // Clear existing details

        JLabel nameLabel = new JLabel("Name: " + book.getString("name"));
        JLabel authorLabel = new JLabel("Author: " + book.getString("author"));
        JLabel quantityLabel = new JLabel("Quantity: " + book.getInt("quantity"));
        JLabel categoryLabel = new JLabel("Category: " + book.getString("category"));

        GroupLayout layout = new GroupLayout(bookDetailsPanel);
        bookDetailsPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel)
                        .addComponent(authorLabel)
                        .addComponent(quantityLabel)
                        .addComponent(categoryLabel)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addComponent(authorLabel)
                        .addComponent(quantityLabel)
                        .addComponent(categoryLabel)
        );

        bookDetailsPanel.revalidate(); // Refresh panel layout
        bookDetailsPanel.repaint(); // Repaint panel
    }

    /**
     * Sets up the book details panel.
     * This panel displays details of the selected book.
     */
    private void setupBookDetailsPanel() {
        GroupLayout layout = new GroupLayout(bookDetailsPanel);
        bookDetailsPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    
        JLabel nameLabel = new JLabel("Name:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel quantityLabel = new JLabel("Quantity:");
        JLabel categoryLabel = new JLabel("Category:");
    
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLabel)
                                        .addComponent(authorLabel)
                                        .addComponent(quantityLabel)
                                        .addComponent(categoryLabel))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addComponent(authorLabel)
                        .addComponent(quantityLabel)
                        .addComponent(categoryLabel)
        );
    
        GroupLayout mainLayout = (GroupLayout) getContentPane().getLayout(); // Get layout of JFrame
        mainLayout.setHorizontalGroup(
                mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bookDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    
        mainLayout.setVerticalGroup(
                mainLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bookDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    
        pack(); // Pack components to adjust window size
    }
    

    /**
     * Main method to run the application.
     * Starts the Dashboard GUI.
     *
     * @param args The command line arguments.
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }
}
