package core.CoinvestEE.controllers;

import core.CoinvestEE.model.TransactionLog;
import core.CoinvestEE.repositories.ClientRepository;
import core.CoinvestEE.repositories.TransactionLogRepository;
import core.CoinvestEE.services.WalletUpdate;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;


//This controller handles the transaction requests, it populates both the admin's view and the client's one
@RestController
@RequestMapping(value = "/transactions")
@Component
@Api(name="Coinvest Transactions API", description = "Provides a handful of methods that present the transactions from the database",
        stage = ApiStage.RC )
public class TransactionController {

    //Our repos
    private final TransactionLogRepository transactionLogRepository;
    private final ClientRepository clientRepository;

    @Autowired
    RestTemplate restTemplate = new RestTemplate();


    //Init our repo
    @Autowired
    public TransactionController(TransactionLogRepository transactionLogRepository, ClientRepository clientRepository){
        this.transactionLogRepository = transactionLogRepository;
        this.clientRepository = clientRepository;
    }


    //List all transactions
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Returns all transactions from the transactions table in the database")
    public List<TransactionLog> getAll(){
        return (List<TransactionLog>) transactionLogRepository.findAll();
    }

    //List most recent transactions
    @RequestMapping(value="/recent/{limit}")
    @ApiMethod(description = "Returns only the most recent transactions based on an id given to the url")
    public List<TransactionLog>getRecent(@ApiPathParam(name= "limit") @PathVariable int limit){
        return (List<TransactionLog>) transactionLogRepository.findMostRecentLogs(limit);
    }

    //Delete a transaction (Admin only)
    @RequestMapping(value="/delete/{id}")
    @ApiMethod(description = "Deletes a transaction on the database given an id (admin only)")
    public List<TransactionLog> remove(@ApiPathParam(name = "id")@PathVariable Long id){
        transactionLogRepository.deleteById(id);

        return (List<TransactionLog>) transactionLogRepository.findAll();
    }

    //This returns the transactions only for the client
    @RequestMapping(value = "/client/transactions", method = RequestMethod.GET)
    @ApiMethod(description = "Returns all the transactions of a client basen on his session attribute stored in the database (client only)")
    public List<TransactionLog> getClientAll(HttpSession session){
        String username = WalletUpdate.resolvePrincipal(session);
        Long cID = clientRepository.findClientIDByClientUsername(username);
        return (List<TransactionLog>) transactionLogRepository.findClientsTransactions(cID);
    }

    //This returns most recent client's transaction
    @RequestMapping(value = "/client/transactions/{limit}", method = RequestMethod.GET)
    @ApiMethod(description = "Returns the most recent transactions of a client based on a url parameter 'limit")
    public List<TransactionLog> getRecentClientAll(@ApiPathParam(name = "limit")@PathVariable int limit,HttpSession session){
        String username = WalletUpdate.resolvePrincipal(session);
        Long cID = clientRepository.findClientIDByClientUsername(username);
        return (List<TransactionLog>) transactionLogRepository.findRecentClientsTransactions(cID, limit);
    }
}
