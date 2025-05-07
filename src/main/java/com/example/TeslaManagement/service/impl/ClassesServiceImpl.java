package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.Class;
import com.example.TeslaManagement.repository.ClassesRepo;
import com.example.TeslaManagement.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    ClassesRepo classesRepo;

    @Override
    public String createClasses(Class aClass) {
        try{
            classesRepo.save(aClass);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in adding class details";
        }
        return "Class details added successfully";
    }

    @Override
    public String updateClasses(Class aClass) {
        try{
            classesRepo.save(aClass);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in updating class details";
        }
        return "Class details updated successfully";
    }

    @Override
    public String deleteClasses(Long class_id) {
        try{
            classesRepo.deleteById(class_id);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in deleting class details";
        }
        return "Class details deleted successfully";
    }

    @Override
    public Class getClassDetails(Long class_id) {
        try {
            if(classesRepo.findById(class_id).isPresent()) {
                return classesRepo.getReferenceById(class_id);
            }
        }catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<Class> getAllClasses() {
        return classesRepo.findAll();
    }
}
