package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Class;
import java.util.List;

public interface ClassesService {
    String createClasses(Class aClass);
    String updateClasses(Class aClass);
    String deleteClasses(Long class_id);
    Class getClassDetails(Long class_id);
    List<Class> getAllClasses();
}
