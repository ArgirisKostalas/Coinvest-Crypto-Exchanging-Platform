package core.CoinvestEE.services;

import core.CoinvestEE.DTObjects.ClientRegistrationDto;

/**
 * Created by Giannis Anastasopoulos on 20/06/21
 */
public interface ClientService {
    boolean save(ClientRegistrationDto clientRegistrationDto);
}
