package core.CoinvestEE.services;

import core.CoinvestEE.DTObjects.ClientRegistrationDto;
import core.CoinvestEE.model.Client;

import core.CoinvestEE.repositories.ClientRepository;

import core.CoinvestEE.repositories.WalletRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.util.List;

/**
 * Created by Giannis Anastasopoulos on 20/06/21
 */

@Service
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;
    private final WalletRepository walletRepository;

    public ClientServiceImpl(ClientRepository clientRepository, WalletRepository walletRepository){
        this.clientRepository = clientRepository;
        this.walletRepository = walletRepository;
    }


    //In this method we implement the User registration, this method returns true if the registration was completed successfully
    //If it is not, it returns false. The Registration Controller can manage the result and present it to the client from there.
    @Override
    public boolean save(ClientRegistrationDto clientRegistrationDto) {

        //Encode Password


        String clientPassword = clientRegistrationDto.getClientPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String clientEncodedPassword = passwordEncoder.encode(clientPassword);

        //Transfer Data from ClientRegistrationDto
        String clientFirstName = clientRegistrationDto.getClientFirstName();
        String clientLastName = clientRegistrationDto.getClientLastName();
        String clientPaymentMethod = clientRegistrationDto.getClientPaymentMethod();
        String clientUsername = clientRegistrationDto.getClientUsername();
        LocalDate clientAge = clientRegistrationDto.getClientAge();
        String clientEmail = clientRegistrationDto.getClientEmail();

        //User Exists or not
        boolean clientExists = false;
        //Assert that the result will be false
        boolean result = false;

        //Create a client object from the registration form fields
        Client client = new Client(clientFirstName, clientLastName, clientPaymentMethod, clientUsername, clientAge, clientEmail, clientEncodedPassword, "ROLE_USER");

        //We store all the listed users in a List, if the username or the email exists, then the user already exists and
        //we return false
        List<String> listedUsers = clientRepository.findAllUsernames();
        listedUsers.addAll(clientRepository.findAllEmails());
        for (String listedUser : listedUsers) {
            if (listedUser.equals(clientUsername) || (listedUser.equals(clientEmail))) {
                clientExists = true;
                break;
            }
        }

        //If the clients doesn't exist, we save him yo our database, and we return true
        if (!clientExists){
            clientRepository.save(client);
            walletRepository.initWallet(client.getClientID(), client.getClientID());
            result=true;
        }

        return result;
    }
}
