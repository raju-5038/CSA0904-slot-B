
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.io.*;
import java.util.List;

public class Main extends JFrame {

    private JTextField studentId = new JTextField(12);
    private JTextField studentName = new JTextField(12);

    private JComboBox<String> eventBox = new JComboBox<>(
        new String[]{"Cultural Fest", "Sports Day", "Technical Symposium"}
    );

    private JComboBox<String> filterBox = new JComboBox<>(
        new String[]{"All Events", "Cultural Fest", "Sports Day",
                     "Technical Symposium"}
    );

    private DefaultTableModel model = new DefaultTableModel(
        new String[]{"ID", "Student ID", "Student Name",
                     "Event", "Registered Date"}, 0
    );

    private JTable table = new JTable(model);
    private JLabel status = new JLabel("Ready");

    private int page = 0;
    private final int PAGE_SIZE = 50;

    private RegistrationDAO dao = new RegistrationDAO();
    private RegistrationService service =
        new RegistrationService(dao);

    public Main() {
        setTitle("Campus Event Registration");
        setSize(850, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportItem = new JMenuItem("Export CSV");
        JMenuItem exitItem = new JMenuItem("Exit");

        exportItem.addActionListener(e -> exportCSV());
        exitItem.addActionListener(e -> dispose());

        fileMenu.add(exportItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        JPanel form = new JPanel(new GridLayout(4, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Registration"));

        JButton registerButton = new JButton("Register");

        form.add(new JLabel("Student ID:"));
        form.add(studentId);
        form.add(new JLabel("Student Name:"));
        form.add(studentName);
        form.add(new JLabel("Event:"));
        form.add(eventBox);
        form.add(new JLabel(""));
        form.add(registerButton);

        JPanel filterPanel = new JPanel();
        JButton filterButton = new JButton("Filter");
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton exportButton = new JButton("Export CSV");

        filterPanel.add(new JLabel("Filter Event:"));
        filterPanel.add(filterBox);
        filterPanel.add(filterButton);
        filterPanel.add(previousButton);
        filterPanel.add(nextButton);
        filterPanel.add(exportButton);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(filterPanel, BorderLayout.CENTER);
        bottom.add(status, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        registerButton.addActionListener(e -> register());
        filterButton.addActionListener(e -> {
            page = 0;
            loadRecords();
        });
        previousButton.addActionListener(e -> {
            if (page > 0) {
                page--;
                loadRecords();
            }
        });
        nextButton.addActionListener(e -> {
            page++;
            loadRecords();
        });
        exportButton.addActionListener(e -> exportCSV());

        loadRecords();
    }

    private String selectedFilter() {
        String value = (String) filterBox.getSelectedItem();
        return value.equals("All Events") ? "" : value;
    }

    private void register() {
        String id = studentId.getText().trim();
        String name = studentName.getText().trim();
        String event = (String) eventBox.getSelectedItem();

        new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                service.register(id, name, event);
                return null;
            }

            protected void done() {
                try {
                    get();
                    JOptionPane.showMessageDialog(Main.this,
                        "Registration successful!");
                    studentId.setText("");
                    studentName.setText("");
                    page = 0;
                    loadRecords();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Main.this,
                        ex.getCause() == null ? ex.getMessage()
                                              : ex.getCause().getMessage(),
                        "Registration Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void loadRecords() {
        status.setText("Loading records...");

        String filter = selectedFilter();
        int offset = page * PAGE_SIZE;

        new SwingWorker<List<Registration>, Void>() {
            protected List<Registration> doInBackground()
                    throws Exception {
                return dao.getRecords(filter, PAGE_SIZE, offset);
            }

            protected void done() {
                try {
                    model.setRowCount(0);

                    for (Registration r : get()) {
                        model.addRow(new Object[]{
                            r.id, r.studentId, r.studentName,
                            r.eventName, r.date
                        });
                    }

                    status.setText("Page " + (page + 1) +
                                   " | Records: " + model.getRowCount());

                } catch (Exception ex) {
                    status.setText("Error loading records");
                    JOptionPane.showMessageDialog(Main.this,
                        ex.getMessage());
                }
            }
        }.execute();
    }

    private void exportCSV() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("registrations.csv"));

        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
            return;

        File file = chooser.getSelectedFile();
        String filter = selectedFilter();

        new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                List<Registration> records =
                    dao.getAllRecords(filter);

                try (PrintWriter out = new PrintWriter(file)) {
                    out.println("ID,Student ID,Student Name,Event,Date");

                    for (Registration r : records) {
                        out.println(r.id + "," +
                            csv(r.studentId) + "," +
                            csv(r.studentName) + "," +
                            csv(r.eventName) + "," +
                            csv(r.date));
                    }
                }
                return null;
            }

            protected void done() {
                try {
                    get();
                    JOptionPane.showMessageDialog(Main.this,
                        "CSV exported successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(Main.this,
                        "Export failed: " + ex.getMessage());
                }
            }
        }.execute();
    }

    private String csv(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new Main().setVisible(true));
    }
}

class Registration {
    int id;
    String studentId, studentName, eventName, date;

    Registration(int id, String studentId, String studentName,
                 String eventName, String date) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.eventName = eventName;
        this.date = date;
    }
}

class RegistrationService {
    private RegistrationDAO dao;

    RegistrationService(RegistrationDAO dao) {
        this.dao = dao;
    }

    void register(String id, String name, String event)
            throws Exception {

        if (id.isEmpty() || name.isEmpty() || event == null)
            throw new Exception("Please fill all fields.");

        if (dao.exists(id, event))
            throw new Exception(
                "This student is already registered for this event.");

        dao.insert(id, name, event);
    }
}

class RegistrationDAO {

    private static final String URL =
        "jdbc:mysql://localhost:3306/campus_events";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR_PASSWORD";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    boolean exists(String id, String event) throws SQLException {
        String sql = "SELECT id FROM registrations " +
                     "WHERE student_id=? AND event_name=?";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, event);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    void insert(String id, String name, String event)
            throws SQLException {

        String sql = "INSERT INTO registrations " +
            "(student_id, student_name, event_name) VALUES (?, ?, ?)";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, event);
            ps.executeUpdate();
        }
    }

    List<Registration> getRecords(String filter, int limit, int offset)
            throws SQLException {
        return fetch(filter, limit, offset);
    }

    List<Registration> getAllRecords(String filter)
            throws SQLException {
        return fetch(filter, 0, 0);
    }

    private List<Registration> fetch(String filter, int limit, int offset)
            throws SQLException {

        List<Registration> list = new java.util.ArrayList<>();

        String sql = "SELECT * FROM registrations";
        if (!filter.isEmpty())
            sql += " WHERE event_name=?";

        sql += " ORDER BY id DESC";

        if (limit > 0)
            sql += " LIMIT ? OFFSET ?";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int index = 1;

            if (!filter.isEmpty())
                ps.setString(index++, filter);

            if (limit > 0) {
                ps.setInt(index++, limit);
                ps.setInt(index, offset);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Registration(
                        rs.getInt("id"),
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getString("event_name"),
                        rs.getString("registered_at")
                    ));
                }
            }
        }

        return list;
    }
}
