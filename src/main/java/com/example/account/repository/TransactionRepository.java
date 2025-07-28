package com.example.account.repository;

import com.example.account.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByExternalId(String externalId);
    boolean existsByExternalId(String externalId);
}
