package core.CoinvestEE.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.CoinvestEE.JSONtoPOJO.SimpleApiRates;
import core.CoinvestEE.model.Rates;
import core.CoinvestEE.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Date;

@Service
public class WalletUpdateImpl implements WalletUpdate{
    private final WalletRepository walletRepository;
    private final RatesRepository ratesRepository;

    @Autowired
    public WalletUpdateImpl(RatesRepository ratesRepository, WalletRepository walletRepository){
        this.ratesRepository = ratesRepository;
        this.walletRepository = walletRepository;
    }


    @Override
    public void refreshRates() throws JsonProcessingException {
        //Create DATE format

        Date today = new Date();

        //API Initialisation
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        //API Call
        String jsonStr = restTemplate.getForObject("https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH,ADA,XMR&tsyms=EUR&api_key=b03db73eaa6a6957380ddf0707beb220a216eb767e059f214de61fcb2ad6b93e", String.class);
        SimpleApiRates fullDetails = mapper.readValue(jsonStr, SimpleApiRates.class);
        //PASS into string
        String adaValueInEuro = fullDetails.getADA().toString();
        String btcValueInEuro = fullDetails.getBTC().toString();
        String ethValueInEuro = fullDetails.getETH().toString();
        String xmrValueInEuro = fullDetails.getXMR().toString();
        //Turn into doubles
        double xmrRealRateEuro = SimpleApiRates.getRealRate(xmrValueInEuro);
        double ethRealRateEuro = SimpleApiRates.getRealRate(ethValueInEuro);
        double btcRealRateEuro = SimpleApiRates.getRealRate(btcValueInEuro);
        double adaRealRateEuro = SimpleApiRates.getRealRate(adaValueInEuro);
        //Save newest rate
        Rates todayRate = new Rates(today, btcRealRateEuro, ethRealRateEuro, xmrRealRateEuro, adaRealRateEuro, 1.00, 1.00);
        ratesRepository.save(todayRate);
    }

    @Override
    public double calculateTotalBalance(Long ID) {
        double total = (ratesRepository.findLatestAdaRate()* walletRepository.findAdaBalance(ID))+(ratesRepository.findLatestBtcRate()* walletRepository.findBtcBalance(ID)+(ratesRepository.findLatestEthRate()* walletRepository.findEthBalance(ID)+(ratesRepository.findLatestXmrRate()* walletRepository.findXmrBalance(ID)+ walletRepository.findEurBalance(ID))));
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(total));
    }
}
