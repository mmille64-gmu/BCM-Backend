package com.example.demo.service;

import com.example.demo.model.FileLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo.repo.FilesRepo;

import javax.persistence.NonUniqueResultException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
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
    public FileLocation findByLocation(String location){
        FileLocation retreived = null;

        Optional<FileLocation> files=repo.findByLocation(location);
        if (files.isPresent()) {
            retreived = files.get();
            return retreived;
        } else {
            throw new IllegalArgumentException("No location found");
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
        FileLocation returnedLocations = new FileLocation();
//        returnedLocations.setLocation(null);
        for(int i=0;i<allDupes.size();i++){
            if(!allDupes.get(i).getDeleteFile() && !allDupes.get(i).getKeepFile() && !allDupes.get(i).getSendToIT()){
                if(returnedLocations.getLocation()==null){
                    returnedLocations.setLocation(allDupes.get(i).getLocation());
                } else {
                    returnedLocations.setLocation(returnedLocations.getLocation()+","+allDupes.get(i).getLocation());
                }
            }
//                repo.deleteById(allDupes.get(i).getId());
        }
        returnedLocations.setUrl(allDupes.get(0).getUrl());
        returnedLocations.setId(allDupes.get(0).getId());
        returnedLocations.setEmail(allDupes.get(0).getEmail());
        returnedLocations.setFirstName(allDupes.get(0).getFirstName());
        returnedLocations.setLastName(allDupes.get(0).getLastName());
//        repo.saveAndFlush(allDupes.get(0));
        return returnedLocations;
    }

    @Override
    public void updateFileLocation(String fileLocationLosingOwnership, FileLocation fileLocationGainingOwnership) {
        List<FileLocation> allDupes = repo.findDuplicateUrls(fileLocationLosingOwnership);


        for(int i=0;i< allDupes.size();i++){
            allDupes.get(i).setEmail(fileLocationGainingOwnership.getEmail());
            allDupes.get(i).setUrl(fileLocationGainingOwnership.getUrl());
            allDupes.get(i).setFirstName(null);
            allDupes.get(i).setLastName(null);
            repo.save(allDupes.get(i));
    }


        //  OLD LOGIC
        //        FileLocation oldFileLocation = null;
//        Optional<FileLocation> fileGrabber = repo.findByEmail(emailLosingOwnership);
//
//        if(fileGrabber.isPresent()){
//            oldFileLocation = fileGrabber.get();
//        }
//
//        fileGrabber = repo.findByEmail(fileLocationGainingOwnership.getEmail());
//
//        if(fileGrabber.isPresent()){
//            fileLocationGainingOwnership = fileGrabber.get();
//        } else {
//            String trimmedUrl = fileLocationGainingOwnership.getEmail().substring(0,fileLocationGainingOwnership.getEmail().indexOf("@"));
//            fileLocationGainingOwnership.setUrl(trimmedUrl);
//            fileLocationGainingOwnership.setLocation(oldFileLocation.getLocation());
//            oldFileLocation.setLocation("");
//            repo.save(oldFileLocation);
//            return repo.saveAndFlush(fileLocationGainingOwnership);
//        }
//        if(fileLocationGainingOwnership.getLocation().length() > 1) {
//            fileLocationGainingOwnership.setLocation(fileLocationGainingOwnership.getLocation() + "," + oldFileLocation.getLocation());
//        } else {
//            fileLocationGainingOwnership.setLocation(oldFileLocation.getLocation());
//        }
//        oldFileLocation.setLocation("");
//
//        repo.save(oldFileLocation);
//        return repo.save(fileLocationGainingOwnership);
    }

    @Override
    public FileLocation respondToForm(FileLocation responseToForm){
        List<FileLocation> dupes = repo.findDuplicateUrls(responseToForm.getUrl());

        for (int i = 0; i < dupes.size(); i++) {
            if(dupes.get(i).getLocation().equals(responseToForm.getLocation())){
                responseToForm.setId(dupes.get(i).getId());
                break;
            }
        }
        responseToForm.setDateAnswered(LocalDateTime.now());
        return repo.save(responseToForm);
    }

    @Override
    public void deleteFileLocation(String url){
        FileLocation toDelete = findByUrl(url);
        repo.deleteById(toDelete.getId());
    }
}
