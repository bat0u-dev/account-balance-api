package com.example.account.repository;

import com.example.account.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BalanceRepository extends JpaRepository<Balance, UUID> {
    Optional<Balance> findByName(String name);
}
