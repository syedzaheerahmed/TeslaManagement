package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Classes;
import java.util.List;

public interface ClassesService {
    public String createClasses(Classes classes);
    public String updateClasses(Classes classes);
    public String deleteClasses(Long class_id);
    public Classes getClassDetails(Long class_id);
    public List<Classes> getAllClasses();
}
