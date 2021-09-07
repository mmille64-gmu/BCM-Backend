package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Table(name = "bcm_table")
@Entity(name = "bcm_table")

public class ReviewedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "email")
    String email;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "location")
    String location;

    @Column(name = "keep_file")
    Boolean keepFile;

    @Column(name = "delete_file")
    Boolean deleteFile;

    @Column(name = "send_to_it")
    Boolean sendToIT;

    @Column(name = "time_stamp")
    LocalDateTime dateSubmitted;

}
