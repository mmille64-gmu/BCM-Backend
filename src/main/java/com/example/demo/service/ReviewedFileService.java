package com.example.demo.service;

import com.example.demo.model.FileLocation;
import com.example.demo.model.ReviewedFile;

import java.util.List;

public interface ReviewedFileService {
    ReviewedFile findByEmail(String email);

    List<ReviewedFile> findAllReviewedFiles();

    ReviewedFile updateReviewedFile(ReviewedFile updatedReviewedFile);

    void deleteReviewedFile(String email);
}
