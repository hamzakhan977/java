package com.hamzaniazi.ums.dto.request;

import com.hamzaniazi.ums.dto.enums.Gender;
import com.hamzaniazi.ums.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    private Long id;
    private String name;
    private String contactNumber;
    private String email;
    private Gender gender;

    public Teacher getTeacher() {
        return new Teacher(
                this.name,
                this.contactNumber,
                this.email,
                null,
                this.gender
        );
    }
}
