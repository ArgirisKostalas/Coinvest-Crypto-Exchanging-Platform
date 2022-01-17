package core.CoinvestEE.DTObjects;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;



//This class is used to transfer our data from the registration form to our controller
public class ClientRegistrationDto {

    private String clientFirstName;
    private String clientLastName;
    private String clientPaymentMethod;
    private String clientUsername;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate clientAge;
    private String clientPassword;
    private String clientMatchingPassword;
    private String clientEmail;


   public ClientRegistrationDto(){

   }

    public ClientRegistrationDto(String clientFirstName, String clientLastName, String clientPaymentMethod, String clientUsername, LocalDate clientAge, String clientPassword, String clientMatchingPassword, String clientEmail) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPaymentMethod = clientPaymentMethod;
        this.clientUsername = clientUsername;
        this.clientAge = clientAge;
        this.clientPassword = clientPassword;
        this.clientMatchingPassword = clientMatchingPassword;
        this.clientEmail = clientEmail;
    }

    //Getters and Setters
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

    public String getClientMatchingPassword() {
        return clientMatchingPassword;
    }

    public void setClientMatchingPassword(String clientMatchingPassword) {
        this.clientMatchingPassword = clientMatchingPassword;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
