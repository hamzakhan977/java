package com.hamzaniazi.ums.dto.request;

import com.hamzaniazi.ums.dto.enums.Gender;
import com.hamzaniazi.ums.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private Long id;
    private String fullName;
    private String address;
    private LocalDate birthday;
    private String email;
    private String contactNumber;
    private Gender gender;

    public Student getStudent() {
        return new Student(
                this.fullName,
                this.address,
                this.birthday,
                this.email,
                this.contactNumber,
                this.gender
        );
    }
}
