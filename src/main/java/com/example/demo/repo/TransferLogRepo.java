package com.example.demo.repo;

import com.example.demo.model.TransferLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferLogRepo extends JpaRepository<TransferLog,Integer> {
}
