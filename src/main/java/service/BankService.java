package service;

import entity.Bank;
import entity.User;
import repository.BankRepository;

public class BankService {
    BankRepository bankRepository;

    public Bank findBankById(int bankId) {
        return bankRepository.read(bankId).orElse(null);
    }
}
