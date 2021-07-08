package com.project.account.dto;

import com.project.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {

    private TransactionalDtoConverter converter;

    public CustomerAccountDtoConverter(TransactionalDtoConverter converter) {
        this.converter = converter;
    }

    public CustomerAccountDto convert(Account from){
        return new CustomerAccountDto(
                Objects.requireNonNull(from.getId()),
                from.getBalance(),
                from.getTransaction().stream().map(converter::convert).collect(Collectors.toSet()),
                from.getCreationDate());
    }
}
