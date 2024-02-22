package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "classes")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long class_id;
    public String batch_name;
    public long branch_id;
    public String batch_timings;
    public long staff_id;

    public Classes() {
        // Default constructor
    }
    Classes(long class_id,String batch_name, long branch_id, String batch_timings, long staff_id) {
        this.class_id = class_id;
        this.batch_name = batch_name;
        this.branch_id = branch_id;
        this.batch_timings = batch_timings;
        this.staff_id = staff_id;
    }
    public long getClass_id() {
        return class_id;
    }
    public String getBatch_name() {
        return batch_name;
    }
    public String getBatch_timings() {
        return batch_timings;
    }
    public long getStaff_id() {
        return staff_id;
    }
    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }
    public void setBatch_name(String batch_name) {
        this.batch_name = batch_name;
    }
    public void setBranch_id(long branch_id) {
        this.branch_id = branch_id;
    }
    public void setBatch_timings(String batch_timings) {
        this.batch_timings = batch_timings;
    }
    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }
}
