package com.example.TeslaManagement.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long student_id;
    private String student_name;
    private Integer batch_year;
    private String gender;
    private Date dob;
    private String school_name;
    private String school_std;
    private String board_of_school;
    private String extra_details;
    private String parent_name;
    private String parent_contact;
    private String student_address;
    private Boolean is_approved = false;
    private Boolean is_active = true;
    private String reason_for_deactivation;
    private Date created_at = new Date();
    private Date updated_at = new Date();
    private String created_by;

}
