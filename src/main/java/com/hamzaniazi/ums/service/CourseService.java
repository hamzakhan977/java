package com.hamzaniazi.ums.service;

import com.hamzaniazi.ums.dto.request.CourseRequest;
import com.hamzaniazi.ums.dto.response.CourseResponse;
import com.hamzaniazi.ums.model.Course;

import java.util.List;

public interface CourseService extends CrudUtil<Course, CourseResponse, CourseRequest> {
    List<CourseResponse> getByTeacher(Long teacherId);
}
