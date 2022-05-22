package model;


/**
 * The type Order details.
 */
public class OrderDetails {

    private int id;
    private int clientID;
    private int productID;
    private int quantity;

    /**
     * Instantiates a new Order details.
     */
    public OrderDetails() {

    }

    /**
     * Instantiates a new Order details.
     *
     * @param orderID   the order id
     * @param clientID  the client id
     * @param productID the product id
     * @param quantity  the quantity
     */
    public OrderDetails(int orderID,int clientID, int productID, int quantity)
    {
        super();
        this.id=orderID;
        this.clientID=clientID;
        this.productID=productID;
        this.quantity=quantity;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Sets client id.
     *
     * @param clientID the client id
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Sets product id.
     *
     * @param productID the product id
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Student [id=" + id + ", name=" + clientID +  ", email=" + productID + ", age=" + quantity
                + "]";
    }


}
