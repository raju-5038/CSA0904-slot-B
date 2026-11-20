
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Main extends JFrame {

    JTextField searchField;
    JComboBox<String> category;
    DefaultListModel<String> model;
    JList<String> resultList;
    JLabel status;

    String[] books = {
        "Java Programming",
        "Python Basics",
        "Data Structures",
        "Database Management",
        "Computer Networks",
        "Operating Systems"
    };

    public Main() {

        setTitle("Campus Library Search");
        setSize(600, 400);
        setLayout(new BorderLayout(10, 10));

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(
            "CAMPUS LIBRARY SEARCH",
            JLabel.CENTER
        );
        title.setFont(new Font("Arial", Font.BOLD, 20));

        topPanel.add(title, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());

        searchField = new JTextField(15);

        category = new JComboBox<>(new String[]{
            "All", "Java", "Python", "Database",
            "Networks", "Operating Systems"
        });

        JButton searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(category);
        searchPanel.add(searchButton);

        topPanel.add(searchPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Results area
        model = new DefaultListModel<>();
        resultList = new JList<>(model);

        JScrollPane scrollPane = new JScrollPane(resultList);

        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton prev = new JButton("Prev");
        JButton next = new JButton("Next");
        JButton reserve = new JButton("Reserve");

        buttonPanel.add(prev);
        buttonPanel.add(next);
        buttonPanel.add(reserve);

        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        status = new JLabel("Status: Ready");
        bottomPanel.add(status, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Search action
        searchButton.addActionListener(e -> searchBooks());

        searchField.addActionListener(e -> searchBooks());

        // Previous button
        prev.addActionListener(e ->
            status.setText("Previous page selected")
        );

        // Next button
        next.addActionListener(e ->
            status.setText("Next page selected")
        );

        // Reserve button
        reserve.addActionListener(e -> {
            String selected = resultList.getSelectedValue();

            if (selected == null) {
                status.setText("Please select a book");
            } else {
                status.setText("Reserved: " + selected);
            }
        });

        // Window closing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadBooks();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    void loadBooks() {
        model.clear();

        for (String book : books) {
            model.addElement(book);
        }
    }

    void searchBooks() {

        String keyword = searchField.getText()
                .trim().toLowerCase();

        String selectedCategory =
                category.getSelectedItem().toString();

        model.clear();

        for (String book : books) {

            boolean matchesKeyword =
                book.toLowerCase().contains(keyword);

            boolean matchesCategory =
                selectedCategory.equals("All") ||
                book.toLowerCase().contains(
                    selectedCategory.toLowerCase()
                );

            if (matchesKeyword && matchesCategory) {
                model.addElement(book);
            }
        }

        status.setText(
            "Results found: " + model.size()
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
