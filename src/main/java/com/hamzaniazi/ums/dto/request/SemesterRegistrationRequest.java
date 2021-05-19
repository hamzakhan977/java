package com.hamzaniazi.ums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterRegistrationRequest {
    private Long studentId;
    private Long semesterId;
    private List<Long> coursesIds;
}
