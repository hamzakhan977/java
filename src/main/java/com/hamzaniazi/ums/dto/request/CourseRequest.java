package com.hamzaniazi.ums.dto.request;

import com.hamzaniazi.ums.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private Long id;
    private String name;
    private String description;

    public Course getCourse() {
        return new Course(
                this.name,
                this.description,
                null
        );
    }
}
