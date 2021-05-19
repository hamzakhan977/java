package com.hamzaniazi.ums.service;

import com.hamzaniazi.ums.dto.request.TeacherRequest;
import com.hamzaniazi.ums.dto.response.TeacherResponse;
import com.hamzaniazi.ums.model.Teacher;

import java.util.List;

public interface TeacherService extends CrudUtil<Teacher, TeacherResponse, TeacherRequest> {
    String assignCourses(Long teacherId, List<Long> courseIds);
}
