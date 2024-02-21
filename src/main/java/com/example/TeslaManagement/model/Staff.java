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
    public long branch_id;
    public String address;
    public String contact_number;
    public boolean is_teaching_staff;
    public boolean is_admin;
    public boolean is_active;
    public String reason_for_deactivation;
    public Staff() {
        // Default constructor
    }
    Staff(long staff_id, String staff_name, long branch_id, String address, String contact_number, boolean is_teaching_staff, boolean is_admin, boolean is_active, String reason_for_deactivation) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.branch_id = branch_id;
        this.address = address;
        this.contact_number = contact_number;
        this.is_teaching_staff = is_teaching_staff;
        this.is_admin = is_admin;
        this.is_active = is_active;
        this.reason_for_deactivation = reason_for_deactivation;
    }
}
