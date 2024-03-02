package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "staff")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long staff_id;
    public String staff_name;
    public Integer[] branch_id;
    public String address;
    public String contact_number;
    public boolean is_teaching_staff;
    public String role;
    public boolean is_active;
    public String reason_for_deactivation;
    public Staff() {
        // Default constructor
    }

    Staff(long staff_id, String staff_name, Integer[] branch_id, String address, String contact_number, boolean is_teaching_staff, String role, boolean is_active, String reason_for_deactivation) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.branch_id = branch_id;
        this.address = address;
        this.contact_number = contact_number;
        this.is_teaching_staff = is_teaching_staff;
        this.role = role;
        this.is_active = is_active;
        this.reason_for_deactivation = reason_for_deactivation;
    }
    public long getStaff_id() {
        return staff_id;
    }
    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }
    public String getStaff_name() {
        return staff_name;
    }
    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }
    public Integer[] getBranch_id() {
        return branch_id;
    }
    public void setBranch_id(Integer[] branch_id) {
        this.branch_id = branch_id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContact_number() {
        return contact_number;
    }
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    public boolean isIs_teaching_staff() {
        return is_teaching_staff;
    }
    public void setIs_teaching_staff(boolean is_teaching_staff) {
        this.is_teaching_staff = is_teaching_staff;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean isIs_active() {
        return is_active;
    }
    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    public String getReason_for_deactivation() {
        return reason_for_deactivation;
    }
    public void setReason_for_deactivation(String reason_for_deactivation) {
        this.reason_for_deactivation = reason_for_deactivation;
    }
}
