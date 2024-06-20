import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class BookManagerGUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        // Create JSON file with books (only needed once)
        createJsonFile();

        // Launch the GUI
        SwingUtilities.invokeLater(BookManagerGUI::new);
    }

    public BookManagerGUI() {
        setTitle("Book Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create table model and table
        tableModel = new DefaultTableModel(new String[]{"Code", "Title", "Author", "Type"}, 0);
        table = new JTable(tableModel);

        // Create button and add action listener
        JButton viewBooksButton = new JButton("View Books");
        viewBooksButton.addActionListener(e -> loadBooks());

        // Layout components
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(viewBooksButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private static void createJsonFile() {
        JSONArray books = new JSONArray();

        books.put(createBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"));
        books.put(createBook("To Kill a Mockingbird", "Harper Lee", "Fiction"));
        books.put(createBook("1984", "George Orwell", "Dystopian"));
        books.put(createBook("Pride and Prejudice", "Jane Austen", "Romance"));
        books.put(createBook("The Catcher in the Rye", "J.D. Salinger", "Fiction"));
        books.put(createBook("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        books.put(createBook("Moby-Dick", "Herman Melville", "Adventure"));
        books.put(createBook("War and Peace", "Leo Tolstoy", "Historical Fiction"));
        books.put(createBook("The Odyssey", "Homer", "Epic"));
        books.put(createBook("Ulysses", "James Joyce", "Modernist"));

        try (FileWriter file = new FileWriter("books.json")) {
            file.write(books.toString());
            file.flush();
            System.out.println("JSON file created: " + books.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject createBook(String title, String author, String type) {
        JSONObject book = new JSONObject();
        book.put("code", generateUniqueCode());
        book.put("title", title);
        book.put("author", author);
        book.put("type", type);
        return book;
    }

    private static int generateUniqueCode() {
        return 1000 + RANDOM.nextInt(9000); // Generates a 4-digit number between 1000 and 9999
    }

    private void loadBooks() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("books.json")));
            JSONArray books = new JSONArray(content);

            // Clear existing data
            tableModel.setRowCount(0);

            // Add rows to the table
            for (int i = 0; i < books.length(); i++) {
                JSONObject book = books.getJSONObject(i);
                int code = book.getInt("code");
                String title = book.getString("title");
                String author = book.getString("author");
                String type = book.getString("type");

                tableModel.addRow(new Object[]{code, title, author, type});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
