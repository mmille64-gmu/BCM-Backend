package com.example.demo.repo;

import com.example.demo.model.FileLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilesRepo extends JpaRepository<FileLocation,Integer> {

    Optional<FileLocation> findByEmail(@Param("email") String email);

    Optional<FileLocation> findByUrl(@Param("url") String url);

    @Query("SELECT u FROM un_reviewed_files u WHERE u.url=:newUrl")
    List<FileLocation> findDuplicateUrls(@Param("newUrl") String newUrl);

//    @Query("select f from unReviewedFiles f where f.email=:category")
//    List<FileLocation> getProductsByCategory(@Param("category") String email);
}
