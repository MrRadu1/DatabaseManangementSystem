package Presentation;


import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * The type Product interface.
 */
public class ProductInterface extends JFrame {

    private final JButton insrtBtn;
    private final JButton deleteBtn;
    private final JButton updtBtn;

    private final JTextField idF;

    private final JTextField nmF;

    private final JTextField prcF;

    private final JTextField stkF;
    private final JScrollPane scrollP;


    /**
     * Instantiates a new Product interface.
     *
     * @param parentInterface the parent interface
     */
    public ProductInterface(UserInterface parentInterface) {


        this.setTitle("Products");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(499, 149, 899, 699);
        this.getContentPane().setLayout(null);

        
        JLabel nameLabel = new JLabel("Name: ");

        nameLabel.setBounds(20, 70, 100, 30);
        getContentPane().add(nameLabel);

        nmF = new JTextField();
        nmF.setBounds(80, 70, 125, 30);
        getContentPane().add(nmF);

        JLabel priceLabel = new JLabel("Price: ");

        priceLabel.setBounds(20, 110, 100, 30);
        getContentPane().add(priceLabel);

        prcF = new JTextField();
        prcF.setBounds(80, 110, 125, 30);
        getContentPane().add(prcF);

        JLabel stockLabel = new JLabel("Stock: ");

        stockLabel.setBounds(20, 150, 100, 30);
        getContentPane().add(stockLabel);

        stkF = new JTextField();
        stkF.setBounds(80, 150, 125, 30);
        getContentPane().add(stkF);

        JButton backButton = new JButton("Back");

        backButton.setBounds(750, 550, 100, 50);
        backButton.addActionListener(e -> {
            setVisible(false);
            parentInterface.setVisible(true);
        });
        getContentPane().add(backButton);


        insrtBtn = new JButton("Insert");
        insrtBtn.setBounds(200, 550, 100, 50);
        getContentPane().add(insrtBtn);

        JLabel idLabel = new JLabel("ID: ");

        idLabel.setBounds(20, 30, 100, 30);
        getContentPane().add(idLabel);

        idF = new JTextField();
        idF.setBounds(80, 30, 75, 30);
        getContentPane().add(idF);



        scrollP = new JScrollPane();
        scrollP.setBounds(250, 70, 600, 400);
        getContentPane().add(scrollP);

        deleteBtn = new JButton("Delete");

        deleteBtn.setBounds(330, 550, 100, 50);
        getContentPane().add(deleteBtn);
        updtBtn = new JButton("Update");
        updtBtn.setBounds(460, 550, 100, 50);
        getContentPane().add(updtBtn);

    }

    /**
     * Gets id field.
     *
     * @return the id field
     */
    public String getidF() {
        return idF.getText();
    }

    /**
     * Gets name field.
     *
     * @return the name field
     */
    public String getnmF() {
        return nmF.getText();
    }

    /**
     * Gets price field.
     *
     * @return the price field
     */
    public String getprcF() {
        return prcF.getText();
    }

    /**
     * Gets stock field.
     *
     * @return the stock field
     */
    public String getstkF() {
        return stkF.getText();
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
        deleteBtn.addActionListener(actionListener);
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
     * Gets my scroll pane.
     *
     * @return the my scroll pane
     */
    public JScrollPane getscrollP() {
        return this.scrollP;
    }
}
