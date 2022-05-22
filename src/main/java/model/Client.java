package model;

/**
 * The type Client.
 */
public class Client {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    /**
     * Instantiates a new Client.
     */
    public  Client() {

    }

    /**
     * Instantiates a new Client.
     *
     * @param clientID    the client id
     * @param name        the name
     * @param email       the email
     * @param phoneNumber the phone number
     */
    public Client(int clientID, String name, String email, String phoneNumber) {
        this.id = clientID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name +  ", email=" + email + ", age=" + phoneNumber
                + "]";
    }



}
