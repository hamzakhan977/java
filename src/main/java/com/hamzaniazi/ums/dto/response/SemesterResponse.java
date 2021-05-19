package com.hamzaniazi.ums.dto.response;

import com.hamzaniazi.ums.model.Semester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterResponse {
    private Long id;
    private String name;
    private Long year;
    private Long duration;
    private String description;

    public SemesterResponse(Semester semester) {
        this.id = semester.getId();
        this.name = semester.getName();
        this.year = semester.getYear();
        this.duration = semester.getDuration();
        this.description = semester.getDescription();
    }
}
