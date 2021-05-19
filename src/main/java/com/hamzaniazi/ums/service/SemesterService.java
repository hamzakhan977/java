package com.hamzaniazi.ums.service;

import com.hamzaniazi.ums.dto.request.SemesterRequest;
import com.hamzaniazi.ums.dto.response.SemesterResponse;
import com.hamzaniazi.ums.model.Semester;

public interface SemesterService extends CrudUtil<Semester, SemesterResponse, SemesterRequest> {
}
