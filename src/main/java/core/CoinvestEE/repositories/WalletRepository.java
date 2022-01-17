package core.CoinvestEE.repositories;

import core.CoinvestEE.model.Client;
import core.CoinvestEE.model.Wallet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


//This class declares all the database queries directed to the Wallet table
public interface WalletRepository extends CrudRepository<Wallet, Long> {
    //Select Queries
    @Query(value="select * from Wallet where wallet_id=?",
    nativeQuery = true)
    Iterable<Wallet> findAllById(Long ID);
    @Query(value="select ada_balance from Wallet where wallet_id=?",
            nativeQuery = true)
    Double findAdaBalance(Long ID);
    @Query(value="select btc_balance from Wallet where wallet_id=?",
            nativeQuery = true)
    Double findBtcBalance(Long ID);
    @Query(value="select eth_balance from Wallet where wallet_id=?",
            nativeQuery = true)
    Double findEthBalance(Long ID);
    @Query(value="select monero_balance from Wallet where wallet_id=?",
            nativeQuery = true)
    Double findXmrBalance(Long ID);
    @Query(value="select eur_balance from Wallet where wallet_id=?",
            nativeQuery = true)
    Double findEurBalance(Long ID);

    //Buy Queries

    @Transactional
    @Modifying
    @Query(value="update wallet set eur_balance=eur_balance-?1 , btc_balance=btc_balance + ?2 where wallet_id=?3",
            nativeQuery = true)
    void updateBtcBuyBalance(double checkBalance, double txNumber, Long ID );

    @Transactional
    @Modifying
    @Query(value="update wallet set eur_balance=eur_balance-?1 , eth_balance=eth_balance + ?2 where wallet_id=?3",
            nativeQuery = true)
    void updateEthBuyBalance(double checkBalance, double txNumber, Long ID );

    @Transactional
    @Modifying
    @Query(value="update wallet set eur_balance=eur_balance-?1 , ada_balance=ada_balance + ?2 where wallet_id=?3",
            nativeQuery = true)
    void updateAdaBuyBalance(double checkBalance, double txNumber, Long ID );

    @Transactional
    @Modifying
    @Query(value="update wallet set eur_balance=eur_balance-?1 , monero_balance=monero_balance + ?2 where wallet_id=?3",
            nativeQuery = true)
    void updateXmrBuyBalance(double checkBalance, double txNumber, Long ID );

    //Sell Queries

    @Transactional
    @Modifying
    @Query(value="update wallet set eur_balance=eur_balance+?1, btc_balance=btc_balance - ?2 where wallet_id=?3",
            nativeQuery = true)
    void updateBtcSellBalance(double checkBalance, double txNumber, Long ID);

    @Transactional
    @Modifying
    @Query(value="update wallet set eur_balance=eur_balance+?1, eth_balance=eth_balance - ?2 where wallet_id=?3",
            nativeQuery = true)
    void updateEthSellBalance(double checkBalance, double txNumber, Long ID);

    @Transactional
    @Modifying
    @Query(value="update wallet set eur_balance=eur_balance+?1, ada_balance=ada_balance - ?2 where wallet_id=?3",
            nativeQuery = true)
    void updateAdaSellBalance(double checkBalance, double txNumber, Long ID);

    @Transactional
    @Modifying
    @Query(value="update wallet set eur_balance=eur_balance+?1, monero_balance=monero_balance - ?2 where wallet_id=?3",
            nativeQuery = true)
    void updateXmrSellBalance(double checkBalance, double txNumber, Long ID);

    //Initialisation Query
    @Transactional
    @Modifying
    @Query(value ="insert into wallet values (?1,'0','0','0','0','0','0',?2);",
            nativeQuery = true)
    void initWallet(Long wID, Long cID);
}
