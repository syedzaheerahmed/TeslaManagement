package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Classes;
import java.util.List;

public interface ClassesService {
    String createClasses(Classes classes);
    String updateClasses(Classes classes);
    String deleteClasses(Long class_id);
    Classes getClassDetails(Long class_id);
    List<Classes> getAllClasses();
}
