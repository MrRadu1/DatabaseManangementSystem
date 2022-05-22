package Presentation;

import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The type Order interface.
 */
public class OrderInterface extends JFrame {

    private final JButton insrtBtn;
    private final JButton deltBtn;
    private final JButton updtBtn;

    private final JButton billBtn;

    private final JTextField idField;

    private final JTextField quantityField;

    private final JComboBox<Integer> myComboBox;
    private final JComboBox<Integer> myComboBox2;
    private final JScrollPane myScrollPane;


    /**
     * Instantiates a new Order interface.
     *
     * @param ParentInterface the parent interface
     */
    public OrderInterface(UserInterface ParentInterface) {


        this.setTitle("Orders");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 900, 700);
        this.getContentPane().setLayout(null);

   


        JLabel quantityLabel = new JLabel("Quantity : ");

        quantityLabel.setBounds(20, 150, 100, 30);
        getContentPane().add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(100, 150, 125, 30);
        getContentPane().add(quantityField);

        myComboBox = new JComboBox<>();
        myComboBox.setBounds(100, 70, 125, 30);
        
        getContentPane().add(myComboBox);


        JLabel idClientLabel = new JLabel("Client ID : ");

        idClientLabel.setBounds(20, 70, 100, 30);
        getContentPane().add(idClientLabel);

        JLabel idProductLabel = new JLabel("Product ID : ");

        idProductLabel.setBounds(20, 110, 100, 30);
        getContentPane().add(idProductLabel);
        
        myComboBox2 = new JComboBox<>();
        myComboBox2.setBounds(110, 110, 125, 30);

        getContentPane().add(myComboBox2);

        JButton backButton = new JButton("Back");

        backButton.setBounds(750, 550, 100, 50);
        backButton.addActionListener(e -> {
            setVisible(false);
            ParentInterface.setVisible(true);
        });
        getContentPane().add(backButton);

        insrtBtn = new JButton("Insert");

        insrtBtn.setBounds(200, 550, 100, 50);
        getContentPane().add(insrtBtn);


        deltBtn = new JButton("Delete");

        deltBtn.setBounds(330, 550, 100, 50);
        getContentPane().add(deltBtn);


        myScrollPane = new JScrollPane();
        myScrollPane.setBounds(250, 70, 600, 400);
        getContentPane().add(myScrollPane);
        
        updtBtn = new JButton("Update");

        updtBtn.setBounds(460, 550, 100, 50);
        getContentPane().add(updtBtn);

        JLabel idLabel = new JLabel("ID : ");

        idLabel.setBounds(20, 30, 100, 30);
        getContentPane().add(idLabel);

        idField = new JTextField();
        idField.setBounds(80, 30, 75, 30);
        getContentPane().add(idField);

        billBtn = new JButton("Generate bill !");

        billBtn.setBounds(20, 350, 200, 50);
        getContentPane().add(billBtn);



    }

    /**
     * Gets id field.
     *
     * @return the id field
     */
    public String getIdField() {
        return idField.getText();
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientID() {
        return (Integer) myComboBox.getSelectedItem();
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProductID() {
        return (Integer) myComboBox2.getSelectedItem();
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public String getQuantity() {
        return quantityField.getText();
    }

    /**
     * Gets combo box.
     *
     * @return the combo box
     */
    public JComboBox getComboBox() {
        return myComboBox;
    }

    /**
     * Gets combo box 2.
     *
     * @return the combo box 2
     */
    public  JComboBox getComboBox2() {
        return myComboBox2;
    }

    /**
     * Insert listener.
     *
     * @param actionListener the action listener
     */
    public void insertListener(ActionListener actionListener)
    {
        insrtBtn.addActionListener(actionListener);
    }

    /**
     * Delete listener.
     *
     * @param actionListener the action listener
     */
    public void deleteListener(ActionListener actionListener)
    {
        deltBtn.addActionListener(actionListener);
    }

    /**
     * Update listener.
     *
     * @param actionListener the action listener
     */
    public void updateListener(ActionListener actionListener)
    {
        updtBtn.addActionListener(actionListener);
    }

    /**
     * Generate listener.
     *
     * @param actionListener the action listener
     */
    public void generateListener(ActionListener actionListener)
    {
        billBtn.addActionListener(actionListener);
    }

    /**
     * Gets my scroll pane.
     *
     * @return the my scroll pane
     */
    public JScrollPane getMyScrollPane() {
        return this.myScrollPane;
    }

}
