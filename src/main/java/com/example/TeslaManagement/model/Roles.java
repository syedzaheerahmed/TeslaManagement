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
}
