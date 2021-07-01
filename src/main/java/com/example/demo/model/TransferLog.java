package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Table(name = "transfer_log")
@Entity(name = "transfer_log")
public class TransferLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "original_file_owner")
    String originalFileOwner;

    @Column(name = "new_file_owner")
    String newFileOwner;

    @Column(name = "date_of_transfer")
    LocalDateTime dateOfTransfer;
}
