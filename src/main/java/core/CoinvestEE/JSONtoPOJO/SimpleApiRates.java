package core.CoinvestEE.JSONtoPOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "BTC",
        "ETH",
        "ADA",
        "XMR"
})
public class SimpleApiRates {

    public SimpleApiRates(){

    }

    @JsonProperty("BTC")
    private Object BTC;

    @JsonProperty("ETH")
    private Object ETH;

    @JsonProperty("ADA")
    private Object ADA;

    @JsonProperty("XMR")
    private Object XMR;

    private double EUR;

    private String USD;


    @JsonProperty("XMR")
    public Object getXMR() {
        return XMR;
    }

    @JsonProperty("XMR")
    public void setXMR(Object XMR) {
        this.XMR = XMR;
    }




    public double getEUR() {
        return EUR;
    }


    public void setEUR(double EUR) {
        this.EUR = EUR;
    }

    public String getUSD() {
        return USD;
    }

    public void setUSD(String USD) {
        this.USD = USD;
    }

    @JsonProperty("BTC")
    public Object getBTC() {
        return BTC;
    }

    @JsonProperty("BTC")
    public void setBTC(Object BTC) {
        this.BTC = BTC;
    }

    @JsonProperty("ETH")
    public Object getETH() {
        return ETH;
    }

    @JsonProperty("ETH")
    public void setETH(Object ETH) {
        this.ETH = ETH;
    }

    @JsonProperty("ADA")
    public Object getADA() {
        return ADA;
    }

    @JsonProperty("ADA")
    public void setADA(Object ADA) {
        this.ADA = ADA;
    }

    //Get the information from the Json API response,
    //converts it into numbers.
    public static Double getRealRate(String coinrate){
        char [] chars = coinrate.toCharArray();

        StringBuilder sb = new StringBuilder();
        for(char c: chars){
            if(Character.isDigit(c)|| c =='.'){
                sb.append(c);
            }
        }
        return Double.parseDouble(String.valueOf(sb));
    }


}
