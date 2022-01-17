package core.CoinvestEE.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;



@Entity
@Table(name = "transactions")
public class TransactionLog {
    //Class Constructor


    public TransactionLog() {
    }

    public TransactionLog( String txType, Date txDate, double txFee, Long clientID, double txAmount,double txFunds, String txSrcCoin, String txDestCoin) {

        this.txType = txType;
        this.txDate = txDate;
        this.txFee = txFee;
        this.clientID = clientID;
        this.txAmount = txAmount;
        this.txFunds = txFunds;
        this.txSrcCoin = txSrcCoin;
        this.txDestCoin = txDestCoin;
    }


    //Class Members
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txID;


    private String txType;
    private Date txDate;
    private double txFee;
    private double txAmount;
    private double txFunds;
    private String txSrcCoin;
    private String txDestCoin;
    private Long clientID;

    //Class Getters and Setters

    public Long getTxID() {
        return txID;
    }

    public void setTxID(Long txID) {
        this.txID = txID;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public double getTxFee() {
        return txFee;
    }

    public void setTxFee(double txFee) {
        this.txFee = txFee;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public double getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(double txAmount) {
        this.txAmount = txAmount;
    }

    public String getTxSrcCoin() {
        return txSrcCoin;
    }

    public void setTxSrcCoin(String txSrcCoin) {
        this.txSrcCoin = txSrcCoin;
    }

    public String getTxDestCoin() {
        return txDestCoin;
    }

    public void setTxDestCoin(String txDestCoin) {
        this.txDestCoin = txDestCoin;
    }

    public double getTxFunds() {
        return txFunds;
    }

    public void setTxFunds(double txFunds) {
        this.txFunds = txFunds;
    }

    //toString() method
    @Override
    public String toString() {
        return "TransactionLog{" +
                "txID=" + txID +
                ", txType='" + txType + '\'' +
                ", txDate=" + txDate +
                ", txFee=" + txFee +
                ", clientID=" + clientID +
                '}';
    }

    //equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionLog that = (TransactionLog) o;
        return txID == that.txID;
    }

    //hasCode() method
    @Override
    public int hashCode() {
        return Objects.hash(txID);
    }
}
