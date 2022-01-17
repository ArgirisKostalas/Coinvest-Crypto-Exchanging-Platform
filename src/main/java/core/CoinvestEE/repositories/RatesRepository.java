package core.CoinvestEE.repositories;

import core.CoinvestEE.model.Rates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



public interface RatesRepository extends CrudRepository<Rates, Long> {
    @Query(value = "SELECT ada_rate FROM Rates WHERE rateid=(SELECT max(rateid) FROM Rates);",
    nativeQuery = true)
    Double findLatestAdaRate();
    @Query(value = "SELECT bitcoin_rate FROM Rates WHERE rateid=(SELECT max(rateid) FROM Rates);",
            nativeQuery = true)
    Double findLatestBtcRate();
    @Query(value = "SELECT ethereum_rate FROM Rates WHERE rateid=(SELECT max(rateid) FROM Rates);",
            nativeQuery = true)
    Double findLatestEthRate();
    @Query(value = "SELECT monero_rate FROM Rates WHERE rateid=(SELECT max(rateid) FROM Rates);",
            nativeQuery = true)
    Double findLatestXmrRate();

}
