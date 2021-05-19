package com.hamzaniazi.ums.dto.response;

import com.hamzaniazi.ums.dto.enums.Gender;
import com.hamzaniazi.ums.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private String fullName;
    private String address;
    private LocalDate birthday;
    private String email;
    private String contactNumber;
    private Gender gender;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.fullName = student.getFullName();
        this.address = student.getAddress();
        this.birthday = student.getBirthday();
        this.email = student.getEmail();
        this.contactNumber = student.getContactNumber();
        this.gender = student.getGender();
    }
}
