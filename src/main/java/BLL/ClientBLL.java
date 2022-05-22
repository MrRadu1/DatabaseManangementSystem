package BLL;

import DAO.ClientDAO;

import model.Client;


import javax.swing.*;


/**
 * The type Client bll.
 */
public class ClientBLL {
    /**
     * The C d.
     */
    ClientDAO cD;
    /**
     * The C v.
     */


    /**
     * Instantiates a new Client bll.
     */
    public ClientBLL() {
        cD = new ClientDAO();
    }

    /**
     * Search id client.
     *
     * @param id the id
     * @return the client
     */
    public Client searchID(int id) {
        return cD.findById(id);
    }

    /**
     * Create table j table.
     *
     * @return the j table
     */
    public JTable createTable() {
        return cD.createTable();
    }

    /**
     * Insert.
     *
     * @param id    the id
     * @param name  the name
     * @param email the email
     * @param pN    the p n
     */
    public void insert(String id, String name, String email, String pN) {
        int idInsert;
        try {
            idInsert=Integer.parseInt(id);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bad input");
            return;
        }
            Client myNewClient = new Client(idInsert, name, email, pN);
            cD.Insert(myNewClient);
}

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(String id) {
        int idD;
        try {
            idD = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        cD.delete(cD.findById(idD));

    }

    /**
     * Update.
     *
     * @param id    the id
     * @param name  the name
     * @param email the email
     * @param pN    the p n
     */
    public void update(String id, String name, String email, String pN) {
        int idU;
        try {
            idU=Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
            Client myNewClient = searchID(idU);
            myNewClient.setEmail(email);
            myNewClient.setName(name);
            myNewClient.setPhoneNumber(pN);
            cD.update(myNewClient);
    }

}
