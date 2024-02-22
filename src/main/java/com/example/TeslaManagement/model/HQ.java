package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "HQtable")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class HQ {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long hq_id;
    public String hq_name;
    public String address;
    HQ(long hq_id, String hq_name, String address) {
        this.hq_id = hq_id;
        this.hq_name = hq_name;
        this.address = address;
    }
    public HQ() {
    }
    public long getHq_id() {
        return hq_id;
    }
    public void setHq_id(long hq_id) {
        this.hq_id = hq_id;
    }
    public String getHq_name() {
        return hq_name;
    }
    public void setHq_name(String hq_name) {
        this.hq_name = hq_name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
