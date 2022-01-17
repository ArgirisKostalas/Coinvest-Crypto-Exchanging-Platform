package core.CoinvestEE.controllers;

import core.CoinvestEE.model.Client;
import core.CoinvestEE.repositories.*;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;




//This API is called only from our admin control panel
@RestController
@RequestMapping("/client")
@Component
@Api(name="Coinvest Client API", description = "Provides the list of all clients, only clients and a method to delete them",
     stage = ApiStage.RC )
public class ClientController {
    //Our repos
    private final ClientRepository clientRepository;

    //init them
    @Autowired
    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    //Return all the users
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all clients from the database")
    public List<Client> getAll(){
        return (List<Client>) clientRepository.findAll();
    }

    //Find the users without admins included
    @RequestMapping(value="clients")
    @ApiMethod(description = "Get Clients only (no admins) from the database")
    public List<Client> getClients(){return clientRepository.findClientsOnly();}

    //Delete a client
    @RequestMapping(value="/delete/{id}")
    @ApiMethod(description = "Delete a client from the database")
    public List<Client> remove(@ApiPathParam(name="id") @PathVariable Long id){
        clientRepository.deleteById(id);

        return (List<Client>) clientRepository.findAll();
    }


}
