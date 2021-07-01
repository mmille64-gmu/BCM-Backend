package com.example.demo.service;

import com.example.demo.model.TransferLog;
import com.example.demo.repo.TransferLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferLogServiceImpl implements TransferLogService{

    @Autowired
    TransferLogRepo repo;

    @Override
    public TransferLog addNewTransferLog(TransferLog newLog) {
        newLog.setDateOfTransfer(LocalDateTime.now());
        return repo.saveAndFlush(newLog);
    }
}
