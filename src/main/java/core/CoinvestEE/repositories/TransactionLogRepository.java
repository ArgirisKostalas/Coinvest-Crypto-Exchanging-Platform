package core.CoinvestEE.repositories;

import core.CoinvestEE.model.TransactionLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface TransactionLogRepository extends CrudRepository<TransactionLog, Long> {
    @Query(value = "SELECT tx_fee FROM transactions WHERE txid=(SELECT max(txid) FROM transactions);",
            nativeQuery = true)
    double findLatestFee();

    @Query(value ="SELECT * FROM coinvest2.transactions order by tx_date desc limit ? ;",
            nativeQuery = true)
    Iterable<TransactionLog> findMostRecentLogs(int x);

    @Query(value ="SELECT * FROM coinvest2.transactions where clientid=? ;",
            nativeQuery = true)
    Iterable<TransactionLog> findClientsTransactions(Long ID);

    @Query(value ="SELECT * FROM coinvest2.transactions where clientid=?1 order by tx_date desc limit ?2 ",
            nativeQuery = true)
    Iterable<TransactionLog> findRecentClientsTransactions(Long ID, int limit);
}
