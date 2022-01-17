package core.CoinvestEE.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.CoinvestEE.DTObjects.TransactionDTO;

public interface TransactionControl {
    boolean checkBalance(TransactionDTO transactionDTO, Long cID) throws JsonProcessingException;
}
