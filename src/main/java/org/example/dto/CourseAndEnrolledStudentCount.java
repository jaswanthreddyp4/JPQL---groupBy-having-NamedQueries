package org.example.dto;

import org.example.entities.Course;
public record CourseAndEnrolledStudentCount(
        Course course,
        Long count
) {
}
