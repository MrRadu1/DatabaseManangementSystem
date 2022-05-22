package BLL;

import DAO.ClientDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import model.Client;
import model.OrderDetails;
import model.Product;

import javax.swing.*;
import java.util.List;

/**
 * The type Order bll.
 */
public class OrderBLL {
    private final OrderDAO oD;

    /**
     * Instantiates a new Order bll.
     */
    public OrderBLL() {
        oD = new OrderDAO();
    }

    /**
     * Gets orders list.
     *
     * @return the orders list
     */
    public List<OrderDetails> getOrdersList() {
        return oD.findAll();
    }

    /**
     * Get clients id int [ ].
     *
     * @return the int [ ]
     */
    public int[] getClientsID() {
        int[] result;
        ClientDAO cD= new ClientDAO();
        List<Client> lista = cD.findAll();
        result = new int[lista.size()];
        int i=0;
        for (Client c : lista) {
            result[i]= c.getId();
            i++;
        }
        return result;
    }

    /**
     * Get products id int [ ].
     *
     * @return the int [ ]
     */
    public int[] getProductsID() {
        int[] result;
        ProductDAO cD= new ProductDAO();
        List<Product> lista = cD.findAll();
        result = new int[lista.size()];
        int i=0;
        for (Product c : lista) {
            result[i]= c.getId();
            i++;
        }
        return result;
    }


    /**
     * Create table j table.
     *
     * @return the j table
     */
    public JTable createTable() {
        return oD.createTable();
    }

    /**
     * Insert.
     *
     * @param id       the id
     * @param quantity the quantity
     * @param pID      the p id
     * @param cID      the c id
     */
    public void insert(String id, String quantity, int pID, int cID) {
        int idInsert , quan ;
        try {
            idInsert=Integer.parseInt(id);
            quan=Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Bad input");
            return;
        }
        ProductDAO prDao = new ProductDAO();
        Product newP = prDao.findById(pID);
        if (quan <= newP.getStock()) {
            newP.setStock(newP.getStock() - quan);
            prDao.update(newP);
            OrderDetails newO = new OrderDetails(idInsert,cID,pID,quan);
            oD.Insert(newO);
        } else {
            JOptionPane.showMessageDialog(null, "Big quan");
        }
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
        OrderDetails oDelete = oD.findById(idD);
        if (oDelete!=null) {
            int productID = oDelete.getProductID();
            int quantity = oDelete.getQuantity();
            ProductDAO prDAO = new ProductDAO();
            Product myProduct = prDAO.findById(productID);
            //System.out.println(myProduct.getProductID() + " " + myProduct.getStock());
            int productStock = myProduct.getStock();
            myProduct.setStock(productStock + quantity);
            prDAO.update(myProduct);
            oD.delete(oDelete);
        } else
            JOptionPane.showMessageDialog(null, "ERROR when deleting order. Order inexistent! ");
    }

    /**
     * Update.
     *
     * @param id       the id
     * @param quantity the quantity
     */
    public void update(String id, String quantity) {
        int idU, quan;
        try {
            idU=Integer.parseInt(id);
            quan=Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        OrderDetails orderToBeUpdated = oD.findById(idU);
        int productID = orderToBeUpdated.getProductID();
        int clientID = orderToBeUpdated.getClientID();
        int quantityU = orderToBeUpdated.getQuantity();
        ProductDAO prDAO = new ProductDAO();
        Product myProduct = prDAO.findById(productID);
        int productStock = myProduct.getStock();
        if (productStock + quantityU-quan>=0) {
            myProduct.setStock(productStock + quantityU - quan);
            prDAO.update(myProduct);
            OrderDetails myOrder = new OrderDetails(idU, clientID, productID, quan);
            oD.update(myOrder);
        }else {
            JOptionPane.showMessageDialog(null, "Big quan");
        }
    }
    public OrderDetails findByID (int id) {
        return oD.findById(id);
    }
}
