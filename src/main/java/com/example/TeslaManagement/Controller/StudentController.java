package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.DTO.StudentDTO;
import com.example.TeslaManagement.DTO.StudentRequestDTO;
import com.example.TeslaManagement.DTO.StudentResponseDTO;
import com.example.TeslaManagement.Utils.SecurityUtils;
import com.example.TeslaManagement.model.User;
import com.example.TeslaManagement.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private SecurityUtils securityUtils;

    /**
     * Create a new student with integrated user creation
     * This endpoint creates both User and Student records in a single transaction
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        try {
            User requestor = securityUtils.getCurrentUser();
            logger.info("Creating student requested by user: {}", requestor.getUsername());

            StudentResponseDTO createdStudent = studentService.createStudentWithUser(studentRequestDTO, requestor);

            logger.info("Student created successfully with username: {}", createdStudent.getUsername());

            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);

        } catch (Exception e) {
            logger.error("Error creating student: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to create student: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long studentId) {
        StudentDTO student = studentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<?> getStudentByBranchId(@PathVariable("id") Long branchId) {
        try {
            List<StudentDTO> students = studentService.getStudentByBranchId(branchId);
            if (students.isEmpty()) {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(" No Students found for the given branch ID: " + branchId);
            }

            return  ResponseEntity.ok(students);
        }
        catch( Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable("id") Long studentId,
            @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(studentId, studentRequestDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
