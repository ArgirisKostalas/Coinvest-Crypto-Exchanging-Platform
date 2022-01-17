package core.CoinvestEE.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.CoinvestEE.DTObjects.TransactionDTO;
import core.CoinvestEE.repositories.ClientRepository;
import core.CoinvestEE.repositories.TransactionLogRepository;
import core.CoinvestEE.services.TransactionControl;
import core.CoinvestEE.services.WalletUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/market")
public class MarketController {

    //Declare the services
    private final TransactionControl transactionControl;

    //Declare our repos
    private ClientRepository clientRepository;
    private TransactionLogRepository transactionLogRepository;

    @Autowired
    public void ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void TransactionController(TransactionLogRepository transactionLogRepository){this.transactionLogRepository=transactionLogRepository;}

    //Constructor
    public MarketController(TransactionControl transactionControl){
        this.transactionControl = transactionControl;
    }

    @ModelAttribute("transaction")
    public TransactionDTO transactionDTO(){
        return new TransactionDTO();
    }

    //GET Request Handler
    @GetMapping
    public String showMarketForm (Model model){
        model.addAttribute("hide",false);
        return "market";
    }

    //POST Request Handler
    @PostMapping
    public String completeTransaction(@ModelAttribute("transaction") TransactionDTO transactionDTO, Model model, HttpSession session) throws JsonProcessingException {
        //We retrieve our session's username from our database
        String username = WalletUpdate.resolvePrincipal(session);
        //We store the client's ID in a variable so we can process the transaction's details
        Long cID = clientRepository.findClientIDByClientUsername(username);
        if (transactionControl.checkBalance(transactionDTO, cID)){
            model.addAttribute("fee", transactionLogRepository.findLatestFee());
            model.addAttribute("valid", true);
            model.addAttribute("hide", true);
        }else{
            model.addAttribute("valid", false);
        }
        return "market";

    }
}
