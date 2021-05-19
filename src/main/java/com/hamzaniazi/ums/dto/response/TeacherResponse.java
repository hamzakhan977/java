package com.hamzaniazi.ums.dto.response;

import com.hamzaniazi.ums.dto.enums.Gender;
import com.hamzaniazi.ums.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {
    private Long id;
    private String name;
    private String contactNumber;
    private String email;
    private List<CourseResponse> courseResponses;
    private Gender gender;

    public TeacherResponse(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.contactNumber = teacher.getContactNumber();
        this.email = teacher.getEmail();
        this.courseResponses = teacher.getCourses().stream().map(CourseResponse::new).collect(Collectors.toList());
        this.gender = teacher.getGender();
    }
}
