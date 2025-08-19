package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassWithEnrollmentCountDTO {
    private ClassesDTO classInfo;
    private Long enrollmentCount;
}

