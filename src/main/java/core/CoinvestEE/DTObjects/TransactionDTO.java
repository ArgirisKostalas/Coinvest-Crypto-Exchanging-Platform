package core.CoinvestEE.DTObjects;

public class TransactionDTO {

    //DTO Members
    private String txAction;
    private double txNumber;
    private String txCoin;

    //DTO Constructors
    public TransactionDTO(){}

    public TransactionDTO(String txAction, double txNumber, String txCoin){
        this.txAction = txAction;
        this.txNumber = txNumber;
        this.txCoin = txCoin;
    }



    //DTO Getters and Setters

    public String getTxAction() {
        return txAction;
    }

    public void setTxAction(String txAction) {
        this.txAction = txAction;
    }

    public double getTxNumber() {
        return txNumber;
    }

    public void setTxNumber(double txNumber) {
        this.txNumber = txNumber;
    }

    public String getTxCoin() {
        return txCoin;
    }

    public void setTxCoin(String txCoin) {
        this.txCoin = txCoin;
    }



}
