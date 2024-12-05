package org.example.dto;

import org.example.entities.Student;
public record StudentsWhichOptedCoursesFromCseDept(
        Student student,
        String name

) {
}
