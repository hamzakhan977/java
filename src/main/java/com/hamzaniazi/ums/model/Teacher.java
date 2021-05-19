package com.hamzaniazi.ums.model;

import com.hamzaniazi.ums.dto.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @Enumerated
    @Column(name = "gender")
    private Gender gender;
}
