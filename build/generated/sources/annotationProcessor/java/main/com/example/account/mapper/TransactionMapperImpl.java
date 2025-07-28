package com.example.account.mapper;

import com.example.account.dto.TransactionResponse;
import com.example.account.entity.Currency;
import com.example.account.entity.Transaction;
import com.example.account.entity.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T18:51:15+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionResponse toResponse(Transaction tx) {
        if ( tx == null ) {
            return null;
        }

        TransactionType type = null;
        BigDecimal amount = null;
        Currency currency = null;
        LocalDateTime timestamp = null;

        type = tx.getType();
        amount = tx.getAmount();
        currency = tx.getCurrency();
        timestamp = tx.getTimestamp();

        TransactionResponse transactionResponse = new TransactionResponse( type, amount, currency, timestamp );

        return transactionResponse;
    }
}
