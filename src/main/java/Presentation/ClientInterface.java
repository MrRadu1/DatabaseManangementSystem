package Presentation;

import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * The type Client interface.
 */
public class ClientInterface extends JFrame {

    private final JButton insrtBtn;
    private final JButton dltBtn;
    private final JButton uptBtn;

    private final JTextField idTxt;

    private final JTextField nameTxt;

    private final JTextField emailTxt;

    private final JTextField phTxt;
    private final JScrollPane scrollP;

    /**
     * Instantiates a new Client interface.
     *
     * @param parentInterface the parent interface
     */
    public ClientInterface(UserInterface parentInterface) {

        this.setTitle("Clients");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(300, 100, 899, 699);
        this.getContentPane().setLayout(null);

        // use a bigger font



        JLabel nameLabel = new JLabel("Name: ");
        
        nameLabel.setBounds(19, 69, 99, 29);
        getContentPane().add(nameLabel);

        nameTxt = new JTextField();
        nameTxt.setBounds(79, 69, 124, 29);
        getContentPane().add(nameTxt);

        JLabel emailLabel = new JLabel("Email: ");
        
        emailLabel.setBounds(19, 109, 99, 29);
        getContentPane().add(emailLabel);

        JLabel idLabel = new JLabel("ID: ");

        idLabel.setBounds(19, 29, 99, 29);
        getContentPane().add(idLabel);

        idTxt = new JTextField();
        idTxt.setBounds(79, 29, 74, 29);
        getContentPane().add(idTxt);

        emailTxt = new JTextField();
        emailTxt.setBounds(79, 109, 124, 29);
        getContentPane().add(emailTxt);

        JLabel phoneLabel = new JLabel("Phone: ");
        
        phoneLabel.setBounds(19, 149, 99, 29);
        getContentPane().add(phoneLabel);

        phTxt = new JTextField();
        phTxt.setBounds(79, 149, 124, 29);
        getContentPane().add(phTxt);

        // BUTTONS :

        JButton backButton = new JButton("Back");
        
        backButton.setBounds(749, 549, 99, 49);
        backButton.addActionListener(e -> {
            setVisible(false);
            parentInterface.setVisible(true);
        });
        getContentPane().add(backButton);

        uptBtn = new JButton("Update");

        uptBtn.setBounds(459, 549, 99, 49);
        getContentPane().add(uptBtn);
        scrollP = new JScrollPane();
        scrollP.setBounds(249, 69, 599, 399);
        getContentPane().add(scrollP);




        dltBtn = new JButton("Delete");
        
        dltBtn.setBounds(329, 549, 99, 49);
        getContentPane().add(dltBtn);

        insrtBtn = new JButton("Insert");

        insrtBtn.setBounds(199, 549, 99, 49);
        getContentPane().add(insrtBtn);

    }

    /**
     * Gets id field.
     *
     * @return the id field
     */
    public String getidTxt() {
        return idTxt.getText();
    }

    /**
     * Gets name field.
     *
     * @return the name field
     */
    public String getnameTxt() {
        return nameTxt.getText();
    }

    /**
     * Gets email field.
     *
     * @return the email field
     */
    public String getemailTxt() {
        return emailTxt.getText();
    }

    /**
     * Gets phone field.
     *
     * @return the phone field
     */
    public String getphTxt() {
        return phTxt.getText();
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
        dltBtn.addActionListener(actionListener);
    }

    /**
     * Update listener.
     *
     * @param actionListener the action listener
     */
    public void updateListener(ActionListener actionListener)
    {
        uptBtn.addActionListener(actionListener);
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
