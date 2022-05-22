package Presentation;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The type User interface.
 */
public class UserInterface extends JFrame {



    private final JButton tableBtn;
    private final JButton productBtn;
    private final JButton ordertBtn;


    /**
     * Instantiates a new User interface.
     */
    public UserInterface() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 100, 899, 599);
        this.getContentPane().setLayout(null);


        ordertBtn = new JButton(" Order Table");
        ordertBtn.setBounds(299,399,249,49);
        getContentPane().add(ordertBtn);
        
        tableBtn = new JButton(" Client Table");

        tableBtn.setBounds(99,324,249,49);
        getContentPane().add(tableBtn);

        productBtn = new JButton(" Product Table");

        productBtn.setBounds(499,324,249,49);
        getContentPane().add(productBtn);
        

    }

    /**
     * Add access client table button action listener.
     *
     * @param actionListener the action listener
     */
    public void ClientListener(ActionListener actionListener)
    {
        tableBtn.addActionListener(actionListener);
    }

    /**
     * Add access product table button action listener.
     *
     * @param actionListener the action listener
     */
    public void ProductListener(ActionListener actionListener)
    {
        productBtn.addActionListener(actionListener);
    }

    /**
     * Add access order table button action listener.
     *
     * @param actionListener the action listener
     */
    public void OrderListener(ActionListener actionListener)
    {
        ordertBtn.addActionListener(actionListener);
    }

}

