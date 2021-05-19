package com.hamzaniazi.ums.service.impl;

import com.hamzaniazi.ums.dto.request.CourseRequest;
import com.hamzaniazi.ums.dto.response.CourseResponse;
import com.hamzaniazi.ums.exception.ResourceNotFoundException;
import com.hamzaniazi.ums.model.Course;
import com.hamzaniazi.ums.repository.CourseRepository;
import com.hamzaniazi.ums.service.CourseService;
import com.hamzaniazi.ums.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CourseServiceImpl implements CourseService {

    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;

    private TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void setTeacherService(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @Override
    public Course getObject(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return course.get();
        } else {
            logger.error("Cannot find course with ID : {}", id);
            throw new ResourceNotFoundException("Cannot find course with ID" + id);
        }
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public CourseResponse getOneResponse(Long id) {
        return new CourseResponse(getObject(id));
    }

    @Override
    public List<CourseResponse> getAllResponses() {
        return getAll().stream().map(CourseResponse::new).collect(Collectors.toList());
    }

    @Override
    public Page<CourseResponse> getAllWithPagination(PageRequest request) {
        return courseRepository.findAll(request).map(CourseResponse::new);
    }

    @Override
    public void add(CourseRequest courseRequest) {
        Course course = courseRequest.getCourse();
        courseRepository.save(course);
    }

    @Override
    public void update(CourseRequest courseRequest) {
        Course course = getObject(courseRequest.getId());
        course.setName(courseRequest.getName());
        course.setDescription(courseRequest.getDescription());
        courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        courseRepository.delete(getObject(id));
    }

    @Override
    public List<CourseResponse> getByTeacher(Long teacherId) {
        return courseRepository.findAllByTeacherId(teacherId).stream().map(CourseResponse::new).collect(Collectors.toList());
    }
}
