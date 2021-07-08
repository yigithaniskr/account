package com.project.account.dto;

import com.project.account.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionalDtoConverter {

    public TransactionDto convert(Transaction from){
        return new TransactionDto(from.getId(),
                from.getTransactionType(),
                from.getAmount(),
                from.getTransactionDate());
    }
}
