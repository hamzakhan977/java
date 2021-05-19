package com.hamzaniazi.ums.dto.response;

import com.hamzaniazi.ums.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String name;
    private String description;
    private Long teacherId;
    private String teacherName;

    public CourseResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.teacherId = course.getTeacher() != null ? course.getTeacher().getId(): 0L;
        this.teacherName = course.getTeacher() != null ? course.getTeacher().getName() : "";
    }
}
