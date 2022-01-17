package core.CoinvestEE.repositories;

import core.CoinvestEE.model.Client;
import core.CoinvestEE.model.Wallet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface ClientRepository extends CrudRepository<Client, Long> {
    @Query(value = "select client_id from Client where client_username=? limit 1",
            nativeQuery = true)
    Long findClientIDByClientUsername(String username);

    @Query(value="select * from Client where client_id=?",
            nativeQuery = true)
    Iterable<Client> findAllById(Long ID);

    @Query("select Role from Client where clientID=?1")
    String findClientRole(Long ID);

    @Query(value = "select * from Client where role='ROLE_USER'",
            nativeQuery = true)
    List<Client> findClientsOnly();

    @Query(value="select client_username from Client",
            nativeQuery = true)
    List<String> findAllUsernames();

    @Query(value="select client_email from Client",
            nativeQuery = true)
    List<String> findAllEmails();
}
