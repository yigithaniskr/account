package com.project.account.dto;

import com.project.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {

    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionalDtoConverter transactionalDtoConverter;

    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter, TransactionalDtoConverter transactionalDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionalDtoConverter = transactionalDtoConverter;
    }

    public AccountDto convert(Account from){
        return new AccountDto(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                customerDtoConverter.convertToAccountCustomer(from.getCustomer()),
                Objects.requireNonNull(from.getTransaction()).stream().map(transactionalDtoConverter::convert).collect(Collectors.toSet()));
    }
}
