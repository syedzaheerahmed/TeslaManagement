package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "student")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long student_id;
    public String student_name;
    public Integer batch_year;
    public String gender;
    public Date dob;
    public String school_name;
    public String school_std;
    public String board_of_school;
    public String extra_details;
    public String parent_name;
    public String parent_contact;
    public String student_address;
    public Boolean is_approved = false;
    public Boolean is_active = true;
    public String reason_for_deactivation;
    public Date created_at = new Date();
    public Date updated_at = new Date();
    public String created_by;

}
