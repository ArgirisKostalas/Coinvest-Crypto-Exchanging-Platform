package core.CoinvestEE.controllers;

import core.CoinvestEE.DTObjects.ClientRegistrationDto;
import core.CoinvestEE.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



//This Class is a controller that handles the registration process
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    //Declare the Service
    private final ClientService clientService;

    //Constructor
    public RegistrationController(ClientService clientService){
        this.clientService = clientService;
    }

    //We declare the model's attribute at this point, which is a client object
    @ModelAttribute("client")
    public ClientRegistrationDto clientRegistrationDto(){
        return new ClientRegistrationDto();
    }


    //Get Request Handler, it returns the registration form
    @GetMapping
    public String showRegisterForm(){
        return "registration";
    }

    //Post Form Handler, it handles the form response and redirects to the result
    @PostMapping
    public String registerClient(@ModelAttribute("client")ClientRegistrationDto clientRegistrationDto){
        if(clientService.save(clientRegistrationDto)){
            return "redirect:/registration?success";
        }else{
            return "redirect:/registration?failure";
        }

    }


}
