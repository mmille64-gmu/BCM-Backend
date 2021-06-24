package com.example.demo.controller;

import com.example.demo.model.ReviewedFile;
import com.example.demo.service.ReviewedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FilesServiceImpl;


@RequestMapping("/api/reviewed")
@RestController
@CrossOrigin()
public class ReviewedFilesController {

    @Autowired
    ReviewedFileService service;

    @GetMapping("/all")
    public ResponseEntity gettAllReviewedFiles() { return ResponseEntity.ok(service.findAllReviewedFiles());}

    @GetMapping("/{email}")
    public ResponseEntity getAllReviewedFilesByEmail(@PathVariable String email) { return ResponseEntity.ok(service.findByEmail(email));}

    @PostMapping("/")
    public ResponseEntity submitReviewedDocuments(@RequestBody ReviewedFile toSubmit) { return ResponseEntity.ok(service.updateReviewedFile(toSubmit));}
}
