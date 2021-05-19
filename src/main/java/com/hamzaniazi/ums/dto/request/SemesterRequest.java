package com.hamzaniazi.ums.dto.request;

import com.hamzaniazi.ums.model.Semester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterRequest {
    private Long id;
    private String name;
    private Long year;
    private Long duration;
    private String description;

    public Semester getSemester() {
        return new Semester(
                this.name,
                this.year,
                this.duration,
                this.description
        );
    }
}
