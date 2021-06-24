package com.example.demo.service;

import com.example.demo.model.FileLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo.repo.FilesRepo;

import javax.persistence.NonUniqueResultException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    FilesRepo repo;

    @Override
    public FileLocation findByEmail(String email) {
        FileLocation retreived = null;

        Optional<FileLocation> files=repo.findByEmail(email);

        if(files.isPresent()){
            retreived = files.get();
            return retreived;
        } else {
            throw new IllegalArgumentException("No email found");
        }
    }

    @Override
    public FileLocation findByUrl(String url) {
        FileLocation retreived = null;

        try {
            Optional<FileLocation> files=repo.findByUrl(url);
            if (files.isPresent()) {
                retreived = files.get();
                return retreived;
            } else {
                throw new IllegalArgumentException("No url found");
            }
        } catch(NonUniqueResultException | IncorrectResultSizeDataAccessException e) {
            retreived = getDuplicateUrls(url);
            return retreived;
        }
    }

    @Override
    public List<FileLocation> findAllLocations(){
        List<FileLocation> files=repo.findAll();

        return files;

    }

    @Override
    public List<String> findAllUsers(){
        List<FileLocation> allFiles = repo.findAll();
        List<String> allUsers = new ArrayList<>();

        for(int i=0;i< allFiles.size();i++) {
            allUsers.add(allFiles.get(i).getEmail());
        }
        return allUsers;
    }

    @Override
    public FileLocation getDuplicateUrls(String url) {
        List<FileLocation> allDupes = repo.findDuplicateUrls(url);
        for(int i=1;i<allDupes.size();i++){
            allDupes.get(0).setLocation(allDupes.get(0).getLocation()+","+allDupes.get(i).getLocation());
            repo.deleteById(allDupes.get(i).getId());
        }
        repo.saveAndFlush(allDupes.get(0));
        return allDupes.get(0);
    }

    @Override
    public FileLocation updateFileLocation(String emailLosingOwnership, FileLocation fileLocationGainingOwnership) {
        FileLocation oldFileLocation = null;
        Optional<FileLocation> fileGrabber = repo.findByEmail(emailLosingOwnership);

        if(fileGrabber.isPresent()){
            oldFileLocation = fileGrabber.get();
        }

        fileGrabber = repo.findByEmail(fileLocationGainingOwnership.getEmail());

        if(fileGrabber.isPresent()){
            fileLocationGainingOwnership = fileGrabber.get();
        } else {
            String trimmedUrl = fileLocationGainingOwnership.getEmail().substring(0,fileLocationGainingOwnership.getEmail().indexOf("@"));
            fileLocationGainingOwnership.setUrl(trimmedUrl);
            fileLocationGainingOwnership.setLocation(oldFileLocation.getLocation());
            oldFileLocation.setLocation("");
            repo.save(oldFileLocation);
            return repo.saveAndFlush(fileLocationGainingOwnership);
        }
        if(fileLocationGainingOwnership.getLocation().length() > 1) {
            fileLocationGainingOwnership.setLocation(fileLocationGainingOwnership.getLocation() + "," + oldFileLocation.getLocation());
        } else {
            fileLocationGainingOwnership.setLocation(oldFileLocation.getLocation());
        }
        oldFileLocation.setLocation("");

        repo.save(oldFileLocation);
        return repo.save(fileLocationGainingOwnership);
    }

    @Override
    public void deleteFileLocation(String url){
        FileLocation toDelete = findByUrl(url);
        repo.deleteById(toDelete.getId());
    }
}
