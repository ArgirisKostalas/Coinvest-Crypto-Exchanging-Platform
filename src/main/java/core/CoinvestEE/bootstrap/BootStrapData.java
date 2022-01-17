package core.CoinvestEE.bootstrap;

import core.CoinvestEE.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;



@Component
public class BootStrapData implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final WalletRepository walletRepository;
    private final BankRepository bankRepository;
    private final TransactionLogRepository transactionLogRepository;
    private final RatesRepository ratesRepository;

    public BootStrapData(ClientRepository clientRepository, WalletRepository walletRepository, BankRepository bankRepository, TransactionLogRepository transactionLogRepository, RatesRepository ratesRepository) {
        this.clientRepository = clientRepository;
        this.walletRepository = walletRepository;
        this.bankRepository = bankRepository;
        this.transactionLogRepository = transactionLogRepository;
        this.ratesRepository = ratesRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        System.out.println("Started Spring-Boot without errors, operation normal.");

        //Today's date
        LocalDate today = LocalDate.now();

        //TRANSACTIONS AS OF TODAY
        System.out.println("Number of Transactions as of today: "+ today+ ", are: "  + transactionLogRepository.count());
    }
}
