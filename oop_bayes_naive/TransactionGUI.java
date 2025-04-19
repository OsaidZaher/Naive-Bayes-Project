import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Map;
import java.util.List;

public class TransactionGUI extends JFrame {

    private JComboBox<String> transactionComboBox, paymentTypeComboBox;
    private JCheckBox customerVerifiedYesCheckBox, customerVerifiedNoCheckBox;
    private JCheckBox weekendTransferYesCheckBox, weekendTransferNoCheckBox;
    private JCheckBox isTransactionPendingYesCheckBox, isTransactionPendingNoCheckBox;
    private JButton addButton, calcButton, trainButton;
    

    private TransactionModel transactionModel;
    private Map<String, int[]> trainedFrequencyTable;

    public TransactionGUI() {
        super("Transaction GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        transactionModel = TransactionModel.getTransactionModel("Data.csv");
        

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel checkboxPanel = new JPanel(new GridLayout(5, 2, 15, 15));

        // Transaction Type Row
        checkboxPanel.add(new JLabel("Transaction Type:", JLabel.RIGHT));
        String[] transactionOptions = {"Instore", "Online"};
        transactionComboBox = new JComboBox<>(transactionOptions);
        checkboxPanel.add(transactionComboBox);

        // Payment Method Row
        checkboxPanel.add(new JLabel("Payment Method:", JLabel.RIGHT));
        String[] paymentMethods = {"Bank Transfer", "Card"};
        paymentTypeComboBox = new JComboBox<>(paymentMethods);
        checkboxPanel.add(paymentTypeComboBox);

        // User Verified Row
        checkboxPanel.add(new JLabel("User Verified?", JLabel.RIGHT));
        JPanel verificationPanel = new JPanel();
        customerVerifiedYesCheckBox = new JCheckBox("Yes");
        customerVerifiedNoCheckBox = new JCheckBox("No", true);
        ButtonGroup verificationGroup = new ButtonGroup();
        verificationGroup.add(customerVerifiedYesCheckBox);
        verificationGroup.add(customerVerifiedNoCheckBox);
        verificationPanel.add(customerVerifiedYesCheckBox);
        verificationPanel.add(customerVerifiedNoCheckBox);
        checkboxPanel.add(verificationPanel);

        // Weekend Transfer Row
        checkboxPanel.add(new JLabel("Weekend Transfer?", JLabel.RIGHT));
        JPanel weekendPanel = new JPanel();
        weekendTransferYesCheckBox = new JCheckBox("Yes");
        weekendTransferNoCheckBox = new JCheckBox("No", true);
        ButtonGroup weekendGroup = new ButtonGroup();
        weekendGroup.add(weekendTransferYesCheckBox);
        weekendGroup.add(weekendTransferNoCheckBox);
        weekendPanel.add(weekendTransferYesCheckBox);
        weekendPanel.add(weekendTransferNoCheckBox);
        checkboxPanel.add(weekendPanel);

        // Transaction Pending Row
        checkboxPanel.add(new JLabel("Is transaction pending?", JLabel.RIGHT));
        JPanel pendingPanel = new JPanel();
        isTransactionPendingYesCheckBox = new JCheckBox("Yes");
        isTransactionPendingNoCheckBox = new JCheckBox("No", true);
        ButtonGroup pendingGroup = new ButtonGroup();
        pendingGroup.add(isTransactionPendingYesCheckBox);
        pendingGroup.add(isTransactionPendingNoCheckBox);
        pendingPanel.add(isTransactionPendingYesCheckBox);
        pendingPanel.add(isTransactionPendingNoCheckBox);
        checkboxPanel.add(pendingPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addButton = new JButton("Add value");
        calcButton = new JButton("Calculate Probability");
        trainButton = new JButton("Train");
        buttonPanel.add(addButton);
        buttonPanel.add(calcButton);
        buttonPanel.add(trainButton);

        addButton.addActionListener(this::addTransaction);
        calcButton.addActionListener(this::calculateProbability); 

        mainPanel.add(checkboxPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(450, 300);
    }

    private void addTransaction(ActionEvent e) {
        String transactionType = transactionComboBox.getSelectedItem().toString();
        String paymentMethod = paymentTypeComboBox.getSelectedItem().toString();
        String customerVerified = customerVerifiedYesCheckBox.isSelected() ? "Yes" : "No";
        String weekendTransfer = weekendTransferYesCheckBox.isSelected() ? "Yes" : "No";
        String transactionPending = isTransactionPendingYesCheckBox.isSelected() ? "Yes" : "No";

        Transaction transaction = new Transaction(
                transactionType,
                paymentMethod,
                customerVerified,
                weekendTransfer,
                transactionPending
        );

        transactionModel.addTransaction(transaction);
        JOptionPane.showMessageDialog(null, "Data added successfully!");
    }


private void calculateProbability(ActionEvent e) {
    if (trainedFrequencyTable == null) {
        JOptionPane.showMessageDialog(null, "Please train the model first.");
        return;
    }

    String transactionType = transactionComboBox.getSelectedItem().toString();
    String paymentMethod = paymentTypeComboBox.getSelectedItem().toString();
    String customerVerified = customerVerifiedYesCheckBox.isSelected() ? "Yes" : "No";
    String weekendTransfer = weekendTransferYesCheckBox.isSelected() ? "Yes" : "No";

    String permutationKey = String.format("%s,%s,%s,%s",
            transactionType, paymentMethod, customerVerified, weekendTransfer);

    int[] counts = trainedFrequencyTable.getOrDefault(permutationKey, new int[]{0, 0});
    String prediction = counts[0] > counts[1] ? "Yes" : "No";

    JOptionPane.showMessageDialog(null, "Predicted: " + prediction);
}

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TransactionGUI().setVisible(true));
    }
}