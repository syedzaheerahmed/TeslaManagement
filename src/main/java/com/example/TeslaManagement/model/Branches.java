package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "branches")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Branches {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long branch_id;
    public String branch_name;
    public String branch_address;
    public long hq_id;
    public Branches() {
        // Default constructor
    }
    Branches(long branch_id,String branch_name,String branch_address, long hq_id) {
        this.branch_id = branch_id;
        this.branch_address = branch_address;
        this.branch_name = branch_name;
        this.hq_id = hq_id;
    }
    public long getBranch_id() {
        return branch_id;
    }
    public String getBranch_name() {
        return branch_name;
    }
    public String getBranch_address() {
        return branch_address;
    }
    public long getHq_id() {
        return hq_id;
    }
    public void setBranch_id(long branch_id) {
        this.branch_id = branch_id;
    }
    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
    public void setBranch_address(String branch_address) {
        this.branch_address = branch_address;
    }
    public void setHq_id(long hq_id) {
        this.hq_id = hq_id;
    }
}

