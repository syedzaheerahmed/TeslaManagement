package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Classes;
import com.example.TeslaManagement.service.ClassesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassesController {
    ClassesService classesService;
    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    //Get the given class
    @GetMapping("/getClassDetails/{class_id}")
    public Classes getClassDetails(@PathVariable(value = "class_id") Long class_id) {
        return classesService.getClassDetails(class_id);
    }

    //Get all classes
    @GetMapping("/getAllClassDetails")
    public List<Classes> getAllClassDetails() {
        return classesService.getAllClasses();
    }

    //Add class
    @PostMapping("/addClassDetails")
    public String addClassDetails(@RequestBody Classes classes) {
        return classesService.createClasses(classes);
    }

    //update class
    @PutMapping("/updateClassDetails")
    public String updateClassDetails(@RequestBody Classes classes) {
        return classesService.updateClasses(classes);
    }

    //delete class
    @DeleteMapping("/deleteClassDetails/{class_id}")
    public String deleteClassDetails(@PathVariable(value = "class_id") Long class_id) {
        return classesService.deleteClasses(class_id);
    }

}
