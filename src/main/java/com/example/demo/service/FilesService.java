package com.example.demo.service;

import com.example.demo.model.FileLocation;
import java.util.List;

public interface FilesService {

    FileLocation findByEmail(String email);

    FileLocation findByUrl(String url);

    List<FileLocation> findAllLocations();

    FileLocation updateFileLocation(String email, FileLocation updatedFileLocation);

    List<String> findAllUsers();

    FileLocation getDuplicateUrls(String url);

    void deleteFileLocation(String email);
}
