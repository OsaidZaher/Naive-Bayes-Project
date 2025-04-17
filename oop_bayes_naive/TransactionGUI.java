import javax.swing.*;
import java.awt.*;

public class TransactionGUI extends JFrame {

    JComboBox<String> transactionComboBox, paymentTypeComboBox;
    JCheckBox customerVerifiedYesCheckBox, customerVerifiedNoCheckBox;
    JCheckBox weekendTransactionYesCheckBox, weekendTransactionNoCheckBox;
    JButton addButton, calcButton, trainButton;
    
    public TransactionGUI() {
        super("Transaction GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);

        JPanel CheckBoxPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
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
        trainButton = new JButton("Train");
        buttonPanel.add(addButton);
        buttonPanel.add(calcButton);
        buttonPanel.add(trainButton);
        
        add(CheckBoxPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        
    }

    public static void main(String[] args) {
        // auto generated using intellisence no idea what this does 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TransactionGUI().setVisible(true);
            }
        });
    }
}