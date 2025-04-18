import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Formatter;

public class TransactionGUI extends JFrame {

    JComboBox<String> transactionComboBox, paymentTypeComboBox;
    JCheckBox customerVerifiedYesCheckBox, customerVerifiedNoCheckBox;
    JCheckBox weekendTransferYesCheckBox, weekendTransferNoCheckBox;
    JCheckBox isTransactionPendingYesCheckBox, isTransactionPendingNoCheckBox;
    JButton addButton, calcButton, trainButton;

    public TransactionGUI() {
        super("Transaction GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel CheckBoxPanel = new JPanel(new GridLayout(5, 2, 15, 15));

        // Transaction Type Row
        CheckBoxPanel.add(new JLabel("Transaction Type:", JLabel.RIGHT));
        String[] transactionOptions = {"Instore", "Online"};
        transactionComboBox = new JComboBox<>(transactionOptions);
        CheckBoxPanel.add(transactionComboBox);

        // Payment Method Row
        CheckBoxPanel.add(new JLabel("Payment Method:", JLabel.RIGHT));
        String[] paymentMethods = {"Bank Transfer", "Card"};
        paymentTypeComboBox = new JComboBox<>(paymentMethods);
        CheckBoxPanel.add(paymentTypeComboBox);

        // User Verified Row
        CheckBoxPanel.add(new JLabel("User Verified?", JLabel.RIGHT));
        JPanel verificationPanel = new JPanel();
        customerVerifiedYesCheckBox = new JCheckBox("Yes");
        customerVerifiedNoCheckBox = new JCheckBox("No");
        ButtonGroup verificationGroup = new ButtonGroup();
        verificationGroup.add(customerVerifiedYesCheckBox);
        verificationGroup.add(customerVerifiedNoCheckBox);
        verificationPanel.add(customerVerifiedYesCheckBox);
        verificationPanel.add(customerVerifiedNoCheckBox);
        CheckBoxPanel.add(verificationPanel);

        // Weekend Transfer Row
        CheckBoxPanel.add(new JLabel("Weekend Transfer?", JLabel.RIGHT));
        JPanel weekendPanel = new JPanel();
        weekendTransferYesCheckBox = new JCheckBox("Yes");
        weekendTransferNoCheckBox = new JCheckBox("No");
        ButtonGroup weekendGroup = new ButtonGroup();
        weekendGroup.add(weekendTransferYesCheckBox);
        weekendGroup.add(weekendTransferNoCheckBox);
        weekendPanel.add(weekendTransferYesCheckBox);
        weekendPanel.add(weekendTransferNoCheckBox);
        CheckBoxPanel.add(weekendPanel);

        // Transaction Pending Row
        CheckBoxPanel.add(new JLabel("Is transaction pending?", JLabel.RIGHT));
        JPanel pendingPanel = new JPanel();
        isTransactionPendingYesCheckBox = new JCheckBox("Yes");
        isTransactionPendingNoCheckBox = new JCheckBox("No");
        ButtonGroup pendingGroup = new ButtonGroup();
        pendingGroup.add(isTransactionPendingYesCheckBox);
        pendingGroup.add(isTransactionPendingNoCheckBox);
        pendingPanel.add(isTransactionPendingYesCheckBox);
        pendingPanel.add(isTransactionPendingNoCheckBox);
        CheckBoxPanel.add(pendingPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addButton = new JButton("Add value");
        calcButton = new JButton("Calculate Probability");
        trainButton = new JButton("Train");
        buttonPanel.add(addButton);
        buttonPanel.add(calcButton);
        buttonPanel.add(trainButton);

        addButton.addActionListener(e -> {
            // terniary operators 
            String transactionType = transactionComboBox.getSelectedItem().toString();
            String paymentMethod = paymentTypeComboBox.getSelectedItem().toString();
            String customerVerified = customerVerifiedYesCheckBox.isSelected() ? "Yes" : "No";
            String weekendTransfer = weekendTransferYesCheckBox.isSelected() ? "Yes" : "No";
            String transactionPending = isTransactionPendingYesCheckBox.isSelected() ? "Yes" : "No";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data.csv", true))) {
                // error handling if csv not exist or is empty
                boolean fileExists = new File("Data.csv").exists();
                boolean isEmpty = !fileExists || new File("Data.csv").length() == 0;

                if (isEmpty) {
                    writer.write("Transaction Type,Payment Method,Customer Verified,Weekend Transfer,Transaction Pending");
                    writer.newLine();
                }

                writer.write(String.format("%s,%s,%s,%s,%s",
                        transactionType, paymentMethod, customerVerified, weekendTransfer, transactionPending));
                writer.newLine();
                JOptionPane.showMessageDialog(null, "Data added successfully!");
            } catch (IOException err) {
                JOptionPane.showMessageDialog(null, "Error: " + err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(CheckBoxPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(450, 300);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TransactionGUI().setVisible(true));
    }
}