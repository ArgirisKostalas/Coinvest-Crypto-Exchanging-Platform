package core.CoinvestEE.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;



@Entity
public class Rates {
    //Class Constructors


    public Rates() {
    }

    public Rates(Date Day, double bitcoinRate, double ethereumRate, double moneroRate, double adaRate, double eurRate, double usdRate) {
        this.day = Day;
        this.bitcoinRate = bitcoinRate;
        this.ethereumRate = ethereumRate;
        this.moneroRate = moneroRate;
        this.adaRate = adaRate;
        this.eurRate = eurRate;
        this.usdRate = usdRate;
    }



    //Class Members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateID;

    private Date day;

    private double bitcoinRate;
    private double ethereumRate;
    private double moneroRate;
    private double adaRate;
    private double eurRate;
    private double usdRate;

    //Class Getters and Setters


    public Long getRateID() {
        return rateID;
    }

    public void setRateID(Long rateID) {
        this.rateID = rateID;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public double getBitcoinRate() {
        return bitcoinRate;
    }

    public void setBitcoinRate(double bitcoinRate) {
        this.bitcoinRate = bitcoinRate;
    }

    public double getEthereumRate() {
        return ethereumRate;
    }

    public void setEthereumRate(double ethereumRate) {
        this.ethereumRate = ethereumRate;
    }

    public double getMoneroRate() {
        return moneroRate;
    }

    public void setMoneroRate(double moneroRate) {
        this.moneroRate = moneroRate;
    }

    public double getAdaRate() {
        return adaRate;
    }

    public void setAdaRate(double adaRate) {
        this.adaRate = adaRate;
    }

    public double getEurRate() {
        return eurRate;
    }

    public void setEurRate(double eurRate) {
        this.eurRate = eurRate;
    }

    public double getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(double usdRate) {
        this.usdRate = usdRate;
    }

    //toString() Method
    @Override
    public String toString() {
        return "Rates{" +
                "day=" + day +
                ", bitcoinRate=" + bitcoinRate +
                ", ethereumRate=" + ethereumRate +
                ", moneroRate=" + moneroRate +
                ", adaRate=" + adaRate +
                ", eurRate=" + eurRate +
                ", usdRate=" + usdRate +
                '}';
    }

    //equals() Method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rates rates = (Rates) o;
        return Objects.equals(day, rates.day);
    }

    //hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(day);
    }
}
