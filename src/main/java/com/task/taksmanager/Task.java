package com.task.taksmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    private Long id;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private String title;

    private String description;

    private String status;

    private String createdBy;
}