package core.CoinvestEE.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class Wallet {


    //We need this declaration in order to make the Primary Key of the client table
    //a foreign key at this table. Plus, the application works in such way that the wallet's Primary ID
    //is the corresponding primary client ID
    @JoinColumn(name = "client_id")
    @OneToOne(orphanRemoval = true)
    private Client client;


    //Class Constructor


    public Wallet() {
    }

    public Wallet(Long ID, double btcBalance, double ethBalance, double adaBalance, double moneroBalance, double eurBalance, double usdBalance) {
        this.ID= ID;
        this.btcBalance = btcBalance;
        this.ethBalance = ethBalance;
        this.adaBalance = adaBalance;
        this.moneroBalance = moneroBalance;
        this.eurBalance = eurBalance;
        this.usdBalance = usdBalance;
    }

    //Class members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long ID;



    private double btcBalance;
    private double ethBalance;
    private double adaBalance;
    private double moneroBalance;
    private double eurBalance;
    private double usdBalance;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }



    //Class Getters and Setters


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public double getBtcBalance() {
        return btcBalance;
    }

    public void setBtcBalance(double btcBalance) {
        this.btcBalance = btcBalance;
    }

    public double getEthBalance() {
        return ethBalance;
    }

    public void setEthBalance(double ethBalance) {
        this.ethBalance = ethBalance;
    }

    public double getAdaBalance() {
        return adaBalance;
    }

    public void setAdaBalance(double adaBalance) {
        this.adaBalance = adaBalance;
    }

    public double getMoneroBalance() {
        return moneroBalance;
    }

    public void setMoneroBalance(double moneroBalance) {
        this.moneroBalance = moneroBalance;
    }

    public double getEurBalance() {
        return eurBalance;
    }

    public void setEurBalance(double eurBalance) {
        this.eurBalance = eurBalance;
    }

    public double getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(double usdBalance) {
        this.usdBalance = usdBalance;
    }


    //toString() method
    @Override
    public String toString() {
        return "Wallet{" +
                "ID=" + ID +

                ", btcBalance=" + btcBalance +
                ", ethBalance=" + ethBalance +
                ", adaBalance=" + adaBalance +
                ", moneroBalance=" + moneroBalance +
                ", eurBalance=" + eurBalance +
                ", usdBalance=" + usdBalance +
                '}';
    }

    //equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return ID == wallet.ID;
    }

    //hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
