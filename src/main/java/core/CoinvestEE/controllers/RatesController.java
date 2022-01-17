package core.CoinvestEE.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.CoinvestEE.repositories.*;
import core.CoinvestEE.services.WalletUpdate;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;



@RestController
@RequestMapping(value = "/dashboard")
@Component
@Api(name="Coinvest Rates API", description = "Provides the refresh method, which refreshes the balance of the client with real time rates ",
        stage = ApiStage.RC )
    public class RatesController {
    private final ClientRepository clientRepository;


    //Declare the service
    private final WalletUpdate walletUpdate;

    @Autowired
    public RatesController(ClientRepository clientRepository, WalletUpdate walletUpdate){
        this.clientRepository = clientRepository;
        this.walletUpdate = walletUpdate;
    }

    //This is a custom API we create to show, the client's refreshed rate, it feeds the Angular
    //JS framework in order to populate the front end data
    @RequestMapping(value ="/refresh")
    @ApiMethod(description = "Refresh the clients balance, based on his login session stored in the database")
    public double refreshBalance(HttpSession session) throws JsonProcessingException {
        String username = WalletUpdate.resolvePrincipal(session);
        Long cID = clientRepository.findClientIDByClientUsername(username);
        walletUpdate.refreshRates();
        return walletUpdate.calculateTotalBalance(cID);
    }






}
