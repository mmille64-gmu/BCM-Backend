package com.example.demo.service;

import com.example.demo.model.FileLocation;
import java.util.List;

public interface FilesService {

    FileLocation findByEmail(String email);

    FileLocation findByUrl(String url);

    FileLocation findByLocation(String location);

    List<FileLocation> findAllLocations();

    void updateFileLocation(String fileLocationLosingOwnership, FileLocation fileLocationGainingOwnership);

    List<String> findAllUsers();

    FileLocation getDuplicateUrls(String url);

    FileLocation respondToForm(FileLocation responseToForm);

    void deleteFileLocation(String email);
}
