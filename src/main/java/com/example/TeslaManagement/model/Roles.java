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
    public long user_id;
    public String username;
    public String password;
    public long branch_id;
    public String role;
    public long staff_id;
    public Roles() {
        //default Constructor
    }
    Roles(long user_id, String username, String password, long branch_id, String role, long staff_id) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.branch_id = branch_id;
        this.role = role;
        this.staff_id = staff_id;
    }
    public long getUser_id() {
        return user_id;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
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
    public long getBranch_id() {
        return branch_id;
    }
    public void setBranch_id(long branch_id) {
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
