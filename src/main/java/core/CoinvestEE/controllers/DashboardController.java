package core.CoinvestEE.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.CoinvestEE.repositories.ClientRepository;
import core.CoinvestEE.repositories.WalletRepository;
import core.CoinvestEE.services.WalletUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    //Declare the services
    private final WalletUpdate walletUpdate;

    //Declare our repos
    private ClientRepository clientRepository;
    private WalletRepository walletRepository;

    //Wire up our repos
    @Autowired
    public void ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void WalletController(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    public DashboardController(WalletUpdate walletUpdate){
        this.walletUpdate = walletUpdate;
    }


    //This controller handles the dashboard page, it populates the front end page with information
    //such as client's balance, Name, email, etc..
    //If the user connected is ADMIN, then the population is different
    @GetMapping
    public String dashboard(Model model, HttpSession session) throws JsonProcessingException {
        String username = WalletUpdate.resolvePrincipal(session);
        Long cID = clientRepository.findClientIDByClientUsername(username);
        if (clientRepository.findClientRole(cID).equals("ROLE_USER")) {
            walletUpdate.refreshRates();
            double totalBalance = walletUpdate.calculateTotalBalance(cID);
            model.addAttribute("totalBalance", totalBalance);
            model.addAttribute("client", clientRepository.findAllById(cID));
            model.addAttribute("wallet", walletRepository.findAllById(cID));
            model.addAttribute("user", true);
            return "newdashboard";
        }else{
            model.addAttribute("admin", true);
            return "dashboard";
        }

    }
}
