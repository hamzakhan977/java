package com.hamzaniazi.ums.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "semester")
public class Semester extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Long year;

    @Column(name = "duration_in_month")
    private Long duration;

    @Column(name = "description")
    private String description;
}
