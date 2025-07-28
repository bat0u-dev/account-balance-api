package com.example.account.mapper;

import com.example.account.dto.TransactionResponse;
import com.example.account.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toResponse(Transaction tx);
}
