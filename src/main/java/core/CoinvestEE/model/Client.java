package core.CoinvestEE.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Client {

    //Class Constructor


    public Client() {
    }

    public Client(String clientFirstName, String clientLastName, String clientPaymentMethod, String clientUsername, LocalDate clientAge, String clientEmail, String clientPassword, String Role) {


        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPaymentMethod = clientPaymentMethod;
        this.clientUsername = clientUsername;
        this.clientAge = clientAge;
        this.clientPassword = clientPassword;
        this.clientEmail=clientEmail;
        this.Role = Role;
        this.enabled = 1;
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientID;
    private String clientFirstName;
    private String clientLastName;
    private String clientPaymentMethod;
    private String clientUsername;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate clientAge;
    private String clientPassword;


    private String clientEmail;



    private int enabled;


    private String Role;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Wallet wallet;




    //Class Getters and Setters

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }



    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientPaymentMethod() {
        return clientPaymentMethod;
    }

    public void setClientPaymentMethod(String clientPaymentMethod) {
        this.clientPaymentMethod = clientPaymentMethod;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public LocalDate getClientAge() {
        return clientAge;
    }

    public void setClientAge(LocalDate clientAge) {
        this.clientAge = clientAge;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }



    //toString() method
    @Override
    public String toString() {
        return "Client{" +

                ", clientID=" + clientID +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientPaymentMethod='" + clientPaymentMethod + '\'' +
                ", clientUsername='" + clientUsername + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientAge=" + clientAge +
                '}';
    }

    //equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientID == client.clientID;
    }

    //hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(clientID);
    }
}
