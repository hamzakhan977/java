package com.hamzaniazi.ums.service.impl;

import com.hamzaniazi.ums.dto.request.StudentRequest;
import com.hamzaniazi.ums.dto.response.StudentResponse;
import com.hamzaniazi.ums.exception.ResourceNotFoundException;
import com.hamzaniazi.ums.model.Course;
import com.hamzaniazi.ums.model.Semester;
import com.hamzaniazi.ums.model.SemesterRegistration;
import com.hamzaniazi.ums.model.Student;
import com.hamzaniazi.ums.repository.SemesterRegistrationRepository;
import com.hamzaniazi.ums.repository.StudentRepository;
import com.hamzaniazi.ums.service.CourseService;
import com.hamzaniazi.ums.service.SemesterService;
import com.hamzaniazi.ums.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    private final SemesterService semesterService;

    private final CourseService courseService;

    private final SemesterRegistrationRepository semesterRegistrationRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, SemesterService semesterService, CourseService courseService, SemesterRegistrationRepository semesterRegistrationRepository) {
        this.studentRepository = studentRepository;
        this.semesterService = semesterService;
        this.courseService = courseService;
        this.semesterRegistrationRepository = semesterRegistrationRepository;
    }

    @Override
    public Student getObject(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            logger.error("cannot find student with ID :: {}", id);
            throw new ResourceNotFoundException("Cannot find student with ID " + id);
        }
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public StudentResponse getOneResponse(Long id) {
        return new StudentResponse(getObject(id));
    }

    @Override
    public List<StudentResponse> getAllResponses() {
        return getAll().stream().map(StudentResponse::new).collect(Collectors.toList());
    }

    @Override
    public Page<StudentResponse> getAllWithPagination(PageRequest request) {
        return studentRepository.findAll(request).map(StudentResponse::new);
    }

    @Override
    public void add(StudentRequest request) {
        studentRepository.save(request.getStudent());
    }

    @Override
    public void update(StudentRequest request) {
        Student student = getObject(request.getId());
        student.setFullName(request.getFullName());
        student.setAddress(request.getAddress());
        student.setContactNumber(request.getContactNumber());
        student.setBirthday(request.getBirthday());
        student.setEmail(request.getEmail());
        student.setGender(request.getGender());
        studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.delete(getObject(id));
    }

    @Override
    public String assignCourses(Long semesterId, Long studentId, List<Long> courseIds) {
        Student student = getObject(studentId);
        Semester semester = semesterService.getObject(semesterId);

        if (courseIds.size() <= 5){
            List<Course> courses = new ArrayList<>();
            for (Long courseId: courseIds){
                courses.add(courseService.getObject(courseId));
            }

            Optional<SemesterRegistration> registration = semesterRegistrationRepository.findByStudentIdAndSemesterId(studentId, semesterId);

            if (registration.isPresent()){
                registration.get().setCourses(courses);
                semesterRegistrationRepository.save(registration.get());
            }else {
                SemesterRegistration semesterRegistration = new SemesterRegistration();
                semesterRegistration.setCourses(courses);
                semesterRegistration.setStudent(student);
                semesterRegistration.setSemester(semester);
                semesterRegistrationRepository.save(semesterRegistration);
            }

            return "assign successful!";

        }else {
            return "Cannot assign more than 5 subjects for a semester";
        }
    }

    @Override
    public List<StudentResponse> getStudentByCourse(Long courseId) {
        List<SemesterRegistration> coursesContaining = semesterRegistrationRepository.findAllByCoursesContaining(courseService.getObject(courseId));
        List<Student> students = new ArrayList<>();
        for (SemesterRegistration semesterRegistration: coursesContaining){
            students.add(semesterRegistration.getStudent());
        }
        return students.stream().map(StudentResponse::new).collect(Collectors.toList());
    }
}
