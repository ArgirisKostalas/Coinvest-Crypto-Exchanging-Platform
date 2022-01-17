package core.CoinvestEE.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.CoinvestEE.DTObjects.TransactionDTO;
import core.CoinvestEE.model.TransactionLog;
import core.CoinvestEE.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionControlImpl implements TransactionControl{

    //Repos
    private final WalletRepository walletRepository;
    private final RatesRepository ratesRepository;
    private final BankRepository bankRepository;
    private final TransactionLogRepository transactionLogRepository;

    //Services
    private final WalletUpdate walletUpdate;


    @Autowired
    public TransactionControlImpl(RatesRepository ratesRepository, WalletRepository walletRepository, TransactionLogRepository transactionLogRepository, BankRepository bankRepository, WalletUpdate walletUpdate){
        this.ratesRepository = ratesRepository;
        this.walletRepository = walletRepository;
        this.transactionLogRepository = transactionLogRepository;
        this.bankRepository = bankRepository;
        this.walletUpdate = walletUpdate;
    }

    @Override
    public boolean checkBalance(TransactionDTO transactionDTO, Long cID) throws JsonProcessingException {
        walletUpdate.refreshRates();
        String txCoin = transactionDTO.getTxCoin();
        double txNumber = transactionDTO.getTxNumber();
        String txAction = transactionDTO.getTxAction();
        Date today = new Date();
        double coinRate; //Current Coin Rate
        double balanceCheck; //total EUROS
        double coinBalance; //total COINS
        double truncatedTxNumber;
        double bankEurBalance;
        switch (txCoin){
            case "BTC":
                coinRate=ratesRepository.findLatestBtcRate();
                coinBalance= walletRepository.findBtcBalance(cID);
                bankEurBalance = bankRepository.findEurBalance();
                truncatedTxNumber = txNumber - (txNumber*(0.02));
                balanceCheck = truncatedTxNumber*coinRate; //total EUROS
                if((txNumber<=coinBalance)&&(txAction.equals("SELL"))&&(bankEurBalance>=balanceCheck)){
                    bankRepository.AddBtc(txNumber,balanceCheck);
                    walletRepository.updateBtcSellBalance(balanceCheck,txNumber, cID);
                    TransactionLog tx = new TransactionLog(txAction, today, (txNumber*(0.02))*coinRate, cID, txNumber,balanceCheck, "BTC", "EUR" );
                    transactionLogRepository.save(tx);
                    return true;
                }else if((balanceCheck<=walletRepository.findEurBalance(cID))&&(txAction.equals("BUY"))&&(bankRepository.findBtcBalance()>=txNumber)){
                    balanceCheck = (txNumber+(txNumber*(0.02)))*coinRate;
                    bankRepository.RemoveBtc(txNumber,balanceCheck);
                    walletRepository.updateBtcBuyBalance(balanceCheck,txNumber,cID);
                    TransactionLog tx = new TransactionLog(txAction, today, (txNumber*(0.02))*coinRate, cID, txNumber,balanceCheck, "EUR", "BTC");
                    transactionLogRepository.save(tx);
                    return true;
                }
                break;


            case "ETH":
                coinRate=ratesRepository.findLatestEthRate();
                coinBalance= walletRepository.findEthBalance(cID);
                bankEurBalance = bankRepository.findEurBalance();
                truncatedTxNumber = txNumber - (txNumber*(0.02));
                balanceCheck = truncatedTxNumber*coinRate;
                if((txNumber<=coinBalance)&&(txAction.equals("SELL"))&&(bankEurBalance>=balanceCheck)){
                    bankRepository.AddEth(txNumber,balanceCheck);
                    walletRepository.updateEthSellBalance(balanceCheck,txNumber, cID);
                    TransactionLog tx = new TransactionLog(txAction, today, (txNumber*(0.02))*coinRate, cID, txNumber,balanceCheck, "ETH", "EUR" );
                    transactionLogRepository.save(tx);
                    return true;
                }else if((balanceCheck<=walletRepository.findEurBalance(cID))&&(txAction.equals("BUY"))&&(bankRepository.findEthBalance()>=txNumber)){
                    balanceCheck = (txNumber+(txNumber*(0.02)))*coinRate;
                    bankRepository.RemoveEth(txNumber,balanceCheck);
                    walletRepository.updateEthBuyBalance(balanceCheck,txNumber,cID);
                    TransactionLog tx = new TransactionLog(txAction, today, (txNumber*(0.02))*coinRate, cID, txNumber,balanceCheck, "EUR", "ETH");
                    transactionLogRepository.save(tx);
                    return true;
                }
                break;


            case "ADA":
                coinRate=ratesRepository.findLatestAdaRate();
                coinBalance= walletRepository.findAdaBalance(cID);
                bankEurBalance = bankRepository.findEurBalance();
                truncatedTxNumber = txNumber - (txNumber*(0.02));
                balanceCheck = truncatedTxNumber*coinRate;
                if((txNumber<=coinBalance)&&(txAction.equals("SELL"))&&(bankEurBalance>=balanceCheck)){
                    bankRepository.AddAda(txNumber,balanceCheck);
                    walletRepository.updateAdaSellBalance(balanceCheck,txNumber,cID);
                    TransactionLog tx = new TransactionLog(txAction, today, (txNumber*(0.02))*coinRate, cID, txNumber,balanceCheck, "ADA", "EUR" );
                    transactionLogRepository.save(tx);
                    return true;
                }else if((balanceCheck<=walletRepository.findEurBalance(cID))&&(txAction.equals("BUY"))&&(bankRepository.findAdaBalance()>=txNumber)){
                    balanceCheck = (txNumber+(txNumber*(0.02)))*coinRate;
                    bankRepository.RemoveAda(txNumber,balanceCheck);
                    walletRepository.updateAdaBuyBalance(balanceCheck,txNumber,cID);
                    TransactionLog tx = new TransactionLog(txAction, today, (txNumber*(0.02))*coinRate, cID, txNumber,balanceCheck, "EUR", "ADA");
                    transactionLogRepository.save(tx);
                    return true;
                }
                break;

            case "XMR":
                coinRate=ratesRepository.findLatestXmrRate();
                coinBalance= walletRepository.findXmrBalance(cID);
                bankEurBalance = bankRepository.findEurBalance();
                truncatedTxNumber = txNumber - (txNumber*(0.02));
                balanceCheck = truncatedTxNumber*coinRate;
                if((txNumber<=coinBalance)&&(txAction.equals("SELL"))&&(bankEurBalance>=balanceCheck)){
                    bankRepository.AddXmr(txNumber,balanceCheck);
                    walletRepository.updateXmrSellBalance(balanceCheck,txNumber, cID);
                    TransactionLog tx = new TransactionLog(txAction, today, (txNumber*(0.02))*coinRate, cID, txNumber,balanceCheck, "XMR", "EUR" );
                    transactionLogRepository.save(tx);
                    return true;
                }else if((balanceCheck<=walletRepository.findEurBalance(cID))&&(txAction.equals("BUY"))&&(bankRepository.findXmrBalance()>=txNumber)){
                    balanceCheck = (txNumber+(txNumber*(0.02)))*coinRate;
                    bankRepository.RemoveXmr(txNumber,balanceCheck);
                    walletRepository.updateXmrBuyBalance(balanceCheck,txNumber,cID);
                    TransactionLog tx = new TransactionLog(txAction, today, (txNumber*(0.02))*coinRate, cID, txNumber,balanceCheck, "EUR", "XMR");
                    transactionLogRepository.save(tx);
                    return true;
                }
                break;

        }

        return false;
    }
}
