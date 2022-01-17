package core.CoinvestEE.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



//This class handles most views in our application
@Controller
public class ViewController {

    //This is the default mapping of our application, returns the index page
    @RequestMapping("/")
    public String index(){
        return "index";
    }


    //Handle the bad login try
    @RequestMapping("/loginform?error")
    public String loginError(Model model){
        model.addAttribute("loginFail", true);
        return "loginform";
    }

    //This handles the loginform requests
    @RequestMapping("/loginform")
    public String login() {
        return "loginform";
    }

    //This is only applicable to ADMIN_ROLE
    @RequestMapping("/client")
    public String adminClient(){return "adminClient";}



}
