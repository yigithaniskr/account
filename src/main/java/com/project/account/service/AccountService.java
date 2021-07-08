package com.project.account.service;

import com.project.account.dto.AccountDto;
import com.project.account.dto.AccountDtoConverter;
import com.project.account.dto.CreateAccountRequest;
import com.project.account.model.Account;
import com.project.account.model.Customer;
import com.project.account.model.Transaction;
import com.project.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());
        if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO)>0){
            //Transaction transaction = transactionService.initiateMoney(account, createAccountRequest.getInitialCredit());
            Transaction transaction = new Transaction(createAccountRequest.getInitialCredit(), account);
            account.getTransaction().add(transaction);
        }

        return converter.convert(accountRepository.save(account));
    }
}
