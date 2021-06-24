package com.example.demo.repo;

import com.example.demo.model.FileLocation;
import com.example.demo.model.ReviewedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewedFileRepo extends JpaRepository<ReviewedFile,Integer> {

    Optional<ReviewedFile> findByEmail(@Param("email") String email);

}
