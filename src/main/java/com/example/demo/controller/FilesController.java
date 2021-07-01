package com.example.demo.controller;

import com.example.demo.model.FileLocation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FilesServiceImpl;

import java.util.List;


@RequestMapping("/api/files")
@RestController
@CrossOrigin()
public class FilesController {

    @Autowired
    FilesServiceImpl service;

    @GetMapping()
    public ResponseEntity getAllLocations() { return ResponseEntity.ok(service.findAllLocations());}

    @GetMapping("/allUsers")
    public ResponseEntity getAllUsers() { return ResponseEntity.ok(service.findAllUsers());}

    @GetMapping("/email/{email}")
    public ResponseEntity getAllLocationsByEmail(@PathVariable String email) { return ResponseEntity.ok(service.findByEmail(email));}

    @GetMapping("/{url}")
    public ResponseEntity getAllLocationByUrl(@PathVariable String url) { return ResponseEntity.ok(service.findByUrl(url));}

    @PutMapping("/{emailLosingOwnership}")
    public void updateFilePrefrence(@PathVariable String emailLosingOwnership, @RequestBody FileLocation fileLocationGainingOwnership) {service.updateFileLocation(emailLosingOwnership,fileLocationGainingOwnership);}

    @DeleteMapping("/{url}")
    public void deleteFileLocation(@PathVariable String url) {
            service.deleteFileLocation(url);
    }

    @GetMapping("/duplicate/{url}")
    public FileLocation getAllDuplicateUrl(@PathVariable String url){
        return service.getDuplicateUrls(url);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity getByLocation(@PathVariable String location) { return ResponseEntity.ok(service.findByLocation(location));}

    @PutMapping("/response")
    public ResponseEntity respondToForm(@RequestBody FileLocation fileLocationRespondedTo) { return ResponseEntity.ok(service.respondToForm(fileLocationRespondedTo));}

}

