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

    Student(long student_id, String student_name, Integer batch_year, String gender, Date dob, String school_name, String school_std, String board_of_school, String extra_details, String parent_name, String parent_contact, String student_address, Boolean is_approved, Boolean is_active, String reason_for_deactivation, Date created_at, Date updated_at, String created_by) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.batch_year = batch_year;
        this.gender = gender;
        this.dob = dob;
        this.school_name = school_name;
        this.school_std = school_std;
        this.board_of_school = board_of_school;
        this.extra_details = extra_details;
        this.parent_name = parent_name;
        this.parent_contact = parent_contact;
        this.student_address = student_address;
        this.is_approved = is_approved;
        this.is_active = is_active;
        this.reason_for_deactivation = reason_for_deactivation;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
    }
    public Student() {
    }
    public long getStudent_id() {
        return student_id;
    }
    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }
    public String getStudent_name() {
        return student_name;
    }
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
    public Integer getBatch_year() {
        return batch_year;
    }
    public void setBatch_year(Integer batch_year) {
        this.batch_year = batch_year;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getSchool_name() {
        return school_name;
    }
    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }
    public String getSchool_std() {
        return school_std;
    }
    public void setSchool_std(String school_std) {
        this.school_std = school_std;
    }
    public String getBoard_of_school() {
        return board_of_school;
    }
    public void setBoard_of_school(String board_of_school) {
        this.board_of_school = board_of_school;
    }
    public String getExtra_details() {
        return extra_details;
    }
    public void setExtra_details(String extra_details) {
        this.extra_details = extra_details;
    }
    public String getParent_name() {
        return parent_name;
    }
    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }
    public String getParent_contact() {
        return parent_contact;
    }
    public void setParent_contact(String parent_contact) {
        this.parent_contact = parent_contact;
    }
    public String getStudent_address() {
        return student_address;
    }
    public void setStudent_address(String student_address) {
        this.student_address = student_address;
    }
    public Boolean getIs_approved() {
        return is_approved;
    }
    public void setIs_approved(Boolean is_approved) {
        this.is_approved = is_approved;
    }
    public Boolean getIs_active() {
        return is_active;
    }
    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
    public String getReason_for_deactivation() {
        return reason_for_deactivation;
    }
    public void setReason_for_deactivation(String reason_for_deactivation) {
        this.reason_for_deactivation = reason_for_deactivation;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    public String getCreated_by() {
        return created_by;
    }
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
