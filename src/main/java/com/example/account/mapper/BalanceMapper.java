package com.example.account.mapper;

import com.example.account.dto.BalanceResponse;
import com.example.account.entity.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    BalanceResponse toResponse(Balance balance);
}
