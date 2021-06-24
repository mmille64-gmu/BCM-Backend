package com.example.demo.service;

import com.example.demo.model.ReviewedFile;
import com.example.demo.repo.ReviewedFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewedFileServiceImpl implements ReviewedFileService{

    @Autowired
    ReviewedFileRepo repo;

    @Override
    public ReviewedFile findByEmail(String email) {
        ReviewedFile retreived = null;

        Optional<ReviewedFile> files=repo.findByEmail(email);

        if(files.isPresent()){
            retreived = files.get();
            return retreived;
        } else {
            throw new IllegalArgumentException("No email found");
        }
    }

    @Override
    public List<ReviewedFile> findAllReviewedFiles() {
        List<ReviewedFile> files = repo.findAll();

        return files;

    }

    @Override
    public ReviewedFile updateReviewedFile(ReviewedFile updatedReviewedFile) {
        updatedReviewedFile.setDateSubmitted(LocalDateTime.now());
        return repo.saveAndFlush(updatedReviewedFile);
    }

    @Override
    public void deleteReviewedFile(String email) {

    }
}
