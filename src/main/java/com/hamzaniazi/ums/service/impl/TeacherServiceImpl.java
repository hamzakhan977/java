package com.hamzaniazi.ums.service.impl;

import com.hamzaniazi.ums.dto.request.TeacherRequest;
import com.hamzaniazi.ums.dto.response.TeacherResponse;
import com.hamzaniazi.ums.exception.ResourceNotFoundException;
import com.hamzaniazi.ums.model.Course;
import com.hamzaniazi.ums.model.Teacher;
import com.hamzaniazi.ums.repository.TeacherRepository;
import com.hamzaniazi.ums.service.CourseService;
import com.hamzaniazi.ums.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private final TeacherRepository teacherRepository;

    private final CourseService courseService;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseService courseService) {
        this.teacherRepository = teacherRepository;
        this.courseService = courseService;
    }

    @Override
    public Teacher getObject(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            return teacher.get();
        } else {
            logger.error("Cannot find teacher with id :: {} ", id);
            throw new ResourceNotFoundException("cannot find teacher id " + id);
        }
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public TeacherResponse getOneResponse(Long id) {
        return new TeacherResponse(getObject(id));
    }

    @Override
    public List<TeacherResponse> getAllResponses() {
        return getAll().stream().map(TeacherResponse::new).collect(Collectors.toList());
    }

    @Override
    public Page<TeacherResponse> getAllWithPagination(PageRequest request) {
        return teacherRepository.findAll(request).map(TeacherResponse::new);
    }

    @Override
    public void add(TeacherRequest teacherRequest) {
        Teacher teacher = teacherRequest.getTeacher();
        teacherRepository.save(teacher);
    }

    @Override
    public void update(TeacherRequest teacherRequest) {
        Teacher teacher = getObject(teacherRequest.getId());
        teacher.setName(teacherRequest.getName());
        teacher.setContactNumber(teacherRequest.getContactNumber());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setGender(teacherRequest.getGender());
        teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.delete(getObject(id));
    }

    @Override
    public String assignCourses(Long teacherId, List<Long> courseIds) {
        Teacher teacher = getObject(teacherId);
        if (courseIds.size() <= 3){
            List<Course> courses = new ArrayList<>();
            for (Long courseId: courseIds){
                Course course = courseService.getObject(courseId);
                course.setTeacher(teacher);
                courses.add(course);
            }
            teacher.setCourses(courses);
            teacherRepository.save(teacher);
            return "assign successful!";
        }else {
            return "Cannot assign more than 3 subjects";
        }
    }
}
