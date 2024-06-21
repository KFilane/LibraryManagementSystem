import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Dashboard_with_books_table extends JFrame {
    private JTable bookDetailsTable;
    private DefaultTableModel tableModel;
    private JSONArray booksArray;

    public Dashboard_with_books_table() {
        setTitle("Library Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for holding table on the left and buttons on the right
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Initialize table
        bookDetailsTable = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Code");
        tableModel.addColumn("Name");
        tableModel.addColumn("Author");
        tableModel.addColumn("Title");
        tableModel.addColumn("Quantity");
        bookDetailsTable.setModel(tableModel);

        JScrollPane tableScrollPane = new JScrollPane(bookDetailsTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Panel for buttons on the right
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // View Books button
        JButton viewBooksButton = new JButton("View Books");
        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBooks();
            }
        });
        buttonPanel.add(viewBooksButton);

        // Add Admin button
        JButton addAdminButton = new JButton("Add Admin");
        addAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement add admin functionality here
                JOptionPane.showMessageDialog(Dashboard_with_books_table.this, "Add Admin functionality not implemented yet.");
            }
        });
        buttonPanel.add(addAdminButton);

        // Remove Admin button
        JButton removeAdminButton = new JButton("Remove Admin");
        removeAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement remove admin functionality here
                JOptionPane.showMessageDialog(Dashboard_with_books_table.this, "Remove Admin functionality not implemented yet.");
            }
        });
        buttonPanel.add(removeAdminButton);

        // Administrators button
        JButton administratorsButton = new JButton("Administrators");
        administratorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement view administrators functionality here
                JOptionPane.showMessageDialog(Dashboard_with_books_table.this, "View Administrators functionality not implemented yet.");
            }
        });
        buttonPanel.add(administratorsButton);

        // Add Book button
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        buttonPanel.add(addBookButton);

        // Remove Book button
        JButton removeBookButton = new JButton("Remove Book");
        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook();
            }
        });
        buttonPanel.add(removeBookButton);

        // Load initial data
        loadBooks();
        displayBooks();
    }

    private void loadBooks() {
        try (FileInputStream fileInputStream = new FileInputStream("books.json")) {
            booksArray = new JSONArray(new JSONTokener(fileInputStream));
        } catch (IOException e) {
            booksArray = new JSONArray();
        }
    }

    private void displayBooks() {
        tableModel.setRowCount(0); // Clear any existing data

        for (int i = 0; i < booksArray.length(); i++) {
            JSONObject book = booksArray.getJSONObject(i);
            tableModel.addRow(new Object[]{
                    book.optString("code", "N/A"),
                    book.optString("name", "N/A"),
                    book.optString("author", "N/A"),
                    book.optString("title", "N/A"),
                    book.optInt("quantity", 0)
            });
        }
    }

    private void addBook() {
        String code = JOptionPane.showInputDialog(this, "Enter book code:");
        String name = JOptionPane.showInputDialog(this, "Enter book name:");
        String author = JOptionPane.showInputDialog(this, "Enter book author:");
        String title = JOptionPane.showInputDialog(this, "Enter book title:");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter book quantity:"));

        JSONObject newBook = new JSONObject();
        newBook.put("code", code);
        newBook.put("name", name);
        newBook.put("author", author);
        newBook.put("title", title);
        newBook.put("quantity", quantity);

        booksArray.put(newBook);
        saveBooks();
        displayBooks();
    }

    private void removeBook() {
        String code = JOptionPane.showInputDialog(this, "Enter book code to remove:");

        for (int i = 0; i < booksArray.length(); i++) {
            JSONObject book = booksArray.getJSONObject(i);
            if (book.getString("code").equals(code)) {
                booksArray.remove(i);
                break;
            }
        }
        saveBooks();
        displayBooks();
    }

    private void saveBooks() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("books.json")) {
            fileOutputStream.write(booksArray.toString(4).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dashboard_with_books_table().setVisible(true);
            }
        });
    }
}
