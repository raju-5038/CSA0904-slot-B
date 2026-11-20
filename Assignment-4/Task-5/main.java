
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {

    JTextField amount = new JTextField(10);
    JComboBox<String> from = new JComboBox<>(
        new String[]{"INR", "USD", "EUR"});
    JComboBox<String> to = new JComboBox<>(
        new String[]{"USD", "EUR", "INR"});

    JButton convert = new JButton("Convert");

    public Main() {
        setTitle("Currency Converter");
        setLayout(new FlowLayout());

        add(new JLabel("Amount:"));
        add(amount);
        add(from);
        add(new JLabel("To"));
        add(to);
        add(convert);

        convert.addActionListener(this);
        amount.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(350, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        calculate();
    }

    void calculate() {
        try {
            double value = Double.parseDouble(amount.getText());

            double result = value *
                getRate(from.getSelectedItem().toString()) /
                getRate(to.getSelectedItem().toString());

            JOptionPane.showMessageDialog(this,
                String.format("%.2f %s",
                    result, to.getSelectedItem()));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Invalid amount");
        }
    }

    double getRate(String currency) {
        if (currency.equals("USD")) return 83;
        if (currency.equals("EUR")) return 90;
        return 1;
    }

    public static void main(String[] args) {
        new Main();
    }
}
