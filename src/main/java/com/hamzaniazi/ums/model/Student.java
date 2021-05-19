package com.hamzaniazi.ums.model;

import com.hamzaniazi.ums.dto.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student extends BaseEntity {
    @Column(name = "name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "birthDay")
    private LocalDate birthday;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @Enumerated
    @Column(name = "gender")
    private Gender gender;
}
