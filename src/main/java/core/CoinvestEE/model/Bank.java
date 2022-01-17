package core.CoinvestEE.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;



@Entity
public class Bank {
    //Class Constructor


    public Bank() {
    }

    public Bank(double usdBalance, double eurBalance, double btcBalance, double ethBalance, double adaBalance, double moneroBalance) {
        this.usdBalance = usdBalance;
        this.eurBalance = eurBalance;
        this.btcBalance = btcBalance;
        this.ethBalance = ethBalance;
        this.adaBalance = adaBalance;
        this.moneroBalance = moneroBalance;
    }


    //Class members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankID;

    private double usdBalance;
    private double eurBalance;
    private double btcBalance;
    private double ethBalance;
    private double adaBalance;
    private double moneroBalance;

    //Class Getters and Setters

    public Long getBankID() {
        return bankID;
    }

    public void setBankID(Long bankID) {
        this.bankID = bankID;
    }

    public double getUsdBalance() {
        return usdBalance;
    }

    public void setUsdBalance(double usdBalance) {
        this.usdBalance = usdBalance;
    }

    public double getEurBalance() {
        return eurBalance;
    }

    public void setEurBalance(double eurBalance) {
        this.eurBalance = eurBalance;
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


    //toString() method
    @Override
    public String toString() {
        return "Bank{" +
                "bankID=" + bankID +
                ", usdBalance=" + usdBalance +
                ", eurBalance=" + eurBalance +
                ", btcBalance=" + btcBalance +
                ", ethBalance=" + ethBalance +
                ", adaBalance=" + adaBalance +
                ", moneroBalance=" + moneroBalance +
                '}';
    }

    //equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return bankID == bank.bankID;
    }

    //hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(bankID);
    }
}
