package BLL;

import DAO.ProductDAO;
import model.Product;

import javax.swing.*;


/**
 * The type Product bll.
 */
public class ProductBLL {

    /**
     * The P d.
     */
    ProductDAO pD;


    /**
     * Instantiates a new Product bll.
     */
    public ProductBLL() {
            pD = new ProductDAO();

        }

    /**
     * Search id product.
     *
     * @param id the id
     * @return the product
     */
    public Product searchID(int id) {
            return pD.findById(id);
        }

    /**
     * Create table j table.
     *
     * @return the j table
     */
    public JTable createTable() {
            return pD.createTable();
        }

    /**
     * Insert.
     *
     * @param id           the id
     * @param name         the name
     * @param productPrice the product price
     * @param productStock the product stock
     */
    public void insert(String id, String name, String productPrice, String productStock) {
            int idInsert , price, stock;
            try {
                idInsert=Integer.parseInt(id);
                price=Integer.parseInt(productPrice);
                stock=Integer.parseInt(productStock);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Bad input");
                return;
            }
                Product myNewPr = new Product(idInsert, name, price,stock);
                pD.Insert(myNewPr);
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
            Product pDelete = pD.findById(idD);
            if (pDelete!=null) {
                pD.delete(pDelete);
            } else
                JOptionPane.showMessageDialog(null, "ERROR when deleting order. Order inexistent! ");
        }

    /**
     * Update.
     *
     * @param id           the id
     * @param name         the name
     * @param productPrice the product price
     * @param productStock the product stock
     */
    public void update(String id, String name, String productPrice, String productStock) {
            int idU , price, stock;
            try {
                idU=Integer.parseInt(id);
                price=Integer.parseInt(productPrice);
                stock=Integer.parseInt(productStock);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }
                Product newP = pD.findById(idU);
                newP.setName(name);
                newP.setStock(stock);
                newP.setPrice(price);
                pD.update(newP);
        }

    }


