package com.example.demo.controller;

import com.example.demo.model.TransferLog;
import com.example.demo.service.TransferLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/files")
@RestController
@CrossOrigin()
public class TransferLogController {

    @Autowired
    TransferLogService service;

    @PostMapping("/transfer")
    public ResponseEntity addTransferLog(@RequestBody TransferLog newLog){return ResponseEntity.ok(service.addNewTransferLog(newLog));}
}
