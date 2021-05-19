package com.hamzaniazi.ums.service;

import com.hamzaniazi.ums.dto.request.StudentRequest;
import com.hamzaniazi.ums.dto.response.StudentResponse;
import com.hamzaniazi.ums.model.Student;

import java.util.List;

public interface StudentService extends CrudUtil<Student, StudentResponse, StudentRequest> {
    String assignCourses(Long semesterId, Long studentId, List<Long> courseIds);

    List<StudentResponse> getStudentByCourse(Long courseId);
}
