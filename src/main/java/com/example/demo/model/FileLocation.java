package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Table(name = "un_reviewed_files")
@Entity(name = "un_reviewed_files")
public class FileLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "location")
    String location;

    @Column(name = "keep_file")
    Boolean keepFile;

    @Column(name = "delete_file")
    Boolean deleteFile;

    @Column(name = "send_to_it")
    Boolean sendToIT;

    @Column(name = "url")
    String url;



}
