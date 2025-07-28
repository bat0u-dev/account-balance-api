package com.example.account.mapper;

import com.example.account.dto.BalanceResponse;
import com.example.account.entity.Balance;
import com.example.account.entity.Currency;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T18:51:15+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class BalanceMapperImpl implements BalanceMapper {

    @Override
    public BalanceResponse toResponse(Balance balance) {
        if ( balance == null ) {
            return null;
        }

        String name = null;
        Currency currency = null;

        name = balance.getName();
        currency = balance.getCurrency();

        BigDecimal total = null;

        BalanceResponse balanceResponse = new BalanceResponse( name, total, currency );

        return balanceResponse;
    }
}
