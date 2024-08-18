package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long role_id;
    public String username;
    public String password;
    public Integer[] branch_id;
    public String role;
    public long staff_id;
    public Roles() {
        //default Constructor
    }

    Roles(long role_id, String username, String password, Integer[] branch_id, String role, long staff_id) {
        this.role_id = role_id;
        this.username = username;
        this.password = password;
        this.branch_id = branch_id;
        this.role = role;
        this.staff_id = staff_id;
    }
    public long getRole_id() {
        return role_id;
    }
    public void setRole_id(long user_id) {
        this.role_id = user_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer[] getBranch_id() {
        return branch_id;
    }
    public void setBranch_id(Integer[] branch_id) {
        this.branch_id = branch_id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public long getStaff_id() {
        return staff_id;
    }
    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }
}
