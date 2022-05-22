package Presentation;

import BLL.ClientBLL;
import BLL.OrderBLL;
import BLL.ProductBLL;
import connection.ConnectionFactory;
import model.Client;
import model.OrderDetails;
import model.Product;


import javax.swing.*;

import java.io.*;
import java.sql.Connection;



/**
 * The type Controller.
 */
public class Controller {
    private final UserInterface userInterface;
    private final ClientInterface clientInterface;
    private final ProductInterface productInterface;
    private final OrderInterface orderInterface;
    private final ClientBLL client;
    private final OrderBLL order;
    private final ProductBLL product;

    /**
     * Instantiates a new Controller.
     */
    public Controller() {
        Connection con = ConnectionFactory.getConnection();
        if(con!=null)
        {
            System.out.println("Connected !");
        }
        userInterface=new UserInterface();
        clientInterface = new ClientInterface(userInterface);
        productInterface=new ProductInterface(userInterface);
        orderInterface= new OrderInterface(userInterface);
        client=new ClientBLL();
        order=new OrderBLL();
        product=new ProductBLL();
        userInterface.setVisible(true);
        initializeUserButtons();
        initializeClientButtons();
        initializeProductButtons();
        initializeOrderButtons();
    }

    /**
     * Initialize user interface buttons.
     */
    public void initializeUserButtons()
    {
        userInterface.ClientListener(e -> {
            userInterface.setVisible(false);
            updateClients();
        });
        userInterface.ProductListener(e -> {
            userInterface.setVisible(false);
            updateProducts();
        });
        userInterface.OrderListener(e -> {
            userInterface.setVisible(false);
            int[] items;
            int[] items2;
            orderInterface.getComboBox().removeAllItems();
            orderInterface.getComboBox2().removeAllItems();
            items = order.getClientsID();
            items2 = order.getProductsID();
            for (int item : items) {
                orderInterface.getComboBox().addItem(item);
            }
            for (int j : items2) {
                orderInterface.getComboBox2().addItem(j);
            }
            updateOrders();
        });
    }

    /**
     * Initialize client buttons.
     */
    public void initializeClientButtons() {
        clientInterface.insertListener(e -> {
            client.insert(clientInterface.getidTxt(), clientInterface.getnameTxt(), clientInterface.getemailTxt(), clientInterface.getphTxt());
            updateClients();
        });
        clientInterface.deleteListener(e -> {
            client.delete(clientInterface.getidTxt());
            updateClients();
        });
        clientInterface.updateListener(e-> {
            client.update(clientInterface.getidTxt(), clientInterface.getnameTxt(), clientInterface.getemailTxt(), clientInterface.getphTxt());
            updateClients();
        });
    }

    /**
     * Initialize product buttons.
     */
    public void initializeProductButtons() {
        productInterface.insertListener(e -> {
            product.insert(productInterface.getidF(), productInterface.getnmF(), productInterface.getprcF(), productInterface.getstkF());
            updateProducts();
        });
        productInterface.deleteListener(e -> {
            product.delete(productInterface.getidF());
            updateProducts();
        });
        productInterface.updateListener(e-> {
            product.update(productInterface.getidF(), productInterface.getnmF(), productInterface.getprcF(), productInterface.getstkF());
            updateProducts();
        });
    }

    /**
     * Initialize order buttons.
     */
    public void initializeOrderButtons() {
        orderInterface.insertListener(e -> {
            order.insert(orderInterface.getIdField(), orderInterface.getQuantity(), orderInterface.getProductID(), orderInterface.getClientID());
            updateOrders();
        });
        orderInterface.deleteListener(e -> {
            order.delete(orderInterface.getIdField());
            updateOrders();
        });
        orderInterface.updateListener(e -> {
            order.update(orderInterface.getIdField(), orderInterface.getQuantity());
            updateOrders();
        });

        orderInterface.generateListener(e -> {
            File statText = new File("bills.txt");
            FileOutputStream is = null;
            try {
                is = new FileOutputStream(statText);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            assert is != null;
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);
            StringBuilder builder = new StringBuilder();

                OrderDetails currentOrder = order.findByID(Integer.parseInt(orderInterface.getIdField()));
                builder.append("Order : ").append(currentOrder.getId()).append("\n");
                int cID = currentOrder.getClientID();
                int pID = currentOrder.getProductID();
                Client curClient = client.searchID(cID);
                if (curClient != null)
                    builder.append("Name : ").append(curClient.getName()).append("\n");
                Product curProduct = product.searchID(pID);
                if (curProduct != null)
                    builder.append("Product : ").append(curProduct.getName()).append("\n");
                builder.append("Quantity : ").append(currentOrder.getQuantity()).append("\n");
            assert curProduct != null;
            builder.append("Final price : ").append(currentOrder.getQuantity() * curProduct.getPrice()).append("\n\n");
                try {
                    w.write(builder.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                builder.delete(0, builder.length());
            try {
                w.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    /**
     * Update clients.
     */
    public void updateClients(){
        JTable x = client.createTable();
        clientInterface.getscrollP().setViewportView(x);
        clientInterface.setVisible(true);
    }

    /**
     * Update orders.
     */
    public void updateOrders() {
        JTable x = order.createTable();
        orderInterface.getMyScrollPane().setViewportView(x);
        orderInterface.setVisible(true);
    }

    /**
     * Update products.
     */
    public void updateProducts() {
        JTable x = product.createTable();
        productInterface.getscrollP().setViewportView(x);
        productInterface.setVisible(true);
    }
}
