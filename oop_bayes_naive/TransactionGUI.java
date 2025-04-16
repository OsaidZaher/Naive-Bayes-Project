import javax.swing.*;
import java.awt.*;

public class TransactionGUI extends JFrame {

    JComboBox<String> transactionComboBox, paymentTypeComboBox;
    JCheckBox customerVerifiedYesCheckBox, customerVerifiedNoCheckBox;
    JCheckBox weekendTransactionYesCheckBox, weekendTransactionNoCheckBox;
    JButton addButton, calcButton;
    
    public TransactionGUI() {
        super("Transaction GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLayout(new BorderLayout());

        JPanel CheckBoxPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        CheckBoxPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        CheckBoxPanel.add(new JLabel("Transaction Type:", JLabel.RIGHT));
        String[] transactionOptions = {"Instore", "Online"};
        transactionComboBox = new JComboBox<>(transactionOptions);
        CheckBoxPanel.add(transactionComboBox);
        
        CheckBoxPanel.add(new JLabel("Payment Method:", JLabel.RIGHT));
        String[] paymentMethod = {"BankTranser", "Card"};
        paymentTypeComboBox = new JComboBox<>(paymentMethod);
        CheckBoxPanel.add(paymentTypeComboBox);
        
        CheckBoxPanel.add(new JLabel("User Verified?", JLabel.RIGHT));
        
        JPanel customerVerifiedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        customerVerifiedYesCheckBox = new JCheckBox("Yes");
        customerVerifiedNoCheckBox = new JCheckBox("No");
        
        ButtonGroup customerVerifiedGroup = new ButtonGroup();
        customerVerifiedGroup.add(customerVerifiedYesCheckBox);
        customerVerifiedGroup.add(customerVerifiedNoCheckBox);
        
        customerVerifiedPanel.add(customerVerifiedYesCheckBox);
        customerVerifiedPanel.add(customerVerifiedNoCheckBox);
        CheckBoxPanel.add(customerVerifiedPanel);
        
        CheckBoxPanel.add(new JLabel("Weekend Transfer?", JLabel.RIGHT));
        
        JPanel weekendTransactionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        weekendTransactionYesCheckBox = new JCheckBox("Yes");
        weekendTransactionNoCheckBox = new JCheckBox("No");
        
        ButtonGroup weekendTransactionGroup = new ButtonGroup();
        weekendTransactionGroup.add(weekendTransactionYesCheckBox);
        weekendTransactionGroup.add(weekendTransactionNoCheckBox);
        
        weekendTransactionPanel.add(weekendTransactionYesCheckBox);
        weekendTransactionPanel.add(weekendTransactionNoCheckBox);
        CheckBoxPanel.add(weekendTransactionPanel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addButton = new JButton("Add value");
        calcButton = new JButton("Calculate Probability");
        buttonPanel.add(addButton);
        buttonPanel.add(calcButton);
        
        add(CheckBoxPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TransactionGUI().setVisible(true);
            }
        });
    }
}