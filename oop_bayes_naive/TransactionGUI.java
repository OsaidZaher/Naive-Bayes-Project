import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TransactionGUI extends JFrame {


    private JComboBox<String> transactionComboBox, paymentTypeComboBox;
    private JCheckBox customerVerifiedYesCheckBox, customerVerifiedNoCheckBox;
    private JCheckBox weekendTransferYesCheckBox, weekendTransferNoCheckBox;
    private JCheckBox isTransactionPendingYesCheckBox, isTransactionPendingNoCheckBox;
    private JButton addButton, calcButton, trainButton;
    
    private TransactionModel transactionModel;
    private TrainTest modelTrainerTester;

    public TransactionGUI() {
        super("Transaction Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // instnaces of model and train test class
        transactionModel = TransactionModel.getTransactionModel("Data.csv");
        modelTrainerTester = new TrainTest();

        //creating 2 panels for better gui uex
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
        verificationPanel.add(customerVerifiedYesCheckBox);
        verificationPanel.add(customerVerifiedNoCheckBox);
        checkboxPanel.add(verificationPanel);

        // Weekend Transfer Row
        checkboxPanel.add(new JLabel("Weekend Transfer?", JLabel.RIGHT));
        JPanel weekendPanel = new JPanel();
        weekendTransferYesCheckBox = new JCheckBox("Yes");
        weekendTransferNoCheckBox = new JCheckBox("No", true);
        weekendPanel.add(weekendTransferYesCheckBox);
        weekendPanel.add(weekendTransferNoCheckBox);
        checkboxPanel.add(weekendPanel);

        // Transaction Pending Row
        checkboxPanel.add(new JLabel("Is transaction pending?", JLabel.RIGHT));
        JPanel pendingPanel = new JPanel();
        isTransactionPendingYesCheckBox = new JCheckBox("Yes");
        isTransactionPendingNoCheckBox = new JCheckBox("No", true);
   
        pendingPanel.add(isTransactionPendingYesCheckBox);
        pendingPanel.add(isTransactionPendingNoCheckBox);
        checkboxPanel.add(pendingPanel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addButton = new JButton("Add Transaction");
        calcButton = new JButton("Predict");
        trainButton = new JButton("Train Model");
        buttonPanel.add(addButton);
        buttonPanel.add(calcButton);
        buttonPanel.add(trainButton);


        // action listeners lambda referene 
        calcButton.addActionListener(this::predictTransaction);

        // other ways to write it
        addButton.addActionListener(e -> this.addTransactionAction(e));

        //other other way to write it
        trainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trainAndEvaluateAction(e);
            }
        });


        mainPanel.add(checkboxPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setSize(400, 300);
    }

    
    private void addTransactionAction(ActionEvent e) {
        String transactionType = transactionComboBox.getSelectedItem().toString();
        String paymentMethod = paymentTypeComboBox.getSelectedItem().toString();

        // tertinary operators, if checkbox is selected the string value is "Yes" else "No"
        String customerVerified = customerVerifiedYesCheckBox.isSelected() ? "Yes" : "No";
        String weekendTransfer = weekendTransferYesCheckBox.isSelected() ? "Yes" : "No";
        String transactionPending = isTransactionPendingYesCheckBox.isSelected() ? "Yes" : "No";

        // new transaction instance with gui input
        Transaction transaction = new Transaction(
                transactionType,
                paymentMethod,
                customerVerified,
                weekendTransfer,
                transactionPending
        );

        transactionModel.addTransaction(transaction);
        JOptionPane.showMessageDialog(this, "Transaction added successfully!");
    }

    private void predictTransaction(ActionEvent e) {

        // ensure model trained
        if (modelTrainerTester.getTrainedFrequencyTable() == null) {
            JOptionPane.showMessageDialog(this, "Please train the model first.");
            return;
        }

        String transactionType = transactionComboBox.getSelectedItem().toString();
        String paymentMethod = paymentTypeComboBox.getSelectedItem().toString();

        // tertinary operators, if checkbox is selected the string value is "Yes" else "No"
        String customerVerified = customerVerifiedYesCheckBox.isSelected() ? "Yes" : "No";
        String weekendTransfer = weekendTransferYesCheckBox.isSelected() ? "Yes" : "No";

        // calling make prediction method
        String prediction = modelTrainerTester.makePrediction(
            transactionType, paymentMethod, customerVerified, weekendTransfer);
        
        System.out.println("\n--- New Prediction ---");
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Customer Verified: " + customerVerified);
        System.out.println("Weekend Transfer: " + weekendTransfer);
        System.out.println("Prediction: Transaction is " + 
                         (prediction.equals("Yes") ? " pending" : "not pending"));
        
        // Display the prediction
        JOptionPane.showMessageDialog(this, 
        // tertinary operators for result display
            "Prediction: Transaction will " + (prediction.equals("Yes") ? "be pending" : "not be pending"),
            "Prediction Result", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void trainAndEvaluateAction(ActionEvent e) {
        List<Transaction> allTransactions = transactionModel.getTransactions();

        // Train and evaluate method call
        modelTrainerTester.trainAndEvaluate(allTransactions);
        
        JOptionPane.showMessageDialog(this, 
            "Model has been trained, \nnow you can predict.\nCheck console for evaluation report.");
    }

    public static void main(String[] args) {
        // auto created from intellisence but this is the creating new instance of the gui
        SwingUtilities.invokeLater(() -> new TransactionGUI().setVisible(true));
    }
}