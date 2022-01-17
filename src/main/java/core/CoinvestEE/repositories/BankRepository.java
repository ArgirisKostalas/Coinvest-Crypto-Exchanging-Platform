package core.CoinvestEE.repositories;

import core.CoinvestEE.model.Bank;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


public interface BankRepository extends CrudRepository<Bank, Long> {
    @Query(value="select ada_balance from Bank;",
           nativeQuery = true)
    double findAdaBalance();

    @Query(value="select eth_balance from Bank;",
            nativeQuery = true)
    double findEthBalance();

    @Query(value="select btc_balance from Bank;",
            nativeQuery = true)
    double findBtcBalance();

    @Query(value="select eur_balance from Bank;",
            nativeQuery = true)
    double findEurBalance();

    @Query(value="select monero_balance from Bank;",
            nativeQuery = true)
    double findXmrBalance();

    //when client sells ADA
    @Transactional
    @Modifying
    @Query(value="update bank set ada_balance=ada_balance + ?1, eur_balance=eur_balance - ?2",nativeQuery = true)
    void AddAda(double tXnumber, double balanceCheck);

    //when client buys ADA
    @Transactional
    @Modifying
    @Query(value="update bank set ada_balance=ada_balance - ?1, eur_balance=eur_balance + ?2",nativeQuery = true)
    void RemoveAda(double tXnumber, double balanceCheck);

    //when client sells BTC
    @Transactional
    @Modifying
    @Query(value="update bank set btc_balance=btc_balance + ?1, eur_balance=eur_balance - ?2",nativeQuery = true)
    void AddBtc(double tXnumber, double balanceCheck);

    //when client buys BTC
    @Transactional
    @Modifying
    @Query(value="update bank set btc_balance=btc_balance - ?1, eur_balance=eur_balance + ?2",nativeQuery = true)
    void RemoveBtc(double tXnumber, double balanceCheck);

    //when client sells ETH
    @Transactional
    @Modifying
    @Query(value="update bank set eth_balance=eth_balance + ?1, eur_balance=eur_balance - ?2",nativeQuery = true)
    void AddEth(double tXnumber, double balanceCheck);

    //when client buys ETH
    @Transactional
    @Modifying
    @Query(value="update bank set eth_balance=eth_balance - ?1, eur_balance=eur_balance + ?2",nativeQuery = true)
    void RemoveEth(double tXnumber, double balanceCheck);

    //when client sells XMR
    @Transactional
    @Modifying
    @Query(value="update bank set monero_balance=monero_balance + ?1, eur_balance=eur_balance - ?2",nativeQuery = true)
    void AddXmr(double tXnumber, double balanceCheck);

    //when client buys XMR
    @Transactional
    @Modifying
    @Query(value="update bank set monero_balance=monero_balance - ?1, eur_balance=eur_balance + ?2",nativeQuery = true)
    void RemoveXmr(double tXnumber, double balanceCheck);
}
